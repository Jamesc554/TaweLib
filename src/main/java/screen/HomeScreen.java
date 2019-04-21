package screen;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import library.Library;
import library.LibraryResources;
import library.Book;
import library.CopyData;
import library.DVD;
import library.Laptop;
import library.Resource;
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
			root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/home.fxml"));
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
	
	/**
	 * This method reads in all resources within the library and compares
	 * each date that they were added to the last login date of the user 
	 * If the date the resource was added is greater than the date the 
	 * user last logged in then the resource is displayed within the 
	 * recently added window on the home screen.
	 */
	private void setRecentlyAdded() {
		List<Resource> resources = LibraryResources.getAllResources();
		
		for (Resource r : resources) {
			Date bookDate = null;
			//Get the date the resource was added, stored as string so we have to parse to a Date object
			try {
				bookDate = new SimpleDateFormat("dd/MM/yyyy").parse(r.getDateAdded());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			//Date one comes after date two
			if(bookDate.compareTo(Library.getCurrentLoggedInUser().getLastLogin()) > 0) {
				if(r instanceof Book) {
					recentlyAddedList.getItems().add("Book:" + " " + r.getTitle());
				} else if (r instanceof Laptop) {
					recentlyAddedList.getItems().add("Laptop:" + " " + r.getTitle());
				} else if (r instanceof DVD) {
					recentlyAddedList.getItems().add("DVD:" + " " + r.getTitle());
				} else {
					recentlyAddedList.getItems().add("Videogame:" + " " + r.getTitle());
				}
			}
		}
		
		//Update the date the user last logged in
		Date date = Calendar.getInstance().getTime();
	    Library.getCurrentLoggedInUser().setLastLogin(date);
		
	}

	/**
	 * Updates the borrow table date
	 * @param user user object
	 */
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
