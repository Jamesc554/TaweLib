package screen;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import library.Library;

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
	@FXML
	private Label resourceTitle;
	@FXML
	private Label averageRating;
	@FXML
	private ScrollPane ratingScroll;
	@FXML
	private ComboBox oneToFive;
	@FXML
	private TextArea messageBox;
	@FXML
	private Button leaveRating;
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
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(Library.getCurrentLoggedInUser().getProfImage()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (Library.currentUserIsLibrarian()) {
			
		} else {
			
		}

		userIcon.setImage(SwingFXUtils.toFXImage(img, null));
		usernameText.setText(Library.getCurrentLoggedInUser().getUserName());
		searchBar.setText(Library.getSearchScreenText());

		if (Library.currentUserIsLibrarian()) {
			issueDeskBtn.setVisible(true);
		}
		
		oneToFive.getItems().addAll(
	            "1/5 Stars",
	            "2/5 Stars",
	            "3/5 Stars",
	            "4/5 Stars",
	            "5/5 Stars"
	        );
	}
	
//	@FXML
//	/**
//	 * Submits the users rating.
//	 */
//	public void leaveRatingClick() {
//		
//	}
}
