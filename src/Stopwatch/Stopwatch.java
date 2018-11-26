package Stopwatch;

import Sorts.InsertionSort;
import Sorts.MergeSort;
import Sorts.SelectionSort;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.ThreeSum;

public class Stopwatch {

	private final long start;
	
	public Stopwatch() {
		start = System.currentTimeMillis();
	}
	
	public double elapsedTime() {
		long now = System.currentTimeMillis();
		return (now - start) / 1000.0;
	}
	
	public static void main(String[] args) {
		Comparable[] a = new Comparable[100000];
		for (int i = 0; i < 100000; i++)
			a[i] = StdRandom.uniform(0, 100);
		
		
		Stopwatch timer = new Stopwatch();
		SelectionSort.sort(a);
		double time = timer.elapsedTime();
		
		StdOut.println(time + "sec");
		

	}
}
