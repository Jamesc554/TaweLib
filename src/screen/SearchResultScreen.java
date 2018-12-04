package screen;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import library.Library;
import library.LibraryResources;
import resources.Book;
import resources.DVD;
import resources.Laptop;
import resources.Resource;

/**
 * This class represents the search results screen, a list of Resources which
 * are shown as a result of searching for resources.
 * 
 * @author Etienne Badoche, James Carter
 * @version 1.0
 */
public class SearchResultScreen extends Screen implements Initializable{

	// TOP TOOL BAR - COMMON BETWEEN SCREENS - COPY FROM HERE - MAKE SURE THE IDs IN
	// SCENEBUILDER ARE OF THE SAME NAME AS THE VARIBLES HERE!!!!!
	@FXML
	private HBox bookHBox;
	
	@FXML
	private HBox dvdHBox;
	
	@FXML
	private HBox laptopHBox;
	
	

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
	
	@FXML
	private void updateSearchResults() {
		// Empty the current search results
		bookHBox.getChildren().clear();
		dvdHBox.getChildren().clear();
		laptopHBox.getChildren().clear();
		
		// Check the search bar
		String searchString = searchBar.getText();
		List<Book> books = Library.getAllBooks();
		List<DVD> dvds = Library.getAllDVD();
		List<Laptop> laptops = Library.getAllLaptops();
		
		for (Book b : books) {
			if (b.getTitle().toLowerCase().contains(searchString.toLowerCase())) {
				bookHBox.getChildren().add(createStackPaneForResource(b));
			}
		}

		for (DVD d : dvds) {
			if (d.getTitle().toLowerCase().contains(searchString.toLowerCase())) {
				dvdHBox.getChildren().add(createStackPaneForResource(d));
			}
		}

		for (Laptop l : laptops) {
			if (l.getTitle().toLowerCase().contains(searchString.toLowerCase())) {
				laptopHBox.getChildren().add(createStackPaneForResource(l));
			}
		}
	}
	
	private StackPane createStackPaneForResource(Resource r) {
		ImageView imgV = createImageViewForResource(r);
		Text title = new Text(r.getTitle());
		title.setFont(Font.font("Verdana", 20));
		title.setWrappingWidth(imgV.getImage().getWidth());
		title.setFill(Color.BLACK);
		title.setStroke(Color.WHITE);
		title.setStrokeWidth(0.5D);
		title.setTextAlignment(TextAlignment.CENTER);
		Button btn = new Button("Borrow");
		btn.setOnAction(e -> {
			Library.requestResource(r.getUniqueID());
		});
		
		
		VBox layout = new VBox(title, btn);
		layout.setBackground(new Background(new BackgroundFill(new Color(1D, 1D, 1D, 0.5D), null, null)));
		layout.setAlignment(Pos.CENTER);
		
		layout.setVisible(false);
		
		StackPane sp = new StackPane(imgV, layout);
		sp.setOnMouseEntered(mouse -> {
			layout.setVisible(true);
		});
		sp.setOnMouseExited(mouse -> {
			layout.setVisible(false);
		});
		return sp;
	}
	
	private ImageView createImageViewForResource(Resource r) {
		System.out.println(r.getThumbnailImageRef());
		ImageView imgV = new ImageView();
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(r.getThumbnailImageRef()));
		} catch (IOException e) {

		}

		imgV.setImage(SwingFXUtils.toFXImage(img, null));
		
		
		
		return imgV;
	}
	
}
