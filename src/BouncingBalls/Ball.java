package BouncingBalls;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class Ball {

	private double rx, ry; // position
	private double vx, vy; // velocity
	private double radius; // radius

	public Ball() {
		rx = StdRandom.uniform();
		ry = StdRandom.uniform();
		
		vx = StdRandom.uniform(0, 0.03);
		vy = StdRandom.uniform(0, 0.03);

		radius = 0.01;

	}

	public void move(double dt) {
		if ((rx + vx*dt < radius) || (rx + vx*dt > 1.0 - radius))
			vx = -vx;
		if ((ry + vy*dt < radius) || (ry + vy*dt > 1.0 - radius))
			vy = -vy;

		rx = rx + vx*dt;
		ry = ry + vy*dt;
	}

	public void draw() {
		StdDraw.filledCircle(rx, ry, radius);
	}
}
