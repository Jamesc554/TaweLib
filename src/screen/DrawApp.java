package screen;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
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
		
		Spinner<Integer> brushSize = new Spinner<Integer>(0, 64, 4);
		
		Button saveBtn = new Button("Save");
		saveBtn.setOnAction(e->{
			// TODO: Save Functionality, maybe use WriteFile, unsure yet.
		});

		HBox header = new HBox(8);
		header.setPrefWidth(1280);
		header.getChildren().addAll(paintBrushBtn, paintBucketBtn, cPicker, brushSize, saveBtn);
		header.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));

		Canvas canvas = new Canvas(1280, 650);
		HBox drawWindow = new HBox(10);
		drawWindow.getChildren().add(canvas);

		GraphicsContext gc = canvas.getGraphicsContext2D();

		VBox content = new VBox(10);

		content.getChildren().addAll(header, drawWindow);
		content.setAlignment(Pos.TOP_LEFT);


		canvas.setOnMousePressed(mouse -> {
			gc.setLineWidth((double) brushSize.getValueFactory().getValue());
			if (paintBrushBtn.isSelected()) {
				gc.setStroke(cPicker.getValue());
				gc.beginPath();
				gc.lineTo(mouse.getX(), mouse.getY());
			} else if (paintBucketBtn.isSelected()) {
				// TODO: Paint Bucket Implementation
			}
		});

		canvas.setOnMouseDragged(mouse -> {
			if (paintBrushBtn.isSelected()) {
				gc.stroke();
				gc.lineTo(mouse.getX(), mouse.getY());
			} else if (paintBucketBtn.isSelected()) {
				// TODO: Paint Bucket Implementation
			}
		});

		canvas.setOnMouseReleased(mouse -> {
			if (paintBrushBtn.isSelected()) {
				gc.stroke();
				gc.lineTo(mouse.getX(), mouse.getY());
				gc.closePath();
			} else if (paintBucketBtn.isSelected()) {
				// TODO: Paint Bucket Implementation
			}
		});

		components.add(content);

	}

	@Override
	public void search() {
		// TODO Auto-generated method stub

	}

}
