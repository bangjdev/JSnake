package module;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

public class Food extends Point {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1009974618204864667L;
	private Random r = new Random();
	private int radius = 5;

	public Food(int width, int height) {
		x = (2 * r.nextInt(width / radius / 2 - 2) + 1) * radius;
		y = (2 * r.nextInt(height / radius / 2 - 2) + 1) * radius;
	}

	public void paintComponent(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x - radius, y - radius, radius * 2, radius * 2);
	}
}
