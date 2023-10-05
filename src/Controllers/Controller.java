package Controllers;

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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.*;

//import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import model.Paciente;//importando a classe paciente 

public class Controller {
	@FXML
	private TextField filterPaciente;
	
	@FXML
	private VBox ListaPacientes;
	
	
	//classe para fechar a tela - sair 
	public void closePaciente(ActionEvent event) {
		Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        // Fecha o palco (Stage)
        stage.close();
	}
	//botoão para pesquisar 
	public void FilterPaciente(ActionEvent event) throws SQLException {
		try {
			Connection con = dbconnection.Connect.fazer_conexao();
			
			List<Paciente> pacientes = new ArrayList<>();
			
			String sql = "select * from patient where name=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, filterPaciente.getText());
            ResultSet rs = stmt.executeQuery();
			
            while (rs.next()) {
				//esta estrutura está pasicamente percorrendo o banco e adicionando esses valores a lista que será usada para exibir um resultado 
                Paciente paciente = new Paciente();

                paciente.setProntuarionNumber(rs.getInt("medical_record"));
                paciente.setName(rs.getString("name"));
                paciente.setPhoto(rs.getString("photo"));
                pacientes.add(paciente);
            };
            
            Double layoutXPane = 102.0;
			Double layoutYPane = 14.0;
			Double widthPane = 50.0;
			Double heightpane = 70.0;
			
            int n = pacientes.size();
            if(n==0) {
            	System.out.print("nenhum paciente encontrado");
            }else {
            	for (int i=0; i<n; i++) {
            		layoutXPane = layoutXPane + 10.0;
    		    	Pane PacienteList = new Pane();
    		    	PacienteList.setLayoutX(layoutXPane);
    		    	PacienteList.setLayoutY(layoutYPane);
    		    	PacienteList.setPrefWidth(widthPane);
    		    	PacienteList.setPrefHeight(heightpane);
    		    	PacienteList.setStyle("-fx-background-color: black; -fx-background-radius: 10px;");
    		    	ListaPacientes.getChildren().add(PacienteList);
    		    	
    		    	System.out.print(layoutXPane + layoutYPane + widthPane + heightpane);
    		    }
            }
		} catch (SQLException e1) {
			// TODO: handle exception
			System.out.print("catch");
			e1.printStackTrace();
		}
	}
		/*
		Connection con = dbconnection.Connect.fazer_conexao();
		
		List<Paciente> pacientes = new ArrayList<>();
		
		try {
			String sql = "select * from patient where name=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, filterPaciente.getText());
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				//esta estrutura está pasicamente percorrendo o banco e adicionando esses valores a lista que será usada para exibir um resultado 
                Paciente paciente = new Paciente();

                paciente.setProntuarionNumber(rs.getInt("medical_record"));
                paciente.setName(rs.getString("name"));
                paciente.setPhoto(rs.getString("photo"));
                pacientes.add(paciente);
            }
			System.out.printf("Percorrendo o ArrayList (usando o índice)\n");
			Double layoutXPane = 102.0;
			Double layoutYPane = 14.0;
			Double widthPane = 378.0;
			Double heightpane = 70.0;
			
		    int n = pacientes.size();
		    for (int i=0; i<n; i++) {
		      //System.out.printf("Posição %d- %s\n", i, pacientes.get(i));
		    	System.out.printf( "Posição %d- %s\n", i, pacientes.get(i) + " , Número do prontuário: " + pacientes.get(i).getProntuarionNumber() + "pacientes: " + n);
		    	layoutXPane = layoutXPane + 10.0;
		    	Pane PacienteList = new Pane();
		    	PacienteList.setLayoutX(layoutXPane);
		    	PacienteList.setLayoutY(layoutYPane);
		    	PacienteList.setPrefWidth(i);
		    	PacienteList.setStyle("-fx-background-color: black; -fx-background-radius: 10px;");
		    	ListaPacientes.getChildren().add(PacienteList);
		    }
		} catch (Exception ex) {
			// TODO: handle exception
			System.out.print("catch");
			ex.printStackTrace();
		}*/
	}

