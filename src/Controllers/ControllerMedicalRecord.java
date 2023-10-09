package Controllers;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dbconnection.Connect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Paciente;

public class ControllerMedicalRecord{
	@FXML
	private TextField patientFilter;
	
	//Método que chama a função para fazer a busca no banco
	public void FilterPatient(ActionEvent event) throws SQLException {
		Connection connect = dbconnection.Connect.fazer_conexao();
		
		//Lista para adicionar os dados do banco
		List<Paciente> patients = new ArrayList<>();
		
		//Busca por dados
		try {
			String sqlSearch = "select * from patient where name=?";     //Seleciona tudo do campo nome na tabela patient
			PreparedStatement stmt = connect.prepareStatement(sqlSearch);//stmt 
			
			stmt.setString(1, patientFilter.getText());
			ResultSet result = stmt.executeQuery();
			
			while (result.next()) { //Resultado
				
                Paciente paciente = new Paciente();

                paciente.setProntuarionNumber(result.getInt("medical_record"));
                paciente.setName(result.getString("Name"));
                paciente.setPhoto(result.getString("Photo"));
                paciente.setCPF(result.getString("CPF"));
                paciente.setRG(result.getString("RG"));
                paciente.setBirthDate(result.getString("BirthDate"));
                paciente.setAddress(result.getString("Address"));
                paciente.setCell(result.getString("Cell"));
                paciente.setWeight(result.getString("Weight"));
                paciente.setBloodType(result.getString("BloodType"));
                paciente.setCid(result.getString("Cid"));
                paciente.setComplaint(result.getString("Complaint"));
                paciente.setDiseaseHistory(result.getString("DiseaseHistory"));
                paciente.setAllergies(result.getString("Allergies"));
                paciente.setConduct(result.getString("Conduct"));
                paciente.setExams(result.getString("Exams"));
                paciente.setDiagnosticHypothesis(result.getString("DiagnosticHypothesis"));
                paciente.setResults(result.getString("Results"));
                patients.add(paciente);
            }
			
			System.out.printf("Percorrendo o ArrayList (usando o índice)\n");
		    int n = patients.size();
		    for (int i=0; i<n; i++) {
		    	System.out.printf( "Posição %d- %s\n", i, patients.get(i) + " , Número do prontuário: " + patients.get(i).getProntuarionNumber());
		    }
		} catch (Exception ex) {
			// TODO: handle exception
			System.out.print("catch");
			ex.printStackTrace();
		}
	}
}