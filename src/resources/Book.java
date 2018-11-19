package resources;
import java.util.ArrayList;
import java.util.Dictionary;
import utils.Queue;

/**
 * This class models a Library book.
 * @author Peter Daish
 * @since 18/11/2018
 */
public class Book extends Resource {
	private String author; //Author of Book
	private String publisher; //Publisher of Book
	private String genre; //Genre of book (Optional)
	private String isbn;//ISBN of book (Optional)
	private ArrayList<String> languages; //The array of languages the book is currently available in (Optional).
	
	
	/**
	 * The base constructor for Book including all optional attributes.
	 * @param author
	 * The author of the book.
	 * @param genre
	 * The genre of the book.
	 * @param publisher
	 * The publisher of the book.
	 * @param isbn
	 * The book's ISBN.
	 * @param lang
	 * The languages the book is currently available in.
	 * @param year
	 * The year the book was published.
	 * @param title
	 * The title of the book.
	 * @param thumbnailImg
	 * The filepath of the book's avatar.
	 * @param uniqueID
	 * The unique id of the book.
	 * @param dictionaryOfCopies
	 * To keep track of copies of this book.
	 * @param queueOfReservations
	 * To keep track of users who have reserved this book.
	 * @param borrowHistory
	 * The borrow history of each copy of this book.
	 */
	public Book(String author, String genre, String publisher, String isbn, ArrayList<String> lang, 
			String year, String title, String thumbnailImg, String uniqueID, Dictionary dictionaryOfCopies, 
			Queue queueOfReservations, Dictionary borrowHistory) {
		
		//Set all the inherited values from Resource...
		super(year, title, thumbnailImg, uniqueID, dictionaryOfCopies, queueOfReservations, borrowHistory);
		
		//Set all non-inherited values
		this.author = author;
		this.genre = genre;
		this.publisher = publisher;
		this.isbn = isbn;
		this.languages = lang;
	}
	
	/*############################################################
	  ######  BELOW ARE THE CONSTRUCTORS WITH "genre" IN  ########
	  ############################################################
	*/
	
	/**
	 * This constructor for Book including genre attribute.
	 * @param author
	 * The author of the book.
	 * @param genre
	 * The genre of the book.
	 * @param publisher
	 * The publisher of the book.
	 * @param year
	 * The year the book was published.
	 * @param title
	 * The title of the book.
	 * @param thumbnailImg
	 * The filepath of the book's avatar.
	 * @param uniqueID
	 * The unique id of the book.
	 * @param dictionaryOfCopies
	 * To keep track of copies of this book.
	 * @param queueOfReservations
	 * To keep track of users who have reserved this book.
	 * @param borrowHistory
	 * The borrow history of each copy of this book.
	 */
	public Book(String author, String genre, String publisher, String year, String title, String thumbnailImg,
			String uniqueID, Dictionary dictionaryOfCopies, 
			Queue queueOfReservations, Dictionary borrowHistory) {
		
		//Set all the inherited values from Resource...
		super(year, title, thumbnailImg, uniqueID, dictionaryOfCopies, queueOfReservations, borrowHistory);
		
		//Set all non-inherited values
		this.author = author;
		this.genre = genre;
		this.publisher = publisher;
		// isbn and languages are left blank due to being optional extras.
	}
	
	/**
	 * This constructor for Book including genre and isbn attributes.
	 * @param author
	 * The author of the book.
	 * @param genre
	 * The genre of the book.
	 * @param isbn
	 * The isbn of this book.
	 * @param publisher
	 * The publisher of the book.
	 * @param year
	 * The year the book was published.
	 * @param title
	 * The title of the book.
	 * @param thumbnailImg
	 * The filepath of the book's avatar.
	 * @param uniqueID
	 * The unique id of the book.
	 * @param dictionaryOfCopies
	 * To keep track of copies of this book.
	 * @param queueOfReservations
	 * To keep track of users who have reserved this book.
	 * @param borrowHistory
	 * The borrow history of each copy of this book.
	 */
	public Book(String author, String genre, String isbn, String publisher, String year, String title, String thumbnailImg,
			String uniqueID, Dictionary dictionaryOfCopies, 
			Queue queueOfReservations, Dictionary borrowHistory) {
		
		//Set all the inherited values from Resource...
		super(year, title, thumbnailImg, uniqueID, dictionaryOfCopies, queueOfReservations, borrowHistory);
		
		//Set all non-inherited values
		this.author = author;
		this.genre = genre;
		this.publisher = publisher;
		this.isbn = isbn;
		//languages is left blank due to being optional extra.
	}

	/**
	 * This constructor for Book including genre and languages attributes.
	 * @param author
	 * The author of the book.
	 * @param genre
	 * The genre of the book.
	 * @param lang
	 * The languages this book is currently available in.
	 * @param publisher
	 * The publisher of the book.
	 * @param year
	 * The year the book was published.
	 * @param title
	 * The title of the book.
	 * @param thumbnailImg
	 * The filepath of the book's avatar.
	 * @param uniqueID
	 * The unique id of the book.
	 * @param dictionaryOfCopies
	 * To keep track of copies of this book.
	 * @param queueOfReservations
	 * To keep track of users who have reserved this book.
	 * @param borrowHistory
	 * The borrow history of each copy of this book.
	 */
	public Book(String author, String genre, ArrayList<String> lang, String publisher, String year, String title, String thumbnailImg,
			String uniqueID, Dictionary dictionaryOfCopies, 
			Queue queueOfReservations, Dictionary borrowHistory) {
		
		//Set all the inherited values from Resource...
		super(year, title, thumbnailImg, uniqueID, dictionaryOfCopies, queueOfReservations, borrowHistory);
		
		//Set all non-inherited values
		this.author = author;
		this.genre = genre;
		this.publisher = publisher;
		this.languages = lang;
		//isbn is left blank due to being optional extra.
	}

	/* #############################################################
	 * ########  BELOW ARE THE CONSTRUCTORS WITH "isbn" IN  ########
	 * #############################################################
	 */
	
	/**
	 * This constructor for Book including genre and languages attributes.
	 * @param author
	 * The author of the book.
	 * @param isbn
	 * The ISBN of this book.
	 * @param publisher
	 * The publisher of the book.
	 * @param year
	 * The year the book was published.
	 * @param title
	 * The title of the book.
	 * @param thumbnailImg
	 * The filepath of the book's avatar.
	 * @param uniqueID
	 * The unique id of the book.
	 * @param dictionaryOfCopies
	 * To keep track of copies of this book.
	 * @param queueOfReservations
	 * To keep track of users who have reserved this book.
	 * @param borrowHistory
	 * The borrow history of each copy of this book.
	 */
	public Book(String author, String isbn, String publisher, String year, String title, String thumbnailImg,
			String uniqueID, Dictionary dictionaryOfCopies, 
			Queue queueOfReservations, Dictionary borrowHistory) {
		
		//Set all the inherited values from Resource...
		super(year, title, thumbnailImg, uniqueID, dictionaryOfCopies, queueOfReservations, borrowHistory);
		
		//Set all non-inherited values
		this.author = author;
		this.isbn = isbn;
		this.publisher = publisher;
		//isbn is left blank due to being optional extra.
	}
	
	/**
	 * This constructor for Book including genre and languages attributes.
	 * @param author
	 * The author of the book.
	 * @param isbn
	 * The ISBN of this book.
	 * @param lang
	 * The languages this book is published in.
	 * @param publisher
	 * The publisher of the book.
	 * @param year
	 * The year the book was published.
	 * @param title
	 * The title of the book.
	 * @param thumbnailImg
	 * The filepath of the book's avatar.
	 * @param uniqueID
	 * The unique id of the book.
	 * @param dictionaryOfCopies
	 * To keep track of copies of this book.
	 * @param queueOfReservations
	 * To keep track of users who have reserved this book.
	 * @param borrowHistory
	 * The borrow history of each copy of this book.
	 */
	public Book(String author, String isbn, ArrayList<String> lang, String publisher, String year, String title, String thumbnailImg,
			String uniqueID, Dictionary dictionaryOfCopies, 
			Queue queueOfReservations, Dictionary borrowHistory) {
		
		//Set all the inherited values from Resource...
		super(year, title, thumbnailImg, uniqueID, dictionaryOfCopies, queueOfReservations, borrowHistory);
		
		//Set all non-inherited values
		this.author = author;
		this.isbn = isbn;
		this.languages = lang;
		this.publisher = publisher;
		//isbn is left blank due to being optional extra.
	}

	/* #############################################################
	 * ########  BELOW IS THE CONSTRUCTOR WITH "lang" IN  ########
	 * #############################################################
	 */
	
	/** This constructor for Book including genre and languages attributes.
	 * @param author
	 * The author of the book.
	 * @param lang
	 * The languages this book is published in.
	 * @param publisher
	 * The publisher of the book.
	 * @param year
	 * The year the book was published.
	 * @param title
	 * The title of the book.
	 * @param thumbnailImg
	 * The filepath of the book's avatar.
	 * @param uniqueID
	 * The unique id of the book.
	 * @param dictionaryOfCopies
	 * To keep track of copies of this book.
	 * @param queueOfReservations
	 * To keep track of users who have reserved this book.
	 * @param borrowHistory
	 * The borrow history of each copy of this book.
	 */
	public Book(String author, ArrayList<String> lang, String publisher, String year, String title, String thumbnailImg,
			String uniqueID, Dictionary dictionaryOfCopies, 
			Queue queueOfReservations, Dictionary borrowHistory) {
		
		//Set all the inherited values from Resource...
		super(year, title, thumbnailImg, uniqueID, dictionaryOfCopies, queueOfReservations, borrowHistory);
		
		//Set all non-inherited values
		this.author = author;
		this.languages = lang;
		this.publisher = publisher;
		//isbn is left blank due to being optional extra.
	}



	/* #############################################################
	 * ########  BELOW ARE THE GETTERS AND SETTERS OF BOOK  ########
	 * #############################################################
	 */
	
	/**
	 * Returns the author of the book
	 * @return author
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
		return publisher;
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
		return genre;
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
		return isbn;
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
		return languages;
	}

	/**
	 * Sets the languages this book is available in.
	 * @param languages
	 * The languages this book will become available in.
	 */
	public void setLanguages(ArrayList<String> languages) {
		this.languages = languages;
	}
	
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
			} else {
				//print can't find book.
			}
		}
	}
	
}