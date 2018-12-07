package screen;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import io.WriteFile;
import javafx.geometry.Pos;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
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
import javafx.stage.FileChooser;
import library.Library;
import utils.Queue;

public class DrawApp extends Screen {

	private WritableImage prevState = null;

	private Stack<WritableImage> previousStates;
	private Stack<WritableImage> futureStates;

	// JavaFX Components

	// Control Header \\
	private final MenuButton fileBtn = new MenuButton("File");
	private final MenuButton editBtn = new MenuButton("Edit");
	private final MenuButton viewBtn = new MenuButton("View");
	private final MenuButton toolsBtn = new MenuButton("Tools");

	private final MenuItem saveItem = new MenuItem("Save");
	private final MenuItem saveAsItem = new MenuItem("Save As");
	private final MenuItem loadItem = new MenuItem("Load");
	private final MenuItem exitItem = new MenuItem("Exit");

	private final MenuItem undoItem = new MenuItem("Undo");
	private final MenuItem redoItem = new MenuItem("Redo");

	private final MenuItem zoomInItem = new MenuItem("Zoom In");
	private final MenuItem zoomOutItem = new MenuItem("Zoom Out");

	private final MenuItem invertItem = new MenuItem("Invert Colours");
	private final MenuItem grayscaleItem = new MenuItem("Convert to Grayscale");

	// Toolbar \\
	private final ToggleGroup tools = new ToggleGroup();
	private final RadioButton paintBrushBtn = new RadioButton("Paint Brush");
	private final RadioButton paintBucketBtn = new RadioButton("Paint Bucket");
	private final RadioButton lineToolBtn = new RadioButton("Draw Line");

	private final RadioButton shapeToolBtn = new RadioButton("Shape Tool");
	private final ComboBox<String> shapeSelector = new ComboBox<>();

	private final ColorPicker cPicker = new ColorPicker();
	private final Spinner<Integer> brushSize = new Spinner<Integer>(0, 64, 4);

	// Canvas \\
	private final Canvas canvas = new Canvas(256, 256);
	private final GraphicsContext gc = canvas.getGraphicsContext2D();

	// Shapes \\
	private final Line line = new Line();
	private final Rectangle rectangle = new Rectangle();
	private final TriangleMesh triangle = new TriangleMesh();
	private final Circle circle = new Circle();

	// Layout \\
	private final HBox drawWindow = new HBox(10);
	private final HBox controlHeader = new HBox(4);
	private final HBox header = new HBox(8);
	private final VBox content = new VBox(0);

	@Override
	public void start() {
		components = new ArrayList<>();
		previousStates = new Stack<>();
		futureStates = new Stack<>();

		SetupControlHeader();
		SetupToolbar();
		SetupLayout();

		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

		canvas.setOnMousePressed(mouse -> CanvasMousePressed(mouse));
		canvas.setOnMouseDragged(mouse -> CanvasMouseDragged(mouse));
		canvas.setOnMouseReleased(mouse -> CanvasMouseReleased(mouse));

	}

	// Canavs Mouse Events \\

	/**
	 * The event called when the mouse is pressed on the canvas
	 * 
	 * @param mouse The event object
	 */
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
		} else if (shapeToolBtn.isSelected()) {
			switch (shapeSelector.getValue()) {
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

	/**
	 * The event called when the mouse is dragged on the canvas
	 * 
	 * @param mouse The event object
	 */
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
		} else if (shapeToolBtn.isSelected()) {
			gc.drawImage(prevState, 0, 0);
			prevState = convertToImage(canvas);
			switch (shapeSelector.getValue()) {
			case ("Rectangle"):
				rectangle.setWidth(mouse.getX() - rectangle.getX());
				rectangle.setHeight(mouse.getY() - rectangle.getY());
				gc.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
				break;
			case ("Triangle"):
				break;
			case ("Oval"):
				circle.setRadius((mouse.getX() + mouse.getY()) - (circle.getCenterX() + circle.getCenterY()));
				gc.fillOval(circle.getCenterX() - circle.getRadius() / 2, circle.getCenterY() - circle.getRadius() / 2,
						circle.getRadius(), circle.getRadius());
				break;
			default:
				break;
			}
		}
	}

	/**
	 * The event called when the mouse is released on the canvas
	 * 
	 * @param mouse The event object
	 */
	private void CanvasMouseReleased(MouseEvent mouse) {
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
		} else if (shapeToolBtn.isSelected()) {
			switch (shapeSelector.getValue()) {
			case ("Rectangle"):
				gc.drawImage(prevState, 0, 0);
				gc.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
				prevState = convertToImage(canvas);
				break;
			case ("Triangle"):
				break;
			case ("Oval"):
				gc.drawImage(prevState, 0, 0);
				gc.fillOval(circle.getCenterX() - circle.getRadius() / 2, circle.getCenterY() - circle.getRadius() / 2,
						circle.getRadius(), circle.getRadius());
				prevState = convertToImage(canvas);
				break;
			default:
				break;
			}
		}
	}

	// Component Setup \\

	/**
	 * Initialisation of JavaFX components on the ControlHeader
	 */
	private void SetupControlHeader() {
		fileBtn.getItems().addAll(saveItem, loadItem, exitItem);
		editBtn.getItems().addAll(undoItem, redoItem);
		viewBtn.getItems().addAll(zoomInItem, zoomOutItem);
		toolsBtn.getItems().addAll(invertItem, grayscaleItem);

		// File Functions \\
		saveItem.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
			fileChooser.getExtensionFilters().add(extFilter);
			String currentUserName = Library.getCurrentLoggedInUser().getUserName();
			String directoryPath = ("./data/images/" + currentUserName);
			File initDir = new File(directoryPath);
			fileChooser.setInitialDirectory(initDir);

			File file = fileChooser.showSaveDialog(ScreenManager.getStage());

			if (file != null)
				saveImage(canvas, file);
		});

		// TODO: Load Image Functionality

		exitItem.setOnAction(e -> {
			ScreenManager.previousScreen();
		});

		// Edit Functions \\
		undoItem.setOnAction(e -> {
			undo(canvas);
		});

		redoItem.setOnAction(e -> {
			redo(canvas);
		});

		// View Functions \\

		// TODO: Zoom Functionality

		// Tools Functions \\
		invertItem.setOnAction(e -> {
			gc.drawImage(InvertImage(canvas), 0, 0);
		});

		grayscaleItem.setOnAction(e -> {
			gc.drawImage(GrayscaleImage(canvas), 0, 0);
		});
	}

	/**
	 * Initialisation of JavaFX components on the Toolbar
	 */
	private void SetupToolbar() {
		paintBrushBtn.setToggleGroup(tools);
		paintBrushBtn.setSelected(true);

		paintBucketBtn.setToggleGroup(tools);

		lineToolBtn.setToggleGroup(tools);

		shapeToolBtn.setToggleGroup(tools);

		shapeSelector.getItems().addAll("Rectangle", "Triangle", "Oval");
		shapeSelector.setValue("Rectangle");
	}

	/**
	 * Initialisation of JavaFX components for the layout
	 */
	private void SetupLayout() {
		controlHeader.setPrefWidth(1280);
		controlHeader.getChildren().addAll(fileBtn, editBtn, viewBtn, toolsBtn);
		controlHeader.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, null, null)));

		header.setPrefWidth(1280);
		header.getChildren().addAll(paintBrushBtn, paintBucketBtn, lineToolBtn, shapeToolBtn, shapeSelector, cPicker,
				brushSize);
		header.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));

		drawWindow.getChildren().add(canvas);
		drawWindow.setAlignment(Pos.CENTER);

		content.getChildren().addAll(controlHeader, header, drawWindow);
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
		// Check if there are any actions to undo
		if (!previousStates.isEmpty()) {
			addFutureState(c);
			prevState = convertToImage(c);
			c.getGraphicsContext2D().drawImage(previousStates.pop(), 0, 0);
		}
	}

	private void redo(Canvas c) {
		if (!futureStates.isEmpty()) {
			addPreviousState(c);
			prevState = convertToImage(c);
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
		// Get the Pixel Reader and Writer from the canvas so we can read and write to
		// the image directly
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

	/*
	 * Temporary function, going to be fully implemented in WriteFile
	 */
	private void saveImage(Canvas c, File file) {
		WritableImage img = convertToImage(c);
		WriteFile.saveImageToUser(img, file);
	}

	private WritableImage InvertImage(Canvas c) {
		// Get the canvas and convert it to a Writable image so you can read and write
		// directly to the pixels
		WritableImage tmpImg = convertToImage(c);
		PixelReader pr = tmpImg.getPixelReader();
		PixelWriter pw = tmpImg.getPixelWriter();

		// Loop over every pixel and invert its colour
		for (int x = 0; x < c.getWidth(); x++) {
			for (int y = 0; y < c.getHeight(); y++) {
				pw.setColor(x, y, pr.getColor(x, y).invert());
			}
		}

		return tmpImg;
	}

	private WritableImage GrayscaleImage(Canvas c) {
		// Get the canvas and convert it to a Writable image so you can read and write
		// directly to the pixels
		WritableImage tmpImg = convertToImage(c);
		PixelReader pr = tmpImg.getPixelReader();
		PixelWriter pw = tmpImg.getPixelWriter();

		// Loop over every pixel and convert its colour to grayscale
		for (int x = 0; x < c.getWidth(); x++) {
			for (int y = 0; y < c.getHeight(); y++) {
				pw.setColor(x, y, pr.getColor(x, y).grayscale());
			}
		}

		return tmpImg;
	}
}
