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
import javafx.event.Event;
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
	@FXML
	private Button buttonNovo;
	
	public void initialize() throws SQLException {
		//primeira classe a ser executada assim que a tela é aberta 
		System.out.print("primeira classe a ser executada assim que a tela é aberta ");
		
		try {
			Connection con = dbconnection.Connect.fazer_conexao();
			
			String sql = "select * from patient";
			PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            Double layoutXPane = 50.0;
			Double layoutYPane = 14.0;
			Double widthPane = 500.0;
			Double heightpane = 80.0;
			
            while (rs.next()) {
            	Pane PacientesSFiltro = new Pane();
        	    PacientesSFiltro.setLayoutX(layoutXPane);
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
        	    String namepaciente = rs.getString("name");
        	    
        	  //foto
        	    byte[] photoBytes = rs.getBytes("photo");
        	    if (photoBytes != null) {
        	        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(photoBytes);
        	        Image image = new Image(byteArrayInputStream);

        	        ImageView imageView = new ImageView(image);
        	        imageView.setFitWidth(65.0); 
        	        imageView.setFitHeight(60.0);
        	        imageView.setLayoutX(10.0);
        	        imageView.setLayoutY(10.0);

        	       
        	        PacientesSFiltro.getChildren().add(imageView);
        
        	    } else {
        	    	System.out.print("foto indisponivel"); 
        	    }
        	    
        	    //Número do prontuário ]
        	    Label pontuarioPaciente = new Label();
        	    pontuarioPaciente.setText("Nº prontuário: " + rs.getString("medical_record"));
        	    pontuarioPaciente.setLayoutX(84.0);
        	    pontuarioPaciente.setLayoutY(40.0);
        	    pontuarioPaciente.setFont(new Font("Arial", 16));
        	    PacientesSFiltro.getChildren().add(pontuarioPaciente);
        	    int idPaciente = rs.getInt("medical_record");
        	    
        	    //botão editar 
        	    Button buttonEditar = new Button();
        	    buttonEditar.setLayoutX(350.0); 
        	    buttonEditar.setLayoutY(19.0);
        	    buttonEditar.setPrefWidth(100.0);
        	    buttonEditar.setPrefHeight(30.0);
        	    buttonEditar.setText("Editar");
        	    buttonEditar.setOnAction(event -> {
        	    	try {
        	    	    Stage currentStage = (Stage) buttonEditar.getScene().getWindow();
        	    	    currentStage.close();

        	    	    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ProntuarHBUpdate.fxml"));
        	    	    loader.setController(new ControllerUpdate(idPaciente));
        	    	    Parent root = loader.load();
        	    	    
        	    	    Stage stage = new Stage();
        	    	    stage.setTitle("Prontuário: " + namepaciente);
        	    	    stage.setScene(new Scene(root, 600, 700));
        	    	    stage.setResizable(false);
        	    	    stage.show();

        	    	} catch (Exception e) {
        	    	    e.printStackTrace();
        	    	    System.out.print(e);
        	    	}

        	    });
        	    buttonEditar.setStyle("-fx-background-color: #9A0019; -fx-text-fill: white;");
        	    PacientesSFiltro.getChildren().add(buttonEditar);
        	    
        	    
        	    ListaPacientes.getChildren().add(PacientesSFiltro);
        	    
        	    layoutYPane = layoutYPane + 90.0;
        	    System.out.print(layoutXPane + layoutYPane + widthPane + heightpane);
            };
		} catch (SQLException e1) {
			System.out.print("catch");
			e1.printStackTrace();
		}
	}
	public void novoPaciente(ActionEvent event) {
		try {
			Stage currentStage = (Stage) buttonNovo.getScene().getWindow();
            currentStage.close(); // 
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ProntuarHB.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex);
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
		ListaPacientes.getChildren().clear();
		String nameFilter = filterPaciente.getText();
		try {
			Connection con = dbconnection.Connect.fazer_conexao();
			String sql = "select * from patient where name=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, nameFilter);
            ResultSet rs = stmt.executeQuery();
            
            Double layoutXPane = 50.0;
			Double layoutYPane = 14.0;
			Double widthPane = 500.0;
			Double heightpane = 80.0;
			if(rs.next()) {
				System.out.println("Pacientes encontrado");
				do {
					int pacienten = 1;
					System.out.println("paciente "+ pacienten);
					pacienten = pacienten + 1;
					
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
	        	    String namepaciente = rs.getString("name");
	        	    System.out.println("paciente: "+ rs.getString("name"));
	        	    
	        	  //foto
	        	    byte[] photoBytes = rs.getBytes("photo");
	        	    if (photoBytes != null) {
	        	        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(photoBytes);
	        	        Image image = new Image(byteArrayInputStream);

	        	        ImageView imageView = new ImageView(image);
	        	        imageView.setFitWidth(65.0);
	        	        imageView.setFitHeight(60.0);
	        	        imageView.setLayoutX(10.0);
	        	        imageView.setLayoutY(10.0);

	        	       
	        	        PacientesSFiltro.getChildren().add(imageView);
	        
	        	    } else {
	        	    	System.out.print("foto indisponivel"); 
	        	    }
	        	    
	        	    //Número do prontuário ]
	        	    Label pontuarioPaciente = new Label();
	        	    pontuarioPaciente.setText("Nº prontuário: " + rs.getString("medical_record"));
	        	    pontuarioPaciente.setLayoutX(84.0);
	        	    pontuarioPaciente.setLayoutY(40.0);
	        	    pontuarioPaciente.setFont(new Font("Arial", 16));
	        	    PacientesSFiltro.getChildren().add(pontuarioPaciente);
	        	    int idPaciente = rs.getInt("medical_record");
	        	    
	        	    //botão editar 
	        	    Button buttonEditar = new Button();
	        	    buttonEditar.setLayoutX(350.0); 
	        	    buttonEditar.setLayoutY(19.0);
	        	    buttonEditar.setPrefWidth(100.0);
	        	    buttonEditar.setPrefHeight(30.0);
	        	    buttonEditar.setText("Editar");
	        	    buttonEditar.setOnAction(e -> {
	        	    	try {
	        	    	    Stage currentStage = (Stage) buttonEditar.getScene().getWindow();
	        	    	    currentStage.close();

	        	    	    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ProntuarHBUpdate.fxml"));
	        	    	    loader.setController(new ControllerUpdate(idPaciente));
	        	    	    Parent root = loader.load();
	        	    	    
	        	    	    Stage stage = new Stage();
	        	    	    stage.setTitle("Prontuário: " + namepaciente);
	        	    	    stage.setScene(new Scene(root, 600, 700));
	        	    	    stage.setResizable(false);
	        	    	    stage.show();

	        	    	} catch (Exception ex) {
	        	    	    System.out.print(ex);
	        	    	}

	        	    });
	        	    buttonEditar.setStyle("-fx-background-color: #9A0019; -fx-text-fill: white;");
	        	    PacientesSFiltro.getChildren().add(buttonEditar);
	        	    
	        	    
	        	    ListaPacientes.getChildren().add(PacientesSFiltro);
	        	    
	        	    layoutYPane = layoutYPane + 90.0;
	        	    System.out.print(layoutXPane + layoutYPane + widthPane + heightpane);
				} while (rs.next());
			}else {
				System.out.println("Paciente não encontrado");
			}
			
            
		} catch (SQLException e1) {
			// TODO: handle exception
			System.out.print("catch");
			e1.printStackTrace();
		}
	}

}

