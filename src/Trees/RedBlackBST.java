package Trees;

import java.util.NoSuchElementException;

public class RedBlackBST<Key extends Comparable<Key>, Value> {

	private static final boolean RED = true;
	private static final boolean BLACK = false;

	private Node root;

	private class Node {
		private Key key;
		private Value val;

		private Node left;
		private Node right;

		private boolean color;
		private int size;

		public Node(Key key, Value val, boolean color, int size) {
			this.key = key;
			this.val = val;
			this.color = color;
			this.size = size;
		}
	}

	public int size() {
		return size(root);
	}

	private int size(RedBlackBST<Key, Value>.Node x) {
		if (x == null)
			return 0;
		return x.size;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	// is node x red; 
	// false if x is null ?
	public boolean isRed(Node x) {
		if (x == null)
			return false;
		return x.color == RED;
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
				return x.val;
		}
		return null;
	}

	public boolean isContains(Key key) {
		return get(key) != null;
	}

	// Red-Black Tree insertion
	public void put(Key key, Value value) {
		if (key == null)
			throw new IllegalArgumentException("Key is null");
		if (value == null) {
			delete(key);
			return;
		}
		root = put(root, key, value);
		root.color = BLACK;
	}

	public void delete(Key key) {
		if (key == null)
			throw new IllegalArgumentException("Key is null");
		if (!isContains(key))
			return;

		// if both children are black, set root to red
		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;

		root = delete(root, key);

		if (!isEmpty())
			root.color = BLACK;
	}

	private RedBlackBST<Key, Value>.Node put(RedBlackBST<Key, Value>.Node h, Key key, Value value) {
		if (h == null)
			return new Node(key, value,RED, 1);

		int cmp = key.compareTo(h.key);

		if (cmp < 0)
			h.left = put(h.left, key, value);
		else if (cmp > 0)
			h.right = put(h.right, key, value);
		else 
			h.val = value;

		// Balance tree
		if (isRed(h.right) && !isRed(h.left))
			h = rotateLeft(h);
		if (isRed(h.left) && isRed(h.left.left))
			h = rotateRight(h);
		if (isRed(h.left) && isRed(h.right))
			flipColors(h);

		h.size = 1 + size(h.left) + size(h.right);

		return h;
	}

	// flip the colors of a node and its two children
	private void flipColors(RedBlackBST<Key, Value>.Node h) {
		h.color = !h.color;
		h.left.color = !h.left.color;
		h.right.color = !h.right.color;
	}

	private RedBlackBST<Key, Value>.Node rotateRight(RedBlackBST<Key, Value>.Node h) {
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		h.color = x.right.color;
		x.right.color = RED;
		x.size = h.size;
		h.size = size(h.right) + size(h.left) + 1;

		return x;
	}

	private RedBlackBST<Key, Value>.Node rotateLeft(RedBlackBST<Key, Value>.Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = x.left.color;
		x.left.color = RED;
		x.size = h.size;
		h.size = size(h.left) + size(h.right) + 1;

		return x;
	}

	private Node delete(Node h, Key key) {
		if (key.compareTo(h.key) < 0) {
			if (!isRed(h.left) && !isRed(h.left.left))
				h = moveLeftRed(h);
			h.left = delete(h.left, key);
		} else {
			if (key.compareTo(h.key) == 0 && h.right == null)
				return null;
			if (isRed(h.left))
				h = rotateRight(h);
			if (!isRed(h.right) && !isRed(h.right.left))
				h = moveRightRed(h);
			if (key.compareTo(h.key) == 0) {
				Node x = min(h.right);
				h.key = x.key;
				h.val = x.val;
				h.right = deleteMin(h.right);
			}
		}
		return balance(h);
	}

	public void deleteMin() {
		if (isEmpty())
			throw new NoSuchElementException();

		// if both children are black, set root to red
		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;

		root = deleteMin(root);

		if (!isEmpty())
			root.color = BLACK;
	}

	// restore red-black tree invariant
	private RedBlackBST<Key, Value>.Node balance(RedBlackBST<Key, Value>.Node h) {
		if (isRed(h.right))
			h = rotateLeft(h);
		if (isRed(h.left) && isRed(h.left.left))
			h = rotateRight(h);
		if (isRed(h.left) && isRed(h.right))
			flipColors(h);

		h.size = size(h.left) + size(h.right) + 1;

		return h;
	}

	private RedBlackBST<Key, Value>.Node deleteMin(RedBlackBST<Key, Value>.Node h) {
		if (h.left == null)
			return null;

		if (!isRed(h.left) && !isRed(h.left.left))
			h = moveLeftRed(h);

		h.left = deleteMin(h.left);

		return balance(h);
	}

	private RedBlackBST<Key, Value>.Node min(RedBlackBST<Key, Value>.Node x) {
		if (x == null)
			return x;
		else
			return min(x.left);
	}

	// Assuming that h is red and both h.right and h.right.left
	// are black, make h.right or one of its children red.
	private RedBlackBST<Key, Value>.Node moveRightRed(RedBlackBST<Key, Value>.Node h) {
		flipColors(h);
		if (isRed(h.right.left)) {
			h = rotateLeft(h);
			flipColors(h);
		}
		return h;
	}

	// Assuming that h is red and both h.left and h.left.left
	// are black, make h.left or one of its children red.
	private RedBlackBST<Key, Value>.Node moveLeftRed(RedBlackBST<Key, Value>.Node h) {
		flipColors(h);
		if (isRed(h.left.left)) {
			h = rotateRight(h);
			flipColors(h);
		}
		return h;
	}

	public int btw(Key lo, Key hi) {
		if (isContains(hi))
			return rank(hi) - rank(lo)+1;
		else 
			return rank(hi) - rank(lo);
	}

	private int rank(Key key) {
		return rank(key, root);
	}

	private int rank(Key key, Node x) {
		if (x == null) 
			return 0;

		int cmp = key.compareTo(x.key); 
		if (cmp < 0) 
			return rank(key, x.left); 
		else if (cmp > 0) 
			return 1 + size(x.left) + rank(key, x.right); 
		else 
			return size(x.left); 
	}
}
