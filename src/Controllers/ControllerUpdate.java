package Controllers;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbconnection.Connect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.layout.*;
import javafx.stage.Modality;
//import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import model.Paciente;//importando a classe paciente 

public class ControllerUpdate{
	@FXML
	private Button bVoltar;
	
	@FXML
	private TextField patientFilter;
	
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	public void save(ActionEvent event) throws SQLException {
		System.out.print("Salvar");
	}
	
	public void voltar(ActionEvent event) throws SQLException{
		System.out.print("voltar");
	}
			
			
}
