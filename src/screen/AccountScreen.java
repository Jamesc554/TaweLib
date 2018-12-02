package screen;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import library.Library;
import resources.Resource;

/**
 * 
 * @author Samuel Jankinson
 * @version 1.0
 */

public class AccountScreen extends Screen implements Initializable{
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
		private Label usernameField;
		
		@FXML
		private Label nameField;
		
		@FXML
		private Label mobileNumberField;
		
		@FXML
		private Label balanceField;
		
		@SuppressWarnings("rawtypes")
		@FXML
		private ListView addressField;
		
		@FXML
		private ImageView profileImageField;
		
		@FXML
		private Text fineText;
		
		@SuppressWarnings("rawtypes")
		@FXML
		private ListView transactionHistoryField;
		
		@SuppressWarnings("rawtypes")
		@FXML
		private ListView requestedField;
		
		@SuppressWarnings("rawtypes")
		@FXML
		private ListView returnedField;
		
		@SuppressWarnings("rawtypes")
		@FXML
		private ListView borrowedField;
		
		@Override
		public void start() {
			Pane root;
			try {
				root = FXMLLoader.load(getClass().getResource("fxml/Account.fxml"));
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
		
		@FXML
		private void accountDeskButton(Event event) {
			ScreenManager.changeScreen(new AccountScreen());
		}
		
		//TODO: Fix home button
		@FXML
	    private void homeButton(Event event) {
	        ScreenManager.changeScreen(new HomeScreen());
	    }

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			BufferedImage img = null;
	        try {
	            img = ImageIO.read(new File(Library.getCurrentLoggedInUser().getProfImage()));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        //TODO: Change to only librarians once librarian is added
	        if (!Library.currentUserIsLibrarian()) {
	            issueDeskBtn.setVisible(true);
	        }

	        userIcon.setImage(SwingFXUtils.toFXImage(img, null));
	        usernameText.setText(Library.getCurrentLoggedInUser().getUserName());
	        
	        //Set all of the user details.
	        setUsernameLabel();
	        setNameLabel();
	        setMobileNumberLabel();
	        setAddressLabel();
	        setAccountBalanceLabel();
	        setProfileImage();
	        
	        //Set others
	        setTransactionField();
	        setRequestedField();
	        setReservedField();
	        setBorrowedField();
		}
		
		private void setUsernameLabel() {
			usernameField.setText(Library.getCurrentLoggedInUser().getUserName());
		}
		
		private void setNameLabel() {
			nameField.setText((Library.getCurrentLoggedInUser().getFirstName() + " " + (Library.getCurrentLoggedInUser().getLastName())));
		}
		
		private void setMobileNumberLabel() {
			mobileNumberField.setText((Library.getCurrentLoggedInUser().getMobileNumber()));
		}
		
		@SuppressWarnings("unchecked")
		private void setAddressLabel() {
			addressField.getItems().add(Library.getCurrentLoggedInUser().getFirstLineAddress());
			addressField.getItems().add(Library.getCurrentLoggedInUser().getSecondLineAddress());
			addressField.getItems().add(Library.getCurrentLoggedInUser().getTownName());
			addressField.getItems().add(Library.getCurrentLoggedInUser().getPostCode());
		}
		
		private void setAccountBalanceLabel() {
			balanceField.setText((Library.getCurrentLoggedInUser().getAccountBalance()));
		}
		
		private void setProfileImage() {
			BufferedImage profileImage = null;
	        try {
	            profileImage = ImageIO.read(new File(Library.getCurrentLoggedInUser().getProfImage()));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        profileImageField.setImage(SwingFXUtils.toFXImage(profileImage, null));
		}
		
		@SuppressWarnings("unchecked")
		private void setTransactionField() {
			ArrayList<String[]> transactions = Library.getCurrentLoggedInUser().getCurrentlyBorrowedResources();
			for(String[] transaction : transactions) {
				transactionHistoryField.getItems().add(transaction[0] + " " + transaction[1]);
			}
		}
		
		private void setRequestedField() {
			
		}

		private void setReservedField() {
	
		}

		private void setBorrowedField() {
	
		}
}
