package sfu.cmpt307.handleInput;

import sfu.cmpt307.twoThreeTree.TwoThreeNode;
import sfu.cmpt307.twoThreeTree.TwoThreeTree;

public class Interpreter {

	private InputScanner scanner;

	public Interpreter(InputScanner scanner) {
		this.scanner = scanner;
	}

	public static void runInterpreter(InputScanner scanner) {
		Interpreter interpreter = new Interpreter(scanner);
		interpreter.run();
	}

	public void run() {
		TwoThreeTree tree = initializeTreeElements();
		runOperations(tree);
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

	private void runOperations(TwoThreeTree tree) {
		System.out
				.println("\n------------------------- Tree Operations ------------------------------\n");
		for (Operation operation : scanner.getOperations()) {
			Logger.logOperation(operation);
			switch (operation.getOperator()) {
			case INSERT:
				tree.insert(operation.getOperandValue());
				TwoThreeNode inserted = tree
						.search(operation.getOperandValue());
				Logger.logResult("Ater insertion, " + inserted + " is the "
						+ tree.findElementPosition(operation.getOperandValue())
						+ " element of the list");
				break;
			case DELETE:
				try {
					tree.delete(operation.getOperandValue());
					Logger.logResult("Deleted element "
							+ operation.getOperandValue());
				} catch (IllegalArgumentException e) {
					Logger.logResult(e.getMessage());
				}
				break;
			case FIND:
				try {
					TwoThreeNode result = tree.search(operation
							.getOperandValue());
					Logger.logResult("Found - " + result.getKey() + " is the "
							+ tree.findElementPosition(result.getKey())
							+ " element in the list");
				} catch (IllegalArgumentException e) {
					Logger.logResult(e.getMessage());
				}
				break;
			case KthSMALLEST:
				try {
					int result = tree.findKthSmallest(operation
							.getOperandValue());
					Logger.logResult(result + " is the "
							+ operation.getOperandValue()
							+ "st/nd/th smallest element");
				} catch (IllegalArgumentException e) {
					Logger.logResult(e.getMessage());
				}
				break;
			case MAX:
				int maxResult = tree.max();
				Logger.logResult(maxResult
						+ " is the maximum element in the tree");
				break;
			case MIN:
				int minResult = tree.min();
				Logger.logResult(minResult
						+ " is the minimum element in the tree");
				break;
			case INVALID_OPERATOR:
				Logger.logResult("***Invalid Tree Operation***");
				break;
			default:
				Logger.logResult("Unknown Tree Operation");
			}
		}
		tree.printLeaves();
		tree.printFullTree();
	}

	static class Logger {

		public static void logOperation(Operation op) {
			System.out.print(op + ":  \t ");
		}

		public static void logResult(String msg) {
			System.out.print(msg + "\n");
		}
	}
}