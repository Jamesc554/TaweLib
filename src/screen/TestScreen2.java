package screen;

import java.util.ArrayList;

import javafx.scene.control.Button;

public class TestScreen2 extends Screen {

    @Override
    public void start() {
        components = new ArrayList<>();

        Button btn = new Button();
        btn.setText("Go Back");
        btn.setOnAction(e->{
        	ScreenManager.previousScreen();
        });

        components.add(btn);
    }

    @Override
    public void search() {

    }
}
