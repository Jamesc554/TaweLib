package io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;
import library.Library;
import resources.Book;
import resources.DVD;
import resources.Laptop;
import resources.Resource;
import user.Librarian;
import user.User;
import utils.Queue;

/**
 * @author Samuel Jankinson
 */

public class WriteFile extends IO {
	@SuppressWarnings({ "unchecked" })
	// will add borrow history etc after discussed with meeting.
	public static void writeUser(User user) {
		JSONObject object = new JSONObject();
		JSONArray resourceArray = new JSONArray();
		JSONArray transactionArray = new JSONArray();
		JSONArray borrowHistoryArray = new JSONArray();
		object.put("username", user.getUserName());
		object.put("firstName", user.getFirstName());
		object.put("lastName", user.getLastName());
		object.put("mobileNumber", user.getMobileNumber());
		object.put("firstLineAddress", user.getFirstLineAddress());
		object.put("secondLineAddress", user.getSecondLineAddress());
		object.put("postCode", user.getPostCode());
		object.put("townName", user.getTownName());
		object.put("imageAddress", user.getProfImage());
		object.put("accountBalance", String.valueOf(user.getAccountBalanceDouble()));

		for (String resource : user.getCurrentlyBorrowedResources()) {
			resourceArray.add(resource);
		}
		object.put("resourceBorrow", resourceArray);

		ArrayList<String[]> transactions = user.getTransactions();
		for (String[] transaction : transactions) {
			JSONArray singleTransaction = new JSONArray();
			singleTransaction.add(transaction[0]);
			singleTransaction.add(transaction[1]);
			transactionArray.add(singleTransaction);
		}
		object.put("transactionHistory", transactionArray);

		ArrayList<String[]> borrowHistoryStrings = user.getBorrowHistory();
		for (String[] borrowHistory : borrowHistoryStrings) {
			JSONArray borrowArray = new JSONArray();
			borrowArray.add(borrowHistory[0]);
			borrowArray.add(borrowHistory[1]);
			borrowHistoryArray.add(borrowArray);
		}
		object.put("borrowHistory", borrowHistoryArray);
		
		//TODO: Currently Requested
		
		//TODO: Currently Reserved

		try {
			FileWriter file = new FileWriter(IO.getUsersFilePath(), true);
			file.write(object.toJSONString() + "\n");
			file.flush();
			file.close();
		} catch (IOException e) {
			System.out.println("Error writing user to " + IO.getUsersFilePath() + " " + user.getUserName());
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void writeLibrarian(Librarian librarian) {
		JSONObject object = new JSONObject();
		
		object.put("username", librarian.getUserName());
		object.put("firstName", librarian.getFirstName());
		object.put("lastName", librarian.getLastName());
		object.put("mobileNumber", librarian.getMobileNumber());
		object.put("firstLineAddress", librarian.getFirstLineAddress());
		object.put("secondLineAddress", librarian.getSecondLineAddress());
		object.put("postCode", librarian.getPostCode());
		object.put("townName", librarian.getTownName());
		object.put("imageAddress", librarian.getProfImage());
		object.put("accountBalance", String.valueOf(librarian.getAccountBalanceDouble()));
		object.put("empDay", String.valueOf(librarian.getEmploymentDay()));
		object.put("empMonth", String.valueOf(librarian.getEmploymentMonth()));
		object.put("empYear", String.valueOf(librarian.getEmploymentYear()));
		object.put("staffNumber", librarian.getStaffNumber());
		object.put("noOfEmploys", String.valueOf(librarian.getNumberOfEmploys()));
		
		try {
			FileWriter file = new FileWriter(IO.getLibrarianFilePath(), true);
			file.write(object.toJSONString() + "\n");
			file.flush();
			file.close();
		} catch (IOException e) {
			System.out.println("Error writing user to " + IO.getLibrarianFilePath() + " " + librarian.getUserName());
		}
	}

	@SuppressWarnings("unchecked")
	public static void writeBook(Book book) {
		JSONObject object = new JSONObject();
		JSONArray languageArray = new JSONArray();
		JSONArray bookQueueArray = new JSONArray();
		JSONArray listOfCopiesArray = new JSONArray();
		object.put("year", book.getYear());
		object.put("title", book.getTitle());
		object.put("thumbnailImg", book.getThumbnailImageRef());
		object.put("uniqueID", book.getUniqueID());
		object.put("author", book.getAuthor());
		object.put("genre", book.getGenre());
		object.put("isbn", book.getIsbn());
		object.put("publisher", book.getPublisher());

		for (String language : book.getLanguages()) {
			languageArray.add(language);
		}
		object.put("languages", languageArray);

		Queue<User> bookQueue = book.getQueueOfReservations();
		while (!bookQueue.isEmpty()) {
			bookQueueArray.add(bookQueue.peek().getUserName());
			bookQueue.dequeue();
		}
		object.put("bookQueue", bookQueueArray);

		for (String copies : book.getArrayListOfCopies()) {
			listOfCopiesArray.add(copies);
		}
		object.put("listOfCopies", listOfCopiesArray);

		try {
			FileWriter file = new FileWriter(IO.getBookFilePath(), true);
			file.write(object.toJSONString() + "\n");
			file.flush();
			file.close();
		} catch (IOException e) {
			System.out.println("Error writing book to " + IO.getBookFilePath() + " " + book.getUniqueID());
		}
	}

	// will be changed to Dvd dvd and Laptop laptop when those classes are added to
	// git.
	@SuppressWarnings("unchecked")
	public static void writeDvd(DVD dvd) {
		JSONObject object = new JSONObject();
		JSONArray languageArray = new JSONArray();
		JSONArray dvdQueueArray = new JSONArray();
		JSONArray listOfCopiesArray = new JSONArray();

		object.put("year", dvd.getYear());
		object.put("title", dvd.getTitle());
		object.put("thumbnailImg", dvd.getThumbnailImageRef());
		object.put("uniqueID", dvd.getUniqueID());
		object.put("director", dvd.getDirector());
		object.put("runtime", dvd.getRuntime());
		object.put("language", dvd.getLanguage());

		for (String language : dvd.getSubLang()) {
			languageArray.add(language);
		}
		object.put("sub-languages", languageArray);

		Queue<User> dvdQueue = dvd.getQueueOfReservations();
		while (!dvdQueue.isEmpty()) {
			dvdQueueArray.add(dvdQueue.peek().getUserName());
			dvdQueue.dequeue();
		}
		object.put("dvdQueue", dvdQueueArray);
		
		for (String copies : dvd.getArrayListOfCopies()) {
			listOfCopiesArray.add(copies);
		}
		object.put("listOfCopies", listOfCopiesArray);

		try {
			FileWriter file = new FileWriter(IO.getDvdFilePath(), true);
			file.write(object.toJSONString() + "\n");
			file.flush();
			file.close();
		} catch (IOException e) {
			System.out.println("Error writing dvd to " + IO.getDvdFilePath() + " " + dvd.getUniqueID());
		}
	}

	@SuppressWarnings("unchecked")
	public static void writeLaptop(Laptop laptop) {
		JSONObject object = new JSONObject();
		JSONArray laptopQueueArray = new JSONArray();
		JSONArray listOfCopiesArray = new JSONArray();
		
		object.put("uniqueID", laptop.getUniqueID());
		object.put("manufacturer", laptop.getManufacturer());
		object.put("model", laptop.getModel());
		object.put("operatingSys", laptop.getOperatingSys());
		object.put("year", laptop.getYear());
		object.put("title", laptop.getTitle());
		object.put("thumbnailImg", laptop.getThumbnailImageRef());
		
		Queue<User> laptopQueue = laptop.getQueueOfReservations();
		while (!laptopQueue.isEmpty()) {
			laptopQueueArray.add(laptopQueue.peek().getUserName());
			laptopQueue.dequeue();
		}
		object.put("dvdQueue", laptopQueueArray);
		
		for (String copies : laptop.getArrayListOfCopies()) {
			listOfCopiesArray.add(copies);
		}
		object.put("listOfCopies", listOfCopiesArray);
		
		try {
			FileWriter file = new FileWriter(IO.getLaptopFilePath(), true);
			file.write(object.toJSONString() + "\n");
			file.flush();
			file.close();
		} catch (IOException e) {
			System.out.println("Error writing laptop to " + IO.getLaptopFilePath() + " " + laptop.getUniqueID());
		}
	}

	public static void overwriteUsers(ArrayList<User> users) {
		File usersFile = new File(IO.getUsersFilePath());

		if (usersFile.exists()) {
			usersFile.delete();
		}

		for (User user : users) {
			writeUser(user);
		}
	}

	public static void overwriteResources(ArrayList<Book> books, ArrayList<DVD> dvds, ArrayList<Laptop> laptops) {
		File[] resourceFiles = { new File(IO.getBookFilePath()), new File(IO.getDvdFilePath()),
				new File(IO.getLaptopFilePath()) };

		for (File resourceFile : resourceFiles) {
			if (resourceFile.exists()) {
				resourceFile.delete();
			}

		}

		for (Book book : books) {
			writeBook(book);
		}

		for (DVD dvd : dvds) {
			writeDvd(dvd);
		}

		for (Laptop laptop : laptops) {
			writeLaptop(laptop);
		}
	}

	public static void fullWrite(ArrayList<User> users, ArrayList<Book> books, ArrayList<DVD> dvds,
			ArrayList<Laptop> laptops) {
		overwriteUsers(users);
		overwriteResources(books, dvds, laptops);
	}

	public static void backupCurrent() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
		String newFilePath = dateFormat.format(new Date());
		new File("./data/backup/" + newFilePath).mkdirs();
		File currentFile = null;

		currentFile = new File(IO.getUsersFilePath());
		currentFile.renameTo(new File("./data/backup/" + newFilePath + "/users.json"));

		currentFile = new File(IO.getBookFilePath());
		currentFile.renameTo(new File("./data/backup/" + newFilePath + "/book.json"));
		
		currentFile = new File(IO.getDvdFilePath());
		currentFile.renameTo(new File("./data/backup/" + newFilePath + "/dvd.json"));
		
		currentFile = new File(IO.getLaptopFilePath());
		currentFile.renameTo(new File("./data/backup/" + newFilePath + "/laptop.json"));
		
		fullWrite(Library.getAllUsers(), Library.getAllBooks(), Library.getAllDVD(), Library.getAllLaptops());
	}

	public static void saveImageToUser(WritableImage img, String fileName) {
		String currentUserName = Library.getCurrentLoggedInUser().getUserName();
		String directoryPath = ("./data/images/" + currentUserName);
		String filePath = (directoryPath + "/" + fileName + ".png");

		new File(directoryPath).mkdirs();
		File file = new File(filePath);

		try {
			ImageIO.write(SwingFXUtils.fromFXImage(img, null), "png", file);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
