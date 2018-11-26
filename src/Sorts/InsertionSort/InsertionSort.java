package Sorts;

import java.util.Comparator;

/*
 * Ёлементы входной последовательности просматриваютс€ по одному, и каждый новый поступивший элемент размещаетс€ в подход€щее место среди ранее упор€доченных элементов
 */

public class InsertionSort {

	public static void sortOldV(Comparable[] a) {
		int size = a.length;
		for (int i = 0; i < size; i++) {
			for (int j = i; j > 0 && a[j].compareTo(a[j-1]) < 0; j--) {
				Comparable swap = a[j];
				a[j] = a[j-1];
				a[j-1] = swap;
			}
		}
	}

	// Rearranges the array in ascending order, using the natural order.
	public static void sort(Comparable[] a) {
		int n = a.length;
		for (int i = 1; i < n; i++) {
			for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
				exch(a, j, j-1);
			}
			assert isSorted(a, 0, i);
		}
		assert isSorted(a);
	}
	
	/**
	 * Rearranges the subarray a[lo..hi) in ascending order, using the natural order.
	 * @param a  - the array to be sorted
	 * @param lo - left endpoint (inclusive)
	 * @param hi - right endpoint (exclusive)
	 */
	public static void sort(Comparable[] a, int lo, int hi) {
		for (int i = lo+1; i < hi; i++) { // lo+1 - it is written to be clear(can just put 1)
			for (int j = i; j > lo && less(a[j], a[j-1]); j--) {
				exch(a, j, j-1);
			}
		}
		assert isSorted(a, lo, hi);
	}

	// Check if array is sorted
	private static boolean isSorted(Comparable[] a) {
		return isSorted(a, 0, a.length-1);
	}

	// is the array a[] sorted?
	private static boolean isSorted(Object[] a, Comparator comparator) {
		return isSorted(a, comparator, 0, a.length-1);
	}

	// is the array sorted from a[lo] to a[hi]
	private static boolean isSorted(Object[] a, Comparator comparator, int lo, int hi) {
		for (int i = lo+1; i <= hi; i++)
			if (less(comparator, a[i], a[i-1]))
				return false;

		return true;
	}

	// is the array sorted from a[lo] to a[hi]
	private static boolean isSorted(Comparable[] a, int lo, int hi) {
		for (int i = lo + 1; i <= hi; i++)
			if (less(a[i], a[i-1]))
				return false;

		return true;
	}

	// is v < w ?
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	// is v < w ?
	private static boolean less(Comparator comparator, Object v, Object w) {
		return comparator.compare(v, w) < 0;
	}

	// exchange a[i] and a[j]
	private static void exch(Object[] a, int i, int j) {
		Object swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	public static void main(String[] args) {
		Comparable[] a = {7,10,5,3,8,4,2,9,6};

		InsertionSort.sort(a, 0, 9);

		for (int i = 0; i < a.length; i++)
			System.out.println(a[i]);
	}
}
