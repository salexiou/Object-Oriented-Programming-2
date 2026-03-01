package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.border.LineBorder;

import control.GameController;

@SuppressWarnings("serial")
public class BoardCell extends GamePanel implements MouseListener {
	public static final int CELL_PADDING = 10;
	int row, col;	
	public boolean highlighted;

	public BoardCell(GameController gc, int row, int col) {
		super(gc);
		this.setBackground(Color.WHITE);
		this.row = row;
		this.col = col;
		this.addMouseListener(this);
		this.highlighted = false;
		this.setLayout(null);
	}
	
	  private void highlight() {
	        if (getModel().inPlay() && !getModel().endOfGame()) {
	            highlighted = true;
	            repaint();
	        }
	    }

	    private void unHighlight() {
	        if (getModel().inPlay()) {
	            highlighted = false;
	            repaint();
	        }

	    }
	
	@Override
	public void mouseEntered(MouseEvent e) {
		if (getModel().inPlay() && !getModel().endOfGame()) {
		System.out.println("Mouse entered cell " + this);
		this.highlight();
		repaint();
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (getModel().inPlay() && !getModel().endOfGame()) {
		System.out.println("Mouse exited on cell " + this);
		this.unHighlight();
		repaint();
		}
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//this.setBorder(new LineBorder(Color.DARK_GRAY, 1));
		String mark = getModel().getBoardMark(this.row, this.col);
		Graphics2D g2d = (Graphics2D) g;
		int size = this.getSize().width - 2 * CELL_PADDING;
		g2d.setStroke(new BasicStroke(6));
		if (mark == null) {
			if (highlighted) {
				g2d.setColor(Color.LIGHT_GRAY);
				g2d.fillRect(CELL_PADDING, CELL_PADDING, size, size);
			}
			return;
		} else if (mark.equals("X")) {
			g2d.drawLine(CELL_PADDING, CELL_PADDING, CELL_PADDING + size, CELL_PADDING + size);
			g2d.drawLine(CELL_PADDING + size, CELL_PADDING, CELL_PADDING, CELL_PADDING + size);
		} else if (mark.equals("O")){
			g2d.drawOval(CELL_PADDING, CELL_PADDING, size, size);
		} else
			return;
	
		   if (this.getModel().getGamePlayers()[0].getPlName().equals("Mr Bean")) {

	            if (this.getModel().isMover() && !this.getModel().endOfGame()) {

	            	Random r = new Random();
	            	
	                int row = r.nextInt(3);
	                int col = r.nextInt(3);

	                

	                if (getModel().getGameBoard()[row][col] == null && !this.getModel().endOfGame()) {
	                    this.getModel().makeMove(row, col);
	                    getModel().checkIfXWins();
	                }
	            }
	        }

	        if (this.getModel().getGamePlayers()[1].getPlName().equals("Mr Bean")) {

	            if (!this.getModel().isMover() && !this.getModel().endOfGame()) {
	            	
	            	Random r = new Random();

	                int row = r.nextInt(3);
	                int col = r.nextInt(3);


	                if (getModel().getGameBoard()[row][col] == null && !this.getModel().endOfGame()) {
	                    this.getModel().makeMove(row, col);
	                    getModel().checkIfXWins();

	                }
	            }
	        }
	        

	        if (this.getModel().getGamePlayers()[0].getPlName().equals("Hal")) {
	            if ( this.getModel().isMover() &&!this.getModel().endOfGame()) {
	                Point BestMove = getModel().findBestMoveX(getModel().getGameBoard());
	                getModel().makeMove(BestMove.x, BestMove.y);
	                getModel().checkIfXWins();
	            }
	        }

	            if (this.getModel().getGamePlayers()[1].getPlName().equals("Hal")) {
	                if (!this.getModel().isMover() && !this.getModel().endOfGame()) {
	                    Point BestMove = getModel().findBestMoveO(getModel().getGameBoard());
	                    getModel().makeMove(BestMove.x, BestMove.y);
	                    getModel().checkIfXWins();
	                }
	            }
}
	@Override
	public String toString() {
		return "(" + this.row + "," + this.col + ")";
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Mouse clicked on cell " + this);
		if (getModel().inPlay() && !getModel().endOfGame()) {
			getModel().makeMove(row, col);
			repaint();
			getModel().checkIfXWins();
		}
	}
	

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}
}
