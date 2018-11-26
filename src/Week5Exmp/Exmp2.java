package Week5Exmp;

public class Exmp2<Key extends Comparable<Key>, Value> {

	private Node root; // its a root of BST

	private class Node {
		private Node left; // reference to the left subtree(smaller keys)
		private Node right; // reference to the right subtree(larger keys)

		private Key key; // key
		private Value value; // value

		private int size; // number of nodes in subtree

		public Node(Key key, Value value, int size) {
			this.key = key;
			this.value = value;
			this.size = size;
		}
	}

	public boolean isBST() {
		return isBST(root, null, null);
	}
	/*
	 * perform a simple inorder traversal and keep the previous value of the node. 
	 * If the current node is smaller than the previous node then it is not a binary search tree.
	 * You use constant additional space (for the previous value) apart from the recursion stack.
	 */
	private boolean isBST(Node node, Key min, Key max) {
		if (node == null)
			return true;

		if ((min != null && (node.key.compareTo(min) <= 0)) || (max != null && (node.key.compareTo(max) >= 0)))
			return false;

		return isBST(node.left, min, node.key) && isBST(node.right, node.key, max);
	}
}
