import javafx.application.Application;
import javafx.stage.Stage;
import library.Library;
import screen.ScreenManager;

/**
 * <h1>Main.</h1>
 * <p>The main class which starts upon load.</p>
 * @author Peter Daish  
 */
public class Main extends Application {
	
	/**
	 * The main method which loads the program.
	 * @param args
	 * The supplied command-line arguments used to run this program.
	 */
	public static void main(String[] args) {
		launch(args);
	}

    @Override
    /**
     * Starts the library.
     * @param primaryStage
     * The primary stage used for this program.
     */
    public void start(Stage primaryStage) {
        Library.start();

        try {
        	ScreenManager.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
