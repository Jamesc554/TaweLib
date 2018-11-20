package screen;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class TestScreen extends Screen {

    @Override
    public void start() {
        components = new ArrayList<>();
        
        VBox content = new VBox(10);

        Button drawAppBtn = new Button();
        drawAppBtn.setText("Go to DrawApp");
        
        drawAppBtn.setOnAction(e->{
        	ScreenManager.changeScreen(new DrawApp());
        });

        Button homeScreenBtn = new Button();
        homeScreenBtn.setText("Go to HomeScreen");
        
        homeScreenBtn.setOnAction(e->{
        	ScreenManager.changeScreen(new HomeScreen());
        });
        
        Button issueDeskBtn = new Button();
        issueDeskBtn.setText("Go to IssueDesk");
        
        issueDeskBtn.setOnAction(e->{
        	ScreenManager.changeScreen(new IssueDesk());
        });
        
        Button resourceScreenBtn = new Button();
        resourceScreenBtn.setText("Go to ResourceScreen");
        
        resourceScreenBtn.setOnAction(e->{
        	ScreenManager.changeScreen(new ResourceScreen());
        });
        
        Button searchResultScreen = new Button();
        searchResultScreen.setText("Go to SearchResultsScreen");
        
        searchResultScreen.setOnAction(e->{
        	ScreenManager.changeScreen(new SearchResultScreen());
        });
        
        
        content.getChildren().addAll(drawAppBtn, homeScreenBtn, issueDeskBtn, resourceScreenBtn, searchResultScreen);
        
        components.add(content);
    }
}
