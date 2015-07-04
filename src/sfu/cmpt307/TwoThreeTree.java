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
		} else {
			delete(nodeToDelete);
		}
	}

	private void delete(TwoThreeNode nodeToDelete) {
		TwoThreeNode parent = nodeToDelete.getParent();
		if (parent.numChildren() == 3) {
			parent.removeChild(nodeToDelete);
		} else if (parent.numChildren() == 2) {
			mergeChildren(nodeToDelete);
		} else {
			throw new IllegalArgumentException(
					"Parent node has too many children");
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
				.println("\n-------------------------- 2-3 Tree -------------------------------\n");
		root.print();
	}

	public int max() {
		if (root.isLeaf()) {
			return root.getKey();
		}
		TwoThreeNode node = root.getChild(root.numChildren() - 1);
		while (!node.isLeaf()) {
			node = node.getChild(node.numChildren() - 1);
		}
		return node.getKey();
	}

	public int min() {
		if (root.isLeaf()) {
			return root.getKey();
		}
		TwoThreeNode node = root.getChild(0);
		while (!node.isLeaf()) {
			node = node.getChild(0);
		}
		return node.getKey();
	}

	// Not Yet Implemented
	public int findKthSmallest(int k) {
		if (k == 1) {
			return min();
		}
		throw new IllegalAccessError("Not yet implemented");
	}

	public int totalLeafs() {
		return this.root.getTotalLeafsUnderneath();
	}
	
	////////////////////////////////////////////////////////////////////
	// Private methods

	private void splitChildren(TwoThreeNode node) {
		TwoThreeNode sibling = new TwoThreeNode();

		sibling.addChild(node.getChild(2));
		sibling.addChild(node.getChild(3));

		node.removeChild(3);
		node.removeChild(2);

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
		parent.removeChild(nodeToDelete);
		if (parent == root) {
			if (root.numChildren() < 2) {
				root = parent.getChild(0);
			}
		} else {
			TwoThreeNode sibling = findSibling(parent);
			shiftRemainingNodesToSibling(parent, sibling);
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

	private void shiftRemainingNodesToSibling(TwoThreeNode parent,
			TwoThreeNode sibling) {
		if (sibling.numChildren() == 2) {
			// sibling node has room for the remaining leaf node - After which the parent becomes a dangling node and needs to be deleted.
			sibling.addChild(parent.getChild(0));
			parent.removeChild(0);
			delete(parent);
		} else if (sibling.numChildren() == 3) {
			if (parent.getLargestFirstSubtree() > sibling
					.getLargestFirstSubtree()) {
				// sibling is on the LEFT
				parent.addChild(sibling.getChild(2));
				sibling.removeChild(2);
			} else {
				// sibling is on the RIGHT
				parent.addChild(sibling.getChild(0));
				sibling.removeChild(0);
			}
		}
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
