/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.DAO.StudenteNonTrovato;
import it.polito.tdp.lab04.DAO.ValoreNonTrovato;
import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="menuCorsi"
    private ComboBox<Corso> menuCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="buttonCercaIscritti"
    private Button buttonCercaIscritti; // Value injected by FXMLLoader

    @FXML // fx:id="checkBoxControlloMatricola"
    private CheckBox checkBoxControlloMatricola; // Value injected by FXMLLoader

    @FXML // fx:id="buttonCercaCorsi"
    private Button buttonCercaCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="buttonIscriviMatricola"
    private Button buttonIscriviMatricola; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="clear"
    private Button clear; // Value injected by FXMLLoader

    @FXML // fx:id="txtMatricola"
    private TextField txtMatricola; // Value injected by FXMLLoader

    @FXML // fx:id="txtNome"
    private TextField txtNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtCognome"
    private TextField txtCognome; // Value injected by FXMLLoader

	private Model model;

    @FXML
    void buttonCercaIscritti(ActionEvent event) {
    	
    	Corso c = this.menuCorsi.getSelectionModel().getSelectedItem();
   
    	try {
    		List <Studente> s;
    		if(c.getNome().equals("Tutti i corsi")) {
    			s = this.model.getAllStudenti();
    		}else {
			s = this.model.getStudentiToCorso(c);
    		}
			StringBuilder sb = new StringBuilder();
			for(Studente st : s) {
				sb.append(String.format("%-20s ", st.getNome()));
				sb.append(String.format("%-30s ", st.getCognome()));
				sb.append(String.format("%-8d ", st.getMatricola()));
				sb.append(String.format("%-5s\n", st.getCodiceDiStudio()));
			}
			this.txtResult.setText(sb.toString());
		} catch (ValoreNonTrovato e) {
			// TODO Auto-generated catch block
			this.txtResult.setText("corso di studi senza iscritti");
			e.printStackTrace();
		}
    }

    @FXML
    void cercaCorsi(ActionEvent event) {
    	Integer matricola = null ;
    	Studente s =null;
    	try {
    		matricola = Integer.parseInt(this.txtMatricola.getText());
    	}catch (NumberFormatException nfe) {
    		this.txtResult.setText("inserisci un numero come matricola\n");
    	}
    	try {
			s = this.model.cercaStudente(matricola);
			ArrayList <Corso> cc = new ArrayList <>(this.model.getCorsiToStudente(s));
			this.txtResult.clear();
			for(Corso c : cc) {
				this.txtResult.appendText(c.toString(1)+"\n");
			}
		} catch (StudenteNonTrovato e) {
			// TODO Auto-generated catch block
			this.txtResult.setText("matricola non trovata\n");
		}
    	
    	
    }

    @FXML
    void clearResult(ActionEvent event) {
    	this.txtCognome.clear();
    	this.txtMatricola.clear();
    	this.txtNome.clear();
    	this.txtResult.clear();
    }

    @FXML
    void esistenzaMatricola(ActionEvent event) {
    	Integer matricola = null ;
    
    	try {
    		matricola = Integer.parseInt(this.txtMatricola.getText());
    	}catch (NumberFormatException nfe) {
    		this.txtResult.setText("inserisci un numero come matricola\n");
    	}
    	Studente s;
		try {
			s = this.model.cercaStudente(matricola);
			this.txtCognome.setText(s.getCognome());
    		this.txtNome.setText(s.getNome());
		} catch (StudenteNonTrovato e) {
			// TODO Auto-generated catch block
			this.txtResult.setText("matricola non trovata\n");
		}
    	
    }

    @FXML
    void iscriviAlCorso(ActionEvent event) {

    }

    @FXML
    void setCorso(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert menuCorsi != null : "fx:id=\"menuCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert buttonCercaIscritti != null : "fx:id=\"buttonCercaIscritti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert checkBoxControlloMatricola != null : "fx:id=\"checkBoxControlloMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert buttonCercaCorsi != null : "fx:id=\"buttonCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert buttonIscriviMatricola != null : "fx:id=\"buttonIscriviMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clear != null : "fx:id=\"clear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        this.txtResult.setStyle("-fx-font-family: monospace");
        
    }
    public void setModel(Model model) {
    	this.model = model;
    	List<Corso> c = this.model.getAllCorsi();
    	c.add(new Corso ("Tutti i corsi", null,null,"Tutti i corsi"));
    	menuCorsi.getItems().addAll(c);
    }
}
