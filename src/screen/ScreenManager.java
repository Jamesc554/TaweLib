package screen;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Stack;

/**
 * <h1>ScreenManager</h1>
 * <p>ScreenManager is responsible for controlling what Screen is currently being drawn to the display, and how we
 * traverse between those screens</p>
 * @author James Carter
 * @version 0.1
 * @since 11/11/2018
 */
public class ScreenManager {

    private static Stack<Screen> loadedScreens = new Stack<>();
    private static Scene scene;
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static Stage stage;

    public static void main(String[] args){
       // launch(args);
    }

    /**
     * Inherited from Application, entry point for the JavaFX App
     * @param primaryStage
     */
    
    public static void start(Stage primaryStage) {
        stage = primaryStage;
        changeScreen(new LoginScreen());
    }

    /**
     * Gets the current Screen being displayed.
     * @return The currently displayed Screen
     */
    public static Screen getCurrentScreen(){
        if (!loadedScreens.empty())
            return loadedScreens.peek();

        return null;
    }

    /**
     * Pops the current Screen of the top of the stack and sets the current Screen to the next on the stack
     */
    public static void previousScreen(){
        if (loadedScreens.size() > 1) {
            loadedScreens.pop();
            setupScreen();
        } else
            System.out.println("You are already on the last screen, you cannot go back any further");
    }

    /**
     * Puts a new Screen on top of the Stack so that is now the current Screen.
     * @param screen
     * The new Screen
     */
    public static void changeScreen(Screen screen){
        screen.start();
        loadedScreens.push(screen);
        setupScreen();
    }

    private static void setupScreen(){
        StackPane root = new StackPane();
        if (getCurrentScreen().getComponents() != null)
        	root.getChildren().addAll(getCurrentScreen().getComponents());
        scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

	public static Scene getCurrentScene() {
		return scene;
	}
}
