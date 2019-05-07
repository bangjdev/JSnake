package module;

import java.awt.Point;
import java.util.ArrayList;

public class SnakePoint extends Point {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2309307192399764285L;
	private int direction = 3; // 0 1 2 3 - UP LEFT DOWN RIGHT
	private int radius = 5, maxX, maxY;
	public ArrayList<Point> moveHistory = new ArrayList<>(); 

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	private final int X_FIX_VAL[] = { 0, -1, 0, 1 };
	private final int Y_FIX_VAL[] = { -1, 0, 1, 0 };

	public SnakePoint(int x, int y, int maxX, int maxY) {
		this.x = x;
		this.y = y;
		this.maxX = maxX;
		this.maxY = maxY;
	}

	public void move(int direction) {
		x += X_FIX_VAL[direction];
		y += Y_FIX_VAL[direction];
		if (x < 0)
			x = maxX - radius;
		if (y < 0)
			y = maxY - radius;
		x %= maxX;
		y %= maxY;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		if ((direction + this.direction != 2) && (direction + this.direction != 4))
			this.direction = direction;
	}
	
	public double pytagoreDistance(Point a, Point b) {
		return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
	}
	
	public boolean smashed(SnakePoint a) {
		return (pytagoreDistance(this, a) < radius * 2);
	}
	public boolean smashed(Food a) {
		return (pytagoreDistance(this, a) < radius * 2);
	}
}
