package sfu.cmpt307;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoThreeTests {

	public static void main(String[] args) {
		if (args.length > 0) {
			ArrayList<Integer> input = new ArrayList<Integer>();
			try {
				input.addAll(readFileInput(args[0]));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			createTree(input);
		} else {
			testTreeCreation();
		}
	}

	private static void testTreeCreation() {
		TwoThreeNode root = new TwoThreeNode();
		TwoThreeNode one = new TwoThreeNode(1);
		TwoThreeNode two = new TwoThreeNode(2);

		// Need to instantiate the tree with a root and two children nodes
		root.addChild(one);
		root.addChild(two);
		TwoThreeTree tree = new TwoThreeTree(root);

		for (int i = 1; i < 50; i++) {
			tree.insert(i);
		}

		tree.print();
		for (int i = 1; i < 50; i++) {
			System.out.println(i + "th smallest element: "
					+ tree.findKthSmallest(i));
		}
	}

	private static List<Integer> readFileInput(String fileName)
			throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String fullInput = "";
		try {
			StringBuilder stringBuilder = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				stringBuilder.append(line);
				line = br.readLine();
			}
			fullInput = stringBuilder.toString();
		} finally {
			br.close();
		}
		List<String> stringInput = new ArrayList<String>(
				Arrays.asList(fullInput.split("\\s*,\\s*")));
		List<Integer> treeElements = new ArrayList<Integer>();

		for (String element : stringInput) {
			treeElements.add(Integer.parseInt(element));
		}
		return treeElements;
	}

	private static void createTree(List<Integer> elements) {
		if (elements.size() < 2) {
			throw new IllegalArgumentException(
					"need at least two elements to create 2-3 tree");
		}
		TwoThreeNode root = new TwoThreeNode();
		TwoThreeNode one = new TwoThreeNode(elements.get(0));
		TwoThreeNode two = new TwoThreeNode(elements.get(1));

		root.addChild(one);
		root.addChild(two);
		TwoThreeTree tree = new TwoThreeTree(root);

		for (int i = 2; i < elements.size(); i++) {
			tree.insert(elements.get(i));
		}
		tree.print();
	}
}
