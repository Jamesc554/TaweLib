package screen;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.TriangleMesh;
import utils.Queue;

public class DrawApp extends Screen {

	WritableImage prevState = null;

	Stack<WritableImage> previousStates;
	Stack<WritableImage> futureStates;
	
	// JavaFX Components
	
	// Toolbar \\
	ToggleGroup tools = new ToggleGroup();
	RadioButton paintBrushBtn = new RadioButton("Paint Brush");
	RadioButton paintBucketBtn = new RadioButton("Paint Bucket");
	RadioButton lineToolBtn = new RadioButton("Draw Line");
	
	RadioButton shapeToolBtn = new RadioButton("Shape Tool");
	ComboBox<String> shapeSelector = new ComboBox<>();
	
	ColorPicker cPicker = new ColorPicker();
	Spinner<Integer> brushSize = new Spinner<Integer>(0, 64, 4);

	Button saveBtn = new Button("Save");
	Button undoBtn = new Button("Undo");
	Button redoBtn = new Button("Redo");
	
	// Canvas \\
	Canvas canvas = new Canvas(256, 256);
	GraphicsContext gc = canvas.getGraphicsContext2D();
	
	// Shapes \\
	Line line = new Line();
	Rectangle rectangle = new Rectangle();
	TriangleMesh triangle = new TriangleMesh();
	Circle circle = new Circle();
	
	// Layout \\
	HBox drawWindow = new HBox(10);
	HBox header = new HBox(8);
	VBox content = new VBox(10);

	@Override
	public void start() {
		components = new ArrayList<>();
		previousStates = new Stack<>();
		futureStates = new Stack<>();
		
		SetupToolbar();
		SetupLayout();

		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

		canvas.setOnMousePressed(mouse -> CanvasMousePressed(mouse));
		canvas.setOnMouseDragged(mouse -> CanvasMouseDragged(mouse));
		canvas.setOnMouseReleased(mouse -> CanvasMouseReleased(mouse));


	}
	
	
	// Canavs Mouse Events \\
	private void CanvasMousePressed(MouseEvent mouse) {
		addPreviousState(canvas);
		gc.setLineWidth((double) brushSize.getValueFactory().getValue());
		gc.setStroke(cPicker.getValue());
		gc.setFill(cPicker.getValue());
		if (paintBrushBtn.isSelected()) {
			gc.beginPath();
			gc.lineTo(mouse.getX(), mouse.getY());
			prevState = convertToImage(canvas);
		} else if (paintBucketBtn.isSelected()) {
			gc.drawImage(paintBucket(cPicker.getValue(), (int) mouse.getX(), (int) mouse.getY(), canvas), 0, 0);
			prevState = convertToImage(canvas);
		} else if (lineToolBtn.isSelected()) {
			line.setStartX(mouse.getX());
			line.setStartY(mouse.getY());
		} else if (shapeToolBtn.isSelected()){
			switch (shapeSelector.getValue()){
				case ("Rectangle"):
					rectangle.setX(mouse.getX());
					rectangle.setY(mouse.getY());
					break;
				case ("Triangle"):
					// TODO: Triangle Implementation
					break;
				case ("Oval"):
					circle.setCenterX(mouse.getX());
					circle.setCenterY(mouse.getY());
					break;
				default:
					break;
			}
		}
	}

	private void CanvasMouseDragged(MouseEvent mouse) {
		if (paintBrushBtn.isSelected()) {
			gc.strokeOval(mouse.getX(), mouse.getY(), (double) brushSize.getValueFactory().getValue(),
					(double) brushSize.getValueFactory().getValue());
			gc.lineTo(mouse.getX(), mouse.getY());
		} else if (paintBucketBtn.isSelected()) {
			// TODO: Paint Bucket Implementation
		} else if (lineToolBtn.isSelected()) {
			gc.drawImage(prevState, 0, 0);
			prevState = convertToImage(canvas);
			gc.strokeLine(line.getStartX(), line.getStartY(), mouse.getX(), mouse.getY());
		} else if (shapeToolBtn.isSelected()){
			gc.drawImage(prevState, 0, 0);
			prevState = convertToImage(canvas);
			switch (shapeSelector.getValue()){
				case ("Rectangle"):
					rectangle.setWidth(mouse.getX() - rectangle.getX());
					rectangle.setHeight(mouse.getY() - rectangle.getY());
					gc.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
					break;
				case ("Triangle"):
					break;
				case ("Oval"):
					circle.setRadius((mouse.getX() + mouse.getY()) - (circle.getCenterX() + circle.getCenterY()));
					gc.fillOval(circle.getCenterX() - circle.getRadius() / 2, circle.getCenterY() - circle.getRadius() / 2, circle.getRadius(), circle.getRadius());
					break;
				default:
					break;
			}
		}
	}
	
	private void CanvasMouseReleased(MouseEvent mouse) {
		addPreviousState(canvas);
		if (paintBrushBtn.isSelected()) {
			gc.strokeOval(mouse.getX(), mouse.getY(), (double) brushSize.getValueFactory().getValue(),
					(double) brushSize.getValueFactory().getValue());
			gc.lineTo(mouse.getX(), mouse.getY());
			gc.closePath();
			prevState = convertToImage(canvas);
		} else if (paintBucketBtn.isSelected()) {
			// TODO: Paint Bucket Implementation
		} else if (lineToolBtn.isSelected()) {
			gc.drawImage(prevState, 0, 0);
			gc.strokeLine(line.getStartX(), line.getStartY(), mouse.getX(), mouse.getY());
			prevState = convertToImage(canvas);
		} else if (shapeToolBtn.isSelected()){
			switch (shapeSelector.getValue()){
				case ("Rectangle"):
					gc.drawImage(prevState, 0, 0);
					gc.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
					prevState = convertToImage(canvas);
					break;
				case ("Triangle"):
					break;
				case ("Oval"):
					gc.drawImage(prevState, 0, 0);
					gc.fillOval(circle.getCenterX() - circle.getRadius() / 2, circle.getCenterY() - circle.getRadius() / 2, circle.getRadius(), circle.getRadius());
					prevState = convertToImage(canvas);
					break;
				default:
					break;
			}
		}
	}
	
	// Component Setup \\
	private void SetupToolbar() {
		paintBrushBtn.setToggleGroup(tools);
		paintBrushBtn.setSelected(true);

		paintBucketBtn.setToggleGroup(tools);

		lineToolBtn.setToggleGroup(tools);

		shapeToolBtn.setToggleGroup(tools);

		saveBtn.setOnAction(e -> {
			saveImage(canvas);
		});

		undoBtn.setOnAction(e -> {
			undo(canvas);
			undo(canvas);
		});

		redoBtn.setOnAction(e -> {
			redo(canvas);
			redo(canvas);
		});

		shapeSelector.getItems().addAll("Rectangle", "Triangle", "Oval");
		shapeSelector.setValue("Rectangle");
	}
	
	private void SetupLayout() {
		header.setPrefWidth(1280);
		header.getChildren().addAll(paintBrushBtn, paintBucketBtn, lineToolBtn, shapeToolBtn, shapeSelector, cPicker, brushSize, saveBtn, undoBtn,
				redoBtn);
		header.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
		
		drawWindow.getChildren().add(canvas);
		drawWindow.setAlignment(Pos.CENTER);
		
		content.getChildren().addAll(header, drawWindow);
		content.setAlignment(Pos.TOP_LEFT);
		
		components.add(content);
	}
	
	private WritableImage convertToImage(Canvas c) {
		SnapshotParameters sp = new SnapshotParameters();
		sp.setFill(Color.TRANSPARENT);
		WritableImage wi = c.snapshot(sp, null);

		return wi;
	}

	private void addPreviousState(Canvas c) {
		if (!previousStates.isEmpty())
			if (convertToImage(c).equals(previousStates.peek()))
				return;

		previousStates.push(convertToImage(c));
	}

	private void addFutureState(Canvas c) {
		futureStates.push(convertToImage(c));
	}

	private void undo(Canvas c) {
		if (!previousStates.isEmpty()) {
			futureStates.push(previousStates.peek());
			prevState = previousStates.peek();
			c.getGraphicsContext2D().drawImage(previousStates.pop(), 0, 0);
		}
	}

	private void redo(Canvas c) {
		if (!futureStates.isEmpty()) {
			previousStates.push(futureStates.peek());
			prevState = futureStates.peek();
			c.getGraphicsContext2D().drawImage(futureStates.pop(), 0, 0);
		}
	}

	private WritableImage paintBucket(Color c, int startX, int startY, Canvas canvas) {
		WritableImage wi = convertToImage(canvas);
		Color startC = wi.getPixelReader().getColor(startX, startY);

		wi = floodFill(startX, startY, startC, c, wi);

		return wi;
	}

	private WritableImage floodFill(int x, int y, Color startC, Color newC, WritableImage img) {
		PixelReader pr = img.getPixelReader();
		PixelWriter pw = img.getPixelWriter();

		// if the pixel point you chose is the same colour that you want already.
		if (startC == newC)
			return img;
		if (!pr.getColor(x, y).equals(startC))
			return img;

		Queue<int[]> queue = new Queue<int[]>();
		pw.setColor(x, y, newC); // Set the colour of the current node
		int[] node = new int[2];
		node[0] = x;
		node[1] = y;
		queue.enqueue(node); // Add the node to the queue

		// Until we run out of nodes
		while (!queue.isEmpty()) {
			// Take the node at the front of the queue
			int[] n = queue.peek();
			queue.dequeue();

			// Check West
			if (n[0] - 1 > 0)
				// Set the colour of the node and add it to the queue
				if (pr.getColor(n[0] - 1, n[1]).equals(startC)) {
					pw.setColor(n[0] - 1, n[1], newC);
					int[] newNode = new int[2];
					newNode[0] = n[0] - 1;
					newNode[1] = n[1];
					queue.enqueue(newNode);
				}

			// Check East
			if (n[0] + 1 < img.getWidth())
				// Set the colour of the node and add it to the queue
				if (pr.getColor(n[0] + 1, n[1]).equals(startC)) {
					pw.setColor(n[0] + 1, n[1], newC);
					int[] newNode = new int[2];
					newNode[0] = n[0] + 1;
					newNode[1] = n[1];
					queue.enqueue(newNode);
				}

			// Check North
			if (n[1] - 1 > 0)
				// Set the colour of the node and add it to the queue
				if (pr.getColor(n[0], n[1] - 1).equals(startC)) {
					pw.setColor(n[0], n[1] - 1, newC);
					int[] newNode = new int[2];
					newNode[0] = n[0];
					newNode[1] = n[1] - 1;
					queue.enqueue(newNode);
				}

			// Check South
			if (n[1] + 1 < img.getHeight())
				// Set the colour of the node and add it to the queue
				if (pr.getColor(n[0], n[1] + 1).equals(startC)) {
					pw.setColor(n[0], n[1] + 1, newC);
					int[] newNode = new int[2];
					newNode[0] = n[0];
					newNode[1] = n[1] + 1;
					queue.enqueue(newNode);
				}
		}

		return img;
	}

	private void saveImage(Canvas c) {
		WritableImage img = convertToImage(c);
		File file = new File("./data/images/test.png");

		try {
			ImageIO.write(SwingFXUtils.fromFXImage(img, null), "png", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
