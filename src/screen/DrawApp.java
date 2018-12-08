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

/**
 * <h1>This class models a drawing application for profile images.</h1>
 * @author James Carter
 * @version 1.0
 */
public class DrawApp extends Screen {
	// JavaFX Components
	// Control Header \\
	private static final MenuButton FILE_BTN = new MenuButton("File");
	private static final MenuButton EDIT_BTN = new MenuButton("Edit");
	private static final MenuButton VIEW_BTN = new MenuButton("View");
	private static final MenuButton TOOLS_BTN = new MenuButton("Tools");

	private static final MenuItem SAVE_ITEM = new MenuItem("Save");
	private static final MenuItem SAVE_AS_ITEM = new MenuItem("Save As");
	private static final MenuItem LOAD_ITEM = new MenuItem("Load");
	private static final MenuItem EXIT_ITEM = new MenuItem("Exit");

	private static final MenuItem UNDO_ITEM = new MenuItem("Undo");
	private static final MenuItem REDO_ITEM = new MenuItem("Redo");

	private static final MenuItem ZOOM_IN_ITEM = new MenuItem("Zoom In");
	private static final MenuItem ZOOM_OUT_ITEM = new MenuItem("Zoom Out");

	private static final MenuItem INVERT_ITEM = new MenuItem("Invert Colours");
	private static final MenuItem GRAYSCALE_ITEM = new MenuItem("Convert to Grayscale");

	// Toolbar \\
	private static final ToggleGroup TOOLS = new ToggleGroup();
	private static final RadioButton PAINT_BRUSH_ITEM = new RadioButton("Paint Brush");
	private static final RadioButton PAINT_BUCKET_BTN = new RadioButton("Paint Bucket");
	private static final RadioButton LINE_TOOL_BTN = new RadioButton("Draw Line");

	private static final RadioButton SHAPE_TOOL_BTN = new RadioButton("Shape Tool");
	private static final ComboBox<String> SHAPE_SELECTOR = new ComboBox<>();

	private static final ColorPicker C_PICKER = new ColorPicker();
	private static final Spinner<Integer> BRUSH_SIZE = new Spinner<Integer>(0, 64, 4);

	// Canvas \\
	private static final Canvas CANVAS = new Canvas(256, 256);
	private static final GraphicsContext GC = CANVAS.getGraphicsContext2D();

	// Shapes \\
	private static final Line LINE = new Line();
	private static final Rectangle RECTANGLE = new Rectangle();
	private static final TriangleMesh TRIANGLE = new TriangleMesh();
	private static final Circle CIRCLE = new Circle();

	// Layout \\
	private static final HBox DRAW_WINDOW = new HBox(10);
	private static final HBox CONTROL_HEADER = new HBox(4);
	private static final HBox HEADER = new HBox(8);
	private static final VBox CONTENT = new VBox(0);


	private WritableImage prevState = null;

	private Stack<WritableImage> previousStates;
	private Stack<WritableImage> futureStates;

	
	@Override
	public void start() {
		components = new ArrayList<>();
		previousStates = new Stack<>();
		futureStates = new Stack<>();

		SetupControlHeader();
		SetupToolbar();
		SetupLayout();

		GC.setFill(Color.WHITE);
		GC.fillRect(0, 0, CANVAS.getWidth(), CANVAS.getHeight());

		CANVAS.setOnMousePressed(mouse -> CanvasMousePressed(mouse));
		CANVAS.setOnMouseDragged(mouse -> CanvasMouseDragged(mouse));
		CANVAS.setOnMouseReleased(mouse -> CanvasMouseReleased(mouse));

	}

	// Canavs Mouse Events \\

	/**
	 * The event called when the mouse is pressed on the canvas
	 * 
	 * @param mouse The event object
	 */
	private void CanvasMousePressed(MouseEvent mouse) {
		addPreviousState(CANVAS);
		GC.setLineWidth((double) BRUSH_SIZE.getValueFactory().getValue());
		GC.setStroke(C_PICKER.getValue());
		GC.setFill(C_PICKER.getValue());
		if (PAINT_BRUSH_ITEM.isSelected()) {
			GC.beginPath();
			GC.lineTo(mouse.getX(), mouse.getY());
			prevState = convertToImage(CANVAS);
		} else if (PAINT_BUCKET_BTN.isSelected()) {
			GC.drawImage(paintBucket(C_PICKER.getValue(), (int) mouse.getX(), (int) mouse.getY(), CANVAS), 0, 0);
			prevState = convertToImage(CANVAS);
		} else if (LINE_TOOL_BTN.isSelected()) {
			LINE.setStartX(mouse.getX());
			LINE.setStartY(mouse.getY());
		} else if (SHAPE_TOOL_BTN.isSelected()) {
			switch (SHAPE_SELECTOR.getValue()) {
			case ("Rectangle"):
				RECTANGLE.setX(mouse.getX());
				RECTANGLE.setY(mouse.getY());
				break;
			case ("Triangle"):
				// TODO: Triangle Implementation
				break;
			case ("Oval"):
				CIRCLE.setCenterX(mouse.getX());
				CIRCLE.setCenterY(mouse.getY());
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
		if (PAINT_BRUSH_ITEM.isSelected()) {
			GC.strokeOval(mouse.getX(), mouse.getY(), (double) BRUSH_SIZE.getValueFactory().getValue(),
					(double) BRUSH_SIZE.getValueFactory().getValue());
			GC.lineTo(mouse.getX(), mouse.getY());
		} else if (PAINT_BUCKET_BTN.isSelected()) {
			// TODO: Paint Bucket Implementation
		} else if (LINE_TOOL_BTN.isSelected()) {
			GC.drawImage(prevState, 0, 0);
			prevState = convertToImage(CANVAS);
			GC.strokeLine(LINE.getStartX(), LINE.getStartY(), mouse.getX(), mouse.getY());
		} else if (SHAPE_TOOL_BTN.isSelected()) {
			GC.drawImage(prevState, 0, 0);
			prevState = convertToImage(CANVAS);
			switch (SHAPE_SELECTOR.getValue()) {
			case ("Rectangle"):
				RECTANGLE.setWidth(mouse.getX() - RECTANGLE.getX());
				RECTANGLE.setHeight(mouse.getY() - RECTANGLE.getY());
				GC.fillRect(RECTANGLE.getX(), RECTANGLE.getY(), RECTANGLE.getWidth(), RECTANGLE.getHeight());
				break;
			case ("Triangle"):
				break;
			case ("Oval"):
				CIRCLE.setRadius((mouse.getX() + mouse.getY()) - (CIRCLE.getCenterX() + CIRCLE.getCenterY()));
				GC.fillOval(CIRCLE.getCenterX() - CIRCLE.getRadius() / 2, CIRCLE.getCenterY() - CIRCLE.getRadius() / 2,
						CIRCLE.getRadius(), CIRCLE.getRadius());
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
		if (PAINT_BRUSH_ITEM.isSelected()) {
			GC.strokeOval(mouse.getX(), mouse.getY(), (double) BRUSH_SIZE.getValueFactory().getValue(),
					(double) BRUSH_SIZE.getValueFactory().getValue());
			GC.lineTo(mouse.getX(), mouse.getY());
			GC.closePath();
			prevState = convertToImage(CANVAS);
		} else if (PAINT_BUCKET_BTN.isSelected()) {
			// TODO: Paint Bucket Implementation
		} else if (LINE_TOOL_BTN.isSelected()) {
			GC.drawImage(prevState, 0, 0);
			GC.strokeLine(LINE.getStartX(), LINE.getStartY(), mouse.getX(), mouse.getY());
			prevState = convertToImage(CANVAS);
		} else if (SHAPE_TOOL_BTN.isSelected()) {
			switch (SHAPE_SELECTOR.getValue()) {
			case ("Rectangle"):
				GC.drawImage(prevState, 0, 0);
				GC.fillRect(RECTANGLE.getX(), RECTANGLE.getY(), RECTANGLE.getWidth(), RECTANGLE.getHeight());
				prevState = convertToImage(CANVAS);
				break;
			case ("Triangle"):
				break;
			case ("Oval"):
				GC.drawImage(prevState, 0, 0);
				GC.fillOval(CIRCLE.getCenterX() - CIRCLE.getRadius() / 2, CIRCLE.getCenterY() - CIRCLE.getRadius() / 2,
						CIRCLE.getRadius(), CIRCLE.getRadius());
				prevState = convertToImage(CANVAS);
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
		FILE_BTN.getItems().addAll(SAVE_ITEM, LOAD_ITEM, EXIT_ITEM);
		EDIT_BTN.getItems().addAll(UNDO_ITEM, REDO_ITEM);
		VIEW_BTN.getItems().addAll(ZOOM_IN_ITEM, ZOOM_OUT_ITEM);
		TOOLS_BTN.getItems().addAll(INVERT_ITEM, GRAYSCALE_ITEM);

		// File Functions \\
		SAVE_ITEM.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
			fileChooser.getExtensionFilters().add(extFilter);
			String currentUserName = Library.getCurrentLoggedInUser().getUserName();
			String directoryPath = ("./data/images/" + currentUserName);
			File initDir = new File(directoryPath);
			fileChooser.setInitialDirectory(initDir);

			File file = fileChooser.showSaveDialog(ScreenManager.getStage());

			if (file != null)
				saveImage(CANVAS, file);
		});

		// TODO: Load Image Functionality

		EXIT_ITEM.setOnAction(e -> {
			ScreenManager.previousScreen();
		});

		// Edit Functions \\
		UNDO_ITEM.setOnAction(e -> {
			undo(CANVAS);
		});

		REDO_ITEM.setOnAction(e -> {
			redo(CANVAS);
		});

		// View Functions \\

		// TODO: Zoom Functionality

		// Tools Functions \\
		INVERT_ITEM.setOnAction(e -> {
			GC.drawImage(InvertImage(CANVAS), 0, 0);
		});

		GRAYSCALE_ITEM.setOnAction(e -> {
			GC.drawImage(GrayscaleImage(CANVAS), 0, 0);
		});
	}

	/**
	 * Initialisation of JavaFX components on the Toolbar
	 */
	private void SetupToolbar() {
		PAINT_BRUSH_ITEM.setToggleGroup(TOOLS);
		PAINT_BRUSH_ITEM.setSelected(true);

		PAINT_BUCKET_BTN.setToggleGroup(TOOLS);

		LINE_TOOL_BTN.setToggleGroup(TOOLS);

		SHAPE_TOOL_BTN.setToggleGroup(TOOLS);

		SHAPE_SELECTOR.getItems().addAll("Rectangle", "Triangle", "Oval");
		SHAPE_SELECTOR.setValue("Rectangle");
	}

	/**
	 * Initialisation of JavaFX components for the layout
	 */
	private void SetupLayout() {
		CONTROL_HEADER.setPrefWidth(1280);
		CONTROL_HEADER.getChildren().addAll(FILE_BTN, EDIT_BTN, VIEW_BTN, TOOLS_BTN);
		CONTROL_HEADER.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, null, null)));

		HEADER.setPrefWidth(1280);
		HEADER.getChildren().addAll(PAINT_BRUSH_ITEM, PAINT_BUCKET_BTN, LINE_TOOL_BTN, SHAPE_TOOL_BTN, SHAPE_SELECTOR, C_PICKER,
				BRUSH_SIZE);
		HEADER.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));

		DRAW_WINDOW.getChildren().add(CANVAS);
		DRAW_WINDOW.setAlignment(Pos.CENTER);

		CONTENT.getChildren().addAll(CONTROL_HEADER, HEADER, DRAW_WINDOW);
		CONTENT.setAlignment(Pos.TOP_LEFT);

		components.add(CONTENT);
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
