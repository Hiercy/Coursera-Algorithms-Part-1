package Sorts.CollinearPoints;

import java.util.ArrayList;

public class BruteCollinearPoints {

	private ArrayList<LineSegment> segments = new ArrayList<>();

	// finds all line segments containing 4 points
	public BruteCollinearPoints(Point[] points) {
		if (points == null)
			throw new NullPointerException();

		Point[] copy = points.clone();

		if (duplicate(copy))
			throw new IllegalArgumentException("Duplicates");

		for (int fir = 0; fir < copy.length - 3; fir++) {
			for (int sec = fir + 1; sec < copy.length - 2; sec++) {
				double slopeFS = copy[fir].slopeTo(copy[sec]);
				for (int third = sec + 1; third < copy.length - 1; third++) {
					double slopeFT = copy[fir].slopeTo(copy[third]);
					if (slopeFS == slopeFT) {
						for (int forth = third + 1; forth < copy.length; forth++) {
							double slopeFF = copy[fir].slopeTo(copy[forth]);
							if (slopeFS == slopeFF)
								segments.add(new LineSegment(copy[fir], copy[forth]));
						}
					}
				}
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
