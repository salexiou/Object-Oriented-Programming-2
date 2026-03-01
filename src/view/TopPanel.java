package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import control.GameController;
import model.Player;

public class TopPanel extends GamePanel {
	JButton quitBtn;
	JButton addPlayerBtn;
	JButton doneBtn;
	
	
	public TopPanel(GameController gc) {		
		super(gc);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setPreferredSize(new Dimension(MainWindow.WIDTH, MainWindow.TOP_HEIGHT) );
		this.setBorder(new LineBorder(Color.GRAY,1,true));
		quitBtn = new JButton("Quit App");
		quitBtn.setPreferredSize(new Dimension(100, 40));
		quitBtn.addActionListener((e)->{this.gc.quit();});

	
		addPlayerBtn = new JButton("Add Player");
		addPlayerBtn.setPreferredSize(new Dimension(100,40));
		addPlayerBtn.setEnabled(true);
		this.addPlayerBtn.addActionListener((e)->AddPlayer());
	
		doneBtn = new JButton ("Done");
		doneBtn.setPreferredSize(new Dimension(100,40));
		doneBtn.setEnabled(false);
		doneBtn.addActionListener((e)->{try {
			this.gc.startAfterTheGameEnds();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}});

		add(addPlayerBtn);
		add(doneBtn);
		add(quitBtn);
	
	}
	
	
	public void AddPlayer() {
		String name;
		boolean checkForErrors = true;
		name = JOptionPane.showInputDialog("Please Enter the Player's Name");
		 for (int i = 0; i < getModel().getPlayersCatalogue().getNumOfPlayers(); i++) {
	            if(name != null && name.equals(getModel().getPlayersCatalogue().getPlayers()[i].getPlName())) {

	                JOptionPane.showMessageDialog(gc.getView(),"Player already exists","Ooops...",JOptionPane.ERROR_MESSAGE);
	                checkForErrors = false;
	            }
		 }
		 
		 if(checkForErrors && name.length() != 0 && name.length() < 20 ) {			 
	        getModel().getPlayersCatalogue().addPlayer(name);
		 }
		 else if(name.length() == 0) {
             JOptionPane.showMessageDialog(gc.getView(),"Player's name cannot be null","Ooops...",JOptionPane.ERROR_MESSAGE); 
             checkForErrors = false;
            } 
		 else {
			 JOptionPane.showMessageDialog(gc.getView(),"Player's name cannot be greater than 20 characters","Ooops...",JOptionPane.ERROR_MESSAGE); 
             checkForErrors = false;
		 }
}
	public JButton getQuitBtn() {
		return quitBtn;
	}


	public JButton getaddPlayerBtn() {
		return addPlayerBtn;
	}


	public JButton getDoneBtn() {
		return doneBtn;
	}

	
}
