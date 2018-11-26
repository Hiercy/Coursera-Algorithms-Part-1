package Sorts.QuickSort;

import edu.princeton.cs.algs4.StdRandom;

public class Quick3Way {

	public static void sort(Comparable[] a) {
		StdRandom.shuffle(a);
		sort(a, 0, a.length-1);
	}

	private static void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo)
			return;

		int lt = lo;
		int gt = hi;
		int i = lo + 1;

		Comparable v = a[lo];

		while (i <= gt) {
			int cmp = a[i].compareTo(v);
			if (cmp < 0)
				exch(a, lt++, i++);
			else if (cmp > 0)
				exch(a, i, gt--);
			else 
				i++;
		}
		sort(a, lo, lt-1);
		sort(a, gt+1, hi);
	}

	private static void exch(Object[] a, int i, int j) {
		Object tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	public static void main(String[] args) {

	}
}
