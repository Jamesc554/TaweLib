package screen;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import io.ReadFile;
import io.WriteFile;
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
	private static String rTitle;
	private static String rId;
	private static ArrayList<String[]> rRatings = new ArrayList<>();
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
		//Read in the ratings
		rRatings = ReadFile.readRatings();
		leaveRating.setDisable(true);
		
		//If user has borrowed the resource let them leave a rating.
		ArrayList<String[]> borrowHistory = Library.getCurrentLoggedInUser().getBorrowHistory();
		for (String[] borrow : borrowHistory) {
			String resourceID = borrow[0];
			if(resourceID.contains(rId)) {
				leaveRating.setDisable(false);
			}
		}
		
		if(leaveRating.isDisabled()) {
			messageBox.setText("Need to borrow resource to leave review.");
		}
		
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
		resourceTitle.setText(rTitle);

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
		
		calculateAverageRating();
	}
	
	public static void setResource(String id, String title) {
		rId = id;
		rTitle = title;
	}
	
	@FXML
	/**
     * Submits the users rating.
	 */
	public void leaveRatingClick() {
		String tempId = rId;
		String tempMessage = messageBox.getText();
		String stringRating = (String) oneToFive.getValue();
		String tempRating;
		if(stringRating.equals("1/5 Stars")) {
			tempRating = "1";
			WriteFile.writeRatingToFile(tempId, tempMessage, tempRating);
			messageBox.setText("Review Submitted");
			leaveRating.setDisable(true);
		} else if(stringRating.equals("2/5 Stars")) {
			tempRating = "2";
			WriteFile.writeRatingToFile(tempId, tempMessage, tempRating);
			messageBox.setText("Review Submitted");
			leaveRating.setDisable(true);
		} else if(stringRating.equals("3/5 Stars")) {
			tempRating = "3";
			WriteFile.writeRatingToFile(tempId, tempMessage, tempRating);
			messageBox.setText("Review Submitted");
			leaveRating.setDisable(true);
		} else if(stringRating.equals("4/5 Stars")) {
			tempRating = "4";
			WriteFile.writeRatingToFile(tempId, tempMessage, tempRating);
			messageBox.setText("Review Submitted");
			leaveRating.setDisable(true);
		} else if(stringRating.equals("5/5 Stars")) {
			tempRating = "5";
			WriteFile.writeRatingToFile(tempId, tempMessage, tempRating);
			messageBox.setText("Review Submitted");
			leaveRating.setDisable(true);
		} else {
			messageBox.setText("ERROR - Please select a rating");
		}
	}
	
	private void calculateAverageRating() {
		int counter = 0;
		int ratings = 0;
		try {
			for(String[] temp : rRatings) {
				if(temp[0].equals(rId)) {
					counter++;
					ratings += Integer.parseInt(temp[2]);
				}
			}
			
			if(counter == 0) { //incase no ratings.
				ratings = 0;
				averageRating.setText("N/A");
			} else {
				ratings = ratings / counter;
				averageRating.setText(Integer.toString(ratings));
			}
			
			
		} catch (NullPointerException e) {
			e.getCause();
			System.out.println("no ratings loaded");
		}
	}
}
