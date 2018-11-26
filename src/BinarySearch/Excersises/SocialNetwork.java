package BinarySearch.Excersises;

import InOut.StdOut;

/*
 * Social network connectivity. 
 * Given a social network containing n members and 
 * a log file containing m timestamps at which times pairs of members formed friendships, 
 * design an algorithm to determine the earliest time at which all members are connected (i.e., every member is a friend of a friend of a friend ... of a friend). 
 * Assume that the log file is sorted by timestamp and that friendship is an equivalence relation. 
 * The running time of your algorithm should be m*log nm*logn or better and use extra space proportional to n.
 */

public class SocialNetwork {
	private int[] id;
	private int[] sz; // число узлов в поддереве с корнем в i
	private int count; // number of components
	
	// n - кол-во объектов
	public SocialNetwork(int n) {
		id = new int[n];
		sz = new int[n];
		count = n;
		for (int i = 0; i < n; i++) {
			id[i] = i;
			sz[i] = 1;
		}
	}
	
	public int count() {
		return count;
	}
	
	public int find(int p) {
		while (p != id[p])
			p = id[p];
		return p;
	}
	
	// Объединение p и q в один компонент
	public void union(int p, int q) {
		int P = find(p);
		int Q = find(q);
		
		// Если они равны, то они уже в одном компоненте
		if (P == Q)
			return;
		
		if (Q > P) {
			id[P] = Q;
			sz[Q] += sz[P];
		} else {
			id[Q] = P;
			sz[P] += sz[Q];
		}
		count--; // При каждом соединении мест все меньше...
	}
	
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}
	
	public static void main(String[] args) {
		SocialNetwork sn = new SocialNetwork(10);
		StdOut.println(sn.count());
		sn.union(2, 0);
		sn.union(8, 4);
		sn.union(0, 6);
		sn.union(1, 9);
		sn.union(1, 2);
		sn.union(6, 4);
		StdOut.println(sn.connected(8, 2));
		StdOut.println(sn.count());
	}
}
