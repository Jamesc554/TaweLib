package screen;

import java.util.ArrayList;

import javafx.scene.control.Button;

public class TestScreen extends Screen {

    @Override
    public void start() {
        components = new ArrayList<>();

        Button btn = new Button();
        btn.setText("Go to DrawApp");
        
        btn.setOnAction(e->{
        	ScreenManager.changeScreen(new DrawApp());
        });
        
        components.add(btn);
    }

    @Override
    public void search() {

    }
}
