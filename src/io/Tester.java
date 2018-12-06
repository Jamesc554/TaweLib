package io;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import resources.Book;
import resources.DVD;
import user.Librarian;
import user.User;

/**
 * @author Samuel Jankinson
 */

public class Tester {
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		Librarian bob = new Librarian("jane", "jane", "spongebob", "01-2-1-do-1", "123 Pineapple Road", "Sketty", "SA1 0A2", "Swansea", 99999, ".data/images/jane/default_image_4.png", 10, 20, 1969, "69696", 1);
		WriteFile.writeLibrarian(bob);
	}
}
