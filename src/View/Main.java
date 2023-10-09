package View;
import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;

public class Main extends Application{

	
	public void start(Stage primaryStage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("LoginHB.fxml"));//carrega o arquivo fxml.
		primaryStage.setTitle("Login");// nomeia a janela.
	    //primaryStage.getIcons().add(new Image("file:resources/images/docs.png"));
		primaryStage.setScene(new Scene(root, 600, 400));//seta o fxml dentro do stage.
		//root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		primaryStage.initModality(Modality.APPLICATION_MODAL);//impede que o stage seja redimencionado.
		primaryStage.show();//apresenta a a primeira janela
		
	}
	
	/*public void pacientes() throws Exception{
		Stage listaP = new Stage();//cria um novo stage
		Parent windowPaciente = FXMLLoader.load(getClass().getResource("PacienteHB.fxml"));//carrega o arquivo fxml.
		listaP.setTitle("Prontuario do Paciente");// nomeia a janela.
		listaP.setScene(new Scene(windowPaciente, 600, 400));//seta o fxml dentro do stage.
		listaP.initModality(Modality.APPLICATION_MODAL);//impede que o stage seja redimencionado.
		listaP.show();//apresenta a janela Paciente
	}*/
	
	/*public void prontuario() throws Exception{
		Stage prontuarioP = new Stage();//cria um novo stage
		Parent windowProntuario = FXMLLoader.load(getClass().getResource("ProntuarHB.fxml"));//carrega o arquivo fxml.
		prontuarioP.setTitle("Prontuario do Paciente");// nomeia a janela.
		prontuarioP.setScene(new Scene(windowProntuario, 600, 400));//seta o fxml dentro do stage.
		prontuarioP.initModality(Modality.APPLICATION_MODAL);//impede que o stage seja redimencionado.
		prontuarioP.show();//apresenta a janela Prontuario
	}*/
	public static void main(String[] args) {
		launch(args); 
	}
}
