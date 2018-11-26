package Sorts.Example;

import Sorts.ShellSort;

/* 
 * Given two integer arrays of size n, 
 * design a subquadratic algorithm to determine whether one is a permutation of the other. That is, 
 * do they contain exactly the same entries but, possibly, in a different order.
 */
public class Permutation implements Comparable<Permutation>{
	private int x;
	private int y;

	public Permutation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Permutation that) {
		if (this.x > that.x )
			return 1;
		if (this.x < that.x)
			return -1;
		if (this.y > that.y)
			return 1;
		if (this.y < that.y)
			return -1;

		// If both arg is equal return 0;
		return 0;
	}

	public static boolean contain(Comparable[] a, Comparable[] b) {
		ShellSort.sort(a);
		ShellSort.sort(b);

		int count = 0;

		for (int i = 0; i < a.length; i++)
			for (int j = 0; j < b.length; j++)
				if (a[i] == b[j])
					count++;
		
		if (count == b.length)
			return true; 
		
		return false;
	}

	public static void main(String[] args) {
		Comparable[] a = {7,10,5,3,2,4,2,9,6}; // i++
		Comparable[] b = {1,2,3}; // j++

		System.out.println(Permutation.contain(a, b));
	}
}
