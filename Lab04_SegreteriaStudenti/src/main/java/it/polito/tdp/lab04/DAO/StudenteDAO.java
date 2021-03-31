package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
				return s;
			}else {
				throw new StudenteNonTrovato();
			}
		}
		catch (SQLException sqle){
				throw new RuntimeException("Errore Db", sqle);
			}
		
	}
}
