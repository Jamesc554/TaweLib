package screen;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import library.Library;
import resources.Resource;

/**
 * <h1>HomeScreen.</h1>
 * <p>This class represents the Home screen. This is first screen the User sees after
 * logging in.</p>
 * @author Etienne Badoche, Sam Jankinson, Ammar Alamri
 * @version 1.0
 */
public class HomeScreen extends Screen implements Initializable{
	// TOP TOOL BAR - COMMON BETWEEN SCREENS - COPY FROM HERE - MAKE SURE THE IDs IN
	// SCENEBUILDER ARE OF THE SAME NAME AS THE VARIBLES HERE!!!!!
	@FXML
	private Text fineText;
	
	@FXML
	private ListView borrowedItemsList;

	@Override
	/**
	 * This method changes the screen manager to the HomeScreen.
	 */
	public void start() {
		Pane root;
		try {
			root = FXMLLoader.load(getClass().getResource("fxml/home.fxml"));
			ScreenManager.setCurrentScene(new Scene(root, 1280, 720));
	        //setupEvents();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	/**
	 * Initialises the scene.
	 * @param arg0
	 * The location of the root object.
	 * @param arg1
	 * The resource used to localise the root object.
	 */
	public void initialize(URL arg0, ResourceBundle arg1) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(Library.getCurrentLoggedInUser().getProfImage()));
		} catch (IOException e) {
			
		}

		if (Library.currentUserIsLibrarian()) {
			issueDeskBtn.setVisible(true);
		}
		
		userIcon.setImage(SwingFXUtils.toFXImage(img, null));
		usernameText.setText(Library.getCurrentLoggedInUser().getUserName());
		fineText.setText("£" + String.format("%.2f", Library.getCurrentLoggedInUser().getAccountBalanceDouble()));
		setCurrentlyBorrowed();
	}

	/**
	 * This method will updated the ListView of currently borrowed resources.
	 */
	private void setCurrentlyBorrowed() {
		ArrayList<String> borrowedResources = Library.getCurrentLoggedInUser().getCurrentlyBorrowedResources();
		for (String resource : borrowedResources) {
			Resource r = Library.getResource(resource);
			borrowedItemsList.getItems().add("Resource ID: " + r.getUniqueID() + " - " + r.getTitle());
		}
	}

	/**
	 * Searches the library database for results matching full/partial information
	 * entered in the toolbar at the top of the screen.
	 * @param actionEvent
	 * The pressing of the "Enter" key on the user's keyboard.
	 */
    public void onEnter(ActionEvent actionEvent) {
		Library.setSearchStringText(searchBar.getText());
		ScreenManager.changeScreen(new SearchResultScreen());
    }
}
