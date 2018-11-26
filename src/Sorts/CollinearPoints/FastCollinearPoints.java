package Sorts.CollinearPoints;

import java.util.ArrayList;
import java.util.Arrays;

// https://github.com/AlexJoz/Algorithms--Java-/blob/master/Week3-Collinear%20Points/FastCollinearPoints.java#L5
public class FastCollinearPoints {

	private ArrayList<LineSegment> segments = new ArrayList<>();

	public FastCollinearPoints(Point[] points) {
		// check corner cases
		if (points == null)
			throw new NullPointerException();

		Point[] copy = points.clone();
		Arrays.sort(copy);

		if (duplicate(copy)) {
			throw new IllegalArgumentException("duplicate points");
		}

		for (int i = 0; i < copy.length-3; i++) {
			Arrays.sort(copy);

			// Sort the points according to the slopes they makes with p.
			// Check if any 3 (or more) adjacent points in the sorted order
			// have equal slopes with respect to p. If so, these points,
			// together with p, are collinear.

			Arrays.sort(copy, copy[i].slopeOrder());

			for (int j = 0, first = 1, last = 2; last < copy.length; last++) {
				//find last colliniar to j point
				while (last < copy.length && Double.compare(copy[j].slopeTo(copy[first]), copy[j].slopeTo(copy[last])) == 0) {
					last++;
				}
				// if found at least 3 elements, make segment if its unique
				if (last - first >= 3 && copy[j].compareTo(copy[first]) < 0) {
					segments.add(new LineSegment(copy[j], copy[last-1]));
				}
				first = last;
			}
		}
	}

	private boolean duplicate(Point[] copy) {
		for (int i = 0; i < copy.length-1; i++)
			if (copy[i].compareTo(copy[i+1]) == 0)
				return true;

		return false;
	}

	// the number of line segments
	public int numberOfSegments() {
		return segments.size();
	}

	// the line segments
	public LineSegment[] segments() {
		return segments.toArray(new LineSegment[segments.size()]);
	}
}
