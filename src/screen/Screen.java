package screen;

import javafx.scene.Node;

import java.util.List;

public abstract class Screen {

    /**
     *
     */
    protected List<Node> components;

    // TODO: start implementation
    public abstract void start();

    public void switchScreen(Screen screen){
        ScreenManager.changeScreen(screen);
    }

    public void prevScreen(){
        ScreenManager.previousScreen();
    }

    public List<Node> getComponents(){
        return components;
    }

    public void logout(){
        // TODO: LOGOUT FUNCTION
    }

    // TODO: Search implementation
    public abstract void search();

}
