package Sorts.QuickSort;

import edu.princeton.cs.algs4.StdRandom;

public class QuickSort {

	// partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
	// and return the index j.
	private static int partition(Comparable[] a, int lo, int hi) {
		int i = lo;
		int j = hi+1;

		while (true) {
			// find item on left to swap
			while (less(a[++i], a[lo])) {
				if (i == hi)
					break;
			}

			// find item on right to swap
			while (less(a[lo], a[--j])) {
				if (j == lo)
					break;
			}

			// check if pointers cross
			if (i >= j)
				break;

			exch(a,i,j); // swap
		}
		exch(a, lo, j); // swap with partitioning item

		return j; // return index of item now known to be in place
	}

	/**
	 * Rearranges the array in ascending order, using the natural order.
	 * @param a the array to be sorted
	 */
	public static void sort(Comparable[] a) {
		StdRandom.shuffle(a); // shuffle needs for performance guarantee
		sort(a, 0, a.length - 1);
	}

	// quicksort the subarray from a[lo] to a[hi]
	private static void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo)
			return;

		int j = partition(a, lo, hi);

		sort(a, lo, j-1);
		sort(a, j+1, hi);
	}

	// exchange a[i] and a[j]
	private static void exch(Object[] a, int i, int j) {
		Object swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	private static boolean less(Comparable v, Comparable w) {
		if (v == w) return false;   // optimization when reference equals
		return v.compareTo(w) < 0;
	}

	/**
	 * Rearranges the array so that {@code a[k]} contains the kth smallest key;
	 * {@code a[0]} through {@code a[k-1]} are less than (or equal to) {@code a[k]}; and
	 * {@code a[k+1]} through {@code a[n-1]} are greater than (or equal to) {@code a[k]}.
	 *
	 * @param  a the array
	 * @param  k the rank of the key
	 * @return the key of rank {@code k}
	 * @throws IllegalArgumentException unless {@code 0 <= k < a.length}
	 */
	public static Comparable select(Comparable[] a, int k) {
		if (k < 0 || k >= a.length) 
			throw new IllegalArgumentException("Index is not btw 0 and " + a.length + ": " + k);

		StdRandom.shuffle(a);
		int lo = 0;
		int hi = a.length-1;

		while (hi > lo) {
			int i = partition(a, lo, hi);
			if (i > k)
				hi = i - 1;
			else if(i < k)
				lo = i + 1;
			else 
				return a[i];
		}
		return a[lo];
	}

	public static void main(String[] args) {
		Comparable[] a = {7,10,5,3,8,4,2,9,6}; // 2 3 4 5 6 7 8 9 10 -> 2 is 0 index

		//		Comparable[] a = new Comparable[1000];

		//		for (int i = 0; i < a.length; i++)
		//			a[i] = (int)(Math.random()*1000);

		QuickSort.sort(a);
		//		for (int i = 0; i < a.length; i++)
		//			System.out.println(a[i]);

		System.out.println("Fidn Kth element");
		//		StdRandom.shuffle(a);

		// If k is 0 find the first element in array
		// If k = 5 -> 7
		int ith = (int)QuickSort.select(a, 0);
		System.out.println(ith);
	}
}
