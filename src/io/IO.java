package io;

/**
 * @author Samuel Jankinson
 */

public abstract class IO {
	private static final String usersFilePath = "./data/users.json";
	private static final String bookFilePath = "./data/book.json";
	private static final String dvdFilePath = "./data/dvd.json";
	private static final String laptopFilePath = "./data/laptop.json";
	private static final String outDataFilePath = "./data/outData.json";
	private static final String bookQueueFilePath = "./data/bookQueue.json";
	private static final String statsFilePath = "./data/stats.json";
	private static final String transactionFilePath = "./data/transactions.json";
	
	public static String getUsersFilePath() {
		return usersFilePath;
	}
	
	public static String getBookFilePath() {
		return bookFilePath;
	}
	
	public static String getDvdFilePath() {
		return dvdFilePath;
	}
	
	public static String getLaptopFilePath() {
		return laptopFilePath;
	}
	
	public static String getOutDataFilePath() {
		return outDataFilePath;
	}
	
	public static String getBookQueueFilePath() {
		return bookQueueFilePath;
	}
	
	public static String getStatsFilePath() {
		return statsFilePath;
	}
	
	public static String getTransactionFilePath() {
		return transactionFilePath;
	}
}
