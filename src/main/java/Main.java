import io.javalin.Javalin;
import javafx.application.Application;
import javafx.stage.Stage;
import library.Library;
import library.LibraryResources;
import screen.ScreenManager;
import user.User;

import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;
import static javax.swing.UIManager.get;

/**
 * <h1>Main.</h1>
 * <p>The main class which starts upon load.</p>
 * @author James Carter, Peter Daish, Deyan Naydenov   
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

//        Javalin app = Javalin.create().start(8080);
//
//        app.get("/resources", ctx -> ctx.result(LibraryResources.getAllResourcesJSON()));
//        app.get("resources/:id", ctx -> {
//            ctx.result(Library.getResource(ctx.pathParam("id")).toJSON());
//        });
//
//        app.get("/users", ctx -> ctx.result(String.valueOf(Library.getAllUsers())));

        try {
        	ScreenManager.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
