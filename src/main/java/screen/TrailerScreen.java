package screen;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import library.Resource;

import java.io.IOException;

/**
 * <h1>TrailerScreen</h1>
 * <p>This class uses a DVD/Video Game title and year to return an embedded Youtube video of the corresponding
 * DVD/Video Game trailer.</p>
 *
 * @author Etienne Badoche
 * @version 1.0
 */
public class TrailerScreen extends Screen {

    //URL String to embed youtube video link
    private String url = "https://www.youtube.com/embed/";
    //Video width/height constants
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    /**
     * Open the video in a new window.
     */
    @Override
    public void start() {
        //Use JavaFX WebView to render the Youtube webpage (embedded video).
        WebView webView = new WebView();
        webView.getEngine().load(url);

        //Open WebView in new window
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(new Scene(webView, WIDTH, HEIGHT));
        stage.show();

        //Set WebView to null when closing window (gets rid of sound continuing in background when closing video).
        stage.setOnCloseRequest(new EventHandler<WindowEvent>(){
            @Override
            public void handle(WindowEvent event) {
                webView.getEngine().load(null);
            }
        });
    }

    /**
     * Use the resource title and year to generate a Youtube query URL.
     * @param resource The DVD or Video Game resource.
     */
    public void getTrailer(Resource resource) {
        //Set initial query URL
        String queryUrl = "https://www.youtube.com/results?search_query=";
        //Add title and year to query URL
        String title = resource.getTitle();
        String year = resource.getYear();
        String query = title + year;
        //Replace spaces with '+'
        query.replace(" ", "+");
        queryUrl += query + "+trailer";

        //Set first result video url
        getHTML(queryUrl);
        //Open video once url is set
        start();
    }

    /**
     * Use Jsoup library to search through Youtube results page HTML and retrieve the first video link
     * @param queryUrl the Youtube query URL.
     */
    private void getHTML(String queryUrl) {
        try {
            //Search through HTML to get first video link
            Document doc = Jsoup.connect(queryUrl).get();
            Element videoSection = doc.getElementsByClass("yt-lockup-thumbnail").first();
            Element anchor = videoSection.getElementsByTag("a").first();
            String link = anchor.attr("href");

            //Add video link to Youtube embed URL
            link = link.replace("/watch?v=", "");
            url += link;
        } catch (IOException e) {
            //connect(url).get() throws IOException
            System.out.println("Invalid search query url");
        }
    }
}
