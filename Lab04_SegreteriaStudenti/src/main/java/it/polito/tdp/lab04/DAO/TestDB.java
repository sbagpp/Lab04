package it.polito.tdp.lab04.DAO;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class TestDB {

	public static void main(String[] args) throws StudenteNonTrovato, StudenteGiaIscritto {

		/*
		 * 	This is a main to check the DB connection
		 */
		
		CorsoDAO cdao = new CorsoDAO();
		
		for (Corso c : cdao.getTuttiICorsi())
			System.out.println(c);
		StudenteDAO sdao = new StudenteDAO();
		System.out.println(sdao.getStudenteToMatricola(146101));
		Corso c = new Corso ("10BDAPG",null,null,null);
		Studente s = sdao.getStudenteToMatricola(146101);
		if(cdao.inscriviStudenteACorso(s, c)) {
			System.out.print("ok");
		}
		System.out.print("no");
	}

}
