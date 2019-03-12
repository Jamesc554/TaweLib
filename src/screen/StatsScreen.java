package screen;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;
import library.Library;
import resources.Resource;
import user.User;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

/**
 * <h1>StatsScreen</h1>
 * <p>This is the controller class for the Stats Screen fxml</p>
 *
 * @author James Carter
 * @version 1.0
 */
public class StatsScreen extends Screen implements Initializable {

    public BarChart statsChart; // Bar Chart for User Stats
    public CategoryAxis statsCataAxis;
    public NumberAxis statsNumAxis;
    public PieChart typeChart; // Pie Chart for what types of resources a user takes out
    public BarChart finesChat; // Bar Chart for showing Librarians stats about fines
    public PieChart adminChart; // Pie Chart showing what Librarians pay off fines
    public Tab detailedTab; // The Librarian only tab

    /**
     * Initialises the scene.
     * @param location
     * The location of the root object.
     * @param resources
     * The resource used to localise the root object.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Generic Screen Loading
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(Library.getCurrentLoggedInUser().getProfImage())); // Load the Profile image
        } catch (IOException e) {

        }

        // If the user is a librarian, show the issue desk button
        if (Library.currentUserIsLibrarian()) {
            issueDeskBtn.setVisible(true);
        }

        // Setup the logged in user
        User loggedInUser = Library.getCurrentLoggedInUser();
        assert img != null;
        userIcon.setImage(SwingFXUtils.toFXImage(img, null));
        usernameText.setText(loggedInUser.getUserName());

        // Bar Chart for Borrow Stats
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        User currentUser = Library.getCurrentLoggedInUser();
        XYChart.Data dayData = new XYChart.Data<String, Number>("Past Day", currentUser.getResourcesBorrowStats()[0].size());
        XYChart.Data weekData = new XYChart.Data<String, Number>("Past Week", currentUser.getResourcesBorrowStats()[1].size());
        XYChart.Data monthData = new XYChart.Data<String, Number>("Past Month", currentUser.getResourcesBorrowStats()[2].size());

        List<Resource> resourcesList = new ArrayList<>();
        resourcesList.addAll(Library.getAllBooks());
        resourcesList.addAll(Library.getAllDVD());
        resourcesList.addAll(Library.getAllLaptops());

        for (Resource r : resourcesList){
            XYChart.Data data = new XYChart.Data<String, Number>(r.getUniqueID(), r.getResourceStatData().getTotalTimesBorrowed());
            series.getData().add(data);
        }

        series.getData().addAll(dayData, weekData, monthData);
        statsChart.getData().add(series);

        // Pie Chart for Resource Types
        PieChart.Data bookData = new PieChart.Data("Books", currentUser.getResourceTypeStats()[0]);
        PieChart.Data dvdData = new PieChart.Data("DvDs", currentUser.getResourceTypeStats()[1]);
        PieChart.Data laptopData = new PieChart.Data("Laptops", currentUser.getResourceTypeStats()[2]);
        PieChart.Data videoGameData = new PieChart.Data("Video Games", currentUser.getResourceTypeStats()[3]);

        typeChart.getData().addAll(bookData, dvdData, laptopData, videoGameData);

        // LIBRARIAN ONLY
        if (Library.currentUserIsLibrarian()) {
            HashMap<String, Integer> librarianFineAmount = new HashMap<>();
            XYChart.Series<String, Number> fineSeries = new XYChart.Series<>();

            // For every user in the library
            for (User u : Library.getAllUsers()) {
                int amount = 0;
                // All of their transactions
                for (String[] fineInfo : u.getTransactions()) {
                    // Update the librarian to keep track of how much they've paid off
                    int prevAmount = librarianFineAmount.getOrDefault(fineInfo[0], 0);
                    librarianFineAmount.putIfAbsent(fineInfo[0], 0);
                    int a = (int) Double.parseDouble(fineInfo[2]);
                    librarianFineAmount.replace(fineInfo[0], prevAmount + Math.abs(a));
                    // Keep track of all the fines a usr has
                    if (fineInfo[0].equals("Library"))
                        amount += Math.abs(a);
                }
                XYChart.Data fines = new XYChart.Data<String, Number>(u.getUserName(), amount);
                fineSeries.getData().add(fines);
            }

            finesChat.getData().add(fineSeries);

            // Make a PieChart on how many fines Librarians paid off
            for (String name : librarianFineAmount.keySet()) {
                PieChart.Data libData = new PieChart.Data(name, librarianFineAmount.get(name));
                adminChart.getData().add(libData);
            }
        } else {
            detailedTab.setDisable(true);
        }
    }

    /**
     * This method handles the startup procedure for SearchResultScreen such as displaying the scene.
     */
    @Override
    public void start() {
        Pane root;
        try {
            root = FXMLLoader.load(getClass().getResource("fxml/StatsScreen.fxml"));
            ScreenManager.setCurrentScene(new Scene(root, 1280, 720));
            // setupEvents();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
