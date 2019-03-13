package screen;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import library.Library;
import resources.Book;
import resources.CopyData;
import resources.DVD;
import resources.Laptop;
import resources.Resource;
import resources.VideoGame;
import user.User;

/**
 * <h1>HomeScreen.</h1>
 * <p>
 * This class represents the Home screen. This is first screen the User sees
 * after logging in.
 * </p>
 * 
 * @author James Carter, Sam Jankinson, Ammar Alamri
 * @version 1.0
 */
public class HomeScreen extends Screen implements Initializable {
	// TOP TOOL BAR - COMMON BETWEEN SCREENS - COPY FROM HERE - MAKE SURE THE IDs IN
	// SCENEBUILDER ARE OF THE SAME NAME AS THE VARIBLES HERE!!!!!
	@FXML
	private Text fineText;

	@FXML
	private ListView borrowedItemsList;
	
	@FXML
	private ListView recentlyAddedList;

	@FXML
	private TableView<BorrowTableData> borrowTable;
	
	@FXML
	private TableColumn<BorrowTableData, String> rIDCol;
	
	@FXML
	private TableColumn<BorrowTableData, String> rTitleCol;
	
	@FXML
	private TableColumn<BorrowTableData, String> borrowDateCol;
	
	@FXML
	private TableColumn<BorrowTableData, String> returnDateCol;
	
	@FXML
	private TableColumn<BorrowTableData, String> overdueCol;

	@Override
	/**
	 * This method changes the screen manager to the HomeScreen.
	 */
	public void start() {
		Pane root;
		try {
			root = FXMLLoader.load(getClass().getResource("fxml/home.fxml"));
			ScreenManager.setCurrentScene(new Scene(root, 1280, 720));
			// setupEvents();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	/**
	 * Initialises the scene.
	 * 
	 * @param arg0 The location of the root object.
	 * @param arg1 The resource used to localise the root object.
	 */
	public void initialize(URL arg0, ResourceBundle arg1) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(Library.getCurrentLoggedInUser().getProfImage()));
		} catch (IOException e) {

		}

		rIDCol.setCellValueFactory(new PropertyValueFactory<BorrowTableData, String>("resourceID"));
		rTitleCol.setCellValueFactory(new PropertyValueFactory<BorrowTableData, String>("resourceTitle"));
		borrowDateCol.setCellValueFactory(new PropertyValueFactory<BorrowTableData, String>("borrowDate"));
		returnDateCol.setCellValueFactory(new PropertyValueFactory<BorrowTableData, String>("returnDate"));
		overdueCol.setCellValueFactory(new PropertyValueFactory<BorrowTableData, String>("overdue"));

		if (Library.currentUserIsLibrarian()) {
			issueDeskBtn.setVisible(true);
		}

		User loggedInUser = Library.getCurrentLoggedInUser();

		userIcon.setImage(SwingFXUtils.toFXImage(img, null));
		usernameText.setText(loggedInUser.getUserName());
		fineText.setText("£" + String.format("%.2f", loggedInUser.getAccountBalanceDouble()));
		setCurrentlyBorrowed();
		setRecentlyAdded();
		updateBorrowTable(loggedInUser);

		System.out.println(loggedInUser.getUserName());
		System.out.println(loggedInUser.getResourcesBorrowStats()[0].size());
		System.out.println(loggedInUser.getResourcesBorrowStats()[1].size());
		System.out.println(loggedInUser.getResourcesBorrowStats()[2].size());
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
	
	private void setRecentlyAdded() {
		ArrayList<Book> recentlyAddedBook = Library.getAllBooks();
		ArrayList<Laptop> recentlyAddedLaptop = Library.getAllLaptops();
		ArrayList<DVD> recentlyAddedDVD = Library.getAllDVD();
		ArrayList<VideoGame> recentlyAddedVideoGame = Library.getAllVideoGames();
		
		for (Book b : recentlyAddedBook) {
			Date bookDate = null;
			try {
				bookDate = new SimpleDateFormat("dd/MM/yyyy").parse(b.getDateAdded());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(bookDate.compareTo(Library.getCurrentLoggedInUser().getLastLogin()) > 0) {
				recentlyAddedList.getItems().add("Book:" + " " + b.getTitle());
				System.out.println("It worked");
			}
		}
		
		for (Laptop l : recentlyAddedLaptop) {
			Date laptopDate = null;
			try {
				laptopDate = new SimpleDateFormat("dd/MM/yyyy").parse(l.getDateAdded());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(laptopDate.compareTo(Library.getCurrentLoggedInUser().getLastLogin()) > 0) {
				recentlyAddedList.getItems().add("Laptop:" + " " + l.getTitle());
				System.out.println("It worked");
			}
		}
		
		for (DVD d : recentlyAddedDVD) {
			Date dvdDate = null;
			try {
				dvdDate = new SimpleDateFormat("dd/MM/yyyy").parse(d.getDateAdded());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(dvdDate.compareTo(Library.getCurrentLoggedInUser().getLastLogin()) > 0) {
				recentlyAddedList.getItems().add("DVD:" + " " + d.getTitle());
				System.out.println("It worked");
			}
		}
		
		for (VideoGame v : recentlyAddedVideoGame) {
			Date videoGameDate = null;
			try {
				videoGameDate = new SimpleDateFormat("dd/MM/yyyy").parse(v.getDateAdded());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(videoGameDate.compareTo(Library.getCurrentLoggedInUser().getLastLogin()) > 0) {
				recentlyAddedList.getItems().add("Book:" + " " + v.getTitle());
				System.out.println("It worked");
			}
		}
		
		
	}

	private void updateBorrowTable(User user) {
		ArrayList<String> borrowedResources = user.getCurrentlyBorrowedResources();
		for (String resource : borrowedResources) {
			Resource r = Library.getResource(resource);
			CopyData c = null;
			for (CopyData copy : r.getArrayListOfCopies()) {
				if (copy.getCurrentInfo().getUserID().contentEquals(user.getUserName())) {
					c = copy;
				}
			}

			String id = r.getUniqueID();
			String title = r.getTitle();
			String borrowDate = "";
			String returnDate = "";
			String overdue = "No";

			if (c != null) {
				borrowDate = c.getCurrentInfo().getDateBorrowed();
				returnDate = c.getCurrentInfo().getDateRequestedReturn();

				if (Library.findAllOverdue().contains(r.getUniqueID() + "-" + c.getId())) {
					overdue = "Yes";
				}
			}
			
			BorrowTableData btd = new BorrowTableData(id, title, borrowDate, returnDate, overdue);

			borrowTable.getItems().add(btd);
		}
	}

	/**
	 * Searches the library database for results matching full/partial information
	 * entered in the toolbar at the top of the screen.
	 * 
	 * @param actionEvent The pressing of the "Enter" key on the user's keyboard.
	 */
	public void onEnter(ActionEvent actionEvent) {
		Library.setSearchStringText(searchBar.getText());
		ScreenManager.changeScreen(new SearchResultScreen());
	}
}
