package sfu.cmpt307;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TwoThreeNode implements Comparable<TwoThreeNode> {

	private TwoThreeNode parent;
	private Integer valueKey;
	private List<TwoThreeNode> children;

	public TwoThreeNode() {
		children = new ArrayList<TwoThreeNode>();
	}

	public TwoThreeNode(int valueKey) {
		this();
		this.valueKey = valueKey;
	}

	public boolean isLeaf() {
		return children.size() == 0;
	}

	public int getKey() {
		if (!this.isLeaf()) {
			throw new NullPointerException(
					"Trying to get value from internal node");
		}
		return this.valueKey;
	}

	public List<TwoThreeNode> getChildren() {
		return this.children;
	}

	public TwoThreeNode getChild(int index) {
		return children.get(index);
	}

	public void addChild(TwoThreeNode node) {
		if (numChildren() <= 3) {
			children.add(node);
			node.setParent(this);
			Collections.sort(children);
		} else {
			throw new IllegalArgumentException("Node has too many children!");
		}
	}

	public void setParent(TwoThreeNode parent) {
		this.parent = parent;
	}

	public TwoThreeNode getParent() {
		return this.parent;
	}

	public int numChildren() {
		int childCounter = 0;
		for (int i = 0; i < children.size(); i++) {
			if (children.get(i) != null) {
				childCounter++;
			}
		}
		return childCounter;
	}

	public Integer getLargestFirstSubtree() {
		if (!this.isLeaf()) {
			TwoThreeNode node = this.children.get(0);
			while (!node.isLeaf()) {
				node = node.largestSubElement();
			}
			return node.getKey();
		} else {
			return this.valueKey;
		}
	}

	public Integer getLargestSecondSubtree() {
		if (!this.isLeaf()) {
			TwoThreeNode node = this.children.get(1);
			while (!node.isLeaf()) {
				node = node.largestSubElement();
			}
			return node.getKey();
		} else {
			return this.valueKey;
		}
	}

	private TwoThreeNode largestSubElement() {
		return children.get(this.numChildren() - 1);
	}

	@Override
	public String toString() {
		if (isLeaf()) {
			return "key value: " + this.valueKey;
		} else {
			return "Node L: " + String.valueOf(getLargestFirstSubtree())
					+ ", R: " + String.valueOf(getLargestSecondSubtree());
		}
	}

	public void print() {
		print("", true);
	}

	// ///////////////////////////////////////////////////////////////
	// Recursively prints the 2-3 tree in the console

	private void print(String prefix, boolean isTail) {
		System.out.println(prefix + (isTail ? "--- " : "|-- ") + this);
		if (!this.isLeaf()) {
			for (int i = 0; i < numChildren() - 1; i++) {
				children.get(i).print(prefix + (isTail ? "    " : "|   "),
						false);
			}
			if (numChildren() > 0) {
				children.get(numChildren() - 1).print(
						prefix + (isTail ? "    " : "|   "), true);
			}
		}
	}

	// //////////////////////////////////////////////////////////////
	// Satisfy Comparable interface

	@Override
	public int compareTo(TwoThreeNode arg0) {
		if (this.isLeaf() && arg0.isLeaf()) {
			return this.valueKey.compareTo(arg0.valueKey);
		} else {
			return this.getLargestFirstSubtree().compareTo(
					arg0.getLargestFirstSubtree());
		}
	}
}
