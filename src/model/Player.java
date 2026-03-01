package model;

import java.io.Serializable;

public class Player implements Comparable<Player>, Serializable {
	
	private String plName;
	private int numOfGames;
	private int winGames;
	private int loseGames;
	private int tieGames;
	private int score;
	private Game[] bestGames;
	private int  numOfBestGames;
	private Game[] recentGames;
	private int numOfRecentGames;
	
	
	public Player(String plName) {
		this.plName = plName;
		this.winGames = 0;
		this.loseGames = 0;
		this.tieGames = 0;
		this.score = 0;
		this.bestGames = new Game[5];
		this.numOfBestGames = 0;
		this.recentGames = new Game[5];
		this.numOfRecentGames = 0;
	}

	   public Player () {
			this.winGames = 0;
			this.loseGames = 0;
			this.tieGames = 0;
			this.score = 0;
			this.bestGames = new Game[5];
			this.numOfBestGames = 0;
			this.recentGames = new Game[5];
			this.numOfRecentGames = 0;
	       }

	public int scoreCalculate() {
		
		if(this.numOfGames!=0) {
			this.score = 50 * (2 * this.winGames + this.tieGames ) / (this.winGames + this.loseGames + this.tieGames);
		}
		
		else {
			this.score = 0;
		}
		
		return this.score;
	}
	
	
	 public String getPlayerInfo () {

	        return ( "\nGames Played :" + this.numOfGames +"\n"
	        		+ "\nWins :" + this.winGames
	        		+ "\tLoses :" + this.loseGames
	        		+ "\nTies :" + this.tieGames 
	        		+ "\tScore :" +  this.score
	        		);
	    }
	
	public void addwinGames() {
		this.winGames++;
		addNumOfGames();
	}
	
	public void addLoseGames() {
		this.loseGames++;		
		addNumOfGames();

	}
	
	public void addTieGames() {
		this.tieGames++;
		addNumOfGames();

	}
	
	public void addNumOfGames() {
		this.numOfGames++;
	}
	
	public void addBestGames(Game bestGame) {
		 this.bestGames[numOfBestGames]= bestGame;
		 System.out.println("asdasdsasda");
	     numOfBestGames++;
	     addNumOfGames();
	    
	}
	
	
	
	public void addRecentGames(Game recentGame) {
		 this.recentGames[numOfRecentGames]= recentGame;
	     numOfRecentGames++;
		 addNumOfGames();

	}

	/*getters and setters*/

	public String getPlName() {
		return plName;
	}



	public void setPlName(String plName) {
		this.plName = plName;
	}



	public int getNumOfGames() {
		return numOfGames;
	}



	public void setNumOfGames(int numOfGames) {
		this.numOfGames = numOfGames;
	}



	public int getWinGames() {
		return winGames;
	}



	public void setWinGames(int winGames) {
		this.winGames = winGames;
	}



	public int getLoseGames() {
		return loseGames;
	}



	public void setLoseGames(int loseGames) {
		this.loseGames = loseGames;
	}



	public int getTieGames() {
		return tieGames;
	}



	public void setTieGames(int tieGames) {
		this.tieGames = tieGames;
	}



	public int getScore() {
		return this.scoreCalculate();
	}



	public void setScore(int score) {
		this.score = score;
	}



	public Game[] getBestGames() {
		return bestGames;
	}



	public void setBestGames(Game[] bestGames) {
		this.bestGames = bestGames;
	}



	public int getNumOfBestGames() {
		return numOfBestGames;
	}



	public void setNumOfBestGames(int numOfBestGames) {
		this.numOfBestGames = numOfBestGames;
	}



	public Game[] getRecentGames() {
		return recentGames;
	}



	public void setRecentGames(Game[] recentGames) {
		this.recentGames = recentGames;
	}



	public int getNumOfRecentGames() {
		return numOfRecentGames;
	}



	public void setNumOfRecentGames(int numOfRecentGames) {
		this.numOfRecentGames = numOfRecentGames;
	}

	
	public int compareTo(Player o) {
		
		return Integer.compare(this.getScore(), o.getScore());
	}

	@Override
	public String toString() {
		return   this.getPlName() + " : " + this.getScore()  + "\t Games Played:" + this.getNumOfGames()  ;
	}
	
	
}
