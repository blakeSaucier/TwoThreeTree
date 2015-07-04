package sfu.cmpt307;

public class TwoThreeTests {

	public static void main(String[] args) {
		TwoThreeNode root = new TwoThreeNode();
		TwoThreeNode one = new TwoThreeNode(1);
		TwoThreeNode two = new TwoThreeNode(2);

		// Need to instantiate the tree with a root and two children nodes
		root.addChild(one);
		root.addChild(two);
		TwoThreeTree tree = new TwoThreeTree(root);

		for (int i = 0; i < 100; i++) {
			tree.insert(i);
		}

		tree.print();

		for (int i = 0; i < 95; i++) {
			tree.delete(i);
		}
		
		tree.print();
		
		System.out.println("total leafs underneath: " + tree.totalLeafs());
	}
}
