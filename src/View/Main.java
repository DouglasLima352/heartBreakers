package View;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class Main extends Application{

	
	private static Stage stage;
	private static Scene oneScene;
	private static Scene twiceScene;
	
	public void start(Stage primaryStage) throws Exception{
		stage = primaryStage;
		Parent janela1 = FXMLLoader.load(getClass().getResource("PacienteHB.fxml"));
		primaryStage.setTitle("Menu");
	    //primaryStage.getIcons().add(new Image("file:resources/images/docs.png"))
		oneScene = new Scene(janela1, 600, 400);
		//root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		Parent janela2 = FXMLLoader.load(getClass().getResource("ProntuarHB.fxml"));
		primaryStage.setTitle("Prontuário");
		twiceScene = new Scene(janela2, 600, 400);
		//Parent janela2 = FXMLLoader.load(getClass().getResource("ProntuarHB.fxml"));
		
		primaryStage.setScene(oneScene);
		primaryStage.show();
	}
	
	public static void mudarJanela(String scr){
		switch(scr){
			case "one":
				stage.setScene(oneScene);
				break;
			case "twice":
				stage.setScene(twiceScene);
		}
	}
	public static void main(String[] args) {
		launch(args); 

	}
}
