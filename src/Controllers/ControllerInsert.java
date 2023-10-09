package Controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

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

public class ControllerInsert{
	@FXML
	private Button bVoltar;
	
	@FXML
	private Button bSalvar;
	
	@FXML
	private TextField patientFilter;
	
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	public void salvar (ActionEvent event) {
	    Connection connect = null;
	    PreparedStatement stmt = null;

	    try {
	        connect = dbconnection.Connect.fazer_conexao(); // Conectando ao banco de dados

	        List<Paciente> pacientes = new ArrayList<>();

	        String SQLConnect = "SELECT * FROM patient WHERE name = ?";
	        stmt = connect.prepareStatement(SQLConnect);

	        stmt.setString(1, patientFilter.getText());
	        ResultSet result = stmt.executeQuery();

	        while (result.next()) {
	            Paciente paciente = new Paciente();

	            // Configure os campos do paciente aqui, incluindo conversões de tipo se necessário
	            // Exemplo:
	            //paciente.setPhoto(result.getBytes("photo"));
	            paciente.setName(result.getString("name"));
	            paciente.setCPF(result.getString("cpf"));
			    paciente.setRG(result.getString("rg"));
			    paciente.setBirthDate(result.getString("birth_date"));
			    paciente.setAddress(result.getString("address"));
			    paciente.setCell(result.getString("cell"));
			    paciente.setWeight(result.getString("weight"));
			    paciente.setHeight(result.getString("height"));
			    paciente.setGender(result.getString("gender"));
			    paciente.setBloodType(result.getString("blood_type"));
			    paciente.setCid(result.getString("cid"));
			    paciente.setComplaint(result.getString("complaint"));
			    paciente.setDiseaseHistory(result.getString("disease_history"));
			    paciente.setAllergies(result.getString("allergies"));
			    paciente.setConduct(result.getString("conduct"));
			    paciente.setPhysicalState(result.getString("physical_state"));

	            pacientes.add(paciente);
	        }

	        // Agora, vamos inserir os pacientes na tabela 'patient'

	        String SQLInsert = "INSERT INTO patient (photo, name, cpf, rg, birth_date, address, cell, weight, height, gender, blood_type, cid, complaint, disease_history, allergies, conduct, physical_state, exams, diagnostic_hypothesis, results) " +
	            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	        stmt = connect.prepareStatement(SQLInsert);

	        for (Paciente paciente : pacientes) {
	            // Configure os parâmetros do PreparedStatement com base no tipo de dado correto
	            stmt.setString(1, paciente.getPhoto());
	            stmt.setString(2, paciente.getName());
	            stmt.setString(3, paciente.getCPF());
	            stmt.setString(4, paciente.getRG());
	            stmt.setString(1, paciente.getPhoto());
			    stmt.setString(2, paciente.getName());
			    stmt.setString(3, paciente.getCPF());
			    stmt.setString(4, paciente.getRG());
			    stmt.setString(5, paciente.getBirthDate());
			    stmt.setString(6, paciente.getAddress());
			    stmt.setString(7, paciente.getCell());
			    stmt.setString(8, paciente.getWeight());
			    stmt.setString(9, paciente.getHeight());
			    stmt.setString(10, paciente.getGender());
			    stmt.setString(11, paciente.getBloodType());
			    stmt.setString(12, paciente.getCid());
			    stmt.setString(13, paciente.getComplaint());
			    stmt.setString(14, paciente.getDiseaseHistory());
			    stmt.setString(15, paciente.getAllergies());
			    stmt.setString(16, paciente.getConduct());
			    stmt.setString(17, paciente.getPhysicalState());
			    stmt.setString(18, paciente.getExams());
			    stmt.setString(19, paciente.getDiagnosticHypothesis());
			    stmt.setString(20, paciente.getResults());

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
