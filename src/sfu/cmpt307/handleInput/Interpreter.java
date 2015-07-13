package sfu.cmpt307.handleInput;

import sfu.cmpt307.twoThreeTree.TwoThreeNode;
import sfu.cmpt307.twoThreeTree.TwoThreeTree;

public class Interpreter {

	private InputScanner scanner;
	private TwoThreeTree tree;

	public Interpreter(InputScanner scanner) {
		this.scanner = scanner;
		tree = initializeTreeElements();
	}

	public static void makeAndRunInterpreter(InputScanner scanner) {
		Interpreter interpreter = new Interpreter(scanner);
		interpreter.run();
	}

	public void run() {
		runOperations();
	}

	private TwoThreeTree initializeTreeElements() {
		if (scanner.getInitalTreeElements().size() < 2) {
			throw new IllegalArgumentException(
					"Tree must be initialized with 2 or more elements");
		}
		TwoThreeTree tree = TwoThreeTree.manuallyInitTwoNodes(scanner
				.getInitalTreeElements());
		for (Integer element : scanner.getInitalTreeElements()) {
			tree.insert(element);
		}
		return tree;
	}

	private void runOperations() {
		Logger.logOperationStart();
		for (Operation operation : scanner.getOperations()) {
			Logger.logOperation(operation);
			switch (operation.getOperator()) {
			case INSERT:
				doInsert(operation);
				break;
			case DELETE:
				doDelete(operation);
				break;
			case FIND:
				doFind(operation);
				break;
			case KthSMALLEST:
				doSelection(operation);
				break;
			case MAX:
				doMax();
				break;
			case MIN:
				doMin();
				break;
			case INVALID_OPERATOR:
				Logger.logResult("***Invalid Tree Operation***");
				break;
			default:
				Logger.logResult("Unknown Tree Operation");
			}
		}
		tree.printLeaves();
	}

	private void doSelection(Operation operation) {
		try {
			int result = tree.findKthSmallest(operation.getOperandValue());
			Logger.logResult(result + " is the " + operation.getOperandValue()
					+ "st/nd/th smallest element");
		} catch (IllegalArgumentException e) {
			Logger.logResult(e.getMessage());
		}
	}

	private void doFind(Operation operation) {
		try {
			TwoThreeNode result = tree.search(operation.getOperandValue());
			Logger.logResult("Found - " + result.getKey() + " is the "
					+ tree.findElementPosition(result.getKey())
					+ " element in the list");
		} catch (IllegalArgumentException e) {
			Logger.logResult(e.getMessage());
		}
	}

	private void doDelete(Operation operation) {
		try {
			tree.delete(operation.getOperandValue());
			Logger.logResult("Deleted element " + operation.getOperandValue());
		} catch (IllegalArgumentException e) {
			Logger.logResult(e.getMessage());
		}
	}

	private void doInsert(Operation operation) {
		tree.insert(operation.getOperandValue());
		TwoThreeNode inserted = tree.search(operation.getOperandValue());
		Logger.logResult("Ater insertion, " + inserted + " is the "
				+ tree.findElementPosition(operation.getOperandValue())
				+ " element of the list");
	}

	private void doMin() {
		int minResult = tree.min();
		Logger.logResult(minResult + " is the minimum element in the tree");
	}

	private void doMax() {
		int maxResult = tree.max();
		Logger.logResult(maxResult + " is the maximum element in the tree");
	}

	// /////////////////////////////////////////////////////////////////////
	// Inner static class for logging results

	static class Logger {

		public static void logOperation(Operation op) {
			System.out.print(op + ":  \t ");
		}

		public static void logResult(String msg) {
			System.out.print(msg + "\n");
		}

		public static void logOperationStart() {
			System.out
					.println("\n------------------------- Tree Operations ------------------------------\n");
		}
	}
}