package sfu.cmpt307.handleInput;

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
		
		TwoThreeTree tree = TwoThreeTree.manuallyInitTwoNodes(scanner.getInitalTreeElements());
		for(Integer element: scanner.getInitalTreeElements()) {
			tree.insert(element);
		}
		return tree;
	}
	
	private void runOperations(TwoThreeTree tree) {
		for (Operation operation: scanner.getOperations()) {
			Logger.logOperation(operation);
			switch(operation.getOperator()) {
				case INSERT:
					tree.insert(operation.getOperandValue());
					break;
				case DELETE:
					tree.delete(operation.getOperandValue());
					break;
				case FIND:
					try {
						tree.search(operation.getOperandValue());
					} catch (IllegalArgumentException e) {
						Logger.log("Could not find element " + operation.getOperandValue());
					}
					break;
				case KthSMALLEST:
					tree.findKthSmallest(operation.getOperandValue());
					break;
				case MAX:
					tree.max();
					break;
				case MIN:
					tree.min();
					break;
				case NULL_OPERATOR:
					throw new IllegalArgumentException("Invalid tree operation: " + operation.getOperator().getLexeme());
				default:
					throw new IllegalArgumentException("Invalid tree operation:");
			}
		}
		tree.print();
	}
	
	static class Logger {
		public static void log(String msg) {
			System.out.println(msg);
		}
		
		public static void logOperation(Operation op) {
			Logger.log(op.getOperator().getLexeme() + " " + op.getOperandValue());
		}
	}
}