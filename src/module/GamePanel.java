package module;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4280895825532149728L;
	private int fps = 100;
	private int delay = 1000 / fps;
	private SnakeObject snake;
	private Food apple;
	private boolean catching = true;

	public void setCatching(boolean catching) {
		this.catching = catching;
	}

	public SnakeObject getSnake() {
		return snake;
	}

	public void setSnake(SnakeObject snake) {
		this.snake = snake;
	}

	private int panelWidth, panelHeight;

	private Timer gameTimer;	

	public GamePanel(int width, int height) {
		super();		
		addKeyListener(this);
		this.panelWidth = width;
		this.panelHeight = height;
		apple = new Food(panelWidth, panelHeight);
		snake = new SnakeObject(panelWidth, panelHeight);
		panelInit();
	}

	private void panelInit() {
		setFocusable(true);
		setPreferredSize(new Dimension(panelWidth, panelHeight));		
		gameTimer = new Timer(delay, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Toolkit.getDefaultToolkit().sync();
				gameUpdate();
				redraw();	
				catching = true;
			}
		});
		gameTimer.start();
	}

	private void redraw() {
		super.paintComponent(this.getGraphics());
		snake.paintComponent(this.getGraphics());
		apple.paintComponent(this.getGraphics());
	}

	private void gameUpdate() {
		snake.move(this);
		if (snake.checkCollision()) {
			gameOver();
		}								
		if (snake.hasEaten(apple)) {
			snake.growUp();
			apple = new Food(panelWidth, panelHeight);					
		}
	}
	
	private void gameOver() {
		System.exit(0);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (!catching)			
			return;
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			snake.getArrCoors().get(snake.getArrCoors().size() - 1).setDirection(0);
			break;
		case KeyEvent.VK_LEFT:
			snake.getArrCoors().get(snake.getArrCoors().size() - 1).setDirection(1);
			break;
		case KeyEvent.VK_DOWN:
			snake.getArrCoors().get(snake.getArrCoors().size() - 1).setDirection(2);
			break;
		case KeyEvent.VK_RIGHT:
			snake.getArrCoors().get(snake.getArrCoors().size() - 1).setDirection(3);
			break;
		}
		catching = false;
	}

}
