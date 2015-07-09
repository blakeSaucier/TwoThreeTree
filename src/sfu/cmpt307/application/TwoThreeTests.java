package sfu.cmpt307.application;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import sfu.cmpt307.handleInput.InputScanner;
import sfu.cmpt307.handleInput.Interpreter;
import sfu.cmpt307.twoThreeTree.TwoThreeTree;

public class TwoThreeTests {
	
	public static void main(String[] args) throws FileNotFoundException {
		if (args.length > 0) {
			InputScanner input = InputScanner.scan(args[0]);
			Interpreter.runInterpreter(input);
		} else {
			testTreeCreation();
		}
	}

	private static void testTreeCreation() {
		List<Integer> elements = new ArrayList<Integer>();
		for (int i = 0; i < 100; i++ ) {
			elements.add(i);
		}
		
		TwoThreeTree tree = TwoThreeTree.manuallyInitTwoNodes(elements);
		for (Integer element: elements) {
			tree.insert(element);
		}
		
		tree.printFullTree();
	}
}
