package Week5Exmp;

import edu.princeton.cs.algs4.RedBlackBST;

/*
 * Generalized queue. 
 * Design a generalized queue data type that supports all of the following operations in logarithmic time (or better) in the worst case.
 */
public class Exmp6<Item> {
	private RedBlackBST<Integer, Item> queue;
	private int index;
	
	public Exmp6() {
		index = 0;
		queue = new RedBlackBST<>();
	}
	
	public void app(Item item) {queue.put(index++, item);}
	
	public void removeFront() {queue.deleteMin();}
	
	public Item get(int i) {return queue.get(i);}
	
	public void remove(int i) {queue.delete(i);}
}
