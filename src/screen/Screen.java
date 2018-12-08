package screen;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import library.Library;

import java.util.List;

/**
 * <h1>Screen</h1>
 * <p>Screen is an abstract outline of how a Screen functions so that the ScreenManager can use them.</p>
 * @author James Carter
 * @version 0.1
 * @since 11/11/2018
 */
public abstract class Screen {

    protected List<Node> components;
    
    @FXML
	protected TextField searchBar;

	@FXML
	protected Button searchBtn;

	@FXML
	protected ImageView userIcon;

	@FXML
	protected Text usernameText;

	@FXML
	protected Button logoutBtn;

	@FXML
	protected Button homeBtn;

	@FXML
	protected Button accountBtn;

	@FXML
	protected Button issueDeskBtn;

	@FXML
	protected Button drawAppBtn;

    // TODO: start implementation
    public abstract void start();

    /**
     * Changes the screen from the current to the specified one.
     * @param screen
     * Screen being switched to
     */
    public void switchScreen(Screen screen){
        ScreenManager.changeScreen(screen);
    }

    /**
     * Sets the display back to the previous Screen
     */
    public void prevScreen(){
        ScreenManager.previousScreen();
    }

    /**
     * Gets the list of components on the Screen (Everything that needs to be displayed/have functionality)
     * @return List of the components on the Screen
     */
    public List<Node> getComponents(){
        return components;
    }

    protected void logout(){
    	Library.setLoggedInUser(null);
    	ScreenManager.changeScreen(new LoginScreen());
    }
    
    @FXML
	protected void drawAppButton(Event event) {
		ScreenManager.changeScreen(new DrawApp());
	}
	
	@FXML
	protected void searchButton(Event event) {
    	Library.setSearchStringText(searchBar.getText());
		ScreenManager.changeScreen(new SearchResultScreen());
	}
	
	@FXML
	protected void logoutButton(Event event) {
		logout();
	}

	@FXML
	protected void issueDeskButton(Event event) {
		ScreenManager.changeScreen(new IssueDeskScreen());
	}
	
	@FXML
	protected void accountDeskButton(Event event) {
		ScreenManager.changeScreen(new AccountScreen());
	}
	
	//TODO: Fix home button
	@FXML
    protected void homeButton(Event event) {
        ScreenManager.changeScreen(new HomeScreen());
    }

}
