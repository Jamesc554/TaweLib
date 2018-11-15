package screen;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawApp extends Screen {

	@Override
	public void start() {
        components = new ArrayList<>();
        
        Canvas canvas = new Canvas(1280, 720);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        gc.setLineWidth(5);
        gc.setFill(Color.BLACK);
        
        canvas.setOnMousePressed(mouse->{
        	gc.beginPath();
        	gc.lineTo(mouse.getX(), mouse.getY());
        });
        
        canvas.setOnMouseDragged(mouse->{
        	gc.stroke();
        	gc.lineTo(mouse.getX(), mouse.getY());
        });
        
        canvas.setOnMouseReleased(mouse->{
        	gc.stroke();
        	gc.lineTo(mouse.getX(), mouse.getY());
        	gc.closePath();
        });
        
        components.add(canvas);

	}

	@Override
	public void search() {
		// TODO Auto-generated method stub

	}

}
