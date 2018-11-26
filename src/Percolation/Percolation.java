package Percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

	private boolean[][] grid;
	private int size;

	private WeightedQuickUnionUF quickUnion;
	private WeightedQuickUnionUF quickUnion2;

	private int top;
	private int bottom;
	private int openSites;

	// create n-by-n grid, with all sites blocked
	public Percolation(int n) {
		this.size = n;
		this.grid = new boolean[n][n];
		int maxIndex = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				grid[i][j] = false;
				maxIndex = position(i + 1, j + 1);
			}
		}
		top = maxIndex + 1;
		bottom = maxIndex + 2;
		openSites = 0;

		quickUnion = new WeightedQuickUnionUF(bottom + 1);
		quickUnion2 = new WeightedQuickUnionUF(bottom + 1);
	}

	private int position(int x, int y) {
		return x + (y * size);
	}

	// open site (row, col) if it is not open already
	public void open(int i, int j) {
		// Indexes are from 1 to N while array indexes from 0 to N - 1
		grid[j - 1][i - 1] = true;
		openSites++;

		if (j != 1) 
			union(i, j, 0, -1); // Connect to left square

		if (j != size) 
			union(i, j, 0, 1); // Connect to right square

		if (i != 1) 
			union(i, j, -1, 0); // connect to top cell
		else 
			unionVirtual(i, j, top, false); // Connect to top virtual

		if (i != size) 
			union(i, j, 1, 0); // connect to bottom cell
		else 
			// Connect to bottom virtual
			unionVirtual(i, j, bottom, true);
	}

	// is site (row, col) open?
	public boolean isOpen(int i, int j) {
		validateIndex(i, j);
		return grid[j - 1][i - 1];
	}

	// is site (row, col) full?
	public boolean isFull(int i, int j) {
		validateIndex(i, j);
		return quickUnion2.connected(top, position(i, j));
	}

	// does the system percolate?
	public boolean percolates() {
		return quickUnion.connected(bottom, top);
	}

	public int numberOfOpenSites() {
		return openSites;
	}

	private void union(int i, int j, int rowOffset, int columnOffset) {
		int currentKey = position(i, j);
		int column2 = j + columnOffset;
		int row2 = i + rowOffset;
		if (isOpen(row2, column2)) {
			quickUnion.union(currentKey, position(row2, column2));
			quickUnion2.union(currentKey, position(row2, column2));
		}
	}

	private void unionVirtual(int i, int j, int virtualKey, boolean btm) {
		int currentKey = position(i, j);
		quickUnion.union(currentKey, virtualKey);
		if (!btm) {
			quickUnion2.union(currentKey, virtualKey);
		}
	}

	private boolean isValid(int x, int y) {
		return x >= 1 && x <= size && y >= 1 && y <= size;
	}

	private void validateIndex(int x, int y) {
		if (!isValid(x, y))
			throw new IndexOutOfBoundsException(String.format("N:%d x:%d y:%d", size, x, y));
	}

	public static void main(String [] args) {
		Percolation p = new Percolation(3);
		System.out.println("p.isOpen(1, 2) = " + p.isOpen(1, 2));
		p.open(1, 2);
		System.out.println("p.isOpen(1, 2) = " + p.isOpen(1, 2));


		System.out.println("p.isOpen(2,2) = " + p.isOpen(2, 2));
		p.open(2, 2);
		System.out.println("p.isOpen(2,2) = " + p.isOpen(2, 2));
		System.out.println("p.isFull(2, 2) = " + p.isFull(2, 2));


		System.out.println("p.isOpen(3, 1) = " + p.isOpen(3, 2));
		p.open(3, 2);
		System.out.println("p.isOpen(3, 1) = " + p.isOpen(3, 2));
		p.isFull(3, 2);


		System.out.println("p.percolates() = " + p.percolates());

		//		Percolation p = new Percolation(5);
		//		System.out.println("p.isOpen(1, 3) = " + p.isOpen(1, 3));
		//		p.open(1, 3);
		//		System.out.println("p.isOpen(1,3) = " + p.isOpen(1, 3));
		//		System.out.println("p.isFull(1, 3) = " + p.isFull(1, 3));
	}
}
