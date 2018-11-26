package Sorts.Shuffle;

import edu.princeton.cs.algs4.StdRandom;

public class Shuffle {

	public static void shuffle(int[] a) {
		if (a == null)
			throw new IllegalArgumentException("Argument is Null");

		int size = a.length;
		if (size <= 0)
			throw new IllegalArgumentException("0 Element");

		for (int i = 0; i < size; i++) {
			int r = i + StdRandom.uniform(size-i); // between i and n-1;
			int temp = a[i];
			a[i] = a[r];
			a[r] = temp;
		}
	}

	public static void main(String[] args) {
		int[] a = {-1,2};

		Shuffle.shuffle(a);

		for (int i = 0; i < a.length; i++)
			System.out.println(a[i]);
	}
}
