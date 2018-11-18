package io;
public abstract class IO {
	private static String usersFilePath = "./users.json";
	private static String resourceFilePath = "./resources.json";
	private static String outDataFilePath = "./outData.json";
	private static String bookQueueFilePath = "./bookQueue.json";
	private static String statsFilePath = "./stats.json";
	private static String transactionFilePath = "./transactions.json";
	
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
