package Sorts.Example2;

import java.util.Arrays;

/*
 * Suppose that the subarray a[0] to a[n−1] is sorted and the subarray a[n] to a[2*n−1] is sorted. 
 * How can you merge so that a[0] to a[2*n−1] is sorted using an auxiliary array of length n (instead of 2n)?
 */

public class MergingWithSmallerAux {

	public static void mergeWithSmaller(Comparable[] a, Comparable[] aux, int size) {

		for (int k = 0; k < size; k++)
			aux[k] = a[k];

		int i = 0;
		int j = size;
		int k = 0;

		while (k < a.length) {
			if (i >= size)
				a[k++] = a[j++];
			else if (aux[i].compareTo(a[j]) < 0)
				a[k++] = a[j++];
			else 
				a[k++] = aux[i++];
		}
	}

	public static void main(String[] args) {
		Comparable[] a = {40, 61, 70, 71, 99, 100, 75, 55, 51, 20};
		Comparable[] aux = new Comparable[a.length];
		int size = a.length / 2;

		MergingWithSmallerAux.mergeWithSmaller(a, aux, size);

		System.out.println("After merging:");
		Arrays.stream(a).forEach((c) -> System.out.print(c + ","));
		System.out.println();
	}
}
