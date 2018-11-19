package screen;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

public class DrawApp extends Screen {

	@Override
	public void start() {
		components = new ArrayList<>();

		Canvas canvas = new Canvas(640, 380);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		GridPane grid = new GridPane();
		
		ToggleGroup tools = new ToggleGroup();
		RadioButton paintBrushBtn = new RadioButton("Paint Brush");
		paintBrushBtn.setToggleGroup(tools);
		paintBrushBtn.setSelected(true);
		
		RadioButton paintBucketBtn = new RadioButton("Paint Bucket");
		paintBucketBtn.setToggleGroup(tools);
		
		ColorPicker cPicker = new ColorPicker();
		

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

		grid.add(canvas, 0, 0);
		grid.add(paintBrushBtn, 0, 1);
		grid.add(paintBucketBtn, 1, 1);
		grid.add(cPicker, 2, 0);
		
		components.add(grid);

	}

	@Override
	public void search() {
		// TODO Auto-generated method stub

	}

}
