package Controllers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import dbconnection.Connect;

public class ControllerUpdate {
    private int id;
    
    private String nameUpdate;
    private int cpfUpdateInt;
    private int rgUpdateInt;
    private String addressUpdate;
    private Date birth_dateUpdateDataFormated;
    private String genderUpdate;
    private String TipoSanguineoUpdate;
    private Float  weightUpdateValue;
    private Float  heightUpdateValue;
    private String complaintUpdate;
    private String historicUpdate;
    private String allergiesUpdate;
    private String cidUpdate;
    private String physical_examUpdate;
    private String conductUpdate;
    private String diagnostic_hypothesisUpdate;
    private String diagnosisUpdate;
    private byte[] imageBytes;
    private byte[] examFile;
 

    @FXML
    private Label lblprontuario;
    @FXML
    private TextField name;
    @FXML
    private TextField cpf; 
    @FXML 
    private TextField rg;
    @FXML
    private ImageView photo;
    @FXML
    private TextField address;
    @FXML 
    private DatePicker birth_date;
    @FXML
    private TextField gender;
    @FXML
    private TextField TipoSanguineo;
    @FXML
    private TextField weight;
    @FXML
    private TextField height;
    @FXML
    private TextField exams_results;
    
    
    //prontuário 
  	@FXML
  	private TextArea complaint;
  	@FXML
  	private TextArea historic;
  	@FXML 
  	private TextArea allergies;
  	@FXML
  	private TextArea cid;
  	@FXML
  	private TextArea physical_exam;
  	@FXML
  	private TextArea conduct;
  	@FXML
  	private TextArea diagnostic_hypothesis;
  	@FXML 
  	private TextArea diagnosis;
  	@FXML 
  	private Button deletePaciente;
  	

    public ControllerUpdate(int id) {
        this.id = id;
    }

    public void initialize() {
        try {
            lblprontuario.setText("Prontuário Nº: " + id);
            Connection con = Connect.fazer_conexao();

            String sql = "SELECT * FROM patient WHERE medical_record = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            // Process the ResultSet as needed
            if (rs.next()) {
            	//Ficha do paciente
            	byte[] photoBytes = rs.getBytes("photo");
            	//Exibindo
                if (photoBytes != null) {
                	ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(photoBytes);
                	Image image = new Image(byteArrayInputStream); 
                	photo.setImage(image);
        	    } else {
        	    	System.out.print("foto indisponivel"); 
        	    }
                
                
                String pacienteName = rs.getString("name");
                name.setText(pacienteName);
                
                
                String pacienteAddress = rs.getString("address");
                address.setText(pacienteAddress);
                
                int pacienteCPF = rs.getInt("cpf");
                String pacienteCPFString = Integer.toString(pacienteCPF);
                cpf.setText(pacienteCPFString);
                
                int pacienteRG = rs.getInt("rg");
                String pacienteRGString = String.valueOf(pacienteRG);
                rg.setText(pacienteRGString);
                
                Date pacienteDateBird = rs.getDate("birth_date");
                LocalDate pacienteDateBirdLocalDate = pacienteDateBird.toLocalDate();  //Converte para o formato local de data
                birth_date.setValue(pacienteDateBirdLocalDate);
                
                String pacienteSexo = rs.getString("gender");
                gender.setText(pacienteSexo);
                
                Float pacientePeso = rs.getFloat("weight"); 
                String pacientePesoString = Float.toString(pacientePeso);
                weight.setText(pacientePesoString);
                
                Float pacienteAltura = rs.getFloat("height"); 
                String pacienteAlturaString = Float.toString(pacienteAltura);
                height.setText(pacienteAlturaString);
                
                String pacienteTipoSanguineo = rs.getString("blood_type"); 
                TipoSanguineo.setText(pacienteTipoSanguineo);
                
                //prontuário 
                String queixaPrincipal = rs.getString("complaint");
                complaint.setText(queixaPrincipal);
                
                String historico = rs.getString("disease_history");
                historic.setText(historico);
                
                String alergias = rs.getString("allergies");
                allergies.setText(alergias);
                
                String cidPc = rs.getString("cid");
                cid.setText(cidPc);
                
                String physical_state = rs.getString("physical_state");
                physical_exam.setText(physical_state);
                
                String conductD = rs.getString("conduct");
                conduct.setText(conductD);
                
                String diagnostic_hypothesisD = rs.getString("diagnostic_hypothesis");
                diagnostic_hypothesis.setText(diagnostic_hypothesisD);
                
                String diagnosisD = rs.getString("results");
                diagnosis.setText(diagnosisD);
                
               
                
            }else {
            	System.out.print("Erro de conexão");
            }
            
            // Fechar conexões
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public void salvar(ActionEvent event) {
    	nameUpdate = name.getText();
    	
    	String cpfUpdate = cpf.getText();
    	cpfUpdateInt = Integer.parseInt(cpfUpdate);
    	
    	String rgUpdate = rg.getText();
    	rgUpdateInt = Integer.parseInt(rgUpdate);
    	
    	addressUpdate = address.getText();
    	
    	LocalDate birth_dateUpdate = birth_date.getValue();
    	birth_dateUpdateDataFormated = Date.valueOf(birth_dateUpdate);
    	
    	genderUpdate = gender.getText();
    	
    	TipoSanguineoUpdate = TipoSanguineo.getText();
    	
    	String weightUpdate = weight.getText();
    	weightUpdateValue = Float.valueOf(weightUpdate);
    	
    	String heightUpdate = height.getText();
    	heightUpdateValue = Float.valueOf(heightUpdate);
    	
    	//prontuário 
    	complaintUpdate = complaint.getText();
    	
    	historicUpdate = historic.getText();
    	
    	allergiesUpdate = allergies.getText() ;
    	
    	cidUpdate = cid.getText();
    	
    	physical_examUpdate = physical_exam.getText();
    	
    	conductUpdate = conduct.getText();
    	
    	diagnostic_hypothesisUpdate = diagnostic_hypothesis.getText();
    	
    	diagnosisUpdate = diagnosis.getText();
    	
    	try {
    		Connection con = Connect.fazer_conexao();
    		String sql = "UPDATE patient SET name= ?, cpf=?, rg=?, birth_date=?, address=?, weight=?, height=?, gender=?, blood_type=?, cid=?, complaint=?, disease_history=?, allergies=?, conduct=?, physical_state=?, diagnostic_hypothesis=?, results=?, photo=?, exams=? WHERE medical_record = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nameUpdate);
            stmt.setInt(2, cpfUpdateInt);
            stmt.setInt(3, rgUpdateInt);
            stmt.setDate(4, birth_dateUpdateDataFormated);
            stmt.setString(5, addressUpdate);
            stmt.setFloat(6, weightUpdateValue);
            stmt.setFloat(7, heightUpdateValue);
            stmt.setString(8, genderUpdate);
            stmt.setString(9, TipoSanguineoUpdate);
            stmt.setString(10, cidUpdate);
            stmt.setString(11, complaintUpdate);
            stmt.setString(12, historicUpdate);
            stmt.setString(13, allergiesUpdate);
            stmt.setString(14, conductUpdate);
            stmt.setString(15, physical_examUpdate);
            stmt.setString(16, diagnostic_hypothesisUpdate);
            stmt.setString(17, diagnosisUpdate);
            stmt.setBytes(18, imageBytes);
            stmt.setBytes(19, examFile);
            stmt.setInt(20, id);
            int rs = stmt.executeUpdate();
            
            System.out.print("Salvar");
            if(rs > 0){
            	System.out.printf("Novo registro alterado:"+  nameUpdate);
            }else{
            	System.out.println("Não foi possível alterar os registros!");
            }

            
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(e);
			
		}
    }

    public void trocarFoto(ActionEvent event) {
    	System.out.print("Trocar foto");
    	FileChooser selecionarArquivo = new FileChooser();//cria a instancia do FileChooser
    	selecionarArquivo.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagens", "*.png", "JPG", "*.jpg", "JPEG", "*.jpeg"));
    	selecionarArquivo.setTitle("Selecione uma nova foto"); //titulo da janela que vai abrir
    	File novaImage = selecionarArquivo.showOpenDialog(null);
    	
    	if (novaImage != null) {
		    try {
		        imageBytes = Files.readAllBytes(novaImage.toPath());
		        System.out.print(imageBytes);
		        
		    } catch (IOException e) {
		        System.err.println("erro");
		    }
		}
    }
    public void trocarArquivo(ActionEvent event) {
    	System.out.print("Trocar arquivo");
    	FileChooser selecionarArquivo = new FileChooser();//cria a instancia do FileChooser
		selecionarArquivo.setTitle("Selecione um arquivo"); //titulo da janela que vai abrir
		selecionarArquivo.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("ArquivosPDF", "*.pdf")      
        );
	    File arquivoSelecionado = selecionarArquivo.showOpenDialog(null);
	    if (arquivoSelecionado != null) {
		    try {
		    	examFile = Files.readAllBytes(arquivoSelecionado.toPath());
		        System.out.print(examFile);
		    } catch (IOException e) {
		        System.err.println("erro");
		    }
		}
    }
    public void deletePaciente(ActionEvent event) {
    	try {
    		Connection con = Connect.fazer_conexao();
    		String sql = "DELETE FROM patient WHERE medical_record= ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            int rs = stmt.executeUpdate();
            if(rs > 0){
            	System.out.printf("Paciente deletado: "+  nameUpdate);
            	
            	Stage currentStage = (Stage) deletePaciente.getScene().getWindow();
	    	    currentStage.close();

	    	    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PacienteHB.fxml"));
	    	    Parent root = loader.load();
	    	    
	    	    Stage stage = new Stage();
	    	    stage.setTitle("Lista de pacientes: ");
	    	    stage.setScene(new Scene(root, 600, 400));
	    	    stage.setResizable(false);
	    	    stage.show();
            }else{
            	System.out.println("Não foi possível alterar os registros!");
            }
            
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
	        
		}
    	
    }
}
