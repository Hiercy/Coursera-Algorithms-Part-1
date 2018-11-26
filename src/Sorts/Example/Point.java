package Sorts.Example;

import edu.princeton.cs.algs4.Shell;

/*
 * Given two arrays a[] and b[], each containing n distinct 2D points in the plane, 
 * design a subquadratic algorithm to count the number of points that are contained 
 * both in array a[] and array b[].
 */
public class Point implements Comparable<Point> {
	private int x; // x coordinate
	private int y; // y coordinate

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// is this point lexicographically smaller than that one?
	@Override
	public int compareTo(Point that) {
		if (this.x < that.x) // if this less than that return -1
			return -1;
		if (this.y == that.y && this.x == that.x) // if both equals return 0 
			return 0;
		if (this.y < that.y)
			return -1;

		return 1;
	}

	public static int countNumPoint(Comparable[] a, Comparable[] b) {
		Shell.sort(a);
		Shell.sort(b);

		int i = 0;
		int j = 0;
		int count = 0;

		while (i != a.length && j != b.length) {
			if (a[i].compareTo(b[j]) == 0) {
				i++;
				j++;
				count++;
			} else if (a[i].compareTo(b[j]) > 0)
				i++;
			else
				j++;
		}
		System.out.println("I = " + i + "\n" + "J = " + j);
		System.out.print("Count = ");
		return count;
	}

	public static void main(String[] args) {
		Comparable[] a = {7,10,5,3,1,4,2,9,6}; // j++
		Comparable[] b = {7,10,5,3,0,4,2,9,6}; // i++

		System.out.println(Point.countNumPoint(a, b));
	}
}
