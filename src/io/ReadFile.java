package io;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import library.Library;
import resources.Book;
import resources.BorrowHistoryData;
import resources.DVD;
import resources.Laptop;
import user.Librarian;
import user.User;

@SuppressWarnings("Duplicates")
/**
 * <h1>ReadFile.</h1>
 * <p>This class reads data from files saved on disk.</p>
 * @author Samuel Jankinson, James Carter, Etienne Badoche
 */
public class ReadFile extends IO {
	private static FileReader file = null;
	private static BufferedReader reader = null;
	private static String currentLine = null;

	/**
	 * Returns users read from file.
	 * @return userList
	 * A list of users stores on disk.
	 */
	public static ArrayList<User> readUsers() {
		JSONParser parser = new JSONParser();
		JSONArray resourceArray = new JSONArray();
		JSONArray transactionArray = new JSONArray();
		JSONArray borrowHistoryArray = new JSONArray();
		JSONArray requestedArray = new JSONArray();
		JSONArray reservedArray = new JSONArray();
		ArrayList<User> userList = new ArrayList<>();
		try {
			file = new FileReader(IO.getUsersFilePath());
			reader = new BufferedReader(file);
			while ((currentLine = reader.readLine()) != null) {
				JSONObject object = (JSONObject) parser.parse(currentLine);
				User user = new User((String) object.get("username"), (String) object.get("firstName"),
						(String) object.get("lastName"), (String) object.get("mobileNumber"),
						(String) object.get("firstLineAddress"), (String) object.get("secondLineAddress"),
						(String) object.get("postCode"), (String) object.get("townName"),
						Double.parseDouble((String) object.get("accountBalance")), (String) object.get("imageAddress"), Integer.valueOf((String) object.get("noOfResources")));

				resourceArray = (JSONArray) object.get("resourceBorrow");
				ArrayList<String> borrowedResources = new ArrayList<String>();
				if (resourceArray != null) {
					for (Object resource : resourceArray) {
						String stringResource = (String) resource;
						borrowedResources.add(stringResource);
					}
					user.setResourceCurrentlyBorrowed(borrowedResources);
				}

				transactionArray = (JSONArray) object.get("transactionHistory");
				if (transactionArray != null) {
					for (Object transactionInformation : transactionArray) {
						JSONArray transactionInformationArray = (JSONArray) transactionInformation;
						String[] data = new String[3];
						data[0] = (String) transactionInformationArray.get(0);
						data[1] = (String) transactionInformationArray.get(1);
						data[2] = (String) transactionInformationArray.get(2);
						user.addToTransactionHistory(data);
					}
				}

				borrowHistoryArray = (JSONArray) object.get("borrowHistory");
				if (borrowHistoryArray != null) {
					for (Object borrowInformation : borrowHistoryArray) {
						JSONArray borrowInformationArray = (JSONArray) borrowInformation;
						String[] data = new String[2];
						data[1] = (String) borrowInformationArray.get(0);
						data[0] = (String) borrowInformationArray.get(1);
						user.addToBorrowHistory(data);
					}
				}

				requestedArray = (JSONArray) object.get("requested");
				if (requestedArray != null) {
					for (Object requestedResource : requestedArray) {
						String requestedResourceID = (String) requestedResource;
						user.requestResource(requestedResourceID);
					}
				}

				reservedArray = (JSONArray) object.get("reserved");
				if (reservedArray != null) {
					for (Object reservedResource : requestedArray) {
						String reservedResourceID = (String) reservedResource;
						user.addToReserved(reservedResourceID);
					}
				}
				userList.add(user);
			}

			reader.close();
			file.close();
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find " + IO.getUsersFilePath());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("ERROR reading file " + IO.getUsersFilePath());
			e.printStackTrace();
		} catch (ParseException e) {
			System.out.println("ERROR parsing users JSON");
			e.printStackTrace();
		}
		return userList;
	}

	/**
	 * Returns a list of librarians currently employed by the library.
	 * @return librarianList
	 * The list of librarians employed by this library.
	 */
	public static ArrayList<Librarian> readLibrarians() {
		JSONParser parser = new JSONParser();
		JSONArray resourceArray = new JSONArray();
		JSONArray transactionArray = new JSONArray();
		JSONArray borrowHistoryArray = new JSONArray();
		JSONArray requestedArray = new JSONArray();
		JSONArray reservedArray = new JSONArray();
		ArrayList<Librarian> librarianList = new ArrayList<>();
		try {
			file = new FileReader(IO.getLibrarianFilePath());
			reader = new BufferedReader(file);
			while ((currentLine = reader.readLine()) != null) {
				JSONObject object = (JSONObject) parser.parse(currentLine);
				Librarian librarian = new Librarian((String) object.get("username"), (String) object.get("firstName"),
						(String) object.get("lastName"),
						(String) object.get("mobileNumber"),
						(String) object.get("firstLineAddress"),
						(String) object.get("secondLineAddress"),
						(String) object.get("postCode"),
						(String) object.get("townName"),
						Double.parseDouble((String) object.get("accountBalance")),
						(String) object.get("imageAddress"),
						Integer.parseInt((String) object.get("empDay")),
						Integer.parseInt((String) object.get("empMonth")),
						Integer.parseInt((String) object.get("empYear")), (String) object.get("staffNumber"),
						Integer.parseInt((String) object.get("noOfEmploys")),
						Integer.valueOf((String) object.get("noOfResources")));
				
				resourceArray = (JSONArray) object.get("resourceBorrow");
				ArrayList<String> borrowedResources = new ArrayList<String>();
				if (resourceArray != null) {
					for (Object resource : resourceArray) {
						String stringResource = (String) resource;
						borrowedResources.add(stringResource);
					}
					librarian.setResourceCurrentlyBorrowed(borrowedResources);
				}

				transactionArray = (JSONArray) object.get("transactionHistory");
				if (transactionArray != null) {
					for (Object transactionInformation : transactionArray) {
						JSONArray transactionInformationArray = (JSONArray) transactionInformation;
						String[] data = new String[3];
						data[0] = (String) transactionInformationArray.get(0);
						data[1] = (String) transactionInformationArray.get(1);
						data[2] = (String) transactionInformationArray.get(2);
						librarian.addToTransactionHistory(data);
					}
				}

				borrowHistoryArray = (JSONArray) object.get("borrowHistory");
				if (borrowHistoryArray != null) {
					for (Object borrowInformation : borrowHistoryArray) {
						JSONArray borrowInformationArray = (JSONArray) borrowInformation;
						String[] data = new String[2];
						data[1] = (String) borrowInformationArray.get(0);
						data[0] = (String) borrowInformationArray.get(1);
						librarian.addToBorrowHistory(data);
					}
				}

				requestedArray = (JSONArray) object.get("requested");
				if (requestedArray != null) {
					for (Object requestedResource : requestedArray) {
						String requestedResourceID = (String) requestedResource;
						librarian.requestResource(requestedResourceID);
					}
				}

				reservedArray = (JSONArray) object.get("reserved");
				if (reservedArray != null) {
					for (Object reservedResource : requestedArray) {
						String reservedResourceID = (String) reservedResource;
						librarian.addToReserved(reservedResourceID);
					}
				}
				librarianList.add(librarian);
			}
			reader.close();
			file.close();
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find " + IO.getLibrarianFilePath());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("ERROR reading file " + IO.getLibrarianFilePath());
			e.printStackTrace();
		} catch (ParseException e) {
			System.out.println("ERROR parsing users JSON");
			e.printStackTrace();
		}
		return librarianList;
	}

	/**
	 * Returns a list of books owned by this library.
	 * @return bookList
	 * The list of books currently owned by this library.
	 */
	public static ArrayList<Book> readBooks() {
		JSONParser parser = new JSONParser();
		JSONArray languageArray = new JSONArray();
		JSONArray bookQueueArray = new JSONArray();
		JSONArray listOfLoanDur = new JSONArray();
		JSONArray bookBorrowHistoryArray = new JSONArray();
		JSONArray bookCurrentBorrowData = new JSONArray();
		ArrayList<Book> bookList = new ArrayList<Book>();
		try {
			file = new FileReader(IO.getBookFilePath());
			reader = new BufferedReader(file);
			while ((currentLine = reader.readLine()) != null) {
				JSONObject object = (JSONObject) parser.parse(currentLine);
				String year = ((String) object.get("year"));
				String title = ((String) object.get("title"));
				String thumbnailImg = ((String) object.get("thumbnailImg"));
				String uniqueID = ((String) object.get("uniqueID"));
				String author = ((String) object.get("author"));
				String genre = ((String) object.get("genre"));
				String isbn = ((String) object.get("isbn"));
				String publisher = ((String) object.get("publisher"));
				ArrayList<String> languages = new ArrayList<>();
				int noOfCopies = Integer.parseInt((String) object.get("noOfCopies"));
				List<List<BorrowHistoryData>> borrowHistory = new ArrayList<>();
				List<BorrowHistoryData> currentData = new ArrayList<>();
				ArrayList<String> loanDurs = new ArrayList<String>();
				System.out.println("Loading Resource: " + uniqueID);
				listOfLoanDur = (JSONArray) object.get("listOfLoanDur");
				if (listOfLoanDur != null) {
					for (Object loanDur : listOfLoanDur) {
						String loanDurString = (String) loanDur;
						loanDurs.add(loanDurString);
					}
				}
				
				bookBorrowHistoryArray = (JSONArray) object.get("borrowHistory");
				if (bookBorrowHistoryArray != null) {
					int i = 0;
					for (Object copyBorrowHistoryObject : bookBorrowHistoryArray) {
						JSONArray copyBorrowHistoryArray = (JSONArray) copyBorrowHistoryObject;
						List<BorrowHistoryData> copyBorrowHistoryData = new ArrayList<>();
						System.out.println("Loading Copy History for: " + uniqueID + ":" + i++);
						for (Object borrowHistoryObject : copyBorrowHistoryArray) {
							JSONArray borrowHistoryArray = (JSONArray) borrowHistoryObject;
							BorrowHistoryData borrowHistoryData = new BorrowHistoryData();
							borrowHistoryData.setUserID((String) borrowHistoryArray.get(0));
							borrowHistoryData.setDateBorrowed((String) borrowHistoryArray.get(1));
							borrowHistoryData.setDateReturned((String) borrowHistoryArray.get(2));
							borrowHistoryData.setDateRequestedReturn((String) borrowHistoryArray.get(3));
							copyBorrowHistoryData.add(borrowHistoryData);
						}
						borrowHistory.add(copyBorrowHistoryData);
					}
				}
				
				bookCurrentBorrowData = (JSONArray) object.get("currentData");
				if (bookCurrentBorrowData != null) {
					for (Object copyCurrentBorrowDataObject : bookCurrentBorrowData) {
						JSONArray copyCurrentBorrowDataArray = (JSONArray) copyCurrentBorrowDataObject;
						
						BorrowHistoryData borrowHistoryData = new BorrowHistoryData();
						borrowHistoryData.setUserID((String) copyCurrentBorrowDataArray.get(0));
						borrowHistoryData.setDateBorrowed((String) copyCurrentBorrowDataArray.get(1));
						borrowHistoryData.setDateReturned((String) copyCurrentBorrowDataArray.get(2));
						borrowHistoryData.setDateRequestedReturn((String) copyCurrentBorrowDataArray.get(3));
						
						currentData.add(borrowHistoryData);
					}
				}

				languageArray = (JSONArray) object.get("languages");
				if (languageArray != null) {
					for (Object language : languageArray) {
						String stringLanguage = (String) language;
						languages.add(stringLanguage);
					}
				}

				Book bookToAdd = new Book(year, title, thumbnailImg, uniqueID, author, genre, isbn, publisher, languages,
						noOfCopies, loanDurs, borrowHistory, currentData);

				bookQueueArray = (JSONArray) object.get("bookQueue");
				if (bookQueueArray != null) {
					for (Object user : bookQueueArray) {
						String username = (String) user;
						bookToAdd.addUserToRequestQueue(username);
					}
				}
				

				bookList.add(bookToAdd);
			}

			reader.close();
			file.close();
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find " + IO.getBookFilePath());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("ERROR reading file " + IO.getBookFilePath());
			e.printStackTrace();
		} catch (ParseException e) {
			System.out.println("ERROR parsing users JSON");
			e.printStackTrace();
		}
		return bookList;
	}

	/**
	 * Returns a list of DVDs owned by this library.
	 * @return dvds
	 * The list of dvds owned by this library.
	 */
	public static ArrayList<DVD> readDvds() {
		JSONParser parser = new JSONParser();
		JSONArray languageArray = new JSONArray();
		JSONArray dvdQueueArray = new JSONArray();
		JSONArray listOfLoanDur = new JSONArray();
		JSONArray dvdBorrowHistoryArray = new JSONArray();
		JSONArray dvdCurrentBorrowData = new JSONArray();
		ArrayList<DVD> dvds = new ArrayList<DVD>();

		try {
			file = new FileReader(IO.getDvdFilePath());
			reader = new BufferedReader(file);

			while ((currentLine = reader.readLine()) != null) {
				JSONObject object = (JSONObject) parser.parse(currentLine);

				String year = ((String) object.get("year"));
				String title = ((String) object.get("title"));
				String thumbnailImageRef = ((String) object.get("thumbnailImg"));
				String uniqueID = ((String) object.get("uniqueID"));
				String director = ((String) object.get("director"));
				String runtime = ((String) object.get("runtime"));
				String language = ((String) object.get("language"));
				int noOfCopies = Integer.parseInt((String) object.get("noOfCopies"));
				List<List<BorrowHistoryData>> borrowHistory = new ArrayList<>();
				List<BorrowHistoryData> currentData = new ArrayList<>();
				
				System.out.println("Loading Resource: " + uniqueID);

				languageArray = (JSONArray) object.get("sub-languages");
				ArrayList<String> subLang = new ArrayList<>();
				if (languageArray != null) {
					for (Object lang : languageArray) {
						String stringLanguage = (String) language;
						subLang.add(stringLanguage);
					}
				}

				dvdQueueArray = (JSONArray) object.get("bookQueue");
				String dvdQueues = "";
				if (dvdQueueArray != null) {
					for (Object bookQueue : dvdQueueArray) {
						String stringBookQueue = (String) bookQueue;
						dvdQueues += stringBookQueue + ",";
					}
				}

				ArrayList<String> loanDurs = new ArrayList<String>();

				listOfLoanDur = (JSONArray) object.get("listOfLoanDur");
				if (listOfLoanDur != null) {
					for (Object loanDur : listOfLoanDur) {
						String loanDurString = (String) loanDur;
						loanDurs.add(loanDurString);
					}
				}
				
				dvdBorrowHistoryArray = (JSONArray) object.get("borrowHistory");
				if (dvdBorrowHistoryArray != null) {
					int i = 0;
					for (Object copyBorrowHistoryObject : dvdBorrowHistoryArray) {
						JSONArray copyBorrowHistoryArray = (JSONArray) copyBorrowHistoryObject;
						List<BorrowHistoryData> copyBorrowHistoryData = new ArrayList<>();

						System.out.println("Loading Copy History for: " + uniqueID + ":" + i++);
						for (Object borrowHistoryObject : copyBorrowHistoryArray) {
							JSONArray borrowHistoryArray = (JSONArray) borrowHistoryObject;
							BorrowHistoryData borrowHistoryData = new BorrowHistoryData();
							borrowHistoryData.setUserID((String) borrowHistoryArray.get(0));
							borrowHistoryData.setDateBorrowed((String) borrowHistoryArray.get(1));
							borrowHistoryData.setDateReturned((String) borrowHistoryArray.get(2));
							borrowHistoryData.setDateRequestedReturn((String) borrowHistoryArray.get(3));
							copyBorrowHistoryData.add(borrowHistoryData);
						}
						borrowHistory.add(copyBorrowHistoryData);
					}
				}
				
				dvdCurrentBorrowData = (JSONArray) object.get("currentData");
				if (dvdCurrentBorrowData != null) {
					for (Object copyCurrentBorrowDataObject : dvdCurrentBorrowData) {
						JSONArray copyCurrentBorrowDataArray = (JSONArray) copyCurrentBorrowDataObject;
						
						BorrowHistoryData borrowHistoryData = new BorrowHistoryData();
						borrowHistoryData.setUserID((String) copyCurrentBorrowDataArray.get(0));
						borrowHistoryData.setDateBorrowed((String) copyCurrentBorrowDataArray.get(1));
						borrowHistoryData.setDateReturned((String) copyCurrentBorrowDataArray.get(2));
						borrowHistoryData.setDateRequestedReturn((String) copyCurrentBorrowDataArray.get(3));

						currentData.add(borrowHistoryData);
					}
				}

				dvds.add(new DVD(director, runtime, language, subLang, year, title, thumbnailImageRef, uniqueID,
						noOfCopies, loanDurs, borrowHistory, currentData));
			}

			reader.close();
			file.close();
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find " + IO.getDvdFilePath());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("ERROR reading file " + IO.getDvdFilePath());
			e.printStackTrace();
		} catch (ParseException e) {
			System.out.println("ERROR parsing users JSON");
			e.printStackTrace();
		}
		return dvds;
	}

	/**
	 * Returns a list of laptops owned by the library.
	 * @return laptops
	 * The list of laptops currently owned by the library.
	 */
	public static ArrayList<Laptop> readLaptops() {
		JSONParser parser = new JSONParser();
		JSONArray laptopQueueArray = new JSONArray();
		JSONArray listOfLoanDur = new JSONArray();
		JSONArray laptopBorrowHistoryArray = new JSONArray();
		JSONArray laptopCurrentBorrowData = new JSONArray();

		ArrayList<Laptop> laptops = new ArrayList<Laptop>();

		try {
			file = new FileReader(IO.getLaptopFilePath());
			reader = new BufferedReader(file);

			while ((currentLine = reader.readLine()) != null) {
				JSONObject object = (JSONObject) parser.parse(currentLine);

				String uniqueID = ((String) object.get("uniqueID"));
				String manufacturer = ((String) object.get("manufacturer"));
				String model = ((String) object.get("model"));
				String operatingSys = ((String) object.get("operatingSys"));
				String year = ((String) object.get("year"));
				String title = ((String) object.get("title"));
				String thumbnailImg = ((String) object.get("thumbnailImg"));
				int noOfCopies = Integer.parseInt((String) object.get("noOfCopies"));
				
				System.out.println("Loading Resource: " + uniqueID);

				ArrayList<String> loanDurs = new ArrayList<String>();
				List<List<BorrowHistoryData>> borrowHistory = new ArrayList<>();
				List<BorrowHistoryData> currentData = new ArrayList<>();

				listOfLoanDur = (JSONArray) object.get("listOfLoanDur");
				if (listOfLoanDur != null) {
					for (Object loanDur : listOfLoanDur) {
						String loanDurString = (String) loanDur;
						loanDurs.add(loanDurString);
					}
				}
				
				laptopBorrowHistoryArray = (JSONArray) object.get("borrowHistory");
				if (laptopBorrowHistoryArray != null) {
					int i = 0;
					for (Object copyBorrowHistoryObject : laptopBorrowHistoryArray) {
						JSONArray copyBorrowHistoryArray = (JSONArray) copyBorrowHistoryObject;
						List<BorrowHistoryData> copyBorrowHistoryData = new ArrayList<>();
						System.out.println("Loading Copy History for: " + uniqueID + ":" + i++);
						for (Object borrowHistoryObject : copyBorrowHistoryArray) {
							JSONArray borrowHistoryArray = (JSONArray) borrowHistoryObject;
							BorrowHistoryData borrowHistoryData = new BorrowHistoryData();
							borrowHistoryData.setUserID((String) borrowHistoryArray.get(0));
							borrowHistoryData.setDateBorrowed((String) borrowHistoryArray.get(1));
							borrowHistoryData.setDateReturned((String) borrowHistoryArray.get(2));
							borrowHistoryData.setDateRequestedReturn((String) borrowHistoryArray.get(3));
							copyBorrowHistoryData.add(borrowHistoryData);
						}
						borrowHistory.add(copyBorrowHistoryData);
					}
				}
				
				laptopCurrentBorrowData = (JSONArray) object.get("currentData");
				if (laptopCurrentBorrowData != null) {
					for (Object copyCurrentBorrowDataObject : laptopCurrentBorrowData) {
						JSONArray copyCurrentBorrowDataArray = (JSONArray) copyCurrentBorrowDataObject;
						
						BorrowHistoryData borrowHistoryData = new BorrowHistoryData();
						borrowHistoryData.setUserID((String) copyCurrentBorrowDataArray.get(0));
						borrowHistoryData.setDateBorrowed((String) copyCurrentBorrowDataArray.get(1));
						borrowHistoryData.setDateReturned((String) copyCurrentBorrowDataArray.get(2));
						borrowHistoryData.setDateRequestedReturn((String) copyCurrentBorrowDataArray.get(3));
						
						currentData.add(borrowHistoryData);
					}
				}

				Laptop laptopToAdd = new Laptop(year, title, thumbnailImg, uniqueID, manufacturer, model, operatingSys,
						noOfCopies, loanDurs, borrowHistory, currentData);

				laptops.add(laptopToAdd);
			}

			reader.close();
			file.close();
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find " + IO.getLaptopFilePath());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("ERROR reading file " + IO.getLaptopFilePath());
			e.printStackTrace();
		} catch (ParseException e) {
			System.out.println("ERROR parsing users JSON");
			e.printStackTrace();
		}
		return laptops;
	}
}
