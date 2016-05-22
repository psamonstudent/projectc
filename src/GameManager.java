import java.util.Scanner;


public class GameManager {
	
	// Constants
	private static final int GRID_SIZE = 3;
	private static final int NUMBER_OF_PLAYERS = 2;
	private static final int PLAYER_ONE_INDEX = 0;
	private static final int PLAYER_TWO_INDEX = 1;
	public static final char PLAYER_ONE_SYMBOL = 'O';
	public static final char PLAYER_TWO_SYMBOL = 'X';
	public static final int MIN_PLAYER_ENTRY = 0;
	public static final int MAX_PLAYER_ENTRY = 2;
	public static final int CONTINUE = 2;
	public static final int INITIALIZE_TO_ZERO = 0;
	public static final int INITIALIZE_TO_ONE = 1;
	public static final char EMPTY_CELL = ' ';
	public static final int DRAW = 0;	
	public static final int WIN = 1;
	public static final int LOSS = -1;
	public static final int MOD_ODD_EVEN = 2;
	
	
	
	public static final int MAX_TURNS = 8;
	public static final int MAX_LOOPS = 4;
	public static final int MIN_TURNS_FOR_WIN = 4;
	public static final int FIRST_LOOP = 0;
	public static final int SECOND_LOOP = 1;
	public static final int LAST_ROW = 2;
	public static final int LAST_COL = 2;
	public static final int SUM_ROW_COL_FOR_ANTI_DIAGONAL = 2;
	private static final int CONVERT_TO_PLAYER_TWO_STATE = -1;

	Move move;
	Scanner scanner;
	Trace trace;
	private GameBoard gameBoard;
	private Player[] player = new Player[NUMBER_OF_PLAYERS];
	private int playerNumber;
	private int numberOfLoops;
	private int playerOneState = CONTINUE;
	
	public GameManager (Scanner scanner, Trace trace, PlayerManager playerManager){
		
		this.scanner = scanner;
		this.trace = trace;
		playerNumber = PLAYER_ONE_INDEX;
		
	}
	
	// Accessors and Mutators
	public Player getPlayerOne(){
		return player[PLAYER_ONE_INDEX];
	}
	public Player getPlayerTwo(){
		return player[PLAYER_TWO_INDEX];
	}
	
	
	public void playGame(Player playerOne, Player playerTwo){
		
		trace.traceToFile(Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
		
		
		gameBoard = new GameBoard();
		
		if(playerOne instanceof AIPlayer){
			
			player[PLAYER_ONE_INDEX] = new AIPlayer(playerOne, trace, gameBoard);
			
		} else {
			
			player[PLAYER_ONE_INDEX] = new HumanPlayer(playerOne, trace, scanner, gameBoard);
			
		}
		
		if(playerTwo instanceof AIPlayer){
			
			player[PLAYER_TWO_INDEX] = new AIPlayer(playerTwo, trace, gameBoard);
			
		} else {
			
			player[PLAYER_TWO_INDEX] = new HumanPlayer(playerTwo, trace, scanner, gameBoard);
			
		}
		
		player[PLAYER_ONE_INDEX].setSymbol(PLAYER_ONE_SYMBOL);
		player[PLAYER_TWO_INDEX].setSymbol(PLAYER_TWO_SYMBOL);
		
		gameBoard.printGrid();
		
		loopThroughGame();
		
		switch (playerOneState){
		
		case WIN:
			
			System.out.println("Game over. " + player[PLAYER_ONE_INDEX].getGivenName() + " won!");
			break;
			
		case DRAW:
			
			System.out.println("Game over. It was a draw!");
			break;
			
		case LOSS:
			
			System.out.println("Game over. " + player[PLAYER_TWO_INDEX].getGivenName() + " won!");
			break;
		
		}
		
		int playerTwoState = playerOneState * CONVERT_TO_PLAYER_TWO_STATE;
		
		updatePlayer(player[PLAYER_ONE_INDEX], playerOneState);
		updatePlayer(player[PLAYER_TWO_INDEX], playerTwoState);
		
		trace.getTraceWriter().println("playerOne = "  + player[PLAYER_ONE_INDEX].toString());
		trace.getTraceWriter().println("playerOne = "  + player[PLAYER_TWO_INDEX].toString());
	}
	
	
	/* Comment: 		Loop through each move of the game
	*  Precondition: 	Player names are already set
	*  Postcondition: 	Game is finished */
	private void loopThroughGame(){
		
		trace.traceToFile(Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
		
		
		// Local Variables
		int numberOfMoves = INITIALIZE_TO_ZERO;		
		numberOfLoops = INITIALIZE_TO_ZERO;
		
		// While game is not over, loop through game
		while(playerOneState == CONTINUE){
			
			// Alternate between player 1 and 2
			trace.getTraceWriter().println("number of moves = " + numberOfMoves);
			playerNumber = (numberOfMoves++) % MOD_ODD_EVEN;
			
			trace.getTraceWriter().println("playerNumber = " + playerNumber);
			trace.getTraceWriter().println("player is = " + player[playerNumber].getUserName());
			
			player[playerNumber].makeMove();
			
			// Print grid
			gameBoard.printGrid();
			
			// Get game state
			playerOneState = getGameState();
			
			// Increment the number of loops
			numberOfLoops++;
			
		}
		
	}
	
	/* Comment: 		Return game state after player move
	*  Precondition: 	Player has just made a move
	*  Postcondition: 	Current state of the game is returned */	
	private int getGameState(){
		
		trace.traceToFile(Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
		
		
		// If the maximum amount of turns has been taken, the game is a draw
		if(numberOfLoops == MAX_TURNS && checkWin() == CONTINUE)
			
			return DRAW;
			
		// Check for win if number of turns is greater than the minimum (5)
		else if(numberOfLoops >= MIN_TURNS_FOR_WIN)
			
			// Call checkWin method, return checkWin's returned value
			return checkWin();
		
		return CONTINUE;
		
	}
	
	/* Comment: 		Check to see if current player has won.
	*					Rather than checking each cell in every column, row and both diagonals,
	*					algorithm checks the next cell in each direction, from the current cell.
	*					If that cell is not occupied by the current player then the for loop is 
	*					exited. If that cell is occupied, the cell after
	*					is checked. The for loops end after three comparisons, so if keepGoing 
	*					is still true it signifies a win.
	*  Precondition: 	A player has made a move and the minimum number of moves for a win has 
	*					been exceeded.
	*  Postcondition: 	Passes game state */
	private int checkWin(){
		
		trace.traceToFile(Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
		
		
		//Local variables
		int numberOfIterations = INITIALIZE_TO_ZERO;
		boolean keepGoing = false;
			
		/*Iterates a maximum of 4 times. One loop for each check: vertical, horizontal,
		* diagonal, anti-diagonal. */
		while(numberOfIterations < MAX_LOOPS){
			
			// Set row and col to the players last move
			int currentRow = player[playerNumber].getMove().getRow();
			int currentCol = player[playerNumber].getMove().getCol();
			int row = currentRow;
			int col = currentCol;
			
			// For each of the cases: 
			for (int cell = INITIALIZE_TO_ONE; cell < GRID_SIZE; cell++){
				
				// If the first while loop iteration, increment row checking vertical
				if(numberOfIterations == FIRST_LOOP){
					
					row = (currentRow + cell) % GRID_SIZE;
					col = currentCol;
					
				// If the second while loop iteration, increment column checking horizontal	
				} else if(numberOfIterations == SECOND_LOOP){
					
					row = currentRow;
					col = (currentCol + cell) % GRID_SIZE;
					
				// If last move lies on the diagonal, increment row and column checking diagonal
				} else if(currentCol == currentRow){
					
					row = (currentRow + cell) % GRID_SIZE;
					col = (currentCol + cell) % GRID_SIZE;
					
				/*If last move lies on the anti-diagonal, increment row, decrement column
				* checking anti-diagonal. */
				} else if((currentRow + currentCol) == SUM_ROW_COL_FOR_ANTI_DIAGONAL){
					
					col = (currentCol + GRID_SIZE - cell) % GRID_SIZE;
					row = (currentRow + cell) % GRID_SIZE;
					
				/*If players move does not lie on diagonal or anti-diagonal and 
				* a win has not been declared, return state as continue. */
				} else {
					
					return CONTINUE;
					
				}
				
				/*If player occupies the cell next to their last move (last cell checked)
				* keepGoing = true. */
				if(gameBoard.checkGrid(row, col) == player[playerNumber].getSymbol()){
					
					keepGoing = true;
					
				// if player does not occupy next cell keepGoing = false, exit for loop
				}else{
					
					keepGoing = false;
					break;
					
				}
				
			}
		
			/*If keepGoing is still true, then player has three cells in a row, player has won,
			* exit method. */
			if (keepGoing && (playerNumber == PLAYER_ONE_INDEX)){
				
				return WIN;
				
			} else if(keepGoing){
				
				return LOSS;
				
			}
			
			// Increment number of loops
			numberOfIterations++;
		}
		
		// If while loop exits neither player has won
		return CONTINUE;
		
	}

	// Update player scores
	private void updatePlayer(Player player, int result) {
		
		trace.traceToFile(Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
		
		
		player.setGamesPlayed(player.getGamesPlayed() + 1);
		
		switch (result){
		
		case WIN:
			
			player.setGamesWon(player.getGamesWon() + 1);
			break;
			
		case DRAW:
			
			player.setGamesDrawn(player.getGamesDrawn() + 1);
			break;
			
		default:
			
			break;
		
		}
		
		player.updateRatios();
		
	}

}
