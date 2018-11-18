package io;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Tester {
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		WriteFile.writeUsers("ben22", "ben", "smith", "07947147999", "123 Berry Road", "Swansea", "SA20AB", "Sketty", "ben22profile.jpg", 430);
		WriteFile.writeUsers("harry23", "harry", "smith", "07483147999", "76 Pumpkin Road", "Oxford", "OX38AB", "Oxford", "harry23profile.jpg", 0);
		System.out.println(ReadFile.readUsers());
	}
}
