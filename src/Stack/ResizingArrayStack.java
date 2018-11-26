package Stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayStack<Item> implements Iterable<Item> {

	private Item[] a; // array of items
	private int N; // number of elements on stack
	
	public ResizingArrayStack() {
		a = (Item[]) new Object[2];
		N = 0;
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public int size() {
		return N;
	}
	
	// resize the underlying array holding the elements
	private void resize(int capacity) {
		assert capacity >= N;
		
		// Перенос стека в новый массив размером max
		Item[] temp = (Item[]) new Object[capacity];
		
		for (int i = 0; i < N; i++) 
			temp[i] = a[i];
		
		a = temp;
	}
	
	// Adds the items to this stack
	public void push(Item item) {
		if (N == a.length)
			resize(2 * a.length); // double size of array if necessary
		
		a[N++] = item;  // add item
	}
	
	// Removes and returns the item most recently added to this stack
	public Item pop() {
		if (isEmpty())
			throw new NoSuchElementException("Stack underflow");
		
		Item item = a[--N];
		a[N] = null;
		
		if (N > 0 && N == a.length/4)
			resize(a.length/2);
		
		return item;
	}
	
	// Returns (but does not remove) the item most recently added to this stack
	public Item peek() {
		if (isEmpty()) 
			throw new NoSuchElementException("Stack underflow");
		
		return a[N-1];
	}
	
	// Returns an iterator to this stack that iterates through the items in LIFO order
	@Override
	public Iterator<Item> iterator() {
		return new ReverseArrayIterator();
	}
	
	class ReverseArrayIterator implements Iterator<Item> {
		private int i;
		
		public ReverseArrayIterator() {
			i = N-1;
		}

		@Override
		public boolean hasNext() {
			return i >= 0;
		}

		@Override
		public Item next() {
			return a[--i];
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	public static void main(String[] args) {
		ResizingArrayStack<Integer> s = new ResizingArrayStack<Integer>();

//		System.out.println("Peek " + s.peek()); Checked exception
		
		for (int i = 0; i < 10000; i++) {
			s.push(i);
		}
		System.out.println("Peek " + s.peek());
		System.out.println("After Push loop");
		System.out.println("Size = " + s.size());

		while (!s.isEmpty()) {
			s.pop();
		}
		System.out.println("After Pop loop");
		System.out.println("Size = " + s.size());
		
//		System.out.println("Peek " + s.peek());
	}
}
