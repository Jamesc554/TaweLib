package screen;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;

import java.util.ArrayList;

public class TestScreen extends Screen {

    @Override
    public void start() {
        components = new ArrayList<>();

        Button btn = new Button();
        btn.setText("Go to TestScreen2");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ScreenManager.changeScreen(new TestScreen2());
            }
        });

        components.add(btn);
    }

    @Override
    public void search() {

    }
}
