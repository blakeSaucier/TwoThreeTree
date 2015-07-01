package sfu.cmpt307;

public class TwoThreeTree {

	private TwoThreeNode root;

	public TwoThreeTree(TwoThreeNode root) {		
		this.root = root;
	}

	public void insert(int key) {
		TwoThreeNode nodeToInsert = new TwoThreeNode(key);
		insert(nodeToInsert);
	}

	public void insert(TwoThreeNode nodeToInsert) {
		if (root.isLeaf()) {
			root.addChild(nodeToInsert);
		} else {
			TwoThreeNode parentNode = search(nodeToInsert.getKey()).getParent();
			parentNode.addChild(nodeToInsert);
			if (parentNode.numChildren() == 4) {
				splitChildren(parentNode);
			}
		}
	}
	
	// TO-DO
	public void delete(int value) {
		
	}

	public TwoThreeNode search(int searchKey) {
		if (root.isLeaf()) {
			return root;
		} else {
			return depthSearch(root, searchKey);
		}
	}

	public void print() {
		System.out.println("-------------------------- 2-3 Tree -------------------------------");
		root.print();
	}

	private void splitChildren(TwoThreeNode node) {
		TwoThreeNode sibling = new TwoThreeNode();

		sibling.addChild(node.getChild(2));
		sibling.addChild(node.getChild(3));

		node.getChildren().remove(3);
		node.getChildren().remove(2);

		if (node == root) {
			makeNewRoot(node, sibling);
		} else {
			TwoThreeNode parent = node.getParent();
			parent.addChild(sibling);
			if (parent.numChildren() == 4) {
				splitChildren(parent);
			}
		}
	}

	private void makeNewRoot(TwoThreeNode leftChild, TwoThreeNode rightChild) {
		TwoThreeNode newRoot = new TwoThreeNode();
		newRoot.addChild(leftChild);
		newRoot.addChild(rightChild);
		this.root = newRoot;
	}

	private TwoThreeNode depthSearch(TwoThreeNode root, int searchKey) {
		if (searchKey <= root.getLargestFirstSubtree()) {
			TwoThreeTree subtree = new TwoThreeTree(root.getChild(0));
			return subtree.search(searchKey);
		} else {
			if (root.numChildren() == 2
					|| searchKey <= root.getLargestSecondSubtree()) {
				TwoThreeTree subtree = new TwoThreeTree(root.getChild(1));
				return subtree.search(searchKey);
			} else {
				TwoThreeTree subtree = new TwoThreeTree(root.getChild(2));
				return subtree.search(searchKey);
			}
		}
	}
}
