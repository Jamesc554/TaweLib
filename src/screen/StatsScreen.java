package screen;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.Pane;
import library.Library;
import resources.Resource;
import user.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class StatsScreen extends Screen implements Initializable {

    public BarChart statsChart;
    public CategoryAxis statsCataAxis;
    public NumberAxis statsNumAxis;
    public PieChart typeChart;
    public BarChart finesChat;
    public PieChart adminChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Bar Chart
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

        // Pie Chart
        PieChart.Data bookData = new PieChart.Data("Books", currentUser.getResourceTypeStats()[0]);
        PieChart.Data dvdData = new PieChart.Data("DvDs", currentUser.getResourceTypeStats()[1]);
        PieChart.Data laptopData = new PieChart.Data("Laptops", currentUser.getResourceTypeStats()[2]);
        PieChart.Data videoGameData = new PieChart.Data("Video Games", currentUser.getResourceTypeStats()[3]);

        typeChart.getData().addAll(bookData, dvdData, laptopData, videoGameData);

        HashMap<String, Integer> librarianFineAmount = new HashMap<>();
        XYChart.Series<String, Number> fineSeries = new XYChart.Series<>();

        for (User u : Library.getAllUsers()){
            int amount = 0;
            for (String[] fineInfo : u.getTransactions()){
                int prevAmount = librarianFineAmount.getOrDefault(fineInfo[0], 0);
                librarianFineAmount.putIfAbsent(fineInfo[0], 0);
                int a = (int)Double.parseDouble(fineInfo[2]);
                librarianFineAmount.replace(fineInfo[0], prevAmount + Math.abs(a));
                if (fineInfo[0].equals("Library"))
                    amount += Math.abs(a);
            }
            XYChart.Data fines = new XYChart.Data<String, Number>(u.getUserName(), amount);
            fineSeries.getData().add(fines);
        }

        finesChat.getData().add(fineSeries);

        for (String name : librarianFineAmount.keySet()){
            PieChart.Data libData = new PieChart.Data(name, librarianFineAmount.get(name));
            adminChart.getData().add(libData);
        }
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
