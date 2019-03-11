package io;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;
import library.Library;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import resources.*;
import user.Librarian;
import user.User;
import utils.Queue;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <h1>WriteFile.</h1>
 * <p>This class handles all of the data storing for the program. It writes all of the data to JSON files, in which the
 * file path are stored in the IO class.</p>
 *
 * @author Samuel Jankinson, James Carter, Etienne Badoche
 */
public class WriteFile extends IO {
    /**
     * This method saves a user object to the user json file.
     *
     * @param user a user of the library.
     */
    @SuppressWarnings({"unchecked"})
    public static void writeUser(User user) {
        JSONObject object = new JSONObject();
        JSONArray resourceArray = new JSONArray();
        JSONArray transactionArray = new JSONArray();
        JSONArray borrowHistoryArray = new JSONArray();
        JSONObject borrowHistoryObject = new JSONObject();
        JSONArray jsonRequestedArray = new JSONArray();
        JSONArray jsonReservedArray = new JSONArray();
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
            JSONObject singleTransaction = new JSONObject();
            singleTransaction.put("System", transaction[0]);
            singleTransaction.put("Date", transaction[1]);
            singleTransaction.put("Amount", transaction[2]);
            transactionArray.add(singleTransaction);
        }
        object.put("transactionHistory", transactionArray);

        ArrayList<String[]> borrowHistoryStrings = user.getBorrowHistory();
        for (String[] borrowHistory : borrowHistoryStrings) {
            JSONObject borrowHistoryObj = new JSONObject();
            borrowHistoryObj.put("ID", borrowHistory[0]);
            borrowHistoryObj.put("Date", borrowHistory[1]);
            borrowHistoryArray.add(borrowHistoryObj);
        }
        object.put("borrowHistory", borrowHistoryArray);

        ArrayList<String> requestedArray = user.getAllRequested();
        for (String requested : requestedArray) {
            System.out.println(requested);
            jsonRequestedArray.add(requested);
        }
        object.put("requested", jsonRequestedArray);

        ArrayList<String> reservedArray = user.getAllReserved();
        for (String reserved : reservedArray) {
            jsonReservedArray.add(reserved);
        }
        object.put("reserved", jsonReservedArray);

//        ArrayList<Integer> fineArray = user.getFineHistory();
//        JSONArray fineHistoryObject = new JSONArray();
//        for (int amount : fineArray){
//            JSONObject fineData = new JSONObject();
//            fineData.put("Amount", amount);
//            fineHistoryObject.add(fineData);
//        }
//        object.put("fineHistory", fineHistoryObject);

        try {
            FileWriter file = new FileWriter(IO.getUsersFilePath(), true);
            file.write(object.toJSONString() + "\n");
            file.flush();
            file.close();
        } catch (IOException e) {
            System.out.println("Error writing user to " + IO.getUsersFilePath() + " " + user.getUserName());
        }
    }

    /**
     * This method saves a librarian object to the librarian json file. It saves everything to do with the librarian.
     *
     * @param librarian a librarian of the library.
     */
    @SuppressWarnings("unchecked")
    public static void writeLibrarian(Librarian librarian) {
        JSONObject object = new JSONObject();
        JSONArray resourceArray = new JSONArray();
        JSONArray transactionArray = new JSONArray();
        JSONArray borrowHistoryArray = new JSONArray();
        JSONArray jsonRequestedArray = new JSONArray();
        JSONArray jsonReservedArray = new JSONArray();

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

        for (String resource : librarian.getCurrentlyBorrowedResources()) {
            resourceArray.add(resource);
        }
        object.put("resourceBorrow", resourceArray);

        ArrayList<String[]> transactions = librarian.getTransactions();
        for (String[] transaction : transactions) {
            JSONArray singleTransaction = new JSONArray();
            singleTransaction.add(transaction[0]);
            singleTransaction.add(transaction[1]);
            singleTransaction.add(transaction[2]);
            transactionArray.add(singleTransaction);
        }
        object.put("transactionHistory", transactionArray);

        ArrayList<String[]> borrowHistoryStrings = librarian.getBorrowHistory();
        for (String[] borrowHistory : borrowHistoryStrings) {
            JSONArray borrowArray = new JSONArray();
            borrowArray.add(borrowHistory[0]);
            borrowArray.add(borrowHistory[1]);
            borrowHistoryArray.add(borrowArray);
        }
        object.put("borrowHistory", borrowHistoryArray);

        ArrayList<String> requestedArray = librarian.getAllRequested();
        for (String requested : requestedArray) {
            System.out.println(requested);
            jsonRequestedArray.add(requested);
        }
        object.put("requested", jsonRequestedArray);

        ArrayList<String> reservedArray = librarian.getAllReserved();
        for (String reserved : reservedArray) {
            jsonReservedArray.add(reserved);
        }
        object.put("reserved", jsonReservedArray);

        try {
            FileWriter file = new FileWriter(IO.getLibrarianFilePath(), true);
            file.write(object.toJSONString() + "\n");
            file.flush();
            file.close();
        } catch (IOException e) {
            System.out.println("Error writing user to " + IO.getLibrarianFilePath() + " " + librarian.getUserName());
        }
    }

    /**
     * This method saves a book to the book json file. It saves everything to do with the book.
     *
     * @param book a book of the library.
     */
    @SuppressWarnings("unchecked")
    public static void writeBook(Book book) {
        JSONObject object = new JSONObject();
        JSONArray languageArray = new JSONArray();
        JSONArray bookQueueArray = new JSONArray();
        JSONArray listOfLoanDur = new JSONArray();
        JSONArray bookBorrowHistoryArray = new JSONArray();
        JSONArray bookCurrentBorrowData = new JSONArray();

        object.put("year", book.getYear());
        object.put("title", book.getTitle());
        object.put("thumbnailImg", book.getThumbnailImageRef());
        object.put("uniqueID", book.getUniqueID());
        object.put("author", book.getAuthor());
        object.put("genre", book.getGenre());
        object.put("isbn", book.getIsbn());
        object.put("publisher", book.getPublisher());
        object.put("noOfCopies", String.valueOf(book.getNoOfCopies()));

        for (String language : book.getLanguages()) {
            languageArray.add(language);
        }
        object.put("languages", languageArray);

        Queue<User> bookQueue = book.getQueueOfReservations();
        while (!bookQueue.isEmpty()) {
            bookQueueArray.add(bookQueue.peek());
            bookQueue.dequeue();
        }
        object.put("bookQueue", bookQueueArray);

        for (int i = 0; i < book.getNoOfCopies(); i++) {
            listOfLoanDur.add(book.getLoanDuration(String.valueOf(i)));
        }
        object.put("listOfLoanDur", listOfLoanDur);

        BorrowHistorySetter(bookBorrowHistoryArray, book.getArrayListOfCopies());
        object.put("borrowHistory", bookBorrowHistoryArray);

        borrowDataSetter(bookCurrentBorrowData, book.getArrayListOfCopies());
        object.put("currentData", bookCurrentBorrowData);

        try {
            FileWriter file = new FileWriter(IO.getBookFilePath(), true);
            file.write(object.toJSONString() + "\n");
            file.flush();
            file.close();
        } catch (IOException e) {
            System.out.println("Error writing book to " + IO.getBookFilePath() + " " + book.getUniqueID());
        }
    }


    /**
     * This method writes (saves) a DVD to the DVD json file.
     *
     * @param dvd a DVD from the library.
     */
    @SuppressWarnings("unchecked")
    public static void writeDvd(DVD dvd) {
        JSONObject object = new JSONObject();
        JSONArray languageArray = new JSONArray();
        JSONArray dvdQueueArray = new JSONArray();
        JSONArray listOfLoanDur = new JSONArray();
        JSONArray dvdBorrowHistoryArray = new JSONArray();
        JSONArray dvdCurrentBorrowData = new JSONArray();

        object.put("year", dvd.getYear());
        object.put("title", dvd.getTitle());
        object.put("thumbnailImg", dvd.getThumbnailImageRef());
        object.put("uniqueID", dvd.getUniqueID());
        object.put("director", dvd.getDirector());
        object.put("runtime", dvd.getRuntime());
        object.put("language", dvd.getLanguage());
        object.put("noOfCopies", String.valueOf(dvd.getNoOfCopies()));

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

        for (int i = 0; i < dvd.getNoOfCopies(); i++) {
            listOfLoanDur.add(dvd.getLoanDuration(String.valueOf(i)));
        }
        object.put("listOfLoanDur", listOfLoanDur);

        BorrowHistorySetter(dvdBorrowHistoryArray, dvd.getArrayListOfCopies());
        object.put("borrowHistory", dvdBorrowHistoryArray);

        borrowDataSetter(dvdCurrentBorrowData, dvd.getArrayListOfCopies());
        object.put("currentData", dvdCurrentBorrowData);

        try {
            FileWriter file = new FileWriter(IO.getDvdFilePath(), true);
            file.write(object.toJSONString() + "\n");
            file.flush();
            file.close();
        } catch (IOException e) {
            System.out.println("Error writing dvd to " + IO.getDvdFilePath() + " " + dvd.getUniqueID());
        }
    }
    
    /**
     * This method saves a book to the book json file. It saves everything to do with the book.
     *
     * @param book a book of the library.
     */
    @SuppressWarnings("unchecked")
    public static void writeVideoGame(VideoGame videoGame) {
        JSONObject object = new JSONObject();
        JSONArray languageArray = new JSONArray();
        JSONArray videoGameQueueArray = new JSONArray();
        JSONArray listOfLoanDur = new JSONArray();
        JSONArray videoGameBorrowHistoryArray = new JSONArray();
        JSONArray videoGameCurrentBorrowData = new JSONArray();

        object.put("year", videoGame.getYear());
        object.put("title", videoGame.getTitle());
        object.put("thumbnailImg", videoGame.getThumbnailImageRef());
        object.put("uniqueID", videoGame.getUniqueID());
        object.put("publisher", videoGame.getPublisher());
        object.put("genre", videoGame.getGenre());
        object.put("multiplayerSupport", videoGame.getMultiplayerSupport());
        object.put("certificateRating", videoGame.getCertificateRating());
        object.put("noOfCopies", String.valueOf(videoGame.getNoOfCopies()));
        
        for (String language : videoGame.getLanguages()) {
            languageArray.add(language);
        }
        object.put("languages", languageArray);

        Queue<User> videoGameQueue = videoGame.getQueueOfReservations();
        while (!videoGameQueue.isEmpty()) {
            videoGameQueueArray.add(videoGameQueue.peek());
            videoGameQueue.dequeue();
        }
        object.put("videoGameQueue", videoGameQueueArray);

        for (int i = 0; i < videoGame.getNoOfCopies(); i++) {
            listOfLoanDur.add(videoGame.getLoanDuration(String.valueOf(i)));
        }
        object.put("listOfLoanDur", listOfLoanDur);

        BorrowHistorySetter(videoGameBorrowHistoryArray, videoGame.getArrayListOfCopies());
        object.put("borrowHistory", videoGameBorrowHistoryArray);

        borrowDataSetter(videoGameCurrentBorrowData, videoGame.getArrayListOfCopies());
        object.put("currentData", videoGameCurrentBorrowData);

        try {
            FileWriter file = new FileWriter(IO.getVideoGameFilePath(), true);
            file.write(object.toJSONString() + "\n");
            file.flush();
            file.close();
        } catch (IOException e) {
            System.out.println("Error writing video game to " + IO.getVideoGameFilePath() + " " + videoGame.getUniqueID());
        }
    }
    

    private static void BorrowHistorySetter(JSONArray resourceBorrowHistoryArray, List<CopyData> arrayListOfCopies) {
        for (CopyData copy : arrayListOfCopies) {
            JSONArray copyBorrowHistoryArray = new JSONArray();
            for (BorrowHistoryData borrowHistory : copy.getBorrowHistory()) {
                JSONArray borrowHistoryArray = new JSONArray();

                borrowHistoryArray.add(borrowHistory.getUserID());
                borrowHistoryArray.add(borrowHistory.getDateBorrowed());
                borrowHistoryArray.add(borrowHistory.getDateReturned());
                borrowHistoryArray.add(borrowHistory.getDateRequestedReturn());

                copyBorrowHistoryArray.add(borrowHistoryArray);
            }
            resourceBorrowHistoryArray.add(copyBorrowHistoryArray);
        }
    }


    private static void borrowDataSetter(JSONArray resourceCurrentBorrowData, List<CopyData> arrayListOfCopies) {
        for (CopyData copy : arrayListOfCopies) {
            JSONArray currentCopyBorrowData = new JSONArray();

            currentCopyBorrowData.add(copy.getCurrentInfo().getUserID());
            currentCopyBorrowData.add(copy.getCurrentInfo().getDateBorrowed());
            currentCopyBorrowData.add(copy.getCurrentInfo().getDateReturned());
            currentCopyBorrowData.add(copy.getCurrentInfo().getDateRequestedReturn());

            resourceCurrentBorrowData.add(currentCopyBorrowData);
        }
    }

    /**
     * This method writes (saves) a laptop to the laptop json file.
     *
     * @param laptop a laptop from the library.
     */
    @SuppressWarnings("unchecked")
    public static void writeLaptop(Laptop laptop) {
        JSONObject object = new JSONObject();
        JSONArray laptopQueueArray = new JSONArray();
        JSONArray listOfLoanDur = new JSONArray();
        JSONArray laptopBorrowHistoryArray = new JSONArray();
        JSONArray laptopCurrentBorrowData = new JSONArray();

        object.put("uniqueID", laptop.getUniqueID());
        object.put("manufacturer", laptop.getManufacturer());
        object.put("model", laptop.getModel());
        object.put("operatingSys", laptop.getOperatingSys());
        object.put("year", laptop.getYear());
        object.put("title", laptop.getTitle());
        object.put("thumbnailImg", laptop.getThumbnailImageRef());
        object.put("noOfCopies", String.valueOf(laptop.getNoOfCopies()));

        Queue<String> laptopQueue = laptop.getQueueOfReservations();
        while (!laptopQueue.isEmpty()) {
            laptopQueueArray.add(laptopQueue.peek());
            laptopQueue.dequeue();
        }
        object.put("dvdQueue", laptopQueueArray);

        for (int i = 0; i < laptop.getNoOfCopies(); i++) {
            listOfLoanDur.add(laptop.getLoanDuration(String.valueOf(i)));
        }
        object.put("listOfLoanDur", listOfLoanDur);

        BorrowHistorySetter(laptopBorrowHistoryArray, laptop.getArrayListOfCopies());
        object.put("borrowHistory", laptopBorrowHistoryArray);

        borrowDataSetter(laptopCurrentBorrowData, laptop.getArrayListOfCopies());
        object.put("currentData", laptopCurrentBorrowData);

        try {
            FileWriter file = new FileWriter(IO.getLaptopFilePath(), true);
            file.write(object.toJSONString() + "\n");
            file.flush();
            file.close();
        } catch (IOException e) {
            System.out.println("Error writing laptop to " + IO.getLaptopFilePath() + " " + laptop.getUniqueID());
        }
    }

    /**
     * This method overwrites the user json file with all of the users passed into the method.
     *
     * @param users an ArrayList of users from the library.
     */
    public static void overwriteUsers(ArrayList<User> users) {
        File usersFile = new File(IO.getUsersFilePath());

        if (usersFile.exists()) {
            usersFile.delete();
        }

        for (User user : users) {
            writeUser(user);
        }
    }

    /**
     * This method overwrites the librarian json file with all of the librarians passed into the method.
     *
     * @param librarians an ArrayList of librarians from the library.
     */
    public static void overwriteLibrarians(ArrayList<Librarian> librarians) {
        File librarianFile = new File(IO.getLibrarianFilePath());

        if (librarianFile.exists()) {
            librarianFile.delete();
        }

        for (Librarian librarian : librarians) {
            writeLibrarian(librarian);
        }
    }

    /**
     * This method overwrites all of the resource json files, with all of the resources passed into the method.
     *
     * @param books   an ArrayList of books from the library.
     * @param dvds    an ArrayList of dvds from the library.
     * @param laptops an ArrayList of laptops from the library.
     */
    public static void overwriteResources(ArrayList<Book> books, ArrayList<DVD> dvds, ArrayList<Laptop> laptops, ArrayList<VideoGame> videoGames) {
        File[] resourceFiles = {new File(IO.getBookFilePath()), new File(IO.getDvdFilePath()),
                new File(IO.getLaptopFilePath()), new File(IO.getVideoGameFilePath())};

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
        
        for (VideoGame videogame : videoGames) {
            writeVideoGame(videogame);
        }
    }

    /**
     * This method is used to overwrite all of data in the json files that the program is using.
     *
     * @param users      an ArrayList of users from the library.
     * @param books      an ArrayList of the books from the library.
     * @param dvds       an ArrayList of dvds from the library.
     * @param laptops    an ArrayList of laptops from the library.
     * @param librarians an ArrayList of librarians from the library.
     */
    public static void fullWrite(ArrayList<User> users, ArrayList<Book> books, ArrayList<DVD> dvds,
                                 ArrayList<Laptop> laptops,ArrayList<VideoGame> videoGames, ArrayList<Librarian> librarians) {

        overwriteUsers(users);
        overwriteResources(books, dvds, laptops, videoGames);
        overwriteLibrarians(librarians);
    }

    /**
     * This method backs up all of the current json files and creates new ones with the new data from the library
     * (does a full write).
     */
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

        currentFile = new File(IO.getVideoGameFilePath());
        currentFile.renameTo(new File("./data/backup/" + newFilePath + "/videogame.json"));
        
        currentFile = new File(IO.getLibrarianFilePath());
        currentFile.renameTo(new File("./data/backup/" + newFilePath + "/librarians.json"));

        fullWrite(Library.getAllUsers(), Library.getAllBooks(), Library.getAllDVD(), Library.getAllLaptops(), Library.getAllVideoGames(), Library.getAllLibrarians());
    }

    /**
     * This method saves as image to a specific file path.
     *
     * @param img  WritableImage to save.
     * @param file File path to save to.
     */
    public static void saveImageToUser(WritableImage img, File file) {
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(img, null), "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    /**
     * This method writes a new rating to the file.
     */
    public static void writeRatingToFile(String id, String message, String rating) {
    	JSONObject object = new JSONObject();
    	object.put("id", id);
    	object.put("message", message);
    	object.put("rating", rating);
    	object.put("username", Library.getCurrentLoggedInUser().getUserName());
    	
    	try {
            FileWriter file = new FileWriter(IO.getRatingsFilePath(), true);
            file.write(object.toJSONString() + "\n");
            file.flush();
            file.close();
        } catch (IOException e) {
            System.out.println("Error writing ratings to " + IO.getRatingsFilePath());
        }
    }
}
