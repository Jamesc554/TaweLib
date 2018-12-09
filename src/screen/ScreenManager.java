package screen;
import java.util.Stack;
import io.WriteFile;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * <h1>ScreenManager.</h1>
 * <p>ScreenManager is responsible for controlling what Screen is currently being drawn to the display, and how we
 * traverse between those screens</p>
 * @author James Carter
 * @version 0.1
 * @since 11/11/2018
 */
public class ScreenManager {
	private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    
    private static Stack<Screen> loadedScreens = new Stack<>();
    private static Scene scene;
    private static Stage stage;

    /**
     * Return the application's main stage
     * @return the main stage of the application
     */
    public static Stage getStage() {
        return stage;
    }

    /**
     * Inherited from Application, entry point for the JavaFX App
     * @param primaryStage
     * The primary stage for this JavaFX App.
     */
    public static void start(Stage primaryStage) {
        stage = primaryStage;
        
        stage.setOnCloseRequest(e -> {
        	WriteFile.backupCurrent();
        });
        
        changeScreen(new LoginScreen());
    }

    /**
     * Gets the current Screen being displayed.
     * @return The currently displayed Screen
     */
    public static Screen getCurrentScreen() {
        if (!loadedScreens.empty()) {
            return loadedScreens.peek();
        }

        return null;
    }

    /**
     * Pops the current Screen off the top of the stack and sets the current Screen to the next on the stack
     */
    public static void previousScreen() {
        if (loadedScreens.size() > 1) {
            loadedScreens.pop();
            setupScreen();
        } else {
            System.out.println("You are already on the last screen, you cannot go back any further");
        }
    }

    /**
     * Puts a new Screen on top of the Stack so that is now the current Screen.
     * @param screen
     * The new Screen to be put on stack.
     */
    public static void changeScreen(Screen screen) {
        loadedScreens.push(screen);
        setupScreen();
    }

    /**
     * Sets the Screen and its core components.
     */
    private static void setupScreen() {
    	getCurrentScreen().start();
    	
        if (getCurrentScreen().getComponents() != null) {
            StackPane root = new StackPane();
        	root.getChildren().addAll(getCurrentScreen().getComponents());
	        scene = new Scene(root, WIDTH, HEIGHT);
        }
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Returns the current scene.
     * @return scene
     * The current scene.
     */
	public static Scene getCurrentScene() {
		return scene;
	}
	
	/**
	 * Sets a scene.
	 * @param newScene
	 * The new scene to set as current.
	 */
	public static void setCurrentScene(Scene newScene) {
		scene = newScene;
	}
}
