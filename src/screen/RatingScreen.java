package screen;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 * <h1>RatingScreen.</h1>
 * <p>This class represents the Rating View, a screen avaliable to users so they can rate the resources.</p>
 * @author Sam Jankinson
 * @version 1.0
 */
/*
 * TODO: THIS CLASS
 */
public class RatingScreen extends Screen implements Initializable {
	@Override
    /**
     * Sets IssueDesk as the current scene.
     */
    public void start() {
        Pane root;
        try {
            root = FXMLLoader.load(getClass().getResource("fxml/RatingScreen.fxml"));
            ScreenManager.setCurrentScene(new Scene(root, 1280, 720));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
