package Puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {
	
	private Move lastMove;
	
	private class Move implements Comparable<Move> {
		private Move previous;
		private final Board board;
		private int numMoves = 0;

		public Move(Board board) {
			this.board = board;
		}

		public Move(Board board, Move previous) {
			this.board = board;
			this.previous = previous;
			this.numMoves = previous.numMoves + 1;
		}

		public int compareTo(Move move) {
			return (this.board.manhattan() - move.board.manhattan()) + (this.numMoves - move.numMoves);
		}
	}

	// find a solution to the initial board (using the A* algorithm)
	public Solver(Board initial) {
		MinPQ<Move> moves = new MinPQ<Move>();
		moves.insert(new Move(initial));

		MinPQ<Move> twinMoves = new MinPQ<Move>();
		twinMoves.insert(new Move(initial.twin()));

		while (true) {
			lastMove = expand(moves);
			if (lastMove != null || expand(twinMoves) != null) return;
		}
	}

	private Move expand(MinPQ<Move> moves) {
		if (moves.isEmpty()) return null;
		Move bestMove = moves.delMin();
		if (bestMove.board.isGoal()) return bestMove;
		for (Board neighbor : bestMove.board.neighbors()) {
			if (bestMove.previous == null || !neighbor.equals(bestMove.previous.board)) {
				moves.insert(new Move(neighbor, bestMove));
			}
		}
		return null;
	}

	// is the initial board solvable?
	public boolean isSolvable() {
		return (lastMove != null);
	}

	// min number of moves to solve initial board; -1 if unsolvable
	public int moves() {
		return isSolvable() ? lastMove.numMoves : -1;
	}

	// sequence of boards in a shortest solution; null if unsolvable
	public Iterable<Board> solution() {
		if (!isSolvable()) return null;

		Stack<Board> moves = new Stack<Board>();
		while (lastMove != null) {
			moves.push(lastMove.board);
			lastMove = lastMove.previous;
		}

		return moves;
	}
}
