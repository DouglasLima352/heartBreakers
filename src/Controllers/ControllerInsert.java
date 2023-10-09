package Controllers;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dbconnection.Connect;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Paciente;

public class ControllerInsert{
	
	@FXML
	private TextField patientFilter;
	
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
				paciente.setPhysicalState(result.getString("´physical_state"));
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
