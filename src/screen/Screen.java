package screen;

import javafx.scene.Node;

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

    public void logout(){
        // TODO: LOGOUT FUNCTION
    }

    // TODO: Search implementation
    public void search() {
    	
    }

}
