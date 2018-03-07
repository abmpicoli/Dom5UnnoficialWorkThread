package dominionsaver;

import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class DominionSaverJavaFX extends Application {

	public static void main(String... args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Hello world!");
		URL uri = Controller.class.getResource("/dominionsaver/Xxx.fxml");
		Parent p = FXMLLoader.load(uri);
		primaryStage.setScene(new Scene(p));
		primaryStage.show();
		
	}

}
