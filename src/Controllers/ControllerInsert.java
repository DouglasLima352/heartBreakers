package Controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dbconnection.Connect;

import javafx.stage.FileChooser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
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
	private int id;
	private String nameInsert;
	private int rgInsertInt;
	private int cpfInsertInt;
	private Date birthDateFormatedInsert;
	private String addressInsert;
	private Float weightInsertValue;
	private Float heightInsertValue;
	private String genderInsert;
	private String bloodTypeInsert;
	private String cidInsert;
	private String complaintInsert;
	private String diseaseHistoryInsert;
	private String allergiesInsert;
	private String conductInsert;
	private String physicalStateInsert;
	private String diagnosticHypothesisInsert;
	private String examsInsert;
	private String resultsInsert;
    private byte[] imageBytes;
    private byte[] examFile;
	
	
	@FXML
	private Button bVoltar;
	
	@FXML
	private Button bSalvar;
	
	@FXML
	private Button bAnexar;
	
	@FXML
    private ImageView photo;

	@FXML
	private TextField pastaSelecionada;
	
	@FXML
	private TextField name;

	@FXML
	private TextField cpf;

	@FXML
	private TextField rg;

	@FXML
	private DatePicker birthDate;

	@FXML
	private TextField address;

	@FXML
	private TextField cell;

	@FXML
	private TextField weight;

	@FXML
	private TextField height;

	@FXML
	private TextField gender;
	
	@FXML
	private TextField bloodType;

	@FXML
	private TextArea cid;

	@FXML
	private TextArea complaint;
	
	@FXML
	private TextArea diseaseHistory;

	@FXML
	private TextArea allergies;

	@FXML
	private TextArea conduct;
	
	@FXML
	private TextArea physicalState;

	@FXML
	private TextArea diagnosticHypothesis;

	@FXML
	private TextField exams;
	
	@FXML
	private TextField results;
	
	public void bChooser(ActionEvent event) {
		FileChooser selecionarArquivo = new FileChooser();//cria a instancia do FileChooser
		selecionarArquivo.setTitle("Selecione um arquivo"); //titulo da janela que vai abrir
		selecionarArquivo.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("ArquivosPDF", "*.pdf")
				);
	    File arquivoSelecionado = selecionarArquivo.showOpenDialog(null);
	    if (arquivoSelecionado != null) {
	        System.out.println("Arquivo selecionado: " + arquivoSelecionado.getAbsolutePath());
	        pastaSelecionada.setText(arquivoSelecionado.getAbsolutePath());
	    } else {
	        System.out.println("Nenhum arquivo selecionado.");
	    }
	}
	
	public ControllerInsert() {
        //this.id = id;
    }
	
    public void initialize() {
        try {
            Connection con = Connect.fazer_conexao();

            String sql = "SELECT * FROM patient";
            PreparedStatement stmt = con.prepareStatement(sql);
            //stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            
            if (result.next()) {
            	//Ficha do paciente
            	byte[] photoBytes = result.getBytes("photo");
            	//Exibindo
                if (photoBytes != null) {
                	ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(photoBytes);
                	Image image = new Image(byteArrayInputStream); 
                	photo.setImage(image);
        	    } else {
        	    	System.out.print("foto indisponivel"); 
        	    }
                
                String pacienteName = result.getString("name");
                name.setText(pacienteName);
                
                String pacienteAddress = result.getString("address");
                address.setText(pacienteAddress);
                
                int pacienteCPF = result.getInt("cpf");
                String pacienteCPFString = Integer.toString(pacienteCPF);
                cpf.setText(pacienteCPFString);
                
                int pacienteRG = result.getInt("rg");
                String pacienteRGString = String.valueOf(pacienteRG);
                rg.setText(pacienteRGString);
                
                Date pacienteDateBird = result.getDate("birth_date");
                LocalDate pacienteDateBirdLocalDate = pacienteDateBird.toLocalDate();  //Converte para o formato local de data
                birthDate.setValue(pacienteDateBirdLocalDate);
                
                String pacienteSexo = result.getString("gender");
                gender.setText(pacienteSexo);
                
                Float pacientePeso = result.getFloat("weight"); 
                String pacientePesoString = Float.toString(pacientePeso);
                weight.setText(pacientePesoString);
                
                Float pacienteAltura = result.getFloat("height"); 
                String pacienteAlturaString = Float.toString(pacienteAltura);
                height.setText(pacienteAlturaString);
                
                String pacienteTipoSanguineo = result.getString("blood_type"); 
                bloodType.setText(pacienteTipoSanguineo);
                
                //prontuário 
                String queixaPrincipal = result.getString("complaint");
                complaint.setText(queixaPrincipal);
                
                String historico = result.getString("disease_history");
                diseaseHistory.setText(historico);
                
                String alergias = result.getString("allergies");
                allergies.setText(alergias);
                
                String cidPc = result.getString("cid");
                cid.setText(cidPc);
                
                String physical_state = result.getString("physical_state");
                physicalState.setText(physical_state);
                
                String conductD = result.getString("conduct");
                conduct.setText(conductD);
                
                String diagnostic_hypothesisD = result.getString("diagnostic_hypothesis");
                diagnosticHypothesis.setText(diagnostic_hypothesisD);
                
                String diagnosisD = result.getString("exams");
                exams.setText(diagnosisD);
                
                String resultsD = result.getString("results");
                results.setText(resultsD);
                
            }else {
            	System.out.print("Erro de conexão");
            }

            result.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }       
	
	public void salvar (ActionEvent event) {
		
	    try {
	    	nameInsert = name.getText();
	    	String cpfInsert = cpf.getText();
	    	cpfInsertInt = Integer.parseInt(cpfInsert);
	    	String rgInsert = rg.getText();
	    	rgInsertInt = Integer.parseInt(rgInsert);
	    	LocalDate birthDateInsert = birthDate.getValue();
	    	birthDateFormatedInsert = Date.valueOf(birthDateInsert);
	    	addressInsert = address.getText();
	    	String weightInsert = weight.getText();
	    	weightInsertValue = Float.valueOf(weightInsert);
	    	String heightInsert = weight.getText();
	    	heightInsertValue = Float.valueOf(heightInsert);
	    	genderInsert = gender.getText();
	    	bloodTypeInsert = bloodType.getText();
	    	cidInsert = cid.getText();
	    	complaintInsert = complaint.getText();
	    	diseaseHistoryInsert = diseaseHistory.getText();
	    	allergiesInsert = allergies.getText();
	    	conductInsert = conduct.getText();
	    	physicalStateInsert = physicalState.getText();
	    	examsInsert = exams.getText();
	    	diagnosticHypothesisInsert = diagnosticHypothesis.getText();
	    	resultsInsert = results.getText();
	    	
	    	Connection con = Connect.fazer_conexao();
	    	String sql = "INSERT INTO patient (name, cpf, rg, birth_date, address, cell, weight, height, gender, blood_type, cid, complaint, disease_history, allergies, conduct, physical_state, exams, diagnostic_hypothesis, results, photo) " +
		            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    	PreparedStatement stmt = con.prepareStatement(sql);
	             
	        stmt.setString(1, nameInsert);
            stmt.setInt(2, cpfInsertInt);
            stmt.setInt(3, rgInsertInt);
            stmt.setDate(4, birthDateFormatedInsert);
            stmt.setString(5, addressInsert);
            stmt.setFloat(6, weightInsertValue);
            stmt.setFloat(7, heightInsertValue);
            stmt.setString(8, genderInsert);
            stmt.setString(9, bloodTypeInsert);
            stmt.setString(10, cidInsert);
            stmt.setString(11, complaintInsert);
            stmt.setString(12, diseaseHistoryInsert);
            stmt.setString(13, allergiesInsert);
            stmt.setString(14, conductInsert);
            stmt.setString(15, physicalStateInsert);
            stmt.setString(15, examsInsert);
            stmt.setString(16, diagnosticHypothesisInsert);
            stmt.setString(17, resultsInsert);
            stmt.setBytes(18, imageBytes);
            int result = stmt.executeUpdate();
	      
	        System.out.println("Inserção bem-sucedida.");
	        
	        Stage listaP = new Stage();//cria um novo stage
			Parent windowPaciente = FXMLLoader.load(getClass().getResource("PacienteHB.fxml"));//carrega o arquivo fxml.
			listaP.setTitle("Prontuario do Paciente");// nomeia a janela.
			listaP.setScene(new Scene(windowPaciente, 600, 400));//seta o fxml dentro do stage.
			listaP.setResizable(false);//impede que o stage seja redimencionado.
			listaP.show();//apresenta a janela Paciente
	    } catch (SQLException | IOException e) {
	        e.printStackTrace(); // Exceção
	    }
	}

	public void voltar(ActionEvent event) {
			try {
				Stage listaP = new Stage();//cria um novo stage
				Parent windowPaciente = FXMLLoader.load(getClass().getResource("PacienteHB.fxml"));//carrega o arquivo fxml.
				listaP.setTitle("Prontuario do Paciente");// nomeia a janela.
				listaP.setScene(new Scene(windowPaciente, 600, 400));//seta o fxml dentro do stage.
				listaP.setResizable(false);//impede que o stage seja redimencionado.
				listaP.show();//apresenta a janela Paciente		
				
			} catch (Exception e) {
				//e.printStackTrace();// caso ocorra algum erro no processo, e.printStackTrace() detalha o que aconteceu.
					// TODO: handle exception
					System.out.print("catch");
					e.printStackTrace();
			}
		}
}