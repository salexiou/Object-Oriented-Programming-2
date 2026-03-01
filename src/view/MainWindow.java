package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import control.GameController;

public class MainWindow extends JFrame{
	
	static public final int WIDTH = 1200;
	static public final int HEIGHT = 800;
	static public final int TOP_HEIGHT = 80;
	static public final int PLAYER_WIDTH = 300;
	
	
	private TopPanel topPanel;
	private PlayerPanel leftPnl;
	private PlayerPanel rightPnl;
	private MainAreaPanel mainPnl;
	private GameController gc;
	
	public MainWindow(GameController gc) {
		
		super("TUC-TAC-TOE");
		this.gc = gc;
		
		Container c = this.getContentPane();
		c.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		this.addWindowListener(new AppListener());
		
		this.topPanel = new TopPanel(this.gc);
		c.add(topPanel, BorderLayout.PAGE_START);
		
		this.leftPnl = new PlayerPanel(gc,0);
		this.rightPnl = new PlayerPanel(gc,1);
		c.add(this.leftPnl , BorderLayout.LINE_START);
		c.add(this.rightPnl , BorderLayout.LINE_END);
		
		this.mainPnl = new MainAreaPanel(this.gc);
		c.add(this.mainPnl , BorderLayout.CENTER);
		
		this.setVisible(true);
		this.pack();
		this.setLocationRelativeTo(null);
		
		
	}
	
	private class AppListener extends WindowAdapter{
		
		public void windowClosing(WindowEvent e) {
			System.out.println("bye bye");
			System.exit(ABORT);
			
		}
	}

	public PlayerPanel getLeftPnl() {
		return leftPnl;
	}

	public void setLeftPnl(PlayerPanel leftPnl) {
		this.leftPnl = leftPnl;
	}

	public PlayerPanel getRightPnl() {
		return rightPnl;
	}

	public void setRightPnl(PlayerPanel rightPnl) {
		this.rightPnl = rightPnl;
	}

	public MainAreaPanel getMainPnl() {
		return mainPnl;
	}

	public void setMainPnl(MainAreaPanel mainPnl) {
		this.mainPnl = mainPnl;
	}

	
	public TopPanel getTopPanel() {
		return topPanel;
	}

	public void setTopPanel(TopPanel topPanel) {
		this.topPanel = topPanel;
	}	
}
