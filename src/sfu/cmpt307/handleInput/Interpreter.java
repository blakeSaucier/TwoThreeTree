package sfu.cmpt307.handleInput;

import sfu.cmpt307.twoThreeTree.TwoThreeNode;
import sfu.cmpt307.twoThreeTree.TwoThreeTree;

public class Interpreter {

	private InputScanner scanner;
	private TwoThreeTree tree;
	private Logger logger;

	public Interpreter(InputScanner scanner) {
		this.scanner = scanner;
		this.logger = new Logger();
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
		logger.logOperationStart();
		for (Operation operation : scanner.getOperations()) {
			logger.logOperation(operation);
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
				logger.logResult("***Invalid Tree Operation***");
				break;
			default:
				logger.logResult("Unknown Tree Operation");
			}
		}
		logger.print();
		tree.printLeaves();
	}

	private void doSelection(Operation operation) {
		try {
			int result = tree.findKthSmallest(operation.getOperandValue());
			logger.logResult(result + " is the " + operation.getOperandValue()
					+ "st/nd/th smallest element");
		} catch (IllegalArgumentException e) {
			logger.logResult(e.getMessage());
		}
	}

	private void doFind(Operation operation) {
		try {
			TwoThreeNode result = tree.search(operation.getOperandValue());
			logger.logResult("Found - " + result.getKey() + " is the "
					+ tree.findElementPosition(result.getKey())
					+ " element in the list");
		} catch (IllegalArgumentException e) {
			logger.logResult(e.getMessage());
		}
	}

	private void doDelete(Operation operation) {
		try {
			tree.delete(operation.getOperandValue());
			logger.logResult("Deleted element " + operation.getOperandValue());
		} catch (IllegalArgumentException e) {
			logger.logResult(e.getMessage());
		}
	}

	private void doInsert(Operation operation) {
		tree.insert(operation.getOperandValue());
		TwoThreeNode inserted = tree.search(operation.getOperandValue());
		logger.logResult("Ater insertion, " + inserted + " is the "
				+ tree.findElementPosition(operation.getOperandValue())
				+ " element of the list");
	}

	private void doMin() {
		int minResult = tree.min();
		logger.logResult(minResult + " is the minimum element in the tree");
	}

	private void doMax() {
		int maxResult = tree.max();
		logger.logResult(maxResult + " is the maximum element in the tree");
	}
}