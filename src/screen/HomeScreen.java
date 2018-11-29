package screen;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
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
 * This class represents the Home screen, the first screen the User sees after
 * logging in.
 * 
 * @author Etienne Badoche, James Carter
 * @version 1.0
 */
public class HomeScreen extends Screen implements Initializable{

	// TOP TOOL BAR - COMMON BETWEEN SCREENS - COPY FROM HERE - MAKE SURE THE IDs IN
	// SCENEBUILDER ARE OF THE SAME NAME AS THE VARIBLES HERE!!!!!
	@FXML
	private TextField searchBar;

	@FXML
	private Button searchBtn;

	@FXML
	private ImageView userIcon;

	@FXML
	private Text usernameText;

	@FXML
	private Button logoutBtn;

	@FXML
	private Button homeBtn;

	@FXML
	private Button accountBtn;

	@FXML
	private Button issueDeskBtn;

	@FXML
	private Button drawAppBtn;
	
	@FXML
	private Text fineText;
	
	@FXML
	private ListView<Resource> borrowItemsList;

	@Override
	public void start() {
		Pane root;
		try {
			root = FXMLLoader.load(getClass().getResource("fxml/home.fxml"));
			ScreenManager.setCurrentScene(new Scene(root, 1280, 720));
	        //setupEvents();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private void drawAppButton(Event event) {
		ScreenManager.changeScreen(new DrawApp());
	}
	
	@FXML
	private void searchButton(Event event) {
		ScreenManager.changeScreen(new SearchResultScreen());
	}
	
	@FXML
	private void logoutButton(Event event) {
		logout();
	}

	@FXML
	private void issueDeskButton(Event event) {
		ScreenManager.changeScreen(new IssueDeskScreen());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(Library.getCurrentLoggedInUser().getProfImage()));
		} catch (IOException e) {
			
		}

		//TODO: Change to only librarians once librarian is added
		if (!Library.currentUserIsLibrarian()) {
			issueDeskBtn.setVisible(true);
		}
		
		userIcon.setImage(SwingFXUtils.toFXImage(img, null));
		usernameText.setText(Library.getCurrentLoggedInUser().getUserName());
		fineText.setText(Library.getCurrentLoggedInUser().getAccountBalance());
	}

}
