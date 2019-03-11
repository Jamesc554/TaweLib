package screen;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import resources.Resource;

import java.io.IOException;


public class TrailerScreen extends Screen {

    private String url = "https://www.youtube.com/embed/";

    @Override
    public void start() {
        WebView webView = new WebView();
        webView.getEngine().load(url);

        System.out.println(url);

        Stage stage = new Stage();
        stage.setScene(new Scene(webView));
        stage.show();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>(){
            @Override
            public void handle(WindowEvent event) {
                webView.getEngine().load(null);
            }
        });
    }

    public void getTrailer(Resource resource) {
        String queryUrl = "https://www.youtube.com/results?search_query=";
        String title = resource.getTitle();
        String year = resource.getYear();
        String query = title + year;
        query.replace(" ", "+");
        queryUrl += query + "+trailer";

        getHTML(queryUrl);
        start();
    }

    private void getHTML(String queryUrl) {
        try {
            Document doc = Jsoup.connect(queryUrl).get();
            Element videoSection = doc.getElementsByClass("yt-lockup-thumbnail").first();
            Element anchor = videoSection.getElementsByTag("a").first();
            String link = anchor.attr("href");
            link = link.replace("/watch?v=", "");
            url += link;
        } catch (IOException e) {
            System.out.println("Invalid search query url");
        }
    }
}
