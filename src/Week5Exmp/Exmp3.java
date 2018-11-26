package Week5Exmp;


public class Exmp3<Key extends Comparable<Key>, Value> {
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
}
