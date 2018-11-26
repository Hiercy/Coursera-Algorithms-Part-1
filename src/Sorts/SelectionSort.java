package Sorts;

import java.util.Comparator;

/*
 * Циклом проходится по массиву, находит наименьший элемент и ставится на первое место
 */

public class SelectionSort {

	// Rearranges the array in ascending order, using the natural order.
	public static void sort(Comparable[] a) {
		int size = a.length;

		for (int i = 0; i < size; i++) { // Swap a[i] with the smallest element of a[i+1..n]
			int min = i; // Index min element

			for (int j = i+1; j < size; j++) 
				if (less(a[j], a[min])) 
					min = j;

			exch(a, i, min);
			assert isSorted(a, 0, i);
		}
		assert isSorted(a);
	}

	// Rearranges the array in ascending order, using a comparator.
	public static void sort(Object[] a, Comparator comparator) {
		int size = a.length;

		for (int i = 0; i < size; i++) {
			int min = i;

			for (int j = i+1; j < size; j++) {
				if (less(comparator, a[j], a[min]))
					min = j;
			}
			exch(a, i, min);
			assert isSorted(a, comparator, 0, i);
		}
		assert isSorted(a, comparator);
	}

	/**
	 * Rearranges the subarray a[lo..hi) in ascending order, using a comparator.
	 * @param a - the array
	 * @param lo - left endpoint
	 * @param hi - right endpoint
	 * @param comparator - the comparator specifying the order
	 */
	public static void sort(Object[] a, int lo, int hi, Comparator comparator) {
		for (int i = lo + 1; i < hi; i++) {
			for (int j = i; j > lo && less(a[j], a[j-1], comparator); j--) {
				exch(a, j, j-1);
			}
		}
		assert isSorted(a, comparator, lo, hi);
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

	// is v < w ?
	private static boolean less(Object v, Object w, Comparator comparator) {
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

		SelectionSort.sort(a);

		for (int i = 0; i < a.length; i++) 
			System.out.println(a[i]);
	}
}
