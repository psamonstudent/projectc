import java.util.Scanner;



public class HumanPlayer extends Player {
	
	public static final int MIN_PLAYER_ENTRY = 0;
	public static final int MAX_PLAYER_ENTRY = 2;
	
	Scanner scanner;
	
	// Constructor
	public HumanPlayer (Player player, Trace trace, Scanner scanner, GameBoard gameBoard){
		super(player, trace, gameBoard);
		trace.getTraceWriter().println("scanner imported");
		this.scanner = scanner;
	}
	
	public HumanPlayer (Player player, Trace trace, Scanner scanner){
		super(player, trace);
		trace.getTraceWriter().println("scanner imported");
		this.scanner = scanner;
	}
	
	public HumanPlayer(String userName, String familyName, String givenName, Scanner scanner, Trace trace){
		super(userName, familyName, givenName, trace);
		this.scanner = scanner;
		
		trace.getTraceWriter().println("scanner imported");
	}

	//@Override
	public void makeMove() {
		
		trace.traceToFile(Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
		
		boolean invalidEntry = true;
		
		// Read player input, if invalid: print error and repeat
		do{
			
			// Print players move
			System.out.print(givenName + "'s move:\n");
			
			// Read players cell entry
			trace.getTraceWriter().println("has next = " + scanner.hasNextInt());
			
			
			move = new Move(scanner.nextInt(),scanner.nextInt());
			scanner.nextLine();
			
			// Check for invalid entry
			// Invalid values/syntax?
			if (move.getRow() < MIN_PLAYER_ENTRY || move.getRow() > MAX_PLAYER_ENTRY 
			|| move.getCol() < MIN_PLAYER_ENTRY || move.getCol() > MAX_PLAYER_ENTRY){
				
				invalidEntry = true;
				
				// Print error info
				System.out.print("Invalid move. You must place at a");
				System.out.print(" cell within {0,1,2} {0,1,2}.\n");
				
			} else if(gameBoard.checkGrid(move.getRow(), move.getCol()) != EMPTY_CELL){
				
				invalidEntry = true;
				
			} else {

				// No error then entry is valid
				invalidEntry = false;
			}
			
		} while (invalidEntry);
		
		gameBoard.setGrid(move.getRow(),move.getCol(), symbol);
		
	}
	
}
