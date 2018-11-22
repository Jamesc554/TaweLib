package resources;
/**
 * This class is solely to test Book
 * @author PeterDaish
 * @since 19/11/2018
 */
public class BookTest {

	public static void main(String[] args) {
		//Test Book Function.
		Book lordOfRings = new Book("1943", "The Lord Of The Rings: The Fellowship of the ring",
									"path", "00-00-00", "J.R.R Tolkein", "History", "124454", "Tolkein Estate", null );
		
		//Print out book created
		System.out.println(lordOfRings.toString());
		
		System.out.println("\n\n");
		//Add a new language to book
		lordOfRings.addLanguage("English");
		
		System.out.println(lordOfRings.toString());
		//add another new language
		lordOfRings.addLanguage("German");
		
		System.out.println(lordOfRings.toString());
		//delete language
		lordOfRings.removeLanguage("English");
		System.out.println(lordOfRings.toString());
		
		//Add Copy
		lordOfRings.addToCopies();
		System.out.println(lordOfRings.toString());
		//add another
		lordOfRings.addToCopies();
		System.out.println(lordOfRings.toString());
		//Delete one copy
		lordOfRings.removeCopy();
		System.out.println(lordOfRings.toString());
		//Set Optional Attributes
		lordOfRings.addToCopies();
		lordOfRings.setGenre("Fantasy");
		lordOfRings.setIsbn("123456789");
		System.out.println(lordOfRings.toString());
		lordOfRings.addToCopies();
		System.out.println(lordOfRings.toString());
		lordOfRings.removeCopy();
		lordOfRings.removeCopy();
		lordOfRings.removeCopy();
		lordOfRings.removeCopy();
		lordOfRings.addToCopies();
		System.out.println(lordOfRings.toString());
		
		//System.out.println("Int to string: " + Integer.toString(2));
	}
 
}
