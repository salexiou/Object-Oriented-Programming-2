package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import control.GameController;
import model.Player;

public class PlayerPanel extends GamePanel{
	JButton selectPlayerBtn;
	int pos;
	JTextField plName;
	JButton startGameBtn;
	Player currentPlayer = new Player();
	JLabel plMark;
	JTextArea plStats;
	
	public PlayerPanel(GameController gc, int pos) {
		super(gc);
		this.pos=pos;		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));	
		this.setPreferredSize(new Dimension(MainWindow.PLAYER_WIDTH, MainWindow.HEIGHT-MainWindow.TOP_HEIGHT));
		this.setBorder(new LineBorder(Color.GRAY,1,true));
		this.setAlignmentX(CENTER_ALIGNMENT);
		
		selectPlayerBtn = new JButton("Choose Player");
		selectPlayerBtn.setPreferredSize(new Dimension(120 , 40));
		selectPlayerBtn.setAlignmentX(CENTER_ALIGNMENT);
		selectPlayerBtn.addActionListener((e)->{choosePlayer();});
		
		startGameBtn = new JButton ("Start Game");
		startGameBtn.setPreferredSize(new Dimension (120, 40));
		startGameBtn.setAlignmentX(CENTER_ALIGNMENT);
		startGameBtn.setEnabled(false);
        this.startGameBtn.addActionListener(e->{gc.startGame();});
    
		this.add(selectPlayerBtn);
		this.add(startGameBtn);
		
		plName = new JTextField();
		plName.setPreferredSize(new Dimension(MainWindow.PLAYER_WIDTH , 40 ));
		plName.setAlignmentX(CENTER_ALIGNMENT);
		plName.setHorizontalAlignment(JTextField.CENTER);
		plName.setEnabled(false);
		
        plMark = new JLabel(pos==0? "X" : "O");
        plMark.setPreferredSize(new Dimension(MainWindow.PLAYER_WIDTH,80));
        plMark.setAlignmentX(CENTER_ALIGNMENT);
        plMark.setHorizontalAlignment(JTextField.CENTER);
        plMark.setEnabled(false);
        Font markf = new Font("SansSerif", Font.BOLD,90);
        plMark.setFont(markf);
        plMark.setBackground(Color.BLACK);
        this.add(plMark);

        plStats = new JTextArea(10,100);		
		plStats.setPreferredSize(new Dimension(MainWindow.PLAYER_WIDTH,400));
		plStats.setAlignmentX(CENTER_ALIGNMENT);
		Font statsf = new Font("SansSerif", Font.BOLD,18);
		plStats.setFont(statsf);
		plStats.setEnabled(false);		
		plStats.setMargin(new Insets(10, 10, 10, 10));
        plStats.setBackground(Color.BLACK);
        this.add(plStats);
			
	}
	
    public void choosePlayer() {
  
        String[] allPlayers = getModel().getPlayersCatalogue().findPlayersNames();

        String selectedPlayer;

            selectedPlayer = (String) JOptionPane.showInputDialog(this,"Select a Player...","Player selection",JOptionPane.PLAIN_MESSAGE,null,allPlayers,currentPlayer);

            
        if(selectedPlayer != null) {
            if (getModel().getGamePlayers()[pos==0?1:0] != null && selectedPlayer.equals(getModel().getGamePlayers()[pos==0?1:0].getPlName())) {
                JOptionPane.showMessageDialog(gc.getView(),"Player already selected","Ooops...",JOptionPane.ERROR_MESSAGE);
                return;
            }
                this.currentPlayer.setPlName(selectedPlayer);
                gc.selectPlayer(getModel().getPlayersCatalogue().findPlayer(selectedPlayer),pos);
                this.plName.setText(currentPlayer.getPlName());
                this.plStats.setText("Current Player : " + this.currentPlayer.getPlName());    
                this.plStats.append( gc.getModel().getPlayersCatalogue().findPlayer(selectedPlayer).getPlayerInfo());
                this.plName.setText(selectedPlayer);
                     	
              	this.plStats.append("\nRecent Score : " +this.gc.getRecentScore(selectedPlayer, pos) +"\n"+"-----------  " + "Recent Games  ------------- \n");
                
                this.plStats.append(this.gc.printRecentGames(selectedPlayer,pos));
                
                
                this.plStats.append("\n"+"-----------  " + "Best Games  ----------- \n");
                
                this.plStats.append(this.gc.printBestGames(selectedPlayer, pos) + "\n");
              	this.repaint();	

         }
    }
    
    
	public JButton getStartGameBtn() {
        return startGameBtn;
    }
	
	 public void setStartGameBtn(JButton startGameBtn) {
	        this.startGameBtn = startGameBtn;
	 }

	public JButton getSelectPlayerBtn() {
		return selectPlayerBtn;
	}

	public void setSelectPlayerBtn(JButton selectPlayerBtn) {
		this.selectPlayerBtn = selectPlayerBtn;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public JTextField getPlName() {
		return plName;
	}

	public void setPlName(JTextField plName) {
		this.plName = plName;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public JLabel getPlMark() {
		return plMark;
	}

	public void setPlMark(JLabel plMark) {
		this.plMark = plMark;
	}

	public JTextArea getPlStats() {
		return plStats;
	}

	public void setPlStats(String plStats) {
		this.plStats.setText(plStats);;
	}
	 
		
}
