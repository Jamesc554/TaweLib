package screen;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import library.Library;
import user.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StatsScreen extends Screen implements Initializable {

    public BarChart statsChart;
    public CategoryAxis statsCataAxis;
    public NumberAxis statsNumAxis;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        User currentUser = Library.getCurrentLoggedInUser();
        XYChart.Data dayData = new XYChart.Data<String, Number>("Past Day", currentUser.getResourcesBorrowStats()[0].size());
        XYChart.Data weekData = new XYChart.Data<String, Number>("Past Week", currentUser.getResourcesBorrowStats()[1].size());
        XYChart.Data monthData = new XYChart.Data<String, Number>("Past Month", currentUser.getResourcesBorrowStats()[2].size());

        series.getData().add(dayData);
        series.getData().add(weekData);
        series.getData().add(monthData);

        statsChart.getData().add(series);
    }

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
