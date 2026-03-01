package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import control.GameController;

public class HallOfFame extends GamePanel {
	private GameController gc;
	JTextArea PlayersScores;
	JTextField hof;
	JTextArea sorting;

	  public HallOfFame(GameController gc) {
	        super(gc);
	        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
	        this.setPreferredSize(new Dimension(MainWindow.WIDTH-2*MainWindow.PLAYER_WIDTH,MainWindow.HEIGHT- MainWindow.TOP_HEIGHT));
	        this.setBorder(new LineBorder(Color.GRAY,1,true));
	        this.setAlignmentX(CENTER_ALIGNMENT);


	
	        hof = new JTextField();
	        hof .setPreferredSize(new Dimension(MainWindow.WIDTH-MainWindow.PLAYER_WIDTH,40));
	        hof .setMaximumSize(hof .getPreferredSize() );
	        hof.setBorder(new LineBorder(Color.BLACK,1,true));
	        Font FontHof= new Font("SansSerif",Font.BOLD,30);
	        hof.setFont(FontHof);
	        hof.setBackground(Color.DARK_GRAY);
	        hof .setEnabled(false);
	        hof.setText("                      HALL OF FAME");


	        sorting = new JTextArea();
	        sorting .setPreferredSize(new Dimension(MainWindow.WIDTH-2*MainWindow.PLAYER_WIDTH,MainWindow.HEIGHT- 9*MainWindow.TOP_HEIGHT - 20 ));
	        sorting .setMaximumSize(hof .getPreferredSize() );
	        sorting.setBorder(new LineBorder(Color.BLACK,1,true));
	        Font SortingHof= new Font("SansSerif",Font.BOLD,25);
	        sorting.setFont(SortingHof);
	        sorting.setBackground(Color.DARK_GRAY);
	        sorting .setEnabled(false);
	        sorting.setText("        Players are ordered in an descending way! ");

	        
	        
	        PlayersScores=new JTextArea();
	        PlayersScores.setPreferredSize(new Dimension(MainWindow.WIDTH-2*MainWindow.PLAYER_WIDTH,MainWindow.HEIGHT- MainWindow.TOP_HEIGHT-30));
	        Font FontScore=new Font("SansSerif",Font.BOLD,22);
	        PlayersScores.setFont(FontScore);
	        PlayersScores.setBackground(Color.DARK_GRAY);
	        PlayersScores.setEnabled(false);



	        this.add(hof);
	        this.add(sorting);
	        this.add(PlayersScores);

	    }


   public JTextArea getPlayersScores() {
       return PlayersScores;
   }

   public void SetPlayersScore(String name){

       this.PlayersScores.setText(name + PlayersScores.getText());
}

  /* public void paint(Graphics g) {
		int x = this.getWidth() / 2 - 50 ;
		int y = this.getHeight() / 50 ;
		g.drawString("HallOfFame", x, y);
	}*/
}
