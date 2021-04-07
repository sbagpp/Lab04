package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				/*
				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");
				 */
				//System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);
				Corso risultato = new Corso (rs.getString("codins"), rs.getInt("pd"), rs.getInt("crediti"), rs.getString("nome"));
				corsi.add(risultato);
				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
			}

			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	
	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public Corso getCorso (Corso corso) throws ValoreNonTrovato {
		// TODO
		String sql = "Select *"
				+ "from corso"
				+ "where corso.codins = ?";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodIns());
			ResultSet rs = st.executeQuery();
			if(rs.next()){
				//String codIns, Integer periodoDidattico, Integer crediti, String nome
				Corso risultato = new Corso (rs.getString("codins"), rs.getInt("pd"), rs.getInt("crediti"), rs.getString("nome"));
				rs.close();
				st.close();
				conn.close();
				return risultato;
			}else {
				rs.close();
				st.close();
				conn.close();
				throw new ValoreNonTrovato();
			}
		} catch(SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) throws ValoreNonTrovato {
		// TODO
				String sql = "SELECT * FROM ISCRIZIONE, STUDENTE WHERE ISCRIZIONE.`codins`= ? AND ISCRIZIONE.`matricola` = STUDENTE.`matricola`";

				try {
					Connection conn = ConnectDB.getConnection();
					PreparedStatement st = conn.prepareStatement(sql);
					st.setString(1, corso.getCodIns());
					ResultSet rs = st.executeQuery();
					List<Studente> risultato = new ArrayList<>();
					if(rs.next()){
						//Integer matricola, String nome, String cognome, String cds
						Studente s = new Studente (rs.getInt("matricola"), rs.getString("nome"), rs.getString("cognome"), rs.getString("cds"));
						risultato.add(s);
						while(rs.next()) {
							s = new Studente (rs.getInt("matricola"), rs.getString("nome"), rs.getString("cognome"), rs.getString("cds"));
							risultato.add(s);
						}
						rs.close();
						st.close();
						conn.close();
						return risultato;
					}else {
						rs.close();
						st.close();
						conn.close();
						throw new ValoreNonTrovato();
					}
				} catch(SQLException e) {
					// e.printStackTrace();
					throw new RuntimeException("Errore Db", e);
				}
	}
	public List<Corso> getCorsiToStudente(Studente s) {
		// TODO Auto-generated method stub
		final String sql = "select corso.`codins`, corso.`crediti`, corso.`nome`, corso.`pd`"
				+ "from corso, iscrizione"
				+ "where iscrizione.`matricola`=? and iscrizione.`codins`=corso.`codins`";
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, s.getMatricola());
			ResultSet rs = st.executeQuery();
			List <Corso> c = new ArrayList<>();
			while (rs.next()) {
				//String codIns, Integer periodoDidattico, Integer crediti, String nome
				Corso co = new Corso (rs.getString("codins"), rs.getInt("pd"), rs.getInt("crediti"), rs.getString("nome"));
				c.add(co);
			}
			rs.close();
			conn.close();
			st.close();
			return c;
		
		}
		catch (SQLException sqle){
			throw new RuntimeException("Errore Db", sqle);
		}
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) throws StudenteGiaIscritto {
		// TODO
		// ritorna true se l'iscrizione e' avvenuta con successo
		String sql = "Select *"
				+ "from iscrizione" 
				+ "where iscrizione.`codins`=? and iscrizione.`matricola`=?";
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodIns());
			st.setInt(2, studente.getMatricola());
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				rs.close();
				st.close();
				conn.close();
				throw new StudenteGiaIscritto();
			}else {
				sql ="Insert into iscrizione (matricola, codins)"
						+ "values (?,?)";
				st.setString(2, corso.getCodIns());
				st.setInt(1, studente.getMatricola());
				rs = st.executeQuery();
				rs.close();
				st.close();
				conn.close();
				return true;
			}
		}catch(SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
	}

}
