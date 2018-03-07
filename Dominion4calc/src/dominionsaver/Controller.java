package dominionsaver;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class Controller {

	@FXML Button MyButton;

	@FXML public void x(ActionEvent event) {
		
		System.out.println(event.getSource());
		MyButton.setText("TADAAAA");
		
	}
	

}
