package resources;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>VideoGame.</h1>
 * <p>
 * This class models the VideoGame Resource.
 * </p>
 * 
 * @author Peter Daish, Ammar Alamri, Dominik Wojtasiewicz
 * @since 05/03/2019
 */

public class VideoGame extends Resource {
	private final double FINE = 2.00; // The fine amount of a video game when overdue.
	private final double MAX_FINE = 25.00; // The maximum fine value of any video game.

	private static String highestGameID = "VideoGameID:0"; // This is used to calculate a unique GameID.

	private int highestCopyID = 0; // this is used to calculate a unique CopyID.
	private String publisher; // Publisher of game
	private String genre; // Genre of game 
	private String certificateRating;// certificate rating of game
	private String multiplayerSupport;
	private ArrayList<String> languages; // The array of languages the game is currently available in .

	/**
	 * <h1>The constructor for a Video Game.</h1>
	 * 
	 * @param dateAdded			The date this game was added.
	 * @param year              The year this game was published.
	 * @param title             The title of this game.
	 * @param thumbnailImg      The filepath of the avatar of this game.
	 * @param uniqueID          The unique ID of this game.
	 * @param genre             The genre of this game.
	 * @param certRating        The certificate rating of this game.
	 * @param publisher         The publisher of this book.
	 * @param multSupp			The multiplayer support this game offers.
	 * @param lang              The languages this book is available in.
	 * @param noOfCopies        The number of copies of this book currently owned by
	 *                          the library.
	 * @param loanDuration      The loan duration of this book.
	 * @param borrowHistory     The borrow history of a copy of this book.
	 * @param currentBorrowData The information for borrower and dates associated
	 *                          with a copy of a resource.
	 */
	public VideoGame(String dateAdded, String year, String title, String thumbnailImg, String uniqueID, String genre, String certRating,
			String publisher, String multSupp, ArrayList<String> lang, Integer noOfCopies,
			ArrayList<String> loanDuration, List<List<BorrowHistoryData>> borrowHistory,
			List<BorrowHistoryData> currentBorrowData) {
		// Set all the inherited values from Resource...
		super(year, title, thumbnailImg, generateGameID(uniqueID), dateAdded, noOfCopies, loanDuration, borrowHistory,
				currentBorrowData);

		// Set all non-inherited values
		this.publisher = publisher;
		this.genre = genre;
		this.certificateRating = certRating;
		this.multiplayerSupport = multSupp;

		// If lang from file is null, create a new arraylist. otherwise set languages to
		// lang from file.
		if (lang == null) {
			this.languages = new ArrayList<String>();
		} else {
			this.languages = lang;
		}
	}

    /**
     * <h1>The constructor for a Video Game.</h1>
     *
     * @param baseResource      The resource with all the base properties
     * @param genre             The genre of this game.
     * @param certRating        The certificate rating of this game.
     * @param publisher         The publisher of this video game.
     * @param multSupp          If this video game has multiplayer support
     * @param languages         The languages this video game supports
     */
    public VideoGame(Resource baseResource, String genre, String certRating, String publisher, String multSupp, ArrayList<String> languages){
        super(baseResource);

        this.uniqueID = generateGameID(baseResource.uniqueID);

        // Set all non-inherited values
        this.publisher = publisher;
        this.genre = genre;
        this.certificateRating = certRating;
        this.multiplayerSupport = multSupp;

        // If lang from file is null, create a new arraylist. otherwise set languages to
        // lang from file.
        if (languages == null) {
            this.languages = new ArrayList<String>();
        } else {
            this.languages = languages;
        }
    }

	/**
	 * Generates a uniqueID for a game.
	 * 
	 * @param id The stated ID of the game being created.
	 * @return highestGameID the game ID of the latest created game
	 */
	private static String generateGameID(String id) {
		// split Game ID into two strings if ID == null.
		// convert anything after ": " into Int
		// append 1
		// convert back to String
		// assign uniqueID
		// If id != null.
		// we are loading a Game from a file thus set highestGameID to id.

		if (id == null) {
			String[] segmentsOfString = highestGameID.split(":");
			String typeSegment = segmentsOfString[0];
			String numberSegmentOfString = segmentsOfString[1];
			int numberOfID = Integer.parseInt(numberSegmentOfString);
			numberOfID++;
			highestGameID = typeSegment + ":" + Integer.toString(numberOfID);
			return highestGameID;
		} else {
			highestGameID = id;
			return highestGameID;
		}
	}

	/*
	 * ############################################################# ######## BELOW
	 * ARE THE GETTERS AND SETTERS OF VideoGame ########
	 * #############################################################
	 */

	/**
	 * Sets a value to calculate Game IDs from.
	 * 
	 * @param hGameID The highest current value of any Game's ID.
	 */
	public void setHighestBookID(String hGameID) {
		this.highestGameID = hGameID;
	}

	/**
	 * Returns the ID of the latest Game.
	 * 
	 * @return highestGameID The highest current id of any game.
	 */
	public String getHighestBookID() {
		return this.highestGameID;
	}

	/**
	 * Sets a value to calculate a new copy's id from.
	 * 
	 * @param hCopyID The latest ID of any copy.
	 */
	public void setHighestCopyID(int hCopyID) {
		this.highestCopyID = hCopyID;
	}

	/**
	 * Returns the newest copy's ID
	 * 
	 * @return highestCopyID The newest copy's ID.
	 */
	public int getHighestCopyID() {
		return this.highestCopyID;
	}

	/**
	 * Returns the publisher of this game
	 * 
	 * @return publisher The publisher of this game
	 */
	public String getPublisher() {
		return this.publisher;
	}

	/**
	 * Sets the publisher of this game
	 * 
	 * @param publisher The new publisher of this game
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * Gets the genre of this game
	 * 
	 * @return genre The genre of this game.
	 */
	public String getGenre() {
		return this.genre;
	}

	/**
	 * Sets the genre of this game
	 * 
	 * @param genre The new genre of this game.
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * Gets the languages this game is available in
	 * 
	 * @return languages The languages this game is available in.
	 */
	 public ArrayList<String> getLanguages() {
	  return this.languages; 
	  }

	/**
	 * Sets the languages this game is available in.
	 * 
	 * @param languages The languages this game will become available in.
	 */
	public void setLanguages(ArrayList<String> languages) { 
		this.languages = languages;
		}
		 

	/*
	 * #############################################################
	 * ######## BELOW ARE THE COMPLEX METHODS OF Game       ########
	 * #############################################################
	 */

	/**
	 * Adds a new language to this game
	 * 
	 * @param newLang The new languages to be added.
	 */
	
	  public void addLanguage(String newLang) { 
		  this.languages.add(newLang);
		  }
	  
	 /**
		 * Removes a specific languages from this game
		 * 
		 * @param langToRemove The language to be removed.
		 */
	  public void removeLanguage(String langToRemove) { //search through arrayList and remove language selected. 
		  for (int i = 0; i < this.languages.size(); i++) { 
			  if (this.languages.get(i) == langToRemove) { 
				  this.languages.remove(i); 
			  } 
		  }
	  }
			 

	/**
	 * Generates a copy ID based off of previous copyID.
	 * 
	 * @return hCpyID the current highest ID of a copy
	 */
	private String generateCopyID() {
		//
		this.highestCopyID++;
		String hCpyID = Integer.toString(this.highestCopyID);
		return hCpyID;
	}

	/**
	 * Converts Game to single string
	 * 
	 * @return a String representation of this Game. Returns the details of this
	 *         Game.
	 */
	public String toSingleString() {
		return super.toSingleString() + this.publisher + this.genre + this.certificateRating + this.multiplayerSupport;
	}

	/**
	 * Returns the daily fine rate of this resource.
	 * 
	 * @return FINE The daily fine rate of this resource.
	 */
	public double getFineAmount() {
		return this.FINE;
	}

	/**
	 * Returns the maximum fine this resource can reach.
	 * 
	 * @return MAX_FINE The maximum fine a resource can reach.
	 */
	public double getMaxFine() {
		return this.MAX_FINE;
	}

	/**
	 * Returns this video game's certificate rating.
	 * @return certificateRating the age rating of this video game
	 */
	public String getCertificateRating() {
		return certificateRating;
	}

	/**
	 * Sets this video game's certificate rating.
	 * @param certificateRating the certificateRating to set for this video game
	 */
	public void setCertificateRating(String certificateRating) {
		this.certificateRating = certificateRating;
	}

	/**
	 * Returns the multiplayer support status of this video game.
	 * @return multiplayerSupport returns whether the game supports multiplayer
	 */
	public String getMultiplayerSupport() {
		return multiplayerSupport;
	}

	/**
	 * Sets whether this video game supports multiplayer.
	 * 
	 * @param multiplayerSupport whether the game is multiplayer.
	 */
	public void setMultiplayerSupport(String multiplayerSupport) {
		this.multiplayerSupport = multiplayerSupport;
	}
}