package Sorts;

import edu.princeton.cs.algs4.StdRandom;

public class MergeSort {

	public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
		// Слияние a[lo...mid] с a[mid+1...hi]
		assert isSorted(a, lo, mid);
		assert isSorted(a, mid+1, hi);

		int i = lo; // Левая часть левой половины
		int j = mid+1; // Левая часть правой половины

		// lo - начало
		for (int l = lo; l <= hi; l++) // Копирование а[lo..mid] в aux[lo..hi]
			aux[l] = a[l];

		for (int l = lo; l <= hi; l++) { // Слияние обратно в a[lo..hi]
			if (i > mid) // Если левая половина закончилась, то берем из правой
				a[l] = aux[j++];
			else if (j > hi) // Если правая половина зкончилась, то берем из левой
				a[l] = aux[i++];
			else if (less(aux[j], aux[i])) // Текущий ключ из правой половины меньше текущего ключа из левой половины(брать справа)
				a[l] = aux[j++];
			else // Текущий ключ из правой половины больше текущего ключа из левой полвина(брать слева)
				a[l] = aux[i++];
		}
	}

	// mergesort a[lo..hi] using auxiliary array aux[lo..hi]
	private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
		if (hi <= lo)
			return;

		int mid = lo + (hi - lo) / 2;

		sort(a, aux, lo, mid);
		sort(a, aux, mid+1, hi);

		merge(a, aux, lo, mid, hi);
	}

	// Rearranges the array in ascending order, using the natural order.
	public static void sort(Comparable[] a) {
		Comparable[] aux = new Comparable[a.length];

		sort(a, aux, 0, a.length-1);
	}

	private static boolean isSorted(Comparable[] a, int lo, int hi) {
		for (int i = lo+1; i <= hi; i++)
			if (less(a[i], a[i-1]))
				return false;

		return true;
	}

	// is v < w
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	public static void main(String[] args) {
		Comparable[] a = new Comparable[100000];
		for (int i = 0; i < 100000; i++)
			a[i] = StdRandom.uniform(a.length-1);

		MergeSort.sort(a);

		for (int i = 0; i < a.length; i++)
			System.out.println(a[i]);
	}
}
