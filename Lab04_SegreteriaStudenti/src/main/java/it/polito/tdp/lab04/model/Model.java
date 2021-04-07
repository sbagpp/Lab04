package it.polito.tdp.lab04.model;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;
import it.polito.tdp.lab04.DAO.StudenteGiaIscritto;
import it.polito.tdp.lab04.DAO.StudenteNonTrovato;
import it.polito.tdp.lab04.DAO.ValoreNonTrovato;

public class Model {
	private CorsoDAO cDAO;
	private StudenteDAO sDAO;
	
	public Model() {
		this.cDAO = new CorsoDAO();
		this.sDAO = new StudenteDAO();
	}
	
	public List<Corso> getAllCorsi(){
		List <Corso> risultato = this.cDAO.getTuttiICorsi();
		return risultato;
	}
	
	public List<Studente> getStudentiToCorso(Corso corso) throws ValoreNonTrovato {
		List<Studente> risultato = this.cDAO.getStudentiIscrittiAlCorso(corso);
		return risultato;
	}
	
	public Corso getCorso(Corso c) throws ValoreNonTrovato {
		Corso risultato = this.cDAO.getCorso(c);
		return risultato;
	}
	
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) throws StudenteGiaIscritto{
		boolean risultato = this.cDAO.inscriviStudenteACorso(studente, corso);
		return risultato;
	}

	public List<Studente> getAllStudenti() {
		return this.sDAO.getAllStudenti();
	}
	
	public Studente cercaStudente(Integer matricola) throws StudenteNonTrovato {
		return this.sDAO.getStudenteToMatricola(matricola);
	}
	
	public List<Corso> getCorsiToStudente(Studente s){
		return this.cDAO.getCorsiToStudente(s);
	}
}
