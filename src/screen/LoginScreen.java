package screen;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import library.Library;

public class LoginScreen extends Screen{
	
	@FXML
	private Label statusLabel;
	
	@FXML
	private TextField usernameTextField;
	
	@FXML
	private Button loginBtn;

	@FXML
	private void login(Event event) {
		// TODO: remove test username once readAllUsers is finished
		if (Library.checkForUser(usernameTextField.getText())) {
			Library.onLogin(usernameTextField.getText());
			ScreenManager.changeScreen(new HomeScreen());
		} else {
			statusLabel.setText("Username is invalid!");
			statusLabel.setTextFill(Color.RED);
		}
	}
	
	@FXML
	private void onEnter(ActionEvent actionEvent) {
		login(actionEvent);
	}

	@Override
	public void start() {
		Pane root;
		try {
			root = FXMLLoader.load(getClass().getResource("fxml/LoginScreen.fxml"));
			ScreenManager.setCurrentScene(new Scene(root, 1280, 720));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
