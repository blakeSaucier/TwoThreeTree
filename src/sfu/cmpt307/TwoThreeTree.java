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
			TwoThreeNode searchResult = search(nodeToInsert.getKey());
			if (searchResult.getKey() != nodeToInsert.getKey()) {
				TwoThreeNode parentNode = searchResult.getParent();
				parentNode.addChild(nodeToInsert);
				if (parentNode.numChildren() == 4) {
					splitChildren(parentNode);
				}
			}
		}
	}

	public void delete(int key) {
		TwoThreeNode nodeToDelete = search(key);
		if (nodeToDelete.getKey() != key) {
			System.out.println("Key " + key
					+ " could not be found, nothing deleted");
		} else if (nodeToDelete.getParent().numChildren() == 3) {
			TwoThreeNode parent = nodeToDelete.getParent();
			parent.getChildren().remove(nodeToDelete);
		} else if (nodeToDelete.getParent().numChildren() == 2) {
			mergeChildren(nodeToDelete);
		}
	}

	// Search traverses the tree until reaching a leaf node. It does not
	// interpret the value of the leaf node.
	public TwoThreeNode search(int searchKey) {
		if (root.isLeaf()) {
			return root;
		} else {
			return depthSearch(root, searchKey);
		}
	}

	public void print() {
		System.out
				.println("-------------------------- 2-3 Tree -------------------------------");
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

	private void mergeChildren(TwoThreeNode nodeToDelete) {
		TwoThreeNode parent = nodeToDelete.getParent();
		parent.getChildren().remove(nodeToDelete);
		if (parent == root) {
			if (root.numChildren() < 2) {
				root = parent.getChild(0);
			}
		} else {
			TwoThreeNode sibling = findSibling(parent);
			if (sibling.numChildren() == 2) {
				// sibling node has room for the dangling node
				sibling.addChild(parent.getChild(0));
				parent.getChildren().remove(0);
				mergeChildren(parent);
			} else if (sibling.numChildren() == 3) {
				// sibling can have a node removed and merged with remaining
				// dangling node
				parent.addChild(sibling.getChild(2));
				sibling.getChildren().remove(2);
			}
		}
	}

	private TwoThreeNode findSibling(TwoThreeNode node) {
		TwoThreeNode parent = node.getParent();
		TwoThreeNode sibling = null;

		// search from left to right for an appropriate sibling - take the first
		// one found.
		for (int i = 0; i < parent.numChildren(); i++) {
			if (parent.getChild(i) != node) {
				sibling = parent.getChild(i);
				break;
			}
		}
		if (sibling == null) {
			throw new NullPointerException("Could not find a sibling for node "
					+ node);
		}
		return sibling;
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
