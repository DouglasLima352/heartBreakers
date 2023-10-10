package Controllers;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.PacienteSelecionado;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControllerUpdate {
	private int id;
	@FXML
	private Label lblprontuario;
	
    public ControllerUpdate(int id) {
    	this.id = id;
    }
    public void initialize() throws SQLException {
    	try {
    		lblprontuario.setText("Prontuário Nº: " + id);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(e);
		}
    }
}

