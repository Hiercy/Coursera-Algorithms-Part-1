package Sorts;

import edu.princeton.cs.algs4.StdRandom;

public class MergeSort {

	public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
		// ������� a[lo...mid] � a[mid+1...hi]
		assert isSorted(a, lo, mid);
		assert isSorted(a, mid+1, hi);

		int i = lo; // ����� ����� ����� ��������
		int j = mid+1; // ����� ����� ������ ��������

		// lo - ������
		for (int l = lo; l <= hi; l++) // ����������� �[lo..mid] � aux[lo..hi]
			aux[l] = a[l];

		for (int l = lo; l <= hi; l++) { // ������� ������� � a[lo..hi]
			if (i > mid) // ���� ����� �������� �����������, �� ����� �� ������
				a[l] = aux[j++];
			else if (j > hi) // ���� ������ �������� ����������, �� ����� �� �����
				a[l] = aux[i++];
			else if (less(aux[j], aux[i])) // ������� ���� �� ������ �������� ������ �������� ����� �� ����� ��������(����� ������)
				a[l] = aux[j++];
			else // ������� ���� �� ������ �������� ������ �������� ����� �� ����� �������(����� �����)
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
