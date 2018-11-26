package Trees;

public class BST<Key extends Comparable<Key>, Value> {

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

	public boolean isEmpty() {
		return size() == 0;
	}

	public int size() {
		return size(root);
	}

	private int size(BST<Key, Value>.Node x) {
		if (x == null)
			return 0;
		return x.size;
	}

	public void put(Key key, Value value) {
		if (key == null)
			throw new IllegalArgumentException("key cannot be null");
		if (value == null) { // rewrite if val is null
			delete(key);
			return;
		}
		root = put(root, key, value);
	}

	private BST<Key, Value>.Node put(BST<Key, Value>.Node x, Key key, Value value) {
		if (x == null)
			return new Node(key, value, 1);

		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.left = put(x.left, key, value);
		else if (cmp > 0)
			x.right = put(x.right, key, value);
		else
			x.value = value;

		x.size = 1 + size(x.left) + size(x.right);

		return x;
	}

	// Hibbart deletion
	public void delete(Key key) {
		if (key == null)
			throw new IllegalArgumentException("delete() cannot remove null element");
		root = delete(root, key);
	}

	private BST<Key, Value>.Node delete(Node x, Key key) {
		if (x == null)
			return null;

		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.left = delete(x.left, key);
		else if (cmp > 0)
			x.right = delete(x.right, key);
		else {
			if (x.right == null)  // no right child
				return x.left;
			if (x.left == null) // no left child
				return x.right;
			Node t = x;
			x = min(t.right); // replace with successor
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		x.size = size(x.left) + size(x.right) + 1;
		return x;
	}

	public void deleteMin() {
		root = deleteMin(root);
	}

	private BST<Key, Value>.Node deleteMin(BST<Key, Value>.Node x) {
		if (x.left == null)
			return x.right;

		x.left = deleteMin(x.left);

		return x;
	}

	public void deleteMax() {
		root = deleteMax(root);
	}

	private Node deleteMax(Node x) {
		if (x.right == null)
			return x.left;

		x.right = deleteMax(x.right);

		return x;
	}

	public Key min() {
		return min(root).key;
	}

	private BST<Key, Value>.Node min(BST<Key, Value>.Node x) {
		if (x.left == null)
			return x;
		else
			return min(x.left);
	}

	public Key max() {
		return max(root).key;
	}

	private BST<Key, Value>.Node max(BST<Key, Value>.Node x) {
		if (x.right == null)
			return x;
		else
			return min(x.right);
	}

	// Return value corresponding to given key, or null if no such key
	public Value get(Key key) {
		if (key == null)
			throw new IllegalArgumentException("Key is null");

		Node x = root;
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if (cmp < 0)
				x = x.left;
			if (cmp > 0)
				x = x.right;
			else
				return x.value;
		}
		return null;
	}

	// largest key less than or equal to key
	public Key floor(Key key) {
		Node x = floor(root, key);
		if (x == null)
			return null;
		return x.key;
	}

	private BST<Key, Value>.Node floor(BST<Key, Value>.Node x, Key key) {
		if (x == null)
			return null;

		int cmp = key.compareTo(x.key);
		if (cmp == 0)
			return x;
		if (cmp < 0)
			return floor(x.left, key);

		Node t = floor(x.right, key);
		if (t != null)
			return t;
		else 
			return x;
	}

	// smallest key greater than or equal to key
	public Key celling(Key key) {
		Node x = celling(root, key);
		if (x == null)
			return null;
		return x.key;
	}

	private BST<Key, Value>.Node celling(BST<Key, Value>.Node x, Key key) {
		if (x == null)
			return null;

		int cmp = key.compareTo(x.key);
		if (cmp == 0)
			return x;
		if (cmp < 0) {
			Node t = celling(x.left, key);
			if (t != null)
				return t;
			else 
				return x;
		}
		return celling(x.right, key);
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
	
	/* ============================================
	 * |				Traversals				  |
	 * ============================================
	 */
	public void inOrder() {
		inOrder(root);
	}
	
	private void inOrder(Node x) {
		if (x == null)
			return;
		
		inOrder(x.left);
		System.out.print(x.key + " ");
		inOrder(x.right);
	}
	
	public void postOrder() {
		postOrder(root);
	}
	
	private void postOrder(Node x) {
		if (x == null)
			return;
		
		postOrder(x.left);
		postOrder(x.right);
		System.out.print(x.key + " ");
	}
	
	public void preOrder() {
		preOrder(root);
	}
	
	private void preOrder(Node x) {
		if (x == null)
			return;
		
		System.out.print(x.key + " ");
		preOrder(x.left);
		preOrder(x.right);
	}
	
	/* ============================================
	 * |			End	Traversals				  |
	 * ============================================
	 */

	public static void main(String[] args) {
		BST<String, Integer> bst = new BST<>();

		bst.put("F", 1);
		bst.put("B", 2);
		bst.put("G", 3);
		bst.put("A", 4);
		bst.put("D", 5);
		bst.put("I", 6);
		bst.put("C", 7);
		bst.put("E", 8);
		bst.put("H", 9);
		
		System.out.println(bst.isBST());
		
		System.out.println("In Order:");
		bst.inOrder();
		System.out.println("\nPost Order:");
		bst.postOrder();
		System.out.println("\nPre Order:");
		bst.preOrder();
		//		bst.put(1, "P");
		//		bst.put(2, "S");
		//		bst.put(3, "E");
		//		bst.put(4, "U");
		//		bst.put(5, "D");
		//		bst.put(6, "O");
		//		bst.put(7, "M");
		//		bst.put(8, "Y");
		//		bst.put(9, "T");
		//		bst.put(10, "H");
		//		bst.put(11, "I");
		//		bst.put(12, "C");
		//		bst.put(13, "A");
		//		bst.put(14, "L");
		//		bst.put(20, "Q");

		//		System.out.println(bst.size());

		//		System.out.print(bst.get(13));
		//		System.out.print(bst.get(2));
		//		System.out.print(bst.get(3));
		//		System.out.print(bst.get(4));
		//		System.out.print(bst.get(5));
		//		System.out.print(bst.get(6));
		//		System.out.println(bst.get(14));

		//		System.out.println("=====================");
		//		bst.delete(3);
		//		System.out.println(bst.get(3));
		//		bst.put(3, "OOOO");
		//		System.out.println(bst.get(3));
		//		System.out.println(bst.size());
		//		System.out.println(bst.floor(13));
		//		System.out.println(bst.celling(13));
		//
		//		System.out.println(bst.isBST());

	}
}
