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
import View.Main;

public class Controller {
	@FXML
	private TextField filterPaciente;
	
	@FXML
	private AnchorPane ListaPacientes;
	@FXML
	private Button bEditar;
	@FXML
	private Button bVoltar;
	
	public void initialize() throws SQLException {
		//primeira classe a ser executada assim que a tela é aberta 
		System.out.print("primeira classe a ser executada assim que a tela é aberta ");
		
		try {
			Connection con = dbconnection.Connect.fazer_conexao();
			
			List<Paciente> pacientes = new ArrayList<>();
			
			String sql = "select * from patient";
			PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            Double layoutXPane = 50.0;
			Double layoutYPane = 14.0;
			Double widthPane = 500.0;
			Double heightpane = 80.0;
			
            while (rs.next()) {
            	Pane PacientesSFiltro = new Pane();
        	    PacientesSFiltro.setLayoutX(layoutXPane);  // Usando o layoutXPane para posicionar horizontalmente
        	    PacientesSFiltro.setLayoutY(layoutYPane);
        	    PacientesSFiltro.setPrefWidth(widthPane);
        	    PacientesSFiltro.setPrefHeight(heightpane);
        	    PacientesSFiltro.setStyle("-fx-background-color: white; -fx-background-radius: 10px; -fx-alignment: center; -fx-position: absolute");
        	    
        	    //Nome
        	    Label nomePaciente = new Label();
        	    nomePaciente.setText("Nome: " + rs.getString("name"));
        	    nomePaciente.setLayoutX(84.0);
        	    nomePaciente.setLayoutY(10.0);
        	    nomePaciente.setFont(new Font("Arial", 20));
        	    PacientesSFiltro.getChildren().add(nomePaciente);
        	    
        	    
        	    
        	    //Número do prontuário ]
        	    Label pontuarioPaciente = new Label();
        	    pontuarioPaciente.setText("Nº prontuário: " + rs.getString("medical_record"));
        	    pontuarioPaciente.setLayoutX(84.0);
        	    pontuarioPaciente.setLayoutY(40.0);
        	    pontuarioPaciente.setFont(new Font("Arial", 16));
        	    PacientesSFiltro.getChildren().add(pontuarioPaciente);
        	    
        	    //botão editar 
        	    Button buttonEditar = new Button();
        	    buttonEditar.setLayoutX(350.0); 
        	    buttonEditar.setLayoutY(19.0);
        	    buttonEditar.setPrefWidth(100.0);
        	    buttonEditar.setPrefHeight(30.0);
        	    buttonEditar.setText("Editar");
        	    buttonEditar.setStyle("-fx-background-color: #9A0019; -fx-text-fill: white;");
        	    PacientesSFiltro.getChildren().add(buttonEditar);
        	    
        	    
        	    ListaPacientes.getChildren().add(PacientesSFiltro);
        	    
        	    layoutYPane = layoutYPane + 90.0;
        	    System.out.print(layoutXPane + layoutYPane + widthPane + heightpane);
            };
		} catch (SQLException e1) {
			// TODO: handle exception
			System.out.print("catch");
			e1.printStackTrace();
		}
	}
	
	public void editar(ActionEvent event) {
		
		try {
			Stage prontuarioP = new Stage();//cria um novo stage
			Parent windowProntuario = FXMLLoader.load(getClass().getResource("ProntuarHB.fxml"));//carrega o arquivo fxml.
			prontuarioP.setTitle("Prontuario do Paciente");// nomeia a janela.
			prontuarioP.setScene(new Scene(windowProntuario, 600, 400));//seta o fxml dentro do stage.
			prontuarioP.initModality(Modality.APPLICATION_MODAL);//impede que o stage seja redimencionado.
			prontuarioP.show();//apresenta a janela Prontuario
		} catch (Exception e) {
			e.printStackTrace();// caso ocorra algum erro no processo, e.printStackTrace() detalha o que aconteceu.
		}
	}
	
	public void voltar(ActionEvent event) {
		
		try {
			Stage listaP = new Stage();//cria um novo stage
			Parent windowPaciente = FXMLLoader.load(getClass().getResource("PacienteHB.fxml"));//carrega o arquivo fxml.
			listaP.setTitle("Prontuario do Paciente");// nomeia a janela.
			listaP.setScene(new Scene(windowPaciente, 600, 400));//seta o fxml dentro do stage.
			listaP.initModality(Modality.APPLICATION_MODAL);//impede que o stage seja redimencionado.
			listaP.show();//apresenta a janela Paciente
		} catch (Exception e) {
			e.printStackTrace();// caso ocorra algum erro no processo, e.printStackTrace() detalha o que aconteceu.
		}
	}
	
	//classe para fechar a tela - sair 
	public void closePaciente(ActionEvent event) {
		
		Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        // Fecha o palco (Stage)
        stage.close();
	}
	//botoão para pesquisar 
	public void FilterPaciente(ActionEvent event) throws SQLException {
		ListaPacientes.getChildren().clear();//limpa todos os itens que estavam no container 
		try {
			Connection con = dbconnection.Connect.fazer_conexao();
			
			List<Paciente> pacientes = new ArrayList<>();
			
			String sql = "select * from patient where name=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, filterPaciente.getText());
            ResultSet rs = stmt.executeQuery();
			
            Double layoutXPane = 50.0;
			Double layoutYPane = 14.0;
			Double widthPane = 500.0;
			Double heightpane = 80.0;
			if(rs.next()) {
				while (rs.next()) {
	            	Pane PacientesSFiltro = new Pane();
	        	    PacientesSFiltro.setLayoutX(layoutXPane);  // Usando o layoutXPane para posicionar horizontalmente
	        	    PacientesSFiltro.setLayoutY(layoutYPane);
	        	    PacientesSFiltro.setPrefWidth(widthPane);
	        	    PacientesSFiltro.setPrefHeight(heightpane);
	        	    PacientesSFiltro.setStyle("-fx-background-color: white; -fx-background-radius: 10px; -fx-alignment: center; -fx-position: absolute");
	        	    
	        	    //Nome
	        	    Label nomePaciente = new Label();
	        	    nomePaciente.setText("Nome: " + rs.getString("name"));
	        	    nomePaciente.setLayoutX(84.0);
	        	    nomePaciente.setLayoutY(10.0);
	        	    nomePaciente.setFont(new Font("Arial", 20));
	        	    PacientesSFiltro.getChildren().add(nomePaciente);
	        	    
	        	    
	        	    //Número do prontuário ]
	        	    Label pontuarioPaciente = new Label();
	        	    pontuarioPaciente.setText("Nº prontuário: " + rs.getString("medical_record"));
	        	    pontuarioPaciente.setLayoutX(84.0);
	        	    pontuarioPaciente.setLayoutY(40.0);
	        	    pontuarioPaciente.setFont(new Font("Arial", 16));
	        	    PacientesSFiltro.getChildren().add(pontuarioPaciente);
	        	    
	        	    //botão editar 
	        	    Button buttonEditar = new Button();
	        	    buttonEditar.setLayoutX(350.0); 
	        	    buttonEditar.setLayoutY(19.0);
	        	    buttonEditar.setPrefWidth(100.0);
	        	    buttonEditar.setPrefHeight(30.0);
	        	    buttonEditar.setText("Editar");
	        	    buttonEditar.setStyle("-fx-background-color: #9A0019; -fx-text-fill: white;");
	        	    PacientesSFiltro.getChildren().add(buttonEditar);
	        	    
	        	    
	        	    ListaPacientes.getChildren().add(PacientesSFiltro);
	        	    
	        	    layoutYPane = layoutYPane + 90.0;
	        	    System.out.print(layoutXPane + layoutYPane + widthPane + heightpane);
	            };
			}else {
				System.out.println("Nenhum paciente encontrado");
				Pane PacientesSFiltro = new Pane();
        	    PacientesSFiltro.setLayoutX(layoutXPane);  // Usando o layoutXPane para posicionar horizontalmente
        	    PacientesSFiltro.setLayoutY(layoutYPane);
        	    PacientesSFiltro.setPrefWidth(widthPane);
        	    PacientesSFiltro.setPrefHeight(heightpane);
        	    PacientesSFiltro.setStyle("-fx-background-color: white; -fx-background-radius: 10px; -fx-alignment: center; -fx-position: absolute");
        	    
        	    //Nome
        	    Label nomePaciente = new Label();
        	    nomePaciente.setText("Nehum paciente encontrado");
        	    nomePaciente.setLayoutX(84.0);
        	    nomePaciente.setLayoutY(10.0);
        	    nomePaciente.setFont(new Font("Arial", 20));
        	    PacientesSFiltro.getChildren().add(nomePaciente);
			}
            
		} catch (SQLException e1) {
			// TODO: handle exception
			System.out.print("catch");
			e1.printStackTrace();
		}
	}
}

