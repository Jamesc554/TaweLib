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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import library.Library;
import resources.Book;
import resources.DVD;
import resources.Laptop;
import resources.Resource;

/**
 * This class represents the Resource screen, which shows detailed information on a particular Resource.
 * @author Etienne Badoche, Peter Daish
 * @version 1.0
 */
public class ResourceScreen extends Screen implements Initializable{
		
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
		private HBox bookHBox;
		
		@FXML
		private HBox dvdHBox;
		
		@FXML
		private HBox laptopHBox;
		
		//This classes specific attributes.
		@FXML
		private Button editNumOfCopies; //button which handles the addition and deletion of copies.
		
		@FXML
		private Button addCopy;
		
		@FXML
		private Button editResource; //button which allows user to edit a resource's details.
		
		private Object res = null; // this resource must be passed in to this class.
		
		@Override
		public void start() {
			Pane root;
			try {
				root = FXMLLoader.load(getClass().getResource("fxml/Resource.fxml"));
				ScreenManager.setCurrentScene(new Scene(root, 1280, 720));
				// setupEvents();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@FXML
		private void drawAppButton(Event e) {
			ScreenManager.changeScreen(new DrawApp());
		}
		
		@FXML
		private void searchButton(Event e) {
			ScreenManager.changeScreen(new SearchResultScreen());
		}

		@FXML
		private void logoutButton(Event e) {
			logout();
		}
		@FXML
		private void homeButton(Event e) {
			ScreenManager.changeScreen(new HomeScreen());
		}

		@FXML
		private void accountButton(Event e) {
			//ScreenManager.changeScreen(new AccountScreen());
		}

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			BufferedImage img = null;
			try {
				img = ImageIO.read(new File(Library.getCurrentLoggedInUser().getProfImage()));
			} catch (IOException e) {
				e.printStackTrace();
			}

			userIcon.setImage(SwingFXUtils.toFXImage(img, null));
			usernameText.setText(Library.getCurrentLoggedInUser().getUserName());
		}
    
    /**
     * Event handling for editing a Resource.
     */
    public void editResource() {
    	//Edit the specific details of a resource i.e. Author, genre etc.

    }

    /**
     * Event handling for editing number of copies for a Resource.
     */
    public void editNumOfCopies() {
    	//ability to add a copy
    	//ability to delete a copy.
    }
    
    /**
     * Adds a copy to current resource
     */
    public void addCopy() {
    	//if object is a "Book" then add copy to book's copy arraylist. Same for if DVD or Laptop.
    	if(res.getClass()== Book.class) {
    		((Book) res).addToCopies();
    	} else if (res.getClass() == DVD.class) {
    		((DVD) res).addToCopies();
    	} else if (res.getClass() == Laptop.class) {
    		((Laptop) res).addToCopies();
    	}
    	
    }
}
