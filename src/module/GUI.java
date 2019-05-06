package module;

import javax.swing.JFrame;

public class GUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -946347400642720293L;
	private GamePanel pnlGame;

	public GUI() {
		initWindow();
		initComponents();
		pack();
	}

	private void initWindow() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void initComponents() {		
		pnlGame = new GamePanel(800, 600);
		add(pnlGame);
	}

}
