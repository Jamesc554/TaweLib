package screen;

import java.util.ArrayList;

import javafx.scene.control.Button;

public class TestScreen extends Screen {

    @Override
    public void start() {
        components = new ArrayList<>();

        Button drawAppBtn = new Button();
        drawAppBtn.setText("Go to DrawApp");
        
        drawAppBtn.setOnAction(e->{
        	ScreenManager.changeScreen(new DrawApp());
        });
        
        components.add(drawAppBtn);
        
        Button homeScreenBtn = new Button();
        homeScreenBtn.setText("Go to HomeScreen");
        
        homeScreenBtn.setOnAction(e->{
        	ScreenManager.changeScreen(new HomeScreen());
        });
        
        components.add(homeScreenBtn);
        
        Button issueDeskBtn = new Button();
        issueDeskBtn.setText("Go to IssueDesk");
        
        issueDeskBtn.setOnAction(e->{
        	ScreenManager.changeScreen(new IssueDesk());
        });
        
        components.add(issueDeskBtn);
        
        Button resourceScreenBtn = new Button();
        resourceScreenBtn.setText("Go to ResourceScreen");
        
        resourceScreenBtn.setOnAction(e->{
        	ScreenManager.changeScreen(new ResourceScreen());
        });
        
        components.add(resourceScreenBtn);
        
        Button searchResultScreen = new Button();
        searchResultScreen.setText("Go to DrawApp");
        
        searchResultScreen.setOnAction(e->{
        	ScreenManager.changeScreen(new SearchResultScreen());
        });
        
        components.add(searchResultScreen);
    }

    @Override
    public void search() {

    }
}
