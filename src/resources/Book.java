package resources;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;

import user.User;
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
	 * The Constructor for Book.
	 * @param author
	 * @param genre
	 * @param publisher
	 * @param isbn
	 * @param lang
	 * @param year
	 * @param title
	 * @param thumbnailImg
	 * @param uniqueID
	 */
	public Book(String year,String title, String thumbnailImg, String uniqueID,
			String author, String genre, String isbn, String publisher, ArrayList<String> lang) {
		
		//Set all the inherited values from Resource...
		super(year, title, thumbnailImg, uniqueID);
		
		//Set all non-inherited values
		this.author = author;
		this.publisher = publisher;
		this.genre = null;
		this.isbn = null;
		this.languages = new ArrayList<String>();
		
	}
	
	/**
	 * This Method Prints out this Book's attributes for testing purposes.
	 */
	public String toString() {
		return "Year: " + this.year + "\nTitle: " + this.title +
			   "\nThumbnailImageRef: " + this.thumbnailImageRef +
			   "\nuniqueID: " + this.uniqueID +
			   "\nAuthor; " + this.author +
			   "\nPublisher: " + this.publisher +
			   "\nGenre: " + this.genre +
			   "\nISBN: " + this.isbn + 
			   "\nLanguages: " + this.languages +
			   "\nQueueOfReservations: " + this.queueOfReservations +
			   "\nMap Of copies: " + this.dictionaryOfCopies + 
			   "\nMap of borrowHistory: " + this.borrowHistory;
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