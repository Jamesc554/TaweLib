package screen;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Stack;

public class ScreenManager extends Application {

    private static Stack<Screen> loadedScreens = new Stack<>();
    private static Stage stage;
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle("SEGroup2 - Software Engineering Project");
        changeScreen(new TestScreen());
    }

    public static Screen getCurrentScreen(){
        if (!loadedScreens.empty())
            return loadedScreens.peek();

        return null;
    }

    public static void previousScreen(){
        if (loadedScreens.size() > 1) {
            loadedScreens.pop();
            setupScreen();
        } else
            System.out.println("You are already on the last screen, you cannot go back any further");
    }

    public static void changeScreen(Screen screen){
        screen.start();
        loadedScreens.push(screen);
        setupScreen();
    }

    private static void setupScreen(){
        StackPane root = new StackPane();
        root.getChildren().addAll(getCurrentScreen().getComponents());
        stage.setScene(new Scene(root, WIDTH, HEIGHT));
        stage.show();
    }
}
