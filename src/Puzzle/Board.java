package Puzzle;

import java.util.Arrays;
import edu.princeton.cs.algs4.Queue;

public class Board {

	private static final int SPACE = 0;

	private final int[][] blocks;

	// construct a board from an n-by-n array of blocks
	// (where blocks[i][j] = block in row i, column j)
	public Board(int[][] block) {
		if (block == null)
			throw new NullPointerException("qwe");
		this.blocks = cop(block);

		//		blocks = new int[block.length][block.length];
		//		
		//		for (int row = 0; row < this.blocks.length; row++) {
		//			for (int col = 0; col < this.blocks.length; col++) {
		//				blocks[row][col] = block[row][col];
		//			}
		//		}
	}

	// board dimension n
	public int dimension() {
		return blocks.length;
	}

	// number of blocks out of place
	public int hamming() {
		int count = 0; // The number of block in the wrong position
		int size = blocks.length;

		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				// last right pos
				if (row == size-1 && col == size-1)
					break;
				if (blocks[row][col] != row * size + col + 1) 
					count++;
			}
		}
		return count;
	}

	// sum of Manhattan distances between blocks and goal
	public int manhattan() {
		int distance = 0;

		for (int row = 0; row < blocks.length; row++) {
			for (int col = 0; col < blocks.length; col++) {
				int val = blocks[row][col];

				if (blocks[row][col] == 0)
					continue;

				int dx = (val-1) / blocks.length;
				int dy = (val-1) % blocks.length;

				distance += Math.abs(dx - row) + Math.abs(dy - col);
			}
		}
		return distance;
	}

	// is this board the goal board?
	public boolean isGoal() {
		return hamming() == 0;
	}

	// a board that is obtained by exchanging any pair of blocks
	public Board twin() {
		int n = blocks.length;
		int[][] swaped = new int[n][n];

		copyBlocks(swaped);

		if (swaped[0][0] != 0 && swaped[0][1] != 0) {
			swaped[0][0] = blocks[0][1];
			swaped[0][1] = blocks[0][0];
		} else {
			swaped[1][0] = blocks[1][1];
			swaped[1][1] = blocks[1][0];
		}

		return new Board(swaped);
	}

	// does this board equal y?
	public boolean equals(Object y) {
		if (y == null)
			return false;
		if (y == this)
			return true;
		if (y.getClass() != this.getClass())
			return false;

		Board that = (Board) y;

		return Arrays.deepEquals(this.blocks, that.blocks);
	}

	// all neighboring boards
	public Iterable<Board> neighbors() {
		Queue<Board> neighbors = new Queue<Board>();

		int[] location = zeroLocation();
		int row = location[0];
		int col = location[1];

		if (row > 0) 
			neighbors.enqueue(new Board(swap(row, col, row-1, col)));
		if (row < blocks.length-1)
			neighbors.enqueue(new Board(swap(row, col, row+1, col)));
		if (col > 0)
			neighbors.enqueue(new Board(swap(row, col, row, col-1)));
		if (col < blocks.length-1)
			neighbors.enqueue(new Board(swap(row, col, row, col+1)));

		return neighbors;

	}

	// string representation of this board (in the output format specified below)
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(dimension() + "\n");

		for (int row = 0; row < blocks.length; row++) {
			for (int col = 0; col < blocks.length; col++) {
				s.append(String.format("%2d ", blocks[row][col]));
			}
			s.append("\n");
		}
		return s.toString();
	}

	private int[][] cop(int[][] block) {
		int[][] copy = new int[block.length][block.length];

		for (int row = 0; row < block.length; row++) {
			for (int col = 0; col < block.length; col++) {
				copy[row][col] = block[row][col];
			}
		}
		return copy;
	}

	private int[][] swap(int row, int col, int row2, int col2) {
		int[][] copy = cop(blocks);

		int tmp = copy[row][col];
		copy[row][col] = copy[row2][col2];
		copy[row2][col2] = tmp;

		return copy;
	}

	private int[] zeroLocation() {
		for (int row = 0; row < blocks.length; row++) {
			for (int col = 0; col < blocks.length; col++) {
				if (isSpace(blocks[row][col])) {
					int[] location = new int[2];
					location[0] = row;
					location[1] = col;

					return location;
				}
			}
		}
		throw new RuntimeException();
	}

	private boolean isSpace(int i) {
		return i == SPACE;
	}

	private void copyBlocks(int[][] copy) {
		int n = blocks.length;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				copy[i][j] = blocks[i][j];
			}
		}
	}

	//	public static void main(String[] args) {
	//		int[][] a = {{0,1,3},{4,2,5},{7,8,6}};
	//
	//		Board b = new Board(a);
	//
	//		for (int i = 0; i < a.length; i++) {
	//			for (int j = 0; j < a.length; j++) {
	//				System.out.print(a[i][j] + " ");
	//			}
	//			System.out.println();
	//		}
	//		//		System.out.println(b.dimension());
	//		System.out.println(b.hamming());
	//		System.out.println(b.manhattan());
	//		System.out.println(b.isGoal());
	//		System.out.println(b.neighbors());
	//	}
}
