package control;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.Game;
import model.GameModel;
import model.Player;
import view.MainAreaPanel;
import view.MainWindow;

public class GameController extends WindowAdapter{
	MainWindow view;
	GameModel model;
	Game[] listOfGames;
	int numOfGames;	
	
	public GameController() {
		
		this.listOfGames = new Game[200];
		this.numOfGames = 0;
		
	}
	
	public void windowClosing(WindowEvent event) {
		quit();
	}
	
	public void quit() {
		System.out.println("bye bye...");		
		System.exit(0);
	}
	
	public void selectPlayer(Player p, int pos) {
		this.model.selectPlayer(p, pos);	
		System.out.println("Player " + pos + " set to " + p);
		this.view.getRightPnl().getStartGameBtn().setEnabled(model.ready());
		this.view.getLeftPnl().getStartGameBtn().setEnabled(model.ready());
        this.view.getLeftPnl().getStartGameBtn().addActionListener(e->getModel().xPlayerFirstMove());
        this.view.getRightPnl().getStartGameBtn().addActionListener(e->getModel().oPlayerFirstMove());

	}
	
	public void start() throws IOException {
		this.view = new MainWindow(this);
		this.model = new GameModel(this);
		this.view.addWindowListener(this);
		this.view.setVisible(true);
		this.HallOfFame();
	    this.view.getRightPnl().getStartGameBtn().setEnabled(false);
        this.view.getLeftPnl().getStartGameBtn().setEnabled(false);
	}
	
	public void startGame() {
        
			this.view.getLeftPnl().getPlStats().setText("Player currently playing: \n" + this.model.getGamePlayers()[0].getPlName() +
					"\n\n\n\n\n The Stats for the player \n  are disabled until the  \n game reaches to an end");
			
			this.view.getRightPnl().getPlStats().setText("Player currently playing: \n" + this.model.getGamePlayers()[1].getPlName() +
					"\n\n\n\n\n The Stats for the player \n are disabled until the  \n game reaches to an end");
			
			this.model.setGameBoard(new String[3][3]);
	        this.view.getLeftPnl().getStartGameBtn().setEnabled(false);
	        this.view.getRightPnl().getStartGameBtn().setEnabled(false);
	        this.view.getMainPnl().showCard(MainAreaPanel.BOARD);
	        this.view.getLeftPnl().getSelectPlayerBtn().setEnabled(model.noPlay());
	        this.view.getRightPnl().getSelectPlayerBtn().setEnabled(model.noPlay());
	        this.view.getTopPanel().getaddPlayerBtn().setEnabled(false);
	        this.model.getCurrGame().setGamePlayers(this.model.getGamePlayers());
	        if(this.getModel().getGamePlayers()[0].getPlName().equals("Mr Bean") 
        			&& this.getModel().getMoves() == 0 
        			&& this.getModel().isMover()) {
        		
        		this.getModel().mrBeanFirstMove();
        		
        	}

        	else if(this.getModel().getGamePlayers()[1].getPlName().equals("Mr Bean") 
            			&& this.getModel().getMoves() == 0 
            			&& !this.getModel().isMover()) {
            		
            		this.getModel().mrBeanFirstMove();
        }
	       if(this.getModel().getGamePlayers()[0].getPlName().equals("Hal")
	    		   && this.getModel().getMoves() == 0
	    		   && this.getModel().isMover()) {
	    	   
	    	   this.getModel().halFirstMove();
	       }
	       
	       else if(this.getModel().getGamePlayers()[1].getPlName().equals("Hal")
	    		   && this.getModel().getMoves() == 0
	    		   && !this.getModel().isMover()) {
	    	   
	    	   this.getModel().halFirstMove();
	       }
	}

	 public void endGame() {
		 
	    this.view.getTopPanel().getDoneBtn().setEnabled(true);
    	
   }
	 
	 public void startAfterTheGameEnds() throws IOException {
		 
		 		this.printGames();
		 		this.model.getPlayersCatalogue().storePlayers();
		        storeGames();
		        
		 		this.view.getMainPnl().getHallOfFame().getPlayersScores().setText(null);
		        this.view.getTopPanel().getDoneBtn().setEnabled(false);
		        this.view.addWindowListener(this);
		        HallOfFame();
		        
		        this.model = new GameModel(this);
		        
		        this.model.getPlayersCatalogue().loadPLayers();
		        loadGames();
		       
		        
		        this.view.getMainPnl().showCard(MainAreaPanel.HOF);
		        this.view.getLeftPnl().getSelectPlayerBtn().setEnabled(true);
		        this.view.getRightPnl().getSelectPlayerBtn().setEnabled(true);
		        
		        this.view.getLeftPnl().getPlStats().setText(null);
		        this.view.getRightPnl().getPlStats().setText(null);

		        this.view.getLeftPnl().getStartGameBtn().setEnabled(model.ready());
		        this.view.getRightPnl().getStartGameBtn().setEnabled(model.ready());
		        this.view.getTopPanel().getaddPlayerBtn().setEnabled(true);
		        
	 }

	 public void addGame (Game g) {
		 
		
			 this.listOfGames[numOfGames] = g ;
			 this.numOfGames ++ ;
			 System.out.println("\nGame was added succesfully\n");

	 } 
	 
	 
	 public void printGames () {
		 for ( int i = 0 ; i < this.numOfGames ; i++) {
			 System.out.println("Game : " + (i+1) +"\n");
			 this.listOfGames[i].printGame();
		 }
	 }
	 
	 public void sortGamesByDate() {
		 
		 Game temp;
		 if(this.numOfGames!=0) {
			 
			 for(int i = 0 ; i < this.numOfGames; i++) {
				 for(int j = i; j <this.numOfGames; j++) {
					 if(this.listOfGames[i] == null && this.listOfGames[j] == null) {
						 break;
					 }
					 if(this.listOfGames[i].getDate_Time().isBefore(this.listOfGames[j].getDate_Time())) {
						 temp = this.listOfGames[i];
						 this.listOfGames[i] = this.listOfGames[j];
						 this.listOfGames[j] = temp;	
					 }
				 }
			 }
		 }
   }
	 
	 //sorting not working correctly so the best games dont work correctly.
 public void sortBestGames() {
		 
		 Game temp;
		 if(this.numOfGames!=0) {
			 
			 for(int i = 0 ; i < this.numOfGames; i++) {
				 for(int j = i; j <this.numOfGames; j++) {
					 if(this.listOfGames[i] == null && this.listOfGames[j] == null) {
						 break;
					 }
					 if(this.listOfGames[i].compareTo(this.listOfGames[j]) < 0) {
						 temp = this.listOfGames[i];
						 this.listOfGames[i] = this.listOfGames[j];
						 this.listOfGames[j] = temp;	
					 }
				 }
			 }
		 }
   }
	 
	 
	 
	 public String printRecentGames( String p , int pos) {
		 StringBuilder sb = new StringBuilder("");
		 for (Game g : this.listOfGames) {
	    		if (g != null) {
	    		if ( (g.getGamePlayers()[0] != null && g.getGamePlayers()[0].getPlName().equals(p)) 
	    				||g.getGamePlayers()[1] != null && g.getGamePlayers()[1].getPlName().equals(p)){
		
		if (pos == 0) {
			
	    	for (int i = 0; i < this.numOfGames ; i++) {
				this.sortGamesByDate();
	    		if( i > 4 || this.listOfGames[i] ==null ) {
	    			break ;
	    		}else if(i <= 4 && this.listOfGames[i] != null && this.listOfGames[i].gameRecentStats(p) != ""){
	    			sb.append("--->"+this.listOfGames[i].gameRecentStats(p) + "\n");
	    	}
	    }			return sb.toString();	    	
		}
		else 
    	{			
			for (int i = 0; i < this.numOfGames ; i++) {
				this.sortGamesByDate();
	    		if( i > 4 || this.listOfGames[i] == null ) {
	    			break ;
	    		}else if(i <= 4 && this.listOfGames[i] != null && this.listOfGames[i].gameRecentStats(p) != ""){
	    		sb.append("--->"+this.listOfGames[i].gameRecentStats(p) + "\n");
	    	}
	    }return sb.toString();		
		}
	    		
		 }
	    		}	
	    	}
	  return null;
	 }
		    	
	public String printBestGames(String p , int  pos) {
		
		 StringBuilder sb = new StringBuilder("");
		 
		 for (Game g : this.listOfGames) {
	    		if (g != null) {
	    		if ( (g.getGamePlayers()[0] != null && g.getGamePlayers()[0].getPlName().equals(p)) 
	    				||g.getGamePlayers()[1] != null && g.getGamePlayers()[1].getPlName().equals(p)){
		
		if (pos == 0) {
			
	    	for (int i = 0; i < this.numOfGames ; i++) {
	    		this.sortBestGames();
	    		if( i > 4 || this.listOfGames[i] ==null ) {
	    			break ;
	    		}else if(i <= 4 && this.listOfGames[i] != null){
	    			sb.append(this.listOfGames[i].gameBestStats(p) + "\n");
	    	}
	    }
	    	return sb.toString();
		}
		else 
    	{			
			for (int i = 0; i < this.numOfGames ; i++) {
				this.sortBestGames();
				if( i > 4 || this.listOfGames[i] == null) {
	    			break ;
	    		}else if(i <= 4 && this.listOfGames[i] != null){
	    		sb.append(this.listOfGames[i].gameBestStats(p) + "\n");
	    	}
			}
	    	return sb.toString();
	    }
		}
	    		
		 }
	    		}	
	  return null;
	}
	
	
	 public void storeGames() {
	        ObjectOutputStream os = null;
	        FileOutputStream fos = null;
	        try {
	            fos = new FileOutputStream("games.txt");
	            os = new ObjectOutputStream(fos);

	            for (Game g : this.listOfGames) {
	                os.writeObject(g);

	            }
	            System.out.println("Games stored");
	        } catch (FileNotFoundException e) {
	            System.out.println(e.getMessage());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }finally {
	            try {os.close();
	            fos.close();
	            }catch (Exception e) {
	            }
	        }
	    }

	    public void loadGames() {
	        ObjectInputStream ois = null;
	        FileInputStream fis = null;
	        int pos = 0;
	        try {
	            fis = new FileInputStream("games.txt");
	            ois = new ObjectInputStream(fis);

	            while (fis.available()>0) {
	                Game g = (Game) ois.readObject();
	                if (g != null) {
	                    this.listOfGames[pos++] = g;
	                }
	            }
	            System.out.println("Games loaded");
	        } catch (FileNotFoundException e) {
	            System.out.println(e.getMessage());
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ClassNotFoundException e) {
	            System.out.println("Class not found for read objects...");
	        }finally {
	            try {ois.close();
	            fis.close();
	            }catch (Exception e) {
	            }
	        }
	    }
	    
	//recent score not working correctly    
		 public int getRecentScore( String p , int pos) {
			 int score = 0;
			 
			 for (Game g : this.listOfGames) {
		    		if (g != null) {
		    		if ( (g.getGamePlayers()[0] != null && g.getGamePlayers()[0].getPlName().equals(p)) 
		    				||g.getGamePlayers()[1] != null && g.getGamePlayers()[1].getPlName().equals(p)){
			
			if (pos == 0) {
				
		    	for (int i = 0; i < 5 ; i++) {
					this.sortGamesByDate();
					if(i <= 4 && this.listOfGames[i] != null){
		    			score = this.listOfGames[i].setScore1(this.getModel().getGamePlayers()[0].scoreCalculate());
		    	}
		    }  
		    return score;
			}
			else 
	    	{			
				for (int i = 0; i < 5; i++) {
					this.sortGamesByDate();
		    		 if(i <= 4 && this.listOfGames[i] != null){
		    			score = this.listOfGames[i].setScore2(this.getModel().getGamePlayers()[1].scoreCalculate());	    			
		    			
		    		}
		    }
				return score;
			}
		    		
			 }
		    		}	
		    	}
		  return 0;
		 }
	    
	  public void HallOfFame() {
		  			
		  if(this.model.getPlayersCatalogue().getNumOfPlayers() < 10) {
	        for (int i = 0; i < this.model.getPlayersCatalogue().getNumOfPlayers(); i++) {
	        	this.model.getPlayersCatalogue().sortPlayersByScore();
	            this.getView().getMainPnl().getHallOfFame().SetPlayersScore(this.model.getPlayersCatalogue().getPlayers()[i].toString() + "\n\n");

	        }
		  }
	        
	        if(this.model.getPlayersCatalogue().getNumOfPlayers() >= 10) {
		        for (int i = 0; i < 10 ; i++) {
		        	this.model.getPlayersCatalogue().sortPlayersByScore();
		            this.getView().getMainPnl().getHallOfFame().SetPlayersScore(this.model.getPlayersCatalogue().getPlayers()[i].toString() + "\n\n");

		        }
	        }

	    }
	  
	  	
	 
	public MainWindow getView() {
		return view;
	}

	public GameModel getModel() {
		return model;
	}

	public Game[] getListOfGames() {
		return listOfGames;
	}

	public void setListOfGames(Game[] listOfGames) {
		this.listOfGames = listOfGames;
	}

	public int getNumOfGames() {
		return numOfGames;
	}

	public void setNumOfGames(int numOfGames) {
		this.numOfGames = numOfGames;
	}
	
	
}
	
	
	


