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
	public static final int CHANGE_PLAYER = -1;
	public static final int MOD_ODD_EVEN = 2;
	
	
	
	public static final int MAX_TURNS = 8;
	public static final int MAX_LOOPS = 4;
	public static final int MIN_TURNS_FOR_WIN = 4;
	public static final int FIRST_LOOP = 0;
	public static final int SECOND_LOOP = 1;
	public static final int LAST_ROW = 2;
	public static final int LAST_COL = 2;
	public static final int SUM_ROW_COL_FOR_ANTI_DIAGONAL = 2;

	Move move;
	Scanner scanner;
	Trace trace;
	private char[][] grid = new char[GRID_SIZE][GRID_SIZE];
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
		
		player[PLAYER_ONE_INDEX] = playerOne;
		player[PLAYER_TWO_INDEX] = playerTwo;
		player[PLAYER_ONE_INDEX].setSymbol(PLAYER_ONE_SYMBOL);
		player[PLAYER_TWO_INDEX].setSymbol(PLAYER_TWO_SYMBOL);
		
		initializeGrid();
		
		printGrid();
		
		loopThroughGame();
		
		int playerTwoState = playerOneState * CHANGE_PLAYER;
		
		updatePlayer(playerOne, playerOneState);
		updatePlayer(playerTwo, playerTwoState);
		
	}
	
	/* Comment: 		Loop through each move of the game
	*  Precondition: 	Player names are already set
	*  Postcondition: 	Game is finished */
	private void loopThroughGame(){
		
		// Local Variables
		int numberOfMoves = INITIALIZE_TO_ZERO;		
		numberOfLoops = INITIALIZE_TO_ZERO;
		boolean invalidEntry = false;
		
		// While game is not over, loop through game
		while(playerOneState == CONTINUE){
			
			// Alternate between player 1 and 2
			playerNumber = (numberOfMoves++) % MOD_ODD_EVEN;
			
			// Read player input, if invalid: print error and repeat
			do{
				
				// Print players move
				System.out.print(player[playerNumber].getGivenName() + "'s move:\n");
				
				// Read players cell entry
				player[playerNumber].makeMove();
				
					
				// Cell already occupied?
				if(grid[player[playerNumber].getMove().getRow()][player[playerNumber].getMove().getCol()] != EMPTY_CELL){
					
					invalidEntry = true;
					
					// Print error info
					System.out.print("Invalid move. The cell has been occupied.\n");
					
				} else {
	
					// No error then entry is valid
					invalidEntry = false;
				}
				
			} while (invalidEntry);
			
			// Place player's move onto the grid
			setGrid(player[playerNumber].getMove().getRow(), player[playerNumber].getMove().getCol());
			
			// Print grid
			printGrid();
			
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
		
		// If the maximum amount of turns has been taken the game is a draw
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
		
		//Local variables
		int row;
		int col;
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
				if(grid[row][col] == player[playerNumber].getSymbol()){
					
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
				
				return PLAYER_ONE_WIN;
				
			} else if(keepGoing){
				
				return PLAYER_TWO_WIN;
				
			}
			
			// Increment number of loops
			numberOfIterations++;
		}
		
		// If while loop exits neither player has won
		return CONTINUE;
		
	}

	// Update player scores
	private void updatePlayer(Player player, int result) {
		
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
		
	}

	/* Comment: 		Initialize grid array to the space character
	*  Precondition: 	char grid[][] has been declared
	*  Postcondition: 	All elements of grid[][] have the char value: ' ' */
	private void initializeGrid(){
		
		for(int row = INITIALIZE_TO_ZERO; row < GRID_SIZE; row++){		
		
			for(int col = INITIALIZE_TO_ZERO; col < GRID_SIZE; col++){
				
				//Initialize cell to space character
				grid[row][col] = EMPTY_CELL;
				
			}
			
		}	
		
	}
	
	/* Comment: 		Prints grid
	*  Precondition: 	Grid array values are valid (' ', 'O', 'X')
	*  Postcondition: 	Grid is printed to screen with borders and values of cells */
	private void printGrid(){
		
		//Iterate through each row
		for(int row = INITIALIZE_TO_ZERO; row < GRID_SIZE; row++){			
		
			//Iterate through each column
			for(int col = INITIALIZE_TO_ZERO; col < GRID_SIZE; col++){
				
				//Print cell value
				System.out.print(grid[row][col]);
				
				//If column 0 or 1 Print vertical line
				if(col < LAST_COL)
					
					System.out.print("|");
					
			}
			
			//If row 0 or 1 Print horizontal line
			if(row < LAST_ROW)
				
				System.out.print("\n-----\n");
		}
		
		//Print newline at end of grid
		System.out.print("\n");
		
	}
	
	/* Comment: 		Set players move in corresponding cell
	*  Precondition: 	Arguments passed (row and col) are valid {0,1,2}
	*  Postcondition: 	Players symbol is written to the relevant cell in 3D array */
	private void setGrid(int row, int col){
		
		// Set cell symbol for player
		grid[row][col] = player[playerNumber].getSymbol();
		
	}

}
