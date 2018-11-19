package io;

/**
 * @author Samuel Jankinson
 */

public abstract class IO {
	private static String usersFilePath = "./data/users.json";
	private static String resourceFilePath = "./data/resources.json";
	private static String outDataFilePath = "./data/outData.json";
	private static String bookQueueFilePath = "./data/bookQueue.json";
	private static String statsFilePath = "./data/stats.json";
	private static String transactionFilePath = "./data/transactions.json";
	
	public static String getUsersFilePath() {
		return usersFilePath;
	}
	
	public static String getResourceFilePath() {
		return resourceFilePath;
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
