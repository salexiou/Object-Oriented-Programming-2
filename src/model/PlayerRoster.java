package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class PlayerRoster {
	
	private Player[] players;
	private int numOfPlayers = 4;
	
	
	public PlayerRoster() {
		players = new Player[30];
		players[0] = new Player("Eleni");
		players[1] = new Player("Stamatis");
		players[2] = new Player("Mr Bean");
		players[3] = new Player("Hal");

	}
	
	public Player getPlayer (int i ) {
        if (i < numOfPlayers && i >=0)
            return players[i] ;
        else
            return null ;
    }
	
	public void addPlayer(String name) {
		if(this.numOfPlayers < 30 && name.length() != 0 && name.length() < 20) {
			this.players[this.numOfPlayers] = new Player(name);
			this.numOfPlayers++;
			System.out.println("Player with name : \t" +name + "\tadded to game successfully!");
		}
	}
	
	public void addPlayer(Player name) {
		if(this.numOfPlayers < 30 && this.numOfPlayers>=0) {
			this.players[this.numOfPlayers] = new Player();
			this.numOfPlayers++;
		}
	}
	
	public void addAllplayers(Player[] players) {
		
	        for (int i = 0; i < this.numOfPlayers; i++) {
	            if (players[i] != null)
	                this.addPlayer(players[i]);
	        }
	    }
			
	public String[] findPlayersNames(){
		
		String[] playersNames = new String[this.numOfPlayers];
		
		for(int i = 0; i < this.numOfPlayers; i++) {
			
			playersNames[i] = players[i].getPlName();
						
		}
		
		return playersNames;
	}
	
	public Player findPlayer(String name) {
		for(Player p :players) {
			if(p!=null && p.getPlName().equals(name)) 
				return p;	
		}
	return null;
	}
	
	
	public void sortPlayersByScore() {
		  
		  Player temp;
			 if(this.numOfPlayers!=0) {
				 
				 for(int i = 0 ; i < this.numOfPlayers; i++) {
					 for(int j = i; j <this.numOfPlayers; j++) {
						 if(this.players[i] == null && this.players[j] == null || this.numOfPlayers > 30) {
							 break;
						 }
						 if(this.players[i].compareTo(players[j]) > 0) {
							 temp = this.players[i];
							 this.players[i] = this.players[j];
							 this.players[j] = temp;	
						 }
					 }
				 }
			 }
		}
	
	
	
   public void storePlayers() {
        ObjectOutputStream os = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("players.txt");
            os = new ObjectOutputStream(fos);

            for (Player p : getPlayers()) {
                os.writeObject(p);

            }
            System.out.println("Players stored");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {os.close(); fos.close();}catch (Exception e) {
            }
        }
    }

    public void loadPLayers() {
        ObjectInputStream is = null;
        FileInputStream fis = null;
        int pos = 0;
        try {
            fis = new FileInputStream("players.txt");
            is = new ObjectInputStream(fis);

            while (fis.available()>0) {
                Player p = (Player) is.readObject();
                if (p != null) {
                    players[pos] = p;
                    pos++;

                }
            }

            this.setPlayers(players);
            System.out.println("Players loaded");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found for read objects...");
        }finally {
            try {is.close(); fis.close();}catch (Exception e) {
            }
        }
    }
    
  
	/* getters and setters*/
	
	public Player[] getPlayers() {
		return players;
	}

	public void setPlayers(Player[] players) {
		this.players = players;
	}

	public int getNumOfPlayers() {
		return numOfPlayers;
	}

	public void setNumOfPlayers(int numOfPlayers) {
		this.numOfPlayers = numOfPlayers;
	}
	
	
}

