package model;

import java.awt.Point;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;

import control.GameController;
import view.GameBoard;

public class GameModel {
	
	GameController gc;
	PlayerRoster playersCatalogue;
	Game currGame ;
	Player[] gamePlayers;
	String[][] gameBoard;
	int pos;
	int score;
	Boolean mover;	
	int moves;
	boolean Xwins;
    boolean Owins;
    boolean draw;
    Player[] players;

    
	public GameModel(GameController gc) {
		this.gc = gc;
		gamePlayers = new Player[2];
		gameBoard = null;
		playersCatalogue = new PlayerRoster();
		this.players = this.playersCatalogue.getPlayers();
		this.gc.loadGames();
		this.playersCatalogue.loadPLayers();
		this.currGame = new Game() ;
		
	}
		
	
	public void checkDimValidity(int row , int col) {
		if(row < 0 || col < 0 || row > 2 || col > 2) {
			throw new IndexOutOfBoundsException(row + "," + col + "is not a valid board cell");
		}
	}
	
	public void checkMoveValidity(int row , int col) {
		checkDimValidity(row, col);
		if(gameBoard[row][col] != null) {
			throw new IllegalArgumentException("Non playable cell");
		}
	}
	
	public String getBoardMark(int row , int col) {
		checkDimValidity(row, col);
		return gameBoard[row][col];
	}
	
	public void makeMove(int row , int col) { 
		checkMoveValidity(row, col);
		gameBoard[row][col] = getMoverMark();
		mover = !mover;
		moves++;
	}
	
	public String getMoverMark() {
		return mover? "X": "O";
	}
	
	public void xPlayerFirstMove(){
        this.mover=true;

    }
    public void oPlayerFirstMove(){

        this.mover=false;
    }
	
	public void selectPlayer(Player player , int pos) {

		if(pos < 0 && pos > 1) {
			return;
			
		}
		gamePlayers[pos] = player;
		this.currGame.getGamePlayers()[pos] = player ;
	
	}
	
	public boolean ready() {
		return (gamePlayers[0] != null && gamePlayers[1] != null);
	}
	
	public void startGame() {
		gameBoard = new String[3][3];
	}
	
	public boolean inPlay() {
        return gameBoard !=null && moves <9;
	}
	
	public boolean endOfGame(){
	       if(this.Xwins|| this.Owins || this.draw) {
	   		
	    	gc.endGame();
	           return true;
	       }
	       else
	           return false;
	}   
	
	public boolean noPlay() {
		return !inPlay();
	}

	
	
	public void mrBeanFirstMove() {
	
			if(!endOfGame()) {
				Random r = new Random();
					int row = r.nextInt(3);
					int col = r.nextInt(3);
					
					if(getGameBoard()[row][col] == null ) {
						this.makeMove(row, col);
					}
			}
		}
	
	 public void halFirstMove(){

	        if(this.getGamePlayers()[0].getPlName().equals("Hal")){
	            if(!this.endOfGame()){

	               Point BestMove = findBestMoveX(gameBoard);
	                makeMove(BestMove.x, BestMove.y);

	            }
	        }
	        
    		if(this.getGamePlayers()[1].getPlName().equals("Hal")) {
    			if(!this.endOfGame()) {
    				
    				Point BestMove = findBestMoveO(gameBoard);
    				makeMove(BestMove.x, BestMove.y);
    			}
    		}
	 }
	
	// This function returns true if there are moves
	// remaining on the board. It returns false if
	// there are no moves left to play.
	 
	public Boolean isMovesLeft(String[][] board)
	{
	    for (int i = 0; i < 3; i++)
	        for (int j = 0; j < 3; j++)
	            if (board[i][j] == null)
	                return true;
	    return false;
	}
	
	//This is the evaluation function 
	//If X wins on the board we give it a positive value of +10.
	//If O wins on the board we give it a positive value of -10.
	
	public int evaluateX(String[][] b)
	{
	    // Checking for Rows for X or O victory.
	    for (int row = 0; row < 3; row++)
	    {
	    	if(b[row][0]!=null &&  b[row][1]!=null && b[row][2]!=null) {
	        if (b[row][0] == b[row][1] &&
	            b[row][1] == b[row][2])
	        {
	            if (b[row][0].equals("X"))
	                return +10;
	            else if (b[row][0].equals("O"))
	                return -10;
	        }
	    }
	 }
	 
	    // Checking for Columns for X or O victory.
	    for (int col = 0; col < 3; col++)
	    {
	    	if(b[0][col]!=null  && b[1][col]!=null && b[2][col]!=null) {
	        if (b[0][col] == b[1][col] &&
	            b[1][col] == b[2][col])
	        {
	             if (b[0][col].equals("X"))
	                return +10;
	 
	            else if (b[0][col].equals("O"))
	                return -10;
	        }
	    }
	 }
	 
	    // Checking for Diagonals for X or O victory.
	    if(b[0][0]!=null && b[1][1]!=null && b[2][2]!=null) {
	    if (b[0][0] == b[1][1] && b[1][1] == b[2][2])
	    {
	        if (b[0][0].equals("X"))
	            return +10;
	        else if (b[0][0].equals("O"))
	            return -10;
	    }
	}
	    
	    if(b[0][2]!=null && b[1][1]!=null && b[2][0]!=null) {
	    if (b[0][2] == b[1][1] && b[1][1] == b[2][0])
	    {
	        if (b[0][2].equals("X"))
	            return +10;
	        else if (b[0][2].equals("O"))
	            return -10;
	    }
	 }
	 
	    // Else if none of them have won then return 0
	    return 0;
	}
	 
	
	// This is the minimax function. It considers all
	// the possible ways the game can go and returns
	// the value of the board
	
	public int minimaxX(String[][] board,int depth, Boolean isMax)
	{
	    int score = evaluateX(board);
	 
	    // If Maximizer has won the game
	    // return his/her evaluated score
	    if (score == 10)
	        return score;
	 
	    // If Minimizer has won the game
	    // return his/her evaluated score
	    if (score == -10)
	        return score;
	 
	    // If there are no more moves and
	    // no winner then it is a tie
	    if (!isMovesLeft(board))
	        return 0;
	 
	    // If this maximizer's move
	    if (isMax)
	    {
	        int best = -1000;
	 
	        // Traverse all cells
	        for (int i = 0; i < 3; i++)
	        {
	            for (int j = 0; j < 3; j++)
	            {
	                // Check if cell is empty
	                if (board[i][j]== null)
	                {
	                    // Make the move
	                    board[i][j] = "X";
	 
	                    // Call minimax recursively and choose
	                    // the maximum value
	                    best = Math.max(best, minimaxX(board,
	                                    depth + 1, !isMax));
	 
	                    // Undo the move
	                    board[i][j] = null;
	                }
	            }
	        }
	        return best;
	    }
	 
	    // If this minimizer's move
	    else
	    {
	        int best = 1000;
	 
	        // Traverse all cells
	        for (int i = 0; i < 3; i++)
	        {
	            for (int j = 0; j < 3; j++)
	            {
	                // Check if cell is empty
	                if (board[i][j] == null)
	                {
	                    // Make the move
	                    board[i][j] = "O";
	 
	                    // Call minimax recursively and choose
	                    // the minimum value
	                    best = Math.min(best, minimaxX(board,
	                                    depth + 1, !isMax));
	 
	                    // Undo the move
	                    board[i][j] = null;
	                }
	            }
	        }
	        return best;
	    }
	}
	 
	
	// This will return the best possible
	// move for the player	
	public Point findBestMoveX(String[][] board)
	{
	    int bestVal = -1000;
	    Point bestMove = new Point();
	    bestMove.x = -1;
	    bestMove.y = -1;
	 
	    // Traverse all cells, evaluate minimax function
	    // for all empty cells. And return the cell
	    // with optimal value.
	    for (int i = 0; i < 3; i++)
	    {
	        for (int j = 0; j < 3; j++)
	        {
	            // Check if cell is empty
	            if (board[i][j] == null)
	            {
	                // Make the move
	                board[i][j] = "X";
	 
	                // compute evaluation function for this
	                // move.
	                int moveVal = minimaxX(board, 0, false);
	 
	                // Undo the move
	                board[i][j] = null;
	 
	                // If the value of the current move is
	                // more than the best value, then update
	                // best
	                if (moveVal > bestVal)
	                {
	                    bestMove.x = i;
	                    bestMove.y = j;
	                    bestVal = moveVal;
	                }
	            }
	        }
	    }
	 
	    System.out.printf("The value of the best Move " +
	                             "is : %d\n\n", bestVal);
	 
	    return bestMove;
	}
	
	//------------------------------------------HAL IN O POSITION--------------------------------------------------------
	
	//This is the evaluation function 
	//If O wins on the board we give it a positive value of +10.
	//If X wins on the board we give it a positive value of -10.
	
	public int evaluateO(String[][] b)
	{
	    // Checking for Rows for X or O victory.
	    for (int row = 0; row < 3; row++)
	    {
	    	if(b[row][0]!=null &&  b[row][1]!=null && b[row][2]!=null) {
	        if (b[row][0] == b[row][1] &&
	            b[row][1] == b[row][2])
	        {
	            if (b[row][0].equals("X"))
	                return -10;
	            else if (b[row][0].equals("O"))
	                return +10;
	        }
	    }
	}
	 
	    // Checking for Columns for X or O victory.
	    for (int col = 0; col < 3; col++)
	    {
            if(b[0][col]!=null  && b[1][col]!=null && b[2][col]!=null) {
	        if (b[0][col] == b[1][col] &&
	            b[1][col] == b[2][col])
	        {
	            if (b[0][col].equals("X"))
	                return -10;
	 
	            else if (b[0][col].equals("O"))
	                return +10;
	        }
	    }
	 }
	 
	    // Checking for Diagonals for X or O victory.
        if(b[0][0]!=null && b[1][1]!=null && b[2][2]!=null) {
	    if (b[0][0] == b[1][1] && b[1][1] == b[2][2])
	    {
	        if (b[0][0].equals("X"))
	            return -10;
	        else if (b[0][0].equals("O"))
	            return +10;
	    }
    }
        
        if(b[0][2]!=null && b[1][1]!=null && b[2][0]!=null) {
	    if (b[0][2] == b[1][1] && b[1][1] == b[2][0])
	    {
	        if (b[0][2].equals("X"))
	            return -10;
	        else if (b[0][2].equals("O"))
	            return +10;
	    }
     }
	 
	    // Else if none of them have won then return 0
	    return 0;
	}
	 
	
	// This is the minimax function. It considers all
	// the possible ways the game can go and returns
	// the value of the board
	
	public int minimaxO(String[][] board,int depth, Boolean isMax)
	{
	    int score = evaluateO(board);
	 
	    // If Maximizer has won the game
	    // return his/her evaluated score
	    if (score == 10)
	        return score;
	 
	    // If Minimizer has won the game
	    // return his/her evaluated score
	    if (score == -10)
	        return score;
	 
	    // If there are no more moves and
	    // no winner then it is a tie
	    if (!isMovesLeft(board))
	        return 0;
	 
	    // If this maximizer's move
	    if (isMax)
	    {
	        int best = -1000;
	 
	        // Traverse all cells
	        for (int i = 0; i < 3; i++)
	        {
	            for (int j = 0; j < 3; j++)
	            {
	                // Check if cell is empty
	                if (board[i][j]== null)
	                {
	                    // Make the move
	                    board[i][j] = "O";
	 
	                    // Call minimax recursively and choose
	                    // the maximum value
	                    best = Math.max(best, minimaxO(board,
	                                    depth + 1, !isMax));
	 
	                    // Undo the move
	                    board[i][j] = null;
	                }
	            }
	        }
	        return best;
	    }
	 
	    // If this minimizer's move
	    else
	    {
	        int best = 1000;
	 
	        // Traverse all cells
	        for (int i = 0; i < 3; i++)
	        {
	            for (int j = 0; j < 3; j++)
	            {
	                // Check if cell is empty
	                if (board[i][j] == null)
	                {
	                    // Make the move
	                    board[i][j] = "X";
	 
	                    // Call minimax recursively and choose
	                    // the minimum value
	                    best = Math.min(best, minimaxO(board,
	                                    depth + 1, !isMax));
	 
	                    // Undo the move
	                    board[i][j] = null;
	                }
	            }
	        }
	        return best;
	    }
	}
	 
	
	// This will return the best possible
	// move for the player	
	public Point findBestMoveO(String[][] board)
	{
	    int bestVal = -10000;
	    Point bestMove = new Point();
	    bestMove.x = -1;
	    bestMove.y = -1;
	 
	    // Traverse all cells, evaluate minimax function
	    // for all empty cells. And return the cell
	    // with optimal value.
	    for (int i = 0; i < 3; i++)
	    {
	        for (int j = 0; j < 3; j++)
	        {
	            // Check if cell is empty
	            if (board[i][j] == null)
	            {
	                // Make the move
	                board[i][j] = "O";
	 
	                // compute evaluation function for this
	                // move.
	                int moveVal = minimaxO(board, 0, false);
	 
	                // Undo the move
	                board[i][j] = null;
	 
	                // If the value of the current move is
	                // more than the best value, then update
	                // best/
	                if (moveVal > bestVal)
	                {
	                    bestMove.x = i;
	                    bestMove.y = j;
	                    bestVal = moveVal;
	                }
	            }
	        }
	    }
	 
	    System.out.printf("The value of the best Move " +
	                             "is : %d\n\n", bestVal);
	 
	    return bestMove;
	}

	public boolean checkIfXWins(){
		//horizontal win
        if((gameBoard[0][0]!=null) && (gameBoard[0][1]!=null) && (gameBoard[0][2]!=null))
        if((gameBoard[0][0].equals("X")) && (gameBoard[0][1].equals("X")) && (gameBoard[0][2].equals("X"))) {
        	playerXWins();
            return true;
        }
        if((gameBoard[1][0]!=null) && (gameBoard[1][1]!=null) && (gameBoard[1][2]!=null))
        if((gameBoard[1][0].equals("X")) && (gameBoard[1][1].equals("X")) && (gameBoard[1][2].equals("X"))) {
        	 playerXWins();
	         return true;

         }
        if((gameBoard[2][0]!=null) && (gameBoard[2][1]!=null) && (gameBoard[2][2]!=null))
         if((gameBoard[2][0].equals("X")) && (gameBoard[2][1].equals("X")) && (gameBoard[2][2].equals("X"))) {
        	 playerXWins();
             return true;
         }
        //vertical win
        if((gameBoard[0][0]!=null) && (gameBoard[1][0]!=null) && (gameBoard[2][0]!=null))
         if((gameBoard[0][0].equals("X")) && (gameBoard[1][0].equals("X")) && (gameBoard[2][0].equals("X"))) {
        	 playerXWins();
             return true;
         }
        if((gameBoard[0][1]!=null) && (gameBoard[1][1]!=null) && (gameBoard[2][1]!=null))
         if((gameBoard[0][1].equals("X")) && (gameBoard[1][1].equals("X")) && (gameBoard[2][1].equals("X"))){
        	playerXWins();
            return true;
         }
         if((gameBoard[0][2]!=null) && (gameBoard[1][2]!=null) && (gameBoard[2][2]!=null))
         if((gameBoard[0][2].equals("X")) && (gameBoard[1][2].equals("X")) && (gameBoard[2][2].equals("X"))) {
        	 playerXWins();
             return true;
         }
        if((gameBoard[0][0]!=null) && (gameBoard[1][1]!=null) && (gameBoard[2][2]!=null))
         if((gameBoard[0][0].equals("X")) && (gameBoard[1][1].equals("X")) && (gameBoard[2][2].equals("X"))) {
        	 playerXWins();
             return true;
         }
        if((gameBoard[0][2]!=null) && (gameBoard[1][1]!=null) && (gameBoard[2][0]!=null))
         if((gameBoard[0][2].equals("X")) && (gameBoard[1][1].equals("X")) && (gameBoard[2][0].equals("X"))) {
        	 playerXWins();
             return true;
         }
        	 checkIfOWins();
        	 return false;
	}

	
	public boolean checkIfOWins() {
		 if((gameBoard[0][0]!=null) && (gameBoard[0][1]!=null) && (gameBoard[0][2]!=null))
		        if((gameBoard[0][0].equals("O"))&& (gameBoard[0][1].equals("O")) && (gameBoard[0][2].equals("O"))) {
		        	playerOWins();
		            return true;
		        }
		        if((gameBoard[1][0]!=null) && (gameBoard[1][1]!=null) && (gameBoard[1][2]!=null))
		        if((gameBoard[1][0].equals("O")) && (gameBoard[1][1].equals("O")) && (gameBoard[1][2].equals("O"))) {
		        	playerOWins();
		            return true;
		        }
		        if((gameBoard[2][0]!=null) && (gameBoard[2][1]!=null) && (gameBoard[2][2]!=null))
		        if((gameBoard[2][0].equals("O")) && (gameBoard[2][1].equals("O")) && (gameBoard[2][2].equals("O"))) {	   
		        	playerOWins();
		            return true;
		        }
		        if((gameBoard[0][0]!=null) && (gameBoard[1][0]!=null) && (gameBoard[2][0]!=null))
		        if((gameBoard[0][0].equals("O")) && (gameBoard[1][0].equals("O")) && (gameBoard[2][0].equals("O"))) {
		        	playerOWins();
		            return true;
		        }
		        if((gameBoard[0][1]!=null) && (gameBoard[1][1]!=null) && (gameBoard[2][1]!=null))
		        if((gameBoard[0][1].equals("O")) && (gameBoard[1][1].equals("O")) && (gameBoard[2][1].equals("O"))){
		        	playerOWins();
		            return true;
		        }
		        if((gameBoard[0][2]!=null) && (gameBoard[1][2]!=null) && (gameBoard[2][2]!=null))
		        if((gameBoard[0][2].equals("O")) && (gameBoard[1][2].equals("O")) && (gameBoard[2][2].equals("O"))) {
		        	playerOWins();
		            return true;
		        }
		        if((gameBoard[0][0]!=null) && (gameBoard[1][1]!=null) && (gameBoard[2][2]!=null))
		        if((gameBoard[0][0].equals("O")) && (gameBoard[1][1].equals("O")) && (gameBoard[2][2].equals("O"))) {
		        	playerOWins();
		            return true;
		        }
		        if((gameBoard[0][2]!=null) && (gameBoard[1][1]!=null) && (gameBoard[2][0]!=null))
		        if((gameBoard[0][2].equals("O")) && (gameBoard[1][1].equals("O")) && (gameBoard[2][0].equals("O"))) {
		        	playerOWins();
		            return true;
		        }
		        checkForDraw();
		        return false;
	}
	
	public boolean checkForDraw() {
		if(!Xwins && !Owins && moves == 9) {
			draw();
			return true;
		}
		return false;
	}
	
	 public void  playerXWins(){
	        System.out.println("X wins");
	        this.currGame.setResult(1); 
	        this.Xwins=true;
	        this.updateStatsForPlayerX(this.currGame.getGamePlayers()[0].getPlName());
	        this.updateStatsForPlayerO(this.currGame.getGamePlayers()[1].getPlName());
	        this.currGame.getGamePlayers()[0].setScore(this.gamePlayers[0].scoreCalculate());
	        this.currGame.getGamePlayers()[1].setScore(this.gamePlayers[1].scoreCalculate());
	        this.currGame.setDate_Time(LocalDateTime.now());
	        this.currGame.setScore1(this.currGame.getGamePlayers()[0].scoreCalculate());
	        this.currGame.setScore2(this.currGame.getGamePlayers()[1].scoreCalculate());
	    
	        gc.addGame(currGame);
	        endOfGame();

	    }
	 
	 public void  playerOWins(){
	        System.out.println("O wins");
	        this.currGame.setResult(-1);
	        this.Owins=true;	        
	        this.updateStatsForPlayerX(this.currGame.getGamePlayers()[0].getPlName());
	        this.updateStatsForPlayerO(this.currGame.getGamePlayers()[1].getPlName());
	        this.currGame.getGamePlayers()[0].setScore(this.gamePlayers[0].scoreCalculate());
	        this.currGame.getGamePlayers()[1].setScore(this.gamePlayers[1].scoreCalculate());
	        this.currGame.setDate_Time(LocalDateTime.now());
	        this.currGame.setScore1(this.currGame.getGamePlayers()[0].scoreCalculate());
	        this.currGame.setScore2(this.currGame.getGamePlayers()[1].scoreCalculate());
	
	        gc.addGame(currGame);
	        endOfGame();

	    }

	    public void draw() {
	            System.out.println("The game is a Tie");
	            this.currGame.setResult(0);
	            this.draw = true;
		        this.updateStatsForPlayerX(this.currGame.getGamePlayers()[0].getPlName());
		        this.updateStatsForPlayerO(this.currGame.getGamePlayers()[1].getPlName());
		        this.currGame.getGamePlayers()[0].setScore(this.gamePlayers[0].scoreCalculate());
		        this.currGame.getGamePlayers()[1].setScore(this.gamePlayers[1].scoreCalculate());
		        this.currGame.setDate_Time(LocalDateTime.now());
		        this.currGame.setScore1(this.currGame.getGamePlayers()[0].scoreCalculate());
		        this.currGame.setScore2(this.currGame.getGamePlayers()[1].scoreCalculate());

		    	gc.addGame(currGame);
	            endOfGame();
	    }
	

	/* Stats For Lab8    	
	    public String getPlayerStats(String player) {
	    	StringBuilder sb = new StringBuilder("");
	    	sb.append(player).append("\n\n\n");
	    	sb.append("Total:").append("\t").append(4).append("\n");
	    	sb.append("Won:").append("\t").append("75%").append("\n");
	    	sb.append("Lost:").append("\t").append("25%").append("\n");
	    	return sb.toString();
	    	
	    }*/
	    
	    public void updateStatsForPlayerX(String PlayerX) {
	        for (int i = 0; i < getPlayersCatalogue().getNumOfPlayers(); i++) {
	            if (PlayerX.equals(getPlayersCatalogue().getPlayer(i).getPlName())) {
	            	
	                if (Xwins) {
	                    getPlayersCatalogue().getPlayer(i).addwinGames();

	                    break;
	                }
	                    else
	                        if (Owins) {
	                            getPlayersCatalogue().getPlayer(i).addLoseGames();
	                            break;
	                        }


	                else {
	                            getPlayersCatalogue().getPlayer(i).addTieGames();
	                            break;
	                        }
	            }

	        }


	    }



	    public void updateStatsForPlayerO(String PlayerO) {
	        for (int i = 0; i < getPlayersCatalogue().getNumOfPlayers(); i++) {
	            if (PlayerO.equals(getPlayersCatalogue().getPlayer(i).getPlName())) {


	                if (Owins) {
	                    getPlayersCatalogue().getPlayer(i).addwinGames();
	                    break;
	                }
	                else if (Xwins) {
	            	    getPlayersCatalogue().getPlayer(i).addLoseGames();
	                }
	                else {
	                    getPlayersCatalogue().getPlayer(i).addTieGames();

	                }
	            }

	        }

	    }
	    

	public PlayerRoster getPlayersCatalogue() {
		return playersCatalogue;
	}

	public Player[] getGamePlayers() {
		return gamePlayers;
	}

	public String[][] getGameBoard() {
		return gameBoard;
	}

	public void setGameBoard(String[][] gameBoard) {
		this.gameBoard = gameBoard;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}


	public boolean isMover() {
		return mover;
	}


	public void setMover(boolean mover) {
		this.mover = mover;
	}


	public int getMoves() {
		return moves;
	}


	public void setMoves(int moves) {
		this.moves = moves;
	}


	public boolean isXwins() {
		return Xwins;
	}


	public void setXwins(boolean xwins) {
		Xwins = xwins;
	}


	public boolean isOwins() {
		return Owins;
	}


	public void setOwins(boolean owins) {
		Owins = owins;
	}


	public Game getCurrGame() {
		return currGame;
	}


	public void setCurrGame(Game currGame) {
		this.currGame = currGame;
	}


	public Player[] getPlayers() {
		return players;
	}


	public void setPlayers(Player[] players) {
		this.players = players;
	}
 
	
	
	
}
