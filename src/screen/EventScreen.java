package screen;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import event.Event;
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
import library.LibraryEvents;
import library.LibraryResources;
import resources.Book;
import resources.CopyData;
import resources.DVD;
import resources.Laptop;
import resources.Resource;
import resources.VideoGame;
import user.User;

/**
 * <h1>Event Screen.</h1>
 * <p>
 * This class represents the event screen. This is the screen seen after
 * pressing the events button.
 * </p>
 * 
 * @author Deyan Naydenov, Peter Daish, Dominik R Wojtasiewicz.
 * @version 1.0
 */
@SuppressWarnings("Duplicates")
public class EventScreen extends Screen implements Initializable {
	// TOP TOOL BAR - COMMON BETWEEN SCREENS - COPY FROM HERE - MAKE SURE THE IDs IN
	// SCENEBUILDER ARE OF THE SAME NAME AS THE VARIBLES HERE!!!!!
	@FXML
	private Text fineText;

	@FXML
	private ListView events;
	
	@FXML
	private ListView pastEvents;
	
	@FXML
	TableView<EventTableData> eventTable;
	
	@FXML
	private TableColumn<EventTableData, String> eventID;
	
	@FXML
	private TableColumn<EventTableData, String> title;
	
	@FXML
	private TableColumn<EventTableData, String> date;

	@FXML
	private TableColumn<EventTableData, String> time;

	@FXML
	private TableColumn<EventTableData, String> description;

	@FXML
	private TableColumn<EventTableData, String> eventAttendees;

	@Override
	/**
	 * This method changes the screen manager to the HomeScreen.
	 */
	public void start() {
		Pane root;
		try {
			root = FXMLLoader.load(getClass().getResource("fxml/EventScreen.fxml"));
			ScreenManager.setCurrentScene(new Scene(root, 1280, 720));
			// setupEvents();
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
			System.out.println("IOException"+e.getStackTrace());
		}
		
		//populate tables
		eventID.setCellValueFactory(new PropertyValueFactory<EventTableData, String>("eventID"));
		title.setCellValueFactory(new PropertyValueFactory<EventTableData, String>("title"));
		date.setCellValueFactory(new PropertyValueFactory<EventTableData, String>("date"));
		time.setCellValueFactory(new PropertyValueFactory<EventTableData, String>("time"));
		eventAttendees.setCellValueFactory(new PropertyValueFactory<EventTableData, String>("eventAttendees"));
		description.setCellValueFactory(new PropertyValueFactory<EventTableData, String>("description"));

		User loggedInUser = Library.getCurrentLoggedInUser();
		updateEventTableData(loggedInUser);
		
//		eventID.setCellValueFactory(new PropertyValueFactory<EventTableData, String>("eventID"));
//		title.setCellValueFactory(new PropertyValueFactory<EventTableData, String>("eventName"));
//		date.setCellValueFactory(new PropertyValueFactory<EventTableData, String>("eventDate"));
//		time.setCellValueFactory(new PropertyValueFactory<EventTableData, String>("eventEnd"));
//		eventAttendees.setCellValueFactory(new PropertyValueFactory<EventTableData, String>("eventAttendees"));
//		
		if (Library.currentUserIsLibrarian()) {
			issueDeskBtn.setVisible(true);
		}
		userIcon.setImage(SwingFXUtils.toFXImage(img, null));
		usernameText.setText(loggedInUser.getUserName());
		}

	private void updateEventTableData(User user) {
		ArrayList<Event> listOfEvents = LibraryEvents.getAllEvents();
		for (Event event : listOfEvents) {

			String id = event.getEventID();
			String title = event.getTitle();
			String date = event.getDate();
			String time = event.getTime();
			String eventAttendees = Integer.toString(event.getCurrentNumberOfAttending());
			String description = event.getDescription();
			EventTableData etd = new EventTableData(id, title, date, time, eventAttendees, description);

			eventTable.getItems().add(etd);
		}
	}
	
	public void onEnter(ActionEvent actionEvent) {
		Library.setSearchStringText(searchBar.getText());
		ScreenManager.changeScreen(new SearchResultScreen());
	}
}
