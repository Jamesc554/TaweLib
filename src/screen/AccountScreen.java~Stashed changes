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
import javafx.stage.FileChooser;
import library.Library;
import resources.Resource;

/**
 * <h1>AccountScreen.</h1>
 * <p>This class represents the Account screen. 
 * This screen shows the user their information and resources they have, borrowed, requested, etc.</p>
 * @author Samuel Jankinson, Etienne Badoche, Deyan Naydenov 
 * @version 1.0
 */
public class AccountScreen extends Screen implements Initializable {
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
	private ListView reservedField;
	
	@SuppressWarnings("rawtypes")
	@FXML
	private ListView borrowedField;

	@FXML
	private ListView borrowHistoryField;
	
	@FXML
	private Label changeProfileImageLbl;

	@Override
	/**
	 * This method changes the screen manager to the Account screen, so that the user can see their information.
	 */
	public void start() {
		Pane root;
		try {
			root = FXMLLoader.load(getClass().getResource("fxml/Account.fxml"));
			ScreenManager.setCurrentScene(new Scene(root, 1280, 720));
	        //setupEvents();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	/**
 	 * This method loads all of the resources for the scence and updates their values.
	 * It also calls methods to set the labels texts and listviews items.
     * @param location
     * The location of the root object.
     * @param resources
     * The resources used to localise the root object.
     */
	public void initialize(URL location, ResourceBundle resources) {
		BufferedImage img = null;
        try {
            img = ImageIO.read(new File(Library.getCurrentLoggedInUser().getProfImage()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Library.currentUserIsLibrarian()) {
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
        setBorrowHistoryField();
        setChangeImageLabel();
	}

	/**
	 * This methods sets the username label to the username of the currently logged in user.
	 */
	private void setUsernameLabel() {
		usernameField.setText(Library.getCurrentLoggedInUser().getUserName());
	}

	/**
 	 * This method sets the name label to the first name and last name of the currently logged in user.
     */
    private void setNameLabel() {
		nameField.setText((Library.getCurrentLoggedInUser().getFirstName() + 
				" " + (Library.getCurrentLoggedInUser().getLastName())));
	}

	/**
	 * This method sets the mobile number label to the mobile number of the currently logged in user.
 	 */
	private void setMobileNumberLabel() {
		mobileNumberField.setText((Library.getCurrentLoggedInUser().getMobileNumber()));
	}

	/**
 	 * This method sets the address label to the first line, second line, town and post code of the currently
	 * logged in user.
     */
	@SuppressWarnings("unchecked")
	private void setAddressLabel() {
		addressField.getItems().add(Library.getCurrentLoggedInUser().getFirstLineAddress());
		addressField.getItems().add(Library.getCurrentLoggedInUser().getSecondLineAddress());
		addressField.getItems().add(Library.getCurrentLoggedInUser().getTownName());
		addressField.getItems().add(Library.getCurrentLoggedInUser().getPostCode());
	}

	/**
 	 * This method sets the account balance label to the balance of the currently logged in user.
 	 */
    private void setAccountBalanceLabel() {
		balanceField.setText((Library.getCurrentLoggedInUser().getAccountBalanceString()));
	}

	/**
     * This method sets the profile image ImageView to the profile image of the currently logged in user.
     */
	private void setProfileImage() {
		BufferedImage profileImage = null;
        try {
            profileImage = ImageIO.read(new File(Library.getCurrentLoggedInUser().getProfImage()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        profileImageField.setImage(SwingFXUtils.toFXImage(profileImage, null));
	}

	/**
	 * This method adds each transaction to an item in a ListView.
 	 */
	@SuppressWarnings("unchecked")
	private void setTransactionField() {
		ArrayList<String[]> transactionHistory = Library.getCurrentLoggedInUser().getTransactions();
		for (String[] transaction : transactionHistory) {
			transactionHistoryField.getItems().add("Librarian: " + transaction[0] + 
					" - Date: " + transaction[1] + " - £" + transaction[2]);
		}
	}

	/**
	 * This method adds each resource request to an item in a ListView.
	 */
	@SuppressWarnings("unchecked")
	private void setRequestedField() {
		ArrayList<String> currentlyRequested = Library.getCurrentLoggedInUser().getAllRequested();
		for (String requested : currentlyRequested) {
			Resource r = Library.getResource(requested);
			requestedField.getItems().add("Resource ID: " + r.getUniqueID() + " - " + r.getTitle());
		}
	}

	/**
     * This method adds each reserved resource to an item in a ListView.
     */
	@SuppressWarnings("unchecked")
	private void setReservedField() {
		ArrayList<String> currentlyReserved = Library.getCurrentLoggedInUser().getAllReserved();
		for (String reserved : currentlyReserved) {
			Resource r = Library.getResource(reserved);
			reservedField.getItems().add("Resource ID: " + r.getUniqueID() + " - " + r.getTitle());
		}
	}

	/**
 	 * This method adds each resource borrowed by the currently logged in user to an item in a ListView.
	 */
	@SuppressWarnings("unchecked")
	private void setBorrowedField() {
		ArrayList<String> borrowedResources = Library.getCurrentLoggedInUser().getCurrentlyBorrowedResources();
		for (String resource : borrowedResources) {
			Resource r = Library.getResource(resource);
			borrowedField.getItems().add("Resource ID: " + r.getUniqueID() + " - " + r.getTitle());
		}
	}

	/**
	 * This method adds the users borrow history to an item in a ListView.
	 */
	private void setBorrowHistoryField() {
		ArrayList<String[]> borrowHistory = Library.getCurrentLoggedInUser().getBorrowHistory();
		for (String[] borrow : borrowHistory) {
			String resourceID = borrow[0];
			String date = borrow[1];
			borrowHistoryField.getItems().add("Resource ID: " + resourceID + " - Date: " + date);
		}
	}
	
	/**
	 * This method sets the status label when changing profile image to hidden when screen is opened.
	 */
	private void setChangeImageLabel() {
		changeProfileImageLbl.setVisible(false);
	}
	
	/**
	 * This method changes FXML attributes based on if image change succeeded.
	 */
	private void changeImageSuccess() {
		changeProfileImageLbl.setText("Success!");
		changeProfileImageLbl.setVisible(true);
	}
	
	/**
	 * This method changes FXML attributes based on if image change failed.
	 */
	private void changeImageFail() {
		changeProfileImageLbl.setText("Failed!");
		changeProfileImageLbl.setVisible(true);
	}

	/**
 	 * This method opens a file dialog, that allows the user to pick an image file.
     * @return File which the user picked in the file dialog.
     */
	private File getImageFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose an image");
        fileChooser.setInitialDirectory(new File("./data/images/" +
				Library.getCurrentLoggedInUser().getUserName()));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images files", "*png", "*jpg")
        );
        return fileChooser.showOpenDialog(ScreenManager.getStage());
    }

	/**
	 * This fxml method is called when the user clicks the change profile image button. It allows them to choose
	 * an image and it sets this as their profile image.
 	 */
	@FXML
	private void changeButtonClick() {
		try {
			File selectedFile = getImageFile();
	        BufferedImage img = null;
	        try {
	        	img = ImageIO.read(selectedFile);
	        } catch (IOException ex) {
	        	changeImageFail();
	            ex.printStackTrace();
	        }
	        changeImageSuccess();
	        profileImageField.setImage(SwingFXUtils.toFXImage(img, null));
	        Library.getCurrentLoggedInUser().setProfImage(selectedFile.toString());
		} catch (NullPointerException ex) {
			changeImageFail();
	        System.out.println("No image file selected");
	    }
	}
}
