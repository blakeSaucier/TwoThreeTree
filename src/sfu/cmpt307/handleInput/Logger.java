package sfu.cmpt307.handleInput;

public class Logger {
	
	private StringBuilder log;
	
	public Logger() {
		log = new StringBuilder();
	}

	public void logOperation(Operation op) {
		log.append(op + ":  \t ");
	}

	public void logResult(String msg) {
		log.append(msg + "\n");
	}

	public void logOperationStart() {
		log.append("\n------------------------- Tree Operations ------------------------------\n");
	}
	
	public void print() {
		System.out.println(log.toString());
	}
}
