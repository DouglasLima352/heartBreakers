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

public class ControllerInsert{
	@FXML
	private Button bVoltar;
	
	@FXML
	private TextField patientFilter;
	
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
	
	public void test(ActionEvent event) throws SQLException {
		try {
			Connection connect = dbconnection.Connect.fazer_conexao(); //Conectando ao banco db
			
			List<Paciente> pacientes = new ArrayList<>();
			
			String SQLConnect = "sql * from patient where name = ?";
			PreparedStatement stmt = connect.prepareStatement(SQLConnect);
			
			stmt.setString(1, patientFilter.getText());
			ResultSet result = stmt.executeQuery();
			
			while (result.next()) {
				Paciente paciente = new Paciente();
				
				paciente.setPhoto(result.getString("photo"));
				paciente.setName(result.getString("name"));
				paciente.setCPF(result.getString("cpf"));
				paciente.setRG(result.getString("rg"));
				paciente.setBirthDate(result.getString("birth_date"));
				paciente.setAddress(result.getString("address"));
				paciente.setCell(result.getString("cell"));
				paciente.setWeight(result.getString("weight"));
				paciente.setHeight(result.getString("height"));
				paciente.setGender(result.getString("gender"));
				paciente.setBloodType(result.getString("bloodType"));
				paciente.setCid(result.getString("cid"));
				paciente.setComplaint(result.getString("complaint"));
				paciente.setDiseaseHistory(result.getString("diseaseHistory"));
				paciente.setAllergies(result.getString("allergies"));
				paciente.setConduct(result.getString("conduct"));
				paciente.setPhysicalState(result.getString("physical_state"));
				paciente.setExams(result.getString("exams"));
				paciente.setDiagnosticHypothesis(result.getString("diagnostic_hypothesis"));
				paciente.setResults(result.getString("results"));
				pacientes.add(paciente);
			}
			System.out.print("paciente");
			
		} catch (SQLException error) {
			// TODO: handle exception
			System.out.print("catch");
			error.printStackTrace();
		}
		
	}
}
