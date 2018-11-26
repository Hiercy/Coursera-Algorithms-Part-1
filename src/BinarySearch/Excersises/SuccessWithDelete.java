package BinarySearch.Excersises;

/*
 * Successor with delete. Given a set of n integers S={0,1,...,n−1} and a sequence of requests of the following form:
 * *Remove x from S
 * *Find the successor of x: the smallest y in S such that y >= x.
 * design a data type so that all operations (except construction) take logarithmic time or 
 * better in the worst case.
 */

public class SuccessWithDelete {
	private LargeElement le;

	public SuccessWithDelete(int n) {
		le = new LargeElement(n);
	}

	public void delete(int i) {
		le.union(i, i+1);
	}

	public int isSucc(int i) {
		return le.find(i);
	}

	public static void main(String[] args) {
		int N = 50;
		SuccessWithDelete swd = new SuccessWithDelete(50);
		//		    System.out.println(swd.isSucc(10));
		swd.delete(11);
		swd.delete(13);
		swd.delete(12);
		swd.delete(10);
		swd.delete(9);
		swd.delete(15);
		System.out.println(swd.isSucc(16));
		System.out.println(swd.isSucc(9));
		System.out.println(swd.isSucc(10));
	}
}
