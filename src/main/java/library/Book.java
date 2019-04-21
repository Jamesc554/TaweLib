package library;
import io.WriteFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Book.</h1>
 * <p>This class models the Library Resource: Book.</p>
 * @author Peter Daish, Deyan Naydenov, Dominik Wojtasiewicz 
 * @since 18/11/2018
 */
public class Book extends Resource {
	private final double FINE = 2.00; //The fine amount of a book when overdue.
	private final double MAX_FINE = 25.00; //The maximum fine value of any book.
	
	private static String highestBookID = "BookID:0"; //This is used to calculate a unique BookID.
	
	private int highestCopyID = 0; // this is used to calculate a unique CopyID.
	private String author; //Author of Book
	private String publisher; //Publisher of Book
	private String genre; //Genre of book (Optional)
	private String isbn;//ISBN of book (Optional)
	private ArrayList<String> languages; //The array of languages the book is currently available in (Optional).

	/**
	 * <h1>The constructor for a Book.</h1>
	 * @param year
	 * The year this book was published.
	 * @param title
	 * The title of this book.
	 * @param thumbnailImg
	 * The filepath of the avatar of this book.
	 * @param uniqueID
	 * The unique ID of this book.
	 * @param dateAdded
	 * the six digit integer specifying when the book was added.
	 * @param author
	 * The author of this book.
	 * @param genre
	 * The genre of this book.
	 * @param isbn
	 * The isbn of this book.
	 * @param publisher
	 * The publisher of this book.
	 * @param lang
	 * The languages this book is available in.
	 * @param noOfCopies
	 * The number of copies of this book currently owned by the library.
	 * @param loanDuration
	 * The loan duration of this book.
	 * @param borrowHistory
	 * The borrow history of a copy of this book.
	 * @param currentBorrowData
	 * The information for borrower and dates associated with a copy of a resource.
	 */
	public Book(String dateAdded, String year, String title, String thumbnailImg, String uniqueID, String author,
			String genre, String isbn, String publisher, ArrayList<String> lang, Integer noOfCopies,
			ArrayList<String> loanDuration, List<List<BorrowHistoryData>> borrowHistory,
			List<BorrowHistoryData> currentBorrowData) {
		//Set all the inherited values from Resource...
		super(year, title, thumbnailImg, generateBookID(uniqueID), dateAdded, noOfCopies, loanDuration, borrowHistory, currentBorrowData);
		
		//Set all non-inherited values
		this.author = author;
		this.publisher = publisher;
		this.genre = genre;
		this.isbn = isbn;
		
		//If lang from file is null, create a new arraylist. otherwise set languages to lang from file.
		if (lang == null) {
			this.languages = new ArrayList<String>();
		} else {
			this.languages = lang;
		}	
	}

    /**
     * <h1>The constructor for a Book.</h1>
     * @param baseResource  The resource with all the base properties
     * @param author        The Author of the book
     * @param genre         The Genre of the book
     * @param isbn          The ISBN of the book
     * @param publisher     The publisher of the book
     * @param languages     The Languages of the book
     */
	public Book(Resource baseResource, String author, String genre, String isbn, String publisher, ArrayList<String> languages){
	    super(baseResource);

	    this.uniqueID = generateBookID(baseResource.uniqueID);

	    this.author = author;
	    this.publisher = publisher;
	    this.genre = genre;
	    this.isbn = isbn;

	    if (languages == null){
	        this.languages = new ArrayList<>();
        } else {
	        this.languages = languages;
        }
    }
	
	/**
	 * Generates a uniqueID for a book.
	 * @param id The stated ID of the book being created.
	 * @return highestBookID the book ID of the latest created book
	 */
	private static String generateBookID(String id) {
		//split book ID into two strings if ID == null.
		//convert anything after ": " into Int
		//append 1
		// convert back to String
		//assign uniqueID
		//If id != null.
		//we are loading a book from a file thus set highestBookID to id.
		
		if (id == null) {
		String[] segmentsOfString = highestBookID.split(":");
		String typeSegment = segmentsOfString[0];
		String numberSegmentOfString = segmentsOfString[1];
		int numberOfID = Integer.parseInt(numberSegmentOfString);
		numberOfID++;
		highestBookID = typeSegment + ":" + Integer.toString(numberOfID);
		return highestBookID;
		} else {
			highestBookID = id;
			return highestBookID;
		}
	}
	/* This method returns the Book's attributes as a string (FOR TESTING PURPOSES ONLY)
		public String toString() {
			return "Year: " + this.year + "\nTitle: " + this.title +
			   "\nThumbnailImageRef: " + this.thumbnailImageRef +
			   "\nuniqueID: " + this.uniqueID +
			   "\nAuthor; " + this.author +
			   "\nPublisher: " + this.publisher +
			   "\nGenre: " + this.genre +
			   "\nISBN: " + this.isbn + 
			   "\nLanguages: " + this.languages +
			   "\nQueueOfReservations: " + this.queueOfReservations.isEmpty();
		}*/
	/* #############################################################
	 * ########  BELOW ARE THE GETTERS AND SETTERS OF BOOK  ########
	 * #############################################################
	 */
	
	/**
	 * Sets a value to calculate Book IDs from.
	 * @param hBookID 
	 * The highest current value of any Book's ID.
	 */
	public void setHighestBookID(String hBookID) {
		this.highestBookID = hBookID;
	}
	
	/**
	 * Returns the ID of the latest Book.
	 * @return highestBookID 
	 * The highest current id of any book.
	 */
	public String getHighestBookID() {
		return this.highestBookID;
	}
	
	/**
	 * Sets a value to calculate a new copy's id from.
	 * @param hCopyID 
	 * The latest ID of any copy.
	 */
	public void setHighestCopyID(int hCopyID) {
		this.highestCopyID = hCopyID;
	}
	
	/**
	 * Returns the newest copy's ID
	 * @return highestCopyID 
	 * The newest copy's ID.
	 */
	public int getHighestCopyID() {
		return this.highestCopyID;
	}
	
	/**
	 * Returns the author of the book
	 * @return author
	 * The author of this book.
	 */
	public String getAuthor() {
		return author;
	}
	
	/**
	 * Sets the author of this book
	 * @param author 
	 * The new author of this book
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Returns the publisher of this book
	 * @return publisher
	 * The publisher of this book
	 */
	public String getPublisher() {
		return this.publisher;
	}

	/**
	 * Sets the publisher of this book
	 * @param publisher
	 * The new publisher of this book
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * Gets the genre of this book
	 * @return genre
	 * The genre of this book.
	 */
	public String getGenre() {
		return this.genre;
	}

	/**
	 * Sets the genre of this book
	 * @param genre
	 * The new genre of this book.
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * Gets the ISBN of this book
	 * @return isbn
	 * The isbn of this book
	 */
	public String getIsbn() {
		return this.isbn;
	}

	/**
	 * Sets the ISBN of this book
	 * @param isbn
	 * The new ISBN of this book.
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * Gets the languages this book is available in
	 * @return languages
	 * The languages this book is available in.
	 */
	public ArrayList<String> getLanguages() {
		return this.languages;
	}

	/**
	 * Sets the languages this book is available in.
	 * @param languages
	 * The languages this book will become available in.
	 */
	public void setLanguages(ArrayList<String> languages) {
		this.languages = languages;
	}
	
	/* #############################################################
	 * ########  BELOW ARE THE COMPLEX METHODS OF BOOK  	########
	 * #############################################################
	 */
	
	/**
	 * Adds a new language to this book
	 * @param newLang
	 * The new languages to be added.
	 */
	public void addLanguage(String newLang) {
		this.languages.add(newLang);
	}
	
	/**
	 * Removes a specific languages from this book
	 * @param langToRemove
	 * The language to be removed.
	 */
	public void removeLanguage(String langToRemove) {
		//search through arrayList and remove language selected.
		for (int i = 0; i < this.languages.size(); i++) {
			if (this.languages.get(i) == langToRemove) {
				this.languages.remove(i);
			}
		}
	}

	/** 
	 * Generates a copy ID based off of previous copyID.
	 * @return hCpyID the current highest ID of a copy
	 */
	private String generateCopyID() {
		//
		this.highestCopyID++;
		String hCpyID = Integer.toString(this.highestCopyID);
		return hCpyID;
	}
	
	/**
	 * Converts Book to single string
	 * @return a String representation of this book.
	 * Returns the details of this book.
	 */
	public String toSingleString() {
		return super.toSingleString() + ", Author:" + this.author + ", Publisher:" + this.publisher + ", Genre:" + this.genre + ", ISBN:" + this.isbn;
	}

	public String toJSON(){
        return WriteFile.writeBookToObject(this).toJSONString();
    }
	/**
	 * Returns the daily fine rate of this resource.
	 * @return FINE
	 * The daily fine rate of this resource.
	 */
	public double getFineAmount() {
		return this.FINE;
	}

	/**
	 * Returns the maximum fine this resource can reach.
	 * @return MAX_FINE
	 * The maximum fine a resource can reach.
	 */
	public double getMaxFine() {
		return  this.MAX_FINE;
	}
}