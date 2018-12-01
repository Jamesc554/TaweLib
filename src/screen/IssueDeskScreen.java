package screen;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import library.Library;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class represents the Issue Desk, a screen only available to Librarians to authorise payments and loans.
 * @author Etienne Badoche
 * @version 1.0
 */
public class IssueDeskScreen extends Screen implements Initializable {

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
    private TextField loanUsername;
    @FXML
    private TextField loanResourceId;
    @FXML
    private Button loanBtn;
    @FXML
    private Label loanUserError;
    @FXML
    private Label loanResourceError;
    @FXML
    private Label loanSuccess;
    @FXML
    private ChoiceBox loanResourceType;
    @FXML
    private TextField paymentUsername;
    @FXML
    private TextField paymentAmount;
    @FXML
    private Button paymentBtn;
    @FXML
    private Label paymentUserError;
    @FXML
    private Label paymentAmountError;
    @FXML
    private Label paymentSuccess;
    @FXML
    private TextField userUsername;
    @FXML
    private TextField userFirstName;
    @FXML
    private TextField userLastName;
    @FXML
    private TextField userMobile;
    @FXML
    private TextField userAddr1;
    @FXML
    private TextField userAddr2;
    @FXML
    private TextField userPstCd;
    @FXML
    private TextField userTown;
    @FXML
    private Button createUserBtn;

    @Override
    public void start() {
        Pane root;
        try {
            root = FXMLLoader.load(getClass().getResource("fxml/IssueDesk.fxml"));
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

        //TODO: Change to only librarians once librarian is added
        if (!Library.currentUserIsLibrarian()) {
            issueDeskBtn.setVisible(true);
        }

        userIcon.setImage(SwingFXUtils.toFXImage(img, null));
        usernameText.setText(Library.getCurrentLoggedInUser().getUserName());
    }

    @FXML
    private void searchButton(Event e) {
        ScreenManager.changeScreen(new SearchResultScreen());
    }

    @FXML
    private void homeButton(Event e) {
        ScreenManager.changeScreen(new HomeScreen());
    }

    @FXML
    private void drawAppButton(Event e) {
        ScreenManager.changeScreen(new DrawApp());
    }

    @FXML
    private void logoutButton(Event e) {
        logout();
    }
    
    @FXML
	private void accountDeskButton(Event event) {
		ScreenManager.changeScreen(new AccountScreen());
	}

    /**
     * Event handling to process payments.
     * @param e the JavaFX event event
     */
    @FXML
    private void paymentButton(Event e) {
        String user = paymentUsername.getText();

        //Reset all error/success labels
        paymentAmountError.setVisible(false);
        paymentUserError.setVisible(false);
        paymentSuccess.setVisible(false);

        if (Library.checkForUser(user)) {
            try {
                int balance = Integer.parseInt(paymentAmount.getText()) * 100;  //Convert pounds to pence
                Library.subtractBalance(balance, paymentUsername.getText());
                paymentSuccess.setVisible(true);

            } catch (IllegalArgumentException ex) {
                paymentAmountError.setVisible(true);
            }
        } else {
            paymentUserError.setVisible(true);
        }
    }

    /**
     * Event handling to process loans.
     * @param e the JavaFX event
     */
    @FXML
    private void loanButton(Event e) {
        String user = loanUsername.getText();
        String rID = loanResourceId.getText();
        String type = (String) loanResourceType.getValue();

        //Reset all error/success labels
        loanUserError.setVisible(false);
        loanResourceError.setVisible(false);
        loanSuccess.setVisible(false);

        if (Library.checkForUser(user)) {
            try {
                Library.getResourceById(type, rID);  //Throws NullPointerException if ID is not found
                Library.loanResource(user, rID);
                loanSuccess.setVisible(true);
            } catch (NullPointerException ex) {
                System.out.println(ex);
                loanResourceError.setVisible(true);
            }
        } else {
            loanUserError.setVisible(true);
        }
    }

    /**
     * Event handling for User creation.
     */
    @FXML
    private void createUserButton(Event e) {
        Library.addUser(userUsername.getText(), userFirstName.getText(), userLastName.getText(), userMobile.getText(),
                userAddr1.getText(), userAddr2.getText(), userPstCd.getText(), userTown.getText(), 0,
                "./data/images/testUser/testImg32.png");
    }

    /**
     * Event handling for Resource creation.
     */
    public void createResource() {

    }
}
