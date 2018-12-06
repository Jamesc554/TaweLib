package screen;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import library.Library;
import resources.Book;
import resources.CopyData;
import resources.DVD;
import resources.Resource;

/**
 * This class represents the search results screen, a list of Resources which
 * are shown as a result of searching for resources.
 * 
 * @author Etienne Badoche, James Carter
 * @version 1.0
 */
public class SearchResultScreen extends Screen implements Initializable {

	@FXML
	private ComboBox<String> resourceTypeCB;

	@FXML
	private VBox resourcesVBox;

	@FXML
	private ImageView resourceThumbnailImage;

	@FXML
	private Label titleLbl;

	@FXML
	private Label uIDLbl;

	@FXML
	private Label yearLbl;

	@FXML
	private Label rs1Lbl;

	@FXML
	private Label rs2Lbl;

	@FXML
	private Label rs3Lbl;

	@FXML
	private Label rs4Lbl;

	@FXML
	private Label rs5Lbl;
	
	@FXML
	private TextField titleTf;
	@FXML
	private TextField uIDTf;
	@FXML
	private TextField yearTf;
	@FXML
	private TextField rs1Tf;
	@FXML
	private TextField rs2Tf;
	@FXML
	private TextField rs3Tf;
	@FXML
	private TextField rs4Tf;
	@FXML
	private TextField rs5Tf;
	
	@FXML
	private Button borrowButton;
	
	//private TextField[] textFields = {titleTf, uIDTf, yearTf, rs1Tf, rs2Tf, rs3Tf, rs4Tf, rs5Tf};

	@FXML
	private ListView<String> copiesList;

	@Override
	public void start() {
		Pane root;
		try {
			root = FXMLLoader.load(getClass().getResource("fxml/SearchResultScreen.fxml"));
			ScreenManager.setCurrentScene(new Scene(root, 1280, 720));
			// setupEvents();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	@FXML
	protected void searchButton(Event event) {
		updateSearchResults();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(Library.getCurrentLoggedInUser().getProfImage()));
		} catch (IOException e) {

		}

		resourceTypeCB.getItems().setAll("Book", "DVD", "Laptop");
		resourceTypeCB.setValue("Book");

		userIcon.setImage(SwingFXUtils.toFXImage(img, null));
		usernameText.setText(Library.getCurrentLoggedInUser().getUserName());
		updateSearchResults();

		searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
			updateSearchResults();
		});

	}

	/**
	 * Show more information for a particular Resource.
	 */
	public void showMoreDetails() {

	}

	/**
	 * Event handling for requesting a Copy when none are available.
	 */
	public void requestCopy() {

	}
	
	public void borrowResource() {
		Library.requestResource(uIDTf.getText());
	}

	@FXML
	private void updateSearchResults() {
		// Empty the current search results
		resourcesVBox.getChildren().clear();

		// Check the search bar
		String searchString = searchBar.getText();
		String resourceType = resourceTypeCB.getValue();
		List resources = null;

		switch (resourceType) {
		case "Book":
			resources = Library.getAllBooks();
			break;
		case "DVD":
			resources = Library.getAllDVD();
			break;
		case "Laptop":
			resources = Library.getAllLaptops();
			break;
		default:
			break;
		}

		for (Object r : resources) {
			Resource rs = (Resource) r;
			if (rs.getTitle().toLowerCase().contains(searchString.toLowerCase())) {
				resourcesVBox.getChildren().add(createResourceContainer(rs));
			}
		}
	}

	private HBox createResourceContainer(Resource r) {
		ImageView imgV = createImageViewForResource(r);

		Text title = new Text("Title:" + r.getTitle());
		Text uniqueID = new Text("Unique ID: " + r.getUniqueID());
		Text year = new Text("Year: " + r.getYear());
		VBox details = new VBox(title, uniqueID, year);

		HBox container = new HBox(imgV, details);
		container.setOnMouseEntered(mouse -> {
			updateResourceDetails(r);
		});

		return container;
	}

	private void updateResourceDetails(Resource r) {
		resourceThumbnailImage.setImage(getResourceImage(r));
		titleTf.setText(r.getTitle());
		uIDTf.setText( r.getUniqueID());
		yearTf.setText(r.getYear());

		String resourceType = resourceTypeCB.getValue();

		rs4Lbl.setVisible(true);
		rs4Tf.setVisible(true);
		rs5Lbl.setVisible(true);
		rs5Tf.setVisible(true);
		
		TextField[] textFields = {titleTf, uIDTf, yearTf, rs1Tf, rs2Tf, rs3Tf, rs4Tf, rs5Tf};
		
		if (!Library.currentUserIsLibrarian()) {
			for (TextField tf : textFields) {
				tf.setEditable(true);
			}
		} else {
			for (TextField tf : textFields) {
				tf.setEditable(false);
			}
		}
		
		copiesList.getItems().clear();
		
		for (CopyData copy : r.getArrayListOfCopies()) {
			copiesList.getItems().add("Copy: " + copy + " - Available: " + String.valueOf(copy.isAvailable()));
		}

		switch (resourceType) {
		case "Book":
			Book b = (Book) r;
			rs1Lbl.setText("Author: ");
			rs1Tf.setText(b.getAuthor());
			rs2Lbl.setText("Publisher: ");
			rs2Tf.setText(b.getPublisher());
			rs3Lbl.setText("Genre: ");
			rs3Tf.setText(b.getGenre());
			rs4Lbl.setText("Director: ");
			rs4Tf.setText(b.getIsbn());
			
			rs5Lbl.setText("Languages: ");

			rs5Tf.setText(b.getLanguages().get(0));
			ArrayList<String> languages = b.getLanguages();
			languages.remove(0);
			for (String language : languages)
				rs5Tf.setText(rs5Tf.getText() + ", " + language);

			break;
		case "DVD":
			DVD d = (DVD) r;
			rs1Lbl.setText("Director: ");
			rs1Tf.setText(d.getDirector());
			rs2Lbl.setText("Runtime: ");
			rs2Tf.setText(d.getRuntime());
			rs3Lbl.setText("Language: ");
			rs3Tf.setText(d.getLanguage());
			
			rs4Lbl.setText("Sub-Languages: ");

			if (d.getSubLang().isEmpty()) {
				rs4Tf.setText("N/A");
			} else {
				rs4Tf.setText( d.getSubLang().get(0));
				languages = d.getSubLang();
				languages.remove(0);
				for (String language : languages)
					rs4Tf.setText(rs4Tf.getText() + ", " + language);
			}

			rs5Tf.setVisible(false);
			rs5Lbl.setVisible(false);

			break;
		case "Laptop":
			break;
		default:
			break;
		}
	}

	private ImageView createImageViewForResource(Resource r) {
		System.out.println(r.getThumbnailImageRef());
		ImageView imgV = new ImageView();

		imgV.setImage(getResourceImage(r));

		return imgV;
	}

	private WritableImage getResourceImage(Resource r) {
		try {
			return SwingFXUtils.toFXImage(ImageIO.read(new File(r.getThumbnailImageRef())), null);
		} catch (IOException e) {
			System.out.println("Could not load Image for Resource: " + r.getUniqueID());
		}
		return null;
	}

}
