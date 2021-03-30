package it.polito.tdp.lab04.model;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	private CorsoDAO cDAO;
	private StudenteDAO sDAO;
	
	public Model() {
		this.cDAO = new CorsoDAO();
		this.sDAO = new StudenteDAO();
	}
	
	public List<Corso> getAllCorsi(){
		return this.cDAO.getTuttiICorsi();
	}
}
