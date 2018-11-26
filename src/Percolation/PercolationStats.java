package Percolation;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

	private double[] thres; // stores results of percolation experiments
	
	private int n; // number of columns/rows in N x N grid
	private int t; // number of times to run stats
	
	private double CONST_NUMBER = 1.96;

	// perform trials independent experiments on an n-by-n grid
	public PercolationStats(int n, int t) {
		if (n <= 0 || t <= 0) 
			throw new IllegalArgumentException("number experements or size < 0");

		this.t = t;
		this.n = n;
		thres = new double[t];

		for (int i = 0; i < t; i++) 
			testo(i);
	}

	// sample mean of percolation threshold
	public double mean() {
		return StdStats.mean(thres);
	}

	// sample standard deviation of percolation threshold
	public double stddev() {
		return StdStats.stddev(thres);
	}
	
	// low  endpoint of 95% confidence interval
	public double confidenceLo() {
		return mean() - CONST_NUMBER * Math.sqrt(stddev()) / Math.sqrt(t);
	}
	
	// high endpoint of 95% confidence interval
	public double confidenceHi() {
		return mean() + CONST_NUMBER * Math.sqrt(stddev()) / Math.sqrt(t);
	}

	// private helper method
	private void testo(int index) {
		Percolation perc = new Percolation(n);
		double count = 0;
		int i, j;
		while (!perc.percolates()) {
			i = StdRandom.uniform(n) + 1;
			j = StdRandom.uniform(n) + 1;

			if (!perc.isOpen(i, j)) {
				count++;
				perc.open(i, j);
			}
		}
		thres[index] = count / (Math.pow(n, 2));
	}

	public static void main(String[] args) {
		int n = 200;
		int t = 100;
		
		PercolationStats ps = new PercolationStats(n, t);
		
		StdOut.println("mean = " + ps.mean());
		StdOut.println("stddev = " + ps.stddev());
		StdOut.println("high endpoint = " + ps.confidenceHi());
		StdOut.println("low endpoint  = " + ps.confidenceLo());
	}
}
