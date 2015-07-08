package sfu.cmpt307.handleInput;

import sfu.cmpt307.twoThreeTree.TwoThreeNode;
import sfu.cmpt307.twoThreeTree.TwoThreeTree;

public class Interpreter {

	private InputScanner scanner;

	public Interpreter(InputScanner scanner) {
		this.scanner = scanner;
	}

	public void run() {
		TwoThreeTree tree = initializeTree();
		runOperations(tree);
	}

	private TwoThreeTree initializeTree() {
		if (scanner.getInitalTreeElements().size() < 2) {
			throw new IllegalArgumentException(
					"Tree must be initialized with 2 or more elements");
		}

		// Manually initializing the Two-Three tree with the first two input
		// elements.
		// Having a tree with only one element would break the properties of a
		// Two-Three tree that the Insert(), Delete() operations depend on
		TwoThreeNode root = new TwoThreeNode();
		TwoThreeNode one = new TwoThreeNode(scanner.getInitalTreeElements()
				.get(0));
		TwoThreeNode two = new TwoThreeNode(scanner.getInitalTreeElements()
				.get(1));

		root.addChild(one);
		root.addChild(two);
		return new TwoThreeTree(root);
	}
	
	private void runOperations(TwoThreeTree tree) {
		
	}
}
