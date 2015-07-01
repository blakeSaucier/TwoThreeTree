package sfu.cmpt307;

import java.util.Random;

public class TwoThreeTests {

	public static void main(String[] args) {
		TwoThreeNode root = new TwoThreeNode();
		TwoThreeNode one = new TwoThreeNode(1);
		TwoThreeNode three = new TwoThreeNode(3);

		// Need to instantiate the tree with a root and two children nodes
		root.addChild(one);
		root.addChild(three);
		TwoThreeTree tree = new TwoThreeTree(root);

		Random r = new Random();
		for (int i = 0; i < 50; i++) {
			int random = r.nextInt(100);
			tree.insert(random);
		}

		tree.print();

		for (int i = 0; i < 25; i++) {
			int random = r.nextInt(100);
			tree.delete(random);
		}
		tree.print();
	}
}
