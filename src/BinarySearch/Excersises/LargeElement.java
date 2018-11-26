package BinarySearch.Excersises;

import InOut.StdOut;

/*
 * Union-find with specific canonical element. 
 * Add a method find() to the union-find data type so that find(i) returns the largest element in the connected component containing i. 
 * The operations, union(), connected(), and find() should all take logarithmic time or better.
 */

public class LargeElement {
	private int[] id;
	private int[] sz;
	private int[] large;

	public LargeElement(int n) {
		id = new int[n];
		sz = new int[n];
		large = new int[n];
		for(int i = 0; i < n; i++) {
			id[i] = i;
			sz[i] = 1;
			large[i] = i;
		}
	}

	public int way(int p) {
		while (p != id[p])
			p = id[p];
		return p;
	}

	// return the largest element in connected component containing i
	public int find(int p) {
		return large[way(p)];
	}

	public void union(int p, int q) {
		int P = way(p);
		int Q = way(q);
		int larP = find(p);
		int larQ = find(q);
		if (Q == P)
			return;
		
		if (P < Q) {
			id[P] = Q;
			sz[Q] += sz[P];
			large[larP] = large[larQ];
			
//			if (larP < larQ) 
		} else {
			id[Q] = P;
			sz[Q] += sz[P];
			large[larQ] = large[larP];
			
//			if (larP > larQ) 
		}
	}
	
	public boolean connected(int p, int q) {
		return way(p) == way(q);
	}

	public static void main(String[] args) {
		LargeElement le = new LargeElement(10);
		le.union(1, 2);
		le.union(6, 9);
		le.union(1, 9);
		StdOut.println(le.find(1));
		
		le.union(0, 3);
		le.union(7, 8);
		le.union(4, 5);
		le.union(0, 4);
		le.union(4, 7);
		le.union(3, 1);
		StdOut.println(le.find(0));
		StdOut.println(le.find(7));
		StdOut.println(le.connected(7, 0));
		StdOut.println(le.connected(0, 1));
		
	}
}

