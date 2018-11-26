package Bag;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Stats {
	public static void main(String[] args) {
		Bag<Double> num = new Bag<>();
		
		while (!StdIn.isEmpty())
			num.add(StdIn.readDouble());
		
		int n = num.size();
		double sum = 0.0;
		
		for (double x : num)
			sum += x;
		
		double mean = sum / n;
		
		sum = 0.0;
		
		for (double x : num)
			sum += (x - mean) * (x - mean);
		
		double std = Math.sqrt(sum/(n-1));
		
		StdOut.printf("Mean %,2f\n", mean);
		StdOut.printf("Mean Quadratic %,2f\n", std);
	}
}
