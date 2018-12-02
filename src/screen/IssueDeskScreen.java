package screen;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import library.Library;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * This class represents the Issue Desk, a screen only available to Librarians to authorise payments and loans, as well
 * as create new users and resources.
 * @author Etienne Badoche
 * @version 1.0
 */
public class IssueDeskScreen extends Screen implements Initializable {

    @FXML
    private TextField loanUsername;
    @FXML
    private TextField resourceId;
    @FXML
    private Label loanUserError;
    @FXML
    private Label resourceError;
    @FXML
    private Label loanSuccess;
    @FXML
    private Label returnSuccess;
    @FXML
    private TextField paymentUsername;
    @FXML
    private TextField paymentAmount;
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
    private Label userUsernameError;
    @FXML
    private Label userError;
    @FXML
    private Label userSuccess;
    @FXML
    private TextField bookTitle;
    @FXML
    private TextField bookAuthor;
    @FXML
    private TextField bookYear;
    @FXML
    private TextField bookPublisher;
    @FXML
    private TextField bookGenre;
    @FXML
    private TextField bookISBN;
    @FXML
    private TextField bookLanguage;
    @FXML
    private Label bookSuccess;
    @FXML
    private Label bookError;
    @FXML
    private TextField dvdTitle;
    @FXML
    private TextField dvdDirector;
    @FXML
    private TextField dvdYear;
    @FXML
    private TextField dvdRuntime;
    @FXML
    private TextField dvdLanguage;
    @FXML
    private TextField dvdSubs;
    @FXML
    private Label dvdError;
    @FXML
    private Label dvdSuccess;
    @FXML
    private TextField laptopTitle;
    @FXML
    private TextField laptopYear;
    @FXML
    private TextField laptopManuf;
    @FXML
    private TextField laptopModel;
    @FXML
    private TextField laptopOS;
    @FXML
    private Label laptopError;
    @FXML
    private Label laptopSuccess;

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
        String rID = resourceId.getText();

        //Reset all error/success labels
        loanUserError.setVisible(false);
        resourceError.setVisible(false);
        loanSuccess.setVisible(false);
        returnSuccess.setVisible(false);

        //Check Library if user exists
        if (Library.checkForUser(user)) {
            //Check if Resource ID is valid
            if (Library.getResource(rID) != null) {
                Library.loanResource(user, rID);
                loanSuccess.setVisible(true);
            } else {
                resourceError.setVisible(true);
            }
        } else {
            loanUserError.setVisible(true);
        }
    }

    /**
     * Event handling to process returns
     * @param e the JavaFX event
     */
    @FXML
    private void returnButton(Event e) {
        String user = loanUsername.getText();
        String rID = resourceId.getText();

        //Reset all error/success labels
        loanUserError.setVisible(false);
        resourceError.setVisible(false);
        loanSuccess.setVisible(false);
        returnSuccess.setVisible(false);

        //Check Library if user exists
        if (Library.checkForUser(user)) {
            //Check if user is currently borrowing the resource
            if (Library.getCurrentLoggedInUser().getResource(rID) != null) {
                Library.returnResource(user, rID);
                returnSuccess.setVisible(true);
            } else {
                resourceError.setVisible(true);
            }
        } else {
            loanUserError.setVisible(true);
        }
    }

    /**
     * Event handling to create a new User
     * @param e the JavaFX event
     */
    @FXML
    private void createUserButton(Event e) {
        String username = userUsername.getText();
        String firstName = userFirstName.getText();
        String lastName = userLastName.getText();
        String mobileNum = userMobile.getText();
        String address1 = userAddr1.getText();
        String address2 = userAddr2.getText();
        String postCode = userPstCd.getText();
        String town = userTown.getText();

        //Reset all error/success labels
        userUsernameError.setVisible(false);
        userError.setVisible(false);
        userSuccess.setVisible(false);

        //Check if username not already used
        if (!Library.checkForUser(username)) {
            //Check all required fields have inputs
            if (username.equals("") || firstName.equals("") || lastName.equals("") || mobileNum.equals("")
                || address1.equals("") || address2.equals("") || postCode.equals("") || town.equals("")) {
                userError.setVisible(true);
            } else {
                Library.addUser(username, firstName, lastName, mobileNum, address1, address2, postCode, town,
                        0, "./data/images/testUser/testImg32.png");
                userSuccess.setVisible(true);
            }
        } else {
            userUsernameError.setVisible(true);
        }
    }

    /**
     * Event handling to create a new Book
     * @param e the JavaFX event
     */
    @FXML
    private void createBookButton(Event e) {
        String title = bookTitle.getText();
        String author = bookAuthor.getText();
        String year = bookYear.getText();
        String publisher = bookPublisher.getText();
        String genre = bookGenre.getText();
        String isbn = bookISBN.getText();
        String languageString = bookLanguage.getText();
        ArrayList<String> languages;

        //Reset error/success labels
        bookSuccess.setVisible(false);
        bookError.setVisible(false);

        //Check if required fields have input
        if (title.equals("") || author.equals("") || year.equals("") || publisher.equals("")) {
            bookError.setVisible(true);
        } else {
            //Set optional fields to null if empty
            if (genre.equals("")) {
                genre = null;
            }
            if (isbn.equals("")) {
                isbn = null;
            }
            if (languageString.equals("")) {
                languages = null;
            } else {
                //Split language input into ArrayList
                String[] languageArray = languageString.split(", ");
                languages = new ArrayList<>(Arrays.asList(languageArray));
                System.out.println(languages);
            }
            //Add the book to the Library
            Library.addBook(year, title, "./data/imgages/book/Book-Default.jpg", null,
                    author, genre, isbn, publisher, languages);
            bookSuccess.setVisible(true);
        }
    }

    /**
     * Event handling to create a new DVD
     * @param e the JavaFX event
     */
    @FXML
    private void createDVDButton(Event e) {
        String title = dvdTitle.getText();
        String director = dvdDirector.getText();
        String year = dvdYear.getText();
        String runtime = dvdRuntime.getText();
        String language = dvdLanguage.getText();
        String subsString = dvdSubs.getText();
        ArrayList<String> subs;

        //Reset error/success labels
        dvdError.setVisible(false);
        dvdSuccess.setVisible(false);

        //Check if required fields have input
        if (title.equals("") || director.equals("") || year.equals("") || runtime.equals("")) {
            dvdError.setVisible(true);
        } else {
            //Set optional fields to null if empty
            if (language.equals("")) {
                language = null;
            }
            if (subsString.equals("")) {
                subs = null;
            } else {
                //Split subtitles input into ArrayList
                String[] subsArray = subsString.split(", ");
                subs = new ArrayList<>(Arrays.asList(subsArray));
            }
            //Add the DVD to the Library
            Library.addDVD(year, title, "./data/images/dvd/DVD-Default.jpg", null,
                    director, runtime, language, subs);
            dvdSuccess.setVisible(true);
        }
    }

    /**
     * Event handling to create a new Laptop
     * @param e the JavaFX event
     */
    @FXML
    private void createLaptopButton(Event e) {
        String title = laptopTitle.getText();
        String year = laptopYear.getText();
        String manufacturer = laptopManuf.getText();
        String model = laptopModel.getText();
        String os = laptopOS.getText();

        //Reset error/success labels
        laptopError.setVisible(false);
        laptopSuccess.setVisible(false);

        //Check if require fields have input
        if (title.equals("") || year.equals("") || manufacturer.equals("") || model.equals("") || os.equals("")) {
            laptopError.setVisible(true);
        } else {
            //Add the Laptop to the Library
            Library.addLaptop(year, title, "./data/images/laptop/Laptop-Default.jpg", null,
                    manufacturer, model, os);
            laptopSuccess.setVisible(true);
        }
    }
}
