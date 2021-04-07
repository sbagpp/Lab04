package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	public Studente getStudenteToMatricola(Integer matricola) throws StudenteNonTrovato{
		String sql = "select * from studente where studente.`matricola`= ?";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				Studente s = new Studente (rs.getInt("matricola"), rs.getString("nome"), rs.getString("cognome"), rs.getString("cds"));
				//studente trovato;
				rs.close();
				conn.close();
				st.close();
				return s;
			}else {
				rs.close();
				conn.close();
				st.close();
				throw new StudenteNonTrovato();
			}
		}
		catch (SQLException sqle){
				throw new RuntimeException("Errore Db", sqle);
			}
		
	}

	public List<Studente> getAllStudenti() {
		String sql ="SELECT * FROM STUDENTE";
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			List <Studente> ss = new ArrayList<>();
			while(rs.next()) {
				Studente s = new Studente (rs.getInt("matricola"), rs.getString("nome"), rs.getString("cognome"), rs.getString("cds"));
				ss.add(s);
			}
			rs.close();
			conn.close();
			st.close();
			return ss;
		}
		catch (SQLException sqle){
				throw new RuntimeException("Errore Db", sqle);
			}
		
	}

	public List<Corso> getCorsiToStudente(Studente s) {
		// TODO Auto-generated method stub
		String sql = "select corso.`codins`, corso.`crediti`, corso.`nome`, corso.`pd`"
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
}
