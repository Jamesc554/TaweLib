import javafx.application.Application;
import javafx.stage.Stage;
import screen.ScreenManager;

public class Main extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}


    @Override
    public void start(Stage primaryStage) {
        try {
        	ScreenManager.start(primaryStage);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
	
}
