package module;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class SnakeObject {
	private ArrayList<SnakePoint> snakeBody = new ArrayList<>();
	private int maxX, maxY;

	public SnakeObject(int maxX, int maxY) {
		this.maxX = maxX;
		this.maxY = maxY;
		SnakePoint prevPoint = new SnakePoint(0, 0, maxX, maxY);
		for (int i = 0; i < 10; i ++) {
			SnakePoint newPoint = new SnakePoint(0, 0, this.maxX, this.maxY);
			newPoint.x = (2 * i + 1) * newPoint.getRadius();
			newPoint.y = newPoint.getRadius();
			for (int j = prevPoint.x + 1; j <= newPoint.x; j ++)
				newPoint.moveHistory.add(new Point(j, newPoint.y));			
			snakeBody.add(newPoint);
			prevPoint = (SnakePoint) newPoint.clone();
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
	

	public void move(GamePanel pnlGame) {
		// Move head
		SnakePoint head = snakeBody.get(snakeBody.size() - 1);
		head.moveHistory.add((Point) head.clone());
		head.move(head.getDirection());
		System.out.println(snakeBody.get(0).moveHistory.size());
		for (int i = 0; i < snakeBody.size() - 1; i ++) {			
			snakeBody.get(i).moveHistory.add((Point) snakeBody.get(i).clone());			
			snakeBody.get(i).x = snakeBody.get(i + 1).moveHistory.get(0).x;
			snakeBody.get(i).y = snakeBody.get(i + 1).moveHistory.get(0).y;
			snakeBody.get(i + 1).moveHistory.remove(0);
			if (snakeBody.get(i).moveHistory.size() > snakeBody.get(i).getRadius() * 2)
				snakeBody.get(i).moveHistory.remove(0);
		}
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
		int x = snakeBody.get(0).moveHistory.get(0).x;
		int y = snakeBody.get(0).moveHistory.get(0).y;
		snakeBody.add(0, new SnakePoint(x, y, maxX, maxY));
	}

}
