package Controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import dbconnection.Connect;

import javafx.stage.FileChooser;
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

public class ControllerInsert{
	@FXML
	private Button bVoltar;
	
	@FXML
	private Button bSalvar;
	
	@FXML
	private Button bAnexar;

	@FXML
	private TextField pastaSelecionada;
	
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	public void bChooser(ActionEvent event) {
		FileChooser selecionarArquivo = new FileChooser();//cria a instancia do FileChooser
		selecionarArquivo.setTitle("Selecione um arquivo"); //titulo da janela que vai abrir
	    File arquivoSelecionado = selecionarArquivo.showOpenDialog(null);
	    if (arquivoSelecionado != null) {
	        System.out.println("Arquivo selecionado: " + arquivoSelecionado.getAbsolutePath());
	        pastaSelecionada.setText(arquivoSelecionado.getAbsolutePath());
	    } else {
	        System.out.println("Nenhum arquivo selecionado.");
	    }
	}
	
	public void salvar (ActionEvent event) {
	    Connection connect = null;
	    PreparedStatement stmt = null;

	    try {
	        List<Paciente> pacientes = new ArrayList<>();

	        connect = dbconnection.Connect.fazer_conexao(); // Conectando ao banco de dados
	        /*String SQLConnect = "SELECT * FROM patient WHERE name = ?";
	        stmt = connect.prepareStatement(SQLConnect);

	        stmt.setString(1, name.getText());
	        ResultSet result = stmt.executeQuery();*/
	        
	        Paciente paciente = new Paciente();

            paciente.setPhoto(getString("photo"));
            paciente.setName(getString("name"));
            paciente.setCPF(getString("cpf"));
		    paciente.setRG(getString("rg"));
		    paciente.setBirthDate(getString("birth_date"));
		    paciente.setAddress(getString("address"));
		    paciente.setCell(getString("cell"));
		    paciente.setWeight(getString("weight"));
		    paciente.setHeight(getString("height"));
		    paciente.setGender(getString("gender"));
		    paciente.setBloodType(getString("blood_type"));
		    paciente.setCid(getString("cid"));
		    paciente.setComplaint(getString("complaint"));
		    paciente.setDiseaseHistory(getString("disease_history"));
		    paciente.setAllergies(getString("allergies"));
		    paciente.setConduct(getString("conduct"));
		    paciente.setPhysicalState(getString("physical_state"));
		    
		    pacientes.add(paciente);

	        // Agora, vamos inserir os pacientes na tabela 'patient'

	        String SQLInsert = "INSERT INTO patient (photo, name, cpf, rg, birth_date, address, cell, weight, height, gender, blood_type, cid, complaint, disease_history, allergies, conduct, physical_state, exams, diagnostic_hypothesis, results) " +
	            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	        stmt = connect.prepareStatement(SQLInsert);
	        
	        for (Paciente paciente1 : pacientes) {
	            // Configure os parâmetros do PreparedStatement com base no tipo de dado correto
	            stmt.setString(1, paciente1.getPhoto());
	            stmt.setString(2, paciente1.getName());
	            stmt.setString(3, paciente1.getCPF());
	            stmt.setString(4, paciente1.getRG());
	            stmt.setString(1, paciente1.getPhoto());
			    stmt.setString(2, paciente1.getName());
			    stmt.setString(3, paciente1.getCPF());
			    stmt.setString(4, paciente1.getRG());
			    stmt.setString(5, paciente1.getBirthDate());
			    stmt.setString(6, paciente1.getAddress());
			    stmt.setString(7, paciente1.getCell());
			    stmt.setString(8, paciente1.getWeight());
			    stmt.setString(9, paciente1.getHeight());
			    stmt.setString(10, paciente1.getGender());
			    stmt.setString(11, paciente1.getBloodType());
			    stmt.setString(12, paciente1.getCid());
			    stmt.setString(13, paciente1.getComplaint());
			    stmt.setString(14, paciente1.getDiseaseHistory());
			    stmt.setString(15, paciente1.getAllergies());
			    stmt.setString(16, paciente1.getConduct());
			    stmt.setString(17, paciente1.getPhysicalState());
			    stmt.setString(18, paciente1.getExams());
			    stmt.setString(19, paciente1.getDiagnosticHypothesis());
			    stmt.setString(20, paciente1.getResults());

	            // Execute a instrução SQL para inserir o paciente
	            stmt.executeUpdate();
	            stmt.close();
	            connect.close();
	        }

	        System.out.println("Inserção bem-sucedida.");
	        
	        Stage listaP = new Stage();//cria um novo stage
			Parent windowPaciente = FXMLLoader.load(getClass().getResource("PacienteHB.fxml"));//carrega o arquivo fxml.
			listaP.setTitle("Prontuario do Paciente");// nomeia a janela.
			listaP.setScene(new Scene(windowPaciente, 600, 400));//seta o fxml dentro do stage.
			listaP.setResizable(false);//impede que o stage seja redimencionado.
			listaP.show();//apresenta a janela Paciente
	    } catch (SQLException | IOException e) {
	        e.printStackTrace(); // Exceção
	    } finally {
	        // Certifique-se de fechar a conexão e o PreparedStatement
	        try {
	            if (stmt != null) {
	                stmt.close();
	            }
	            if (connect != null) {
	                connect.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	
	private String getString(String string) {
		// TODO Auto-generated method stub
		return null;
	}


	public void voltar(ActionEvent event) {
			try {
				/*Connection connect = dbconnection.Connect.fazer_conexao(); //Conectando ao banco db
				String SQLConnect = "INSERT INTO patient (nome) VALUES (?)";
				
				pst = connect.prepareStatement(SQLConnect);
				rs = pst.executeQuery();*/
				
				if (rs.next()) {
					Stage listaP = new Stage();//cria um novo stage
					Parent windowPaciente = FXMLLoader.load(getClass().getResource("PacienteHB.fxml"));//carrega o arquivo fxml.
					listaP.setTitle("Prontuario do Paciente");// nomeia a janela.
					listaP.setScene(new Scene(windowPaciente, 600, 400));//seta o fxml dentro do stage.
					listaP.setResizable(false);//impede que o stage seja redimencionado.
					listaP.show();//apresenta a janela Paciente
				}else {
					System.out.println("Não foi possível salvar.");
				}
				
				
			} catch (Exception e) {
				//e.printStackTrace();// caso ocorra algum erro no processo, e.printStackTrace() detalha o que aconteceu.
					// TODO: handle exception
					System.out.print("catch");
					e.printStackTrace();
			}
		}
}