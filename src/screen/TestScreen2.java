package screen;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.ArrayList;

public class TestScreen2 extends Screen {

    @Override
    public void start() {
        components = new ArrayList<>();

        Button btn = new Button();
        btn.setText("Go Back");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ScreenManager.previousScreen();
            }
        });

        components.add(btn);
    }

    @Override
    public void search() {

    }
}
