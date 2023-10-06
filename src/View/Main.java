package View;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application{

	public void start(Stage primaryStage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("loginHB.fxml"));
		primaryStage.setTitle("Login");
	    //primaryStage.getIcons().add(new Image("file:resources/images/docs.png"));
		primaryStage.setScene(new Scene(root, 600, 400));
		//root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		primaryStage.show();
		
	}
	public static void main(String[] args) {
		launch(args); 

	}
}
