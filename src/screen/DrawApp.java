package screen;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class DrawApp extends Screen {

	@Override
	public void start() {
		components = new ArrayList<>();


		
		ToggleGroup tools = new ToggleGroup();
		RadioButton paintBrushBtn = new RadioButton("Paint Brush");
		paintBrushBtn.setToggleGroup(tools);
		paintBrushBtn.setSelected(true);
		
		RadioButton paintBucketBtn = new RadioButton("Paint Bucket");
		paintBucketBtn.setToggleGroup(tools);
		
		ColorPicker cPicker = new ColorPicker();
		
		HBox header = new HBox(8);
		header.setPrefWidth(1280);
		header.getChildren().addAll(paintBrushBtn, paintBucketBtn, cPicker);
		header.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
		
		Canvas canvas = new Canvas(1280, 650);
		HBox drawWindow = new HBox(10);
		drawWindow.getChildren().add(canvas);
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		VBox content = new VBox(10);
		
		content.getChildren().addAll(header, drawWindow);
		content.setAlignment(Pos.TOP_LEFT);
		

		gc.setLineWidth(5);

		canvas.setOnMousePressed(mouse -> {
			gc.setStroke(cPicker.getValue());
			gc.beginPath();
			gc.lineTo(mouse.getX(), mouse.getY());
		});

		canvas.setOnMouseDragged(mouse -> {
			gc.stroke();
			gc.lineTo(mouse.getX(), mouse.getY());
		});

		canvas.setOnMouseReleased(mouse -> {
			gc.stroke();
			gc.lineTo(mouse.getX(), mouse.getY());
			gc.closePath();
		});

		components.add(content);

	}

	@Override
	public void search() {
		// TODO Auto-generated method stub

	}

}
