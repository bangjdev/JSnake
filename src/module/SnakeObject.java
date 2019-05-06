package module;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class SnakeObject {
	private ArrayList<SnakePoint> snakeBody = new ArrayList<>();
	private int maxX, maxY;

	public SnakeObject(int maxX, int maxY) {
		this.maxX = maxX;
		this.maxY = maxY;
		for (int i = 0; i < 3; i ++) {
			SnakePoint newPoint = new SnakePoint(0, 0, this.maxX, this.maxY);
			newPoint.x = (2 * i + 1) * newPoint.getRadius();
			newPoint.y = newPoint.getRadius();
			snakeBody.add(newPoint);
		}
	}
	public void paintComponent(Graphics g) {		
		g.setColor(Color.GREEN);
		int i;
		for (i = 0; i < snakeBody.size() - 1; i ++) {
			SnakePoint point = snakeBody.get(i);
			g.fillOval(point.x - point.getRadius(), point.y - point.getRadius(), point.getRadius() * 2,
					point.getRadius() * 2);
		}
		g.setColor(Color.BLACK);
		SnakePoint point = snakeBody.get(i);
		g.fillOval(point.x - point.getRadius(), point.y - point.getRadius(), point.getRadius() * 2,
				point.getRadius() * 2);
	}

	public void move() {
		// Move head
		SnakePoint currentHead = (SnakePoint) snakeBody.get(snakeBody.size() - 1).clone();
		currentHead.move(currentHead.getDirection());
		snakeBody.add(currentHead);
		snakeBody.remove(0);		
	}

	public ArrayList<SnakePoint> getArrCoors() {
		return snakeBody;
	}

	public void setArrCoors(ArrayList<SnakePoint> arrCoors) {
		this.snakeBody = arrCoors;
	}
	
	public boolean checkCollision() {
		SnakePoint head = snakeBody.get(snakeBody.size() - 1);
		for (int i = 0; i < snakeBody.size() - 2; i ++)
			if (snakeBody.get(i).smashed(head)) {
				return true;
			}
		return false;
	}
	
	public boolean hasEaten(Food f) {		
		SnakePoint head = snakeBody.get(snakeBody.size() - 1);
		return (head.smashed(f));			
	}
	
	public void growUp() {
		SnakePoint currentHead = (SnakePoint) snakeBody.get(snakeBody.size() - 1).clone();
		currentHead.move(currentHead.getDirection());
		snakeBody.add(currentHead);
	}

}
