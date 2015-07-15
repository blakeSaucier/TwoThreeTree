package sfu.cmpt307.handleInput;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Logger {

	private static final String FILENAME = "9382.txt";
	private StringBuilder log;

	public Logger() {
		log = new StringBuilder();
	}

	public void logOperation(Operation op) {
		log.append(op + ": \t\t");
	}

	public void logResult(String msg) {

		log.append(msg);
		log.append(System.lineSeparator());
	}

	public void logOperationStart() {
		log.append("------------------------- Tree Operations ------------------------------"
				+ System.lineSeparator());
	}

	public void logLeaves(String leaves) {
		log.append(leaves);
	}

	public void print() {
		System.out.println(log.toString());
	}

	public void writeLogToFile() throws FileNotFoundException {
		PrintWriter out = new PrintWriter(FILENAME);
		out.println(log.toString());
		out.close();
	}
}
