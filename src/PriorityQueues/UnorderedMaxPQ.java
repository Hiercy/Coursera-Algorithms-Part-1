package PriorityQueues;

public class UnorderedMaxPQ<Key extends Comparable<Key>> {

	private Key[] pq; // pq[i] = ith element on pq
	private int n; // number of elements on pq;
	
	// set inititial size of heap to hold size elements
	public UnorderedMaxPQ(int capacity) {
		pq = (Key[]) new Comparable[capacity];
		n = 0;
	}
	
	public boolean isEmpty() {
		return n == 0;
	}
	
	public void insert(Key x) {
		pq[n++] = x;
	}
	
	public Key delMax() {
		if (isEmpty())
			throw new ArrayIndexOutOfBoundsException("Heap is empty");
		int max = 0;
		for (int i = 1; i < n; i++) 
			if (less(max, i))
				max = i;
		exch(max, n-1);
		return pq[--n];
	}

	private void exch(int i, int j) {
		Key swap = pq[i];
		pq[i] = pq[j];
		pq[j] = swap;
	}

	private boolean less(int i, int j) {
		return pq[i].compareTo(pq[j]) < 0;
	}
	
	public static void main(String[] args) {
		UnorderedMaxPQ<String> pq = new UnorderedMaxPQ<String>(10);
//		pq.insert("b");
//		pq.insert("c");
		pq.insert("a");
		
		System.out.println(pq.delMax());
	}
}
