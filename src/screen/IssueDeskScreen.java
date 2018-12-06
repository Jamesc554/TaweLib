package screen;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
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
    private TextField loanCopyID;
    @FXML
    private Label loanUserError;
    @FXML
    private Label loanCopyError;
    @FXML
    private Label loanSuccess;
    @FXML
    private Label outstandingFineMsg;
    @FXML
    private Label overdueCopyMsg;
    @FXML
    private TextField returnUsername;
    @FXML
    private ListView userBorrowList;
    @FXML
    private Label returnSearchError;
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
    private Text paymentSearchBalance;
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
    private ImageView userAvatar;
    @FXML
    private Text userAvatarName;
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
    private TextField bookNumCopies;
    @FXML
    private Label bookSuccess;
    @FXML
    private Label bookError;
    @FXML
    private Label bookCopiesError;
    @FXML
    private Text bookImgName;
    @FXML
    private ImageView bookImg;
    @FXML
    private TextField book1Day;
    @FXML
    private TextField book1Week;
    @FXML
    private TextField book2Weeks;
    @FXML
    private TextField book4Weeks;
    @FXML
    private Label bookDurationError;
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
    private TextField dvdNumCopies;
    @FXML
    private TextField dvd1Day;
    @FXML
    private TextField dvd1Week;
    @FXML
    private TextField dvd2Weeks;
    @FXML
    private TextField dvd4Weeks;
    @FXML
    private Label dvdCopiesError;
    @FXML
    private Label dvdError;
    @FXML
    private Label dvdSuccess;
    @FXML
    private Label dvdDurationError;
    @FXML
    private Text dvdImgName;
    @FXML
    private ImageView dvdImg;
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
    private TextField laptopNumCopies;
    @FXML
    private TextField laptop1Day;
    @FXML
    private TextField laptop1Week;
    @FXML
    private TextField laptop2Weeks;
    @FXML
    private TextField laptop4Weeks;
    @FXML
    private Label laptopError;
    @FXML
    private Label laptopSuccess;
    @FXML
    private Label laptopCopiesError;
    @FXML
    private Label laptopDurationError;
    @FXML
    private Text laptopImgName;
    @FXML
    private ImageView laptopImg;


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

        //Check Library if user exists
        if (Library.checkForUser(user)) {
            //User must not be current user
            if (!Library.getUser(user).equals(Library.getCurrentLoggedInUser())) {
                try {
                    int balance = Integer.parseInt(paymentAmount.getText());
                    Library.subtractBalance(balance, paymentUsername.getText());
                    paymentSuccess.setVisible(true);
                    //Exceptions thrown if negative amount or amount more than account balance
                } catch (IllegalArgumentException ex) {
                    paymentAmountError.setVisible(true);
                }
            } else {
                paymentUserError.setVisible(true);
            }
        } else {
            paymentUserError.setVisible(true);
        }
    }

    @FXML
    private void paymentSearchButton(Event e) {
        String user = paymentUsername.getText();

        //Reset error label
        paymentUserError.setVisible(false);

        //Check if user exists
        if (Library.checkForUser(user)) {
            String balance = Library.getUser(user).getAccountBalanceString();
            paymentSearchBalance.setText("Current balance: " + balance);
            paymentSearchBalance.setVisible(true);
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
        String id = loanCopyID.getText();

        //Reset all error/success labels
        loanUserError.setVisible(false);
        loanCopyError.setVisible(false);
        loanSuccess.setVisible(false);
        outstandingFineMsg.setVisible(false);
        //overdueCopyMsg.setVisible(false);

        //Check Library if user exists
        if (Library.checkForUser(user)) {
            //User must not be current user
            if (!Library.getUser(user).equals(Library.getCurrentLoggedInUser())) {
                //Check if user has no outstanding balance
                if (Library.getUser(user).getAccountBalanceDouble() == 0) {
                    //Check if Resource ID is valid
                    if (Library.getResource(id.split("-")[0]) != null) {
                        //TODO: Check if user has overdue copies
                        Library.loanResource(user, id);
                        loanSuccess.setVisible(true);
                    } else {
                        loanCopyError.setVisible(true);
                    }
                } else {
                    outstandingFineMsg.setVisible(true);
                }
            } else {
                loanUserError.setVisible(true);
            }
        } else {
            loanUserError.setVisible(true);
        }
    }

    /**
     * Event handling to return a searched user's currently borrowed items
     * @param e the JavaFX event
     */
    @FXML
    private void returnSearchButton(Event e) {
        String user = returnUsername.getText();

        //Reset all error/success labels
        returnSearchError.setVisible(false);
        returnSuccess.setVisible(false);

        //Empty list view
        userBorrowList.getItems().clear();
        //Check Library if user exists
        if (Library.checkForUser(user)) {
            //User must not be current user
            if (!Library.getUser(user).equals(Library.getCurrentLoggedInUser())) {
                //Get list of borrowed copies
                ArrayList<String> borrowList = Library.getUser(user).getCurrentlyBorrowedResources();
                for (String item : borrowList) {
                    userBorrowList.getItems().add(item);
                }
            } else {
                returnSearchError.setVisible(true);
            }
        } else {
            returnSearchError.setVisible(true);
        }
    }
    /**
     * Event handling to process returns
     * @param e the JavaFX event
     */
    @FXML
    private void returnButton(Event e) {
        String user = returnUsername.getText();
        int selectedIdx = userBorrowList.getSelectionModel().getSelectedIndex();

        //Reset all error/success labels
        returnSuccess.setVisible(false);
        outstandingFineMsg.setVisible(false);

        if (selectedIdx != -1) {
            String id = userBorrowList.getSelectionModel().getSelectedItem().toString();
            Library.returnResource(user, id);
            userBorrowList.getItems().remove(selectedIdx);
            returnSuccess.setVisible(true);
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
        String avatar = userAvatarName.getText();

        //Reset all error/success labels
        userUsernameError.setVisible(false);
        userError.setVisible(false);
        userSuccess.setVisible(false);

        //Set avatar to default if not selected
        if (avatar.equals("")) {
            avatar = "default_image_1.png";
        }

        //Check if username not already used
        if (!Library.checkForUser(username)) {
            //Check all required fields have inputs
            if (username.equals("") || firstName.equals("") || lastName.equals("") || mobileNum.equals("")
                || address1.equals("") || address2.equals("") || postCode.equals("") || town.equals("")) {
                userError.setVisible(true);
            } else {
                Library.addUser(username, firstName, lastName, mobileNum, address1, address2, postCode, town,
                        0, "./data/images/default/" + avatar);
                userSuccess.setVisible(true);
            }
        } else {
            userUsernameError.setVisible(true);
        }
    }

    @FXML
    private void userAvatarButton(Event e) {
        try {
            File selectedFile = getImageFile("default");
            userAvatarName.setText(selectedFile.getName());
            BufferedImage img = null;
            try {
                img = ImageIO.read(selectedFile);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            userAvatar.setImage(SwingFXUtils.toFXImage(img, null));
        } catch (NullPointerException ex) {
            System.out.println("No book image file selected");
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
        String imageName = bookImgName.getText();

        //Reset error/success labels
        bookSuccess.setVisible(false);
        bookError.setVisible(false);
        bookCopiesError.setVisible(false);
        bookDurationError.setVisible(false);

        //Check if required fields have input
        if (title.equals("") || author.equals("") || year.equals("") || publisher.equals("") || imageName.equals("")) {
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
            int numCopies;
            int num1Day;
            int num1Week;
            int num2Weeks;
            int num4Weeks;
            try {
                //Add the book to the Library
                String image = "./data/images/book/" + imageName;
                if (bookNumCopies.getText().equals("")) {
                    numCopies = 0;
                } else {
                    numCopies = Integer.parseInt(bookNumCopies.getText());
                }
                if (book1Day.getText().equals("")) {
                    num1Day = 0;
                } else {
                    num1Day = Integer.parseInt(book1Day.getText());
                }
                if (book1Week.getText().equals("")) {
                    num1Week = 0;
                } else {
                    num1Week = Integer.parseInt(book1Week.getText());
                }
                if (book2Weeks.getText().equals("")) {
                    num2Weeks = 0;
                } else {
                    num2Weeks = Integer.parseInt(book2Weeks.getText());
                }
                if (book4Weeks.getText().equals("")) {
                    num4Weeks = 0;
                } else {
                    num4Weeks = Integer.parseInt(book4Weeks.getText());
                }
                if (numCopies >= 0 && num1Day >= 0 && num1Week >= 0 && num2Weeks >= 0 && num4Weeks >= 0) {
                    if (num1Day + num1Week + num2Weeks + num4Weeks == numCopies) {
                        ArrayList<String> loanDuration = new ArrayList<>();
                        for (int i = 0; i < num1Day; i++) {
                            loanDuration.add("1");
                        }
                        for (int i = 0; i < num1Week; i++){
                            loanDuration.add("7");
                        }
                        for (int i = 0; i < num2Weeks; i++) {
                            loanDuration.add("14");
                        }
                        for (int i = 0; i < num4Weeks; i++) {
                            loanDuration.add("28");
                        }
                        Library.addBook(year, title, image, null, author, genre, isbn, publisher, languages,
                                numCopies, loanDuration);
                        bookSuccess.setVisible(true);
                        bookImgName.setText("");
                    } else {
                        bookDurationError.setVisible(true);
                    }
                } else {
                    bookCopiesError.setVisible(true);
                }
            } catch (NumberFormatException ex) {
                bookCopiesError.setVisible(true);
            }
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
        String imageName = dvdImgName.getText();

        //Reset error/success labels
        dvdError.setVisible(false);
        dvdSuccess.setVisible(false);
        dvdCopiesError.setVisible(false);
        dvdDurationError.setVisible(false);

        //Check if required fields have input
        if (title.equals("") || director.equals("") || year.equals("") || runtime.equals("") || imageName.equals("")) {
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
            int numCopies;
            int num1Day;
            int num1Week;
            int num2Weeks;
            int num4Weeks;
            try {
                //Add the DVD to the Library
                String image = "./data/images/dvd/" + imageName;
                if (dvdNumCopies.getText().equals("")) {
                    numCopies = 0;
                } else {
                    numCopies = Integer.parseInt(dvdNumCopies.getText());
                }
                if (dvd1Day.getText().equals("")) {
                    num1Day = 0;
                } else {
                    num1Day = Integer.parseInt(dvd1Day.getText());
                }
                if (dvd1Week.getText().equals("")) {
                    num1Week = 0;
                } else {
                    num1Week = Integer.parseInt(dvd1Week.getText());
                }
                if (dvd2Weeks.getText().equals("")) {
                    num2Weeks = 0;
                } else {
                    num2Weeks = Integer.parseInt(dvd2Weeks.getText());
                }
                if (dvd4Weeks.getText().equals("")) {
                    num4Weeks = 0;
                } else {
                    num4Weeks = Integer.parseInt(dvd4Weeks.getText());
                }
                if (numCopies >= 0 && num1Day >= 0 && num1Week >= 0 && num2Weeks >= 0 && num4Weeks >= 0) {
                    if (num1Day + num1Week + num2Weeks + num4Weeks == numCopies) {
                        ArrayList<String> loanDuration = new ArrayList<>();
                        for (int i = 0; i < num1Day; i++) {
                            loanDuration.add("1");
                        }
                        for (int i = 0; i < num1Week; i++){
                            loanDuration.add("7");
                        }
                        for (int i = 0; i < num2Weeks; i++) {
                            loanDuration.add("14");
                        }
                        for (int i = 0; i < num4Weeks; i++) {
                            loanDuration.add("28");
                        }
                        Library.addDVD(year, title, image, null, director, runtime, language, subs, numCopies,
                                loanDuration);
                        dvdSuccess.setVisible(true);
                        dvdImgName.setText("");
                    } else {
                        dvdDurationError.setVisible(true);
                    }
                } else {
                    dvdCopiesError.setVisible(true);
                }
            } catch (NumberFormatException ex) {
                dvdCopiesError.setVisible(true);
            }
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
        String imageName = laptopImgName.getText();

        //Reset error/success labels
        laptopError.setVisible(false);
        laptopSuccess.setVisible(false);
        laptopCopiesError.setVisible(false);
        laptopDurationError.setVisible(false);

        //Check if require fields have input
        if (title.equals("") || year.equals("") || manufacturer.equals("") || model.equals("") || os.equals("")
                || imageName.equals("")) {
            laptopError.setVisible(true);
        } else {
            int numCopies;
            int num1Day;
            int num1Week;
            int num2Weeks;
            int num4Weeks;
            try {
                //Add the Laptop to the Library
                String image = "./data/images/laptop/" + imageName;
                if (laptopNumCopies.getText().equals("")) {
                    numCopies = 0;
                } else {
                    numCopies = Integer.parseInt(laptopNumCopies.getText());
                }
                if (laptop1Day.getText().equals("")) {
                    num1Day = 0;
                } else {
                    num1Day = Integer.parseInt(laptop1Day.getText());
                }
                if (laptop1Week.getText().equals("")) {
                    num1Week = 0;
                } else {
                    num1Week = Integer.parseInt(laptop1Week.getText());
                }
                if (laptop2Weeks.getText().equals("")) {
                    num2Weeks = 0;
                } else {
                    num2Weeks = Integer.parseInt(laptop2Weeks.getText());
                }
                if (laptop4Weeks.getText().equals("")) {
                    num4Weeks = 0;
                } else {
                    num4Weeks = Integer.parseInt(laptop4Weeks.getText());
                }
                if (numCopies >= 0 && num1Day >= 0 && num1Week >= 0 && num2Weeks >= 0 && num4Weeks >= 0) {
                    if (num1Day + num1Week + num2Weeks + num4Weeks == numCopies) {
                        ArrayList<String> loanDuration = new ArrayList<>();
                        for (int i = 0; i < num1Day; i++) {
                            loanDuration.add("1");
                        }
                        for (int i = 0; i < num1Week; i++){
                            loanDuration.add("7");
                        }
                        for (int i = 0; i < num2Weeks; i++) {
                            loanDuration.add("14");
                        }
                        for (int i = 0; i < num4Weeks; i++) {
                            loanDuration.add("28");
                        }
                        Library.addLaptop(year, title, image, null, manufacturer, model, os, numCopies,
                                loanDuration);
                        laptopSuccess.setVisible(true);
                        laptopImgName.setText("");
                    } else {
                        laptopDurationError.setVisible(true);
                    }
                } else {
                    laptopCopiesError.setVisible(true);
                }
            } catch (NumberFormatException ex) {
                laptopCopiesError.setVisible(true);
            }
        }
    }

    /**
     * Event handling to choose a book thumbnail image
     * @param e the JavaFX event
     */
    @FXML
    private void bookImageButton(Event e) {
        try {
            File selectedFile = getImageFile("book");
            bookImgName.setText(selectedFile.getName());
            BufferedImage img = null;
            try {
                img = ImageIO.read(selectedFile);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            bookImg.setImage(SwingFXUtils.toFXImage(img, null));
        } catch (NullPointerException ex) {
            System.out.println("No book image file selected");
        }
    }

    /**
     * Event handling to choose a dvd thumbnail image
     * @param e the JavaFX event
     */
    @FXML
    private void dvdImageButton(Event e) {
         try {
             File selectedFile = getImageFile("dvd");
             dvdImgName.setText(selectedFile.getName());
             BufferedImage img = null;
             try {
                 img = ImageIO.read(selectedFile);
             } catch (IOException ex) {
                 ex.printStackTrace();
             }
             dvdImg.setImage(SwingFXUtils.toFXImage(img, null));
         } catch (NullPointerException ex) {
             System.out.println("No dvd image file selected");
         }
    }

    /**
     * Event handling to choose a laptop thumbnail image
     * @param e the JavaFX event
     */
    @FXML
    private void laptopImageButton(Event e) {
        try {
            File selectedFile = getImageFile("laptop");
            laptopImgName.setText(selectedFile.getName());
            BufferedImage img = null;
            try {
                img = ImageIO.read(selectedFile);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            laptopImg.setImage(SwingFXUtils.toFXImage(img, null));
        } catch (NullPointerException ex) {
            System.out.println("No laptop image file selected");
        }
    }

    /**
     * Opens a FileChooser in the image directory of the selected type
     * @param type the type of resource for which to choose an image (i.e. book/dvd/laptop)
     * @return a File object correspondng to the selected image (null if cancelled)
     */
    private File getImageFile(String type) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose an image");
        fileChooser.setInitialDirectory(new File("./data/images/" + type));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images files", "*png", "*jpg")
        );
        return fileChooser.showOpenDialog(ScreenManager.getStage());
    }
}
