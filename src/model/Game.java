package model;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.time.format.DateTimeFormatter;

public class Game implements Comparable<Game>, Serializable{
	
	private Player [] gamePlayers ;
	// private String pl1 ;
	// private String pl2 ;
	private int result ;
	private int score1 , score2 ;
	private LocalDateTime date_Time  ;

	/*
	 * We are going to suppose that result = 1 means victory for X
	 * 								result = 0 means draw
	 * 							and result = -1 means lose for O
	 */
	
	public Game() {

		this.result = 0;
		this.score1 = 0;
		this.score2 = 0;
		this.gamePlayers = new Player[2];
	}
	
	public void selectPlayer(Player player , int pos) {
		if(pos < 0 && pos > 1) {
			return;
		}
		gamePlayers[pos] = player;

	}
	
	
	public Player[] getGamePlayers() {
		return gamePlayers;
	}

	public void setGamePlayers(Player[] gamePlayers) {
		this.gamePlayers = gamePlayers;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getScore1() {
		return score1;
	}

	public int setScore1(int score1) {
		this.score1 = score1;
		return score1;
	}

	public int getScore2() {
		return score2;
	}

	public int setScore2(int score2) {
		this.score2 = score2;
		return score2;
	}

	public LocalDateTime getDate_Time() {
		return date_Time;
	}

	public void setDate_Time(LocalDateTime date_Time) {
		this.date_Time = date_Time;
	}

	@Override
	public int compareTo(Game g) {
		if (Integer.compare(g.getResult(),this.getResult()) != 0) {
	        return Integer.compare(g.getResult(),this.getResult()) ;
		}
		else {
			if (Integer.compare(g.getScore2(),this.getScore2()) != 0 || Integer.compare(g.getScore1() , this.getScore1() ) != 0) {
			
		    if (g.gamePlayers[0].getPlName().equals(this.gamePlayers[1].getPlName()))
				return Integer.compare(g.getScore1(),this.getScore2()) ;
			else 
				return Integer.compare(g.getScore2(),this.getScore1()) ;
			}
			else return g.getDate_Time().compareTo(this.date_Time) ;

		}
	}
	
	
	public void printGame() {		

    		System.out.println(getGamePlayers()[0].getPlName() + " vs " + getGamePlayers()[1].getPlName()
    				+ ",  Result : " +this.getResult() + "\n" 
    				+ ", Date And Time : " +this.date_Time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
    				);
		
   }	
			
	

	public String gameRecentStats(String name) {
		if(this.gamePlayers[0].getPlName().equals(name) || this.gamePlayers[1].getPlName().equals(name)) {
		if (this.gamePlayers[0].getPlName().equals(name) ) {
			if (this.result == 1 ) {
				return ( "Win vs " + this.gamePlayers[1].getPlName() + "\n" + "Date/Time : " +this.date_Time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))) ; 
			}
			else if (this.result == - 1) {
				return ( "Lose vs " + this.gamePlayers[1].getPlName() + "\n" + "Date/Time : " +this.date_Time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))) ;
			}
			else 
				return ( "Draw vs " + this.gamePlayers[1].getPlName() + "\n" + "Date/Time : " + this.date_Time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))) ;
			}
			else if (this.gamePlayers[1].getPlName().equals(name)) {
				if (this.result == 1 ) {
					return ( "Lose vs " + this.gamePlayers[0].getPlName() + "\n" + "Date/Time : " + this.date_Time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))) ;
				}
				else if (this.result == - 1) {
					return ( "Win vs " + this.gamePlayers[0].getPlName() + "\n" + "Date/Time : " + this.date_Time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))) ;
				}
				else
					return ( "Draw vs " + this.gamePlayers[0].getPlName() + "\n" + "Date/Time : " + this.date_Time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))) ;
			}
	      }
		return "";
	   }
	
	public String gameBestStats(String name) {
		if(this.gamePlayers[0].getPlName().equals(name) || this.gamePlayers[1].getPlName().equals(name)) {
		if (this.gamePlayers[0].getPlName().equals(name) ) {
			if (this.result == 1 ) {
				return ( "Win vs " + this.gamePlayers[1].getPlName()) ; 
			}
			else if (this.result == - 1) {
				return ( "Lose vs " + this.gamePlayers[1].getPlName()) ;
			}
			else 
				return ( "Draw vs " + this.gamePlayers[1].getPlName()) ;
			}
			else if (this.gamePlayers[1].getPlName().equals(name)) {
				if (this.result == 1 ) {
					return ( "Lose vs " + this.gamePlayers[0].getPlName()) ;
				}
				else if (this.result == - 1) {
					return ( "Win vs " + this.gamePlayers[0].getPlName()) ;
				}
				else
					return ( "Draw vs " + this.gamePlayers[0].getPlName()) ;
			}
		}
			return "" ;
	}
	
}