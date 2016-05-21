import java.util.Scanner;



public class HumanPlayer extends Player {
	
	Scanner scanner;
	
	// Constructor
	public HumanPlayer (Player player){
		super(player);
	}
	
	public HumanPlayer(String userName, String familyName, String givenName, Scanner scanner){
		super(userName, familyName, givenName);
		this.scanner = scanner;
	}

	//@Override
	public void makeMove() {
		
		boolean invalidEntry = true;
		
		// Read player input, if invalid: print error and repeat
		do{
			
			// Print players move
			System.out.print(userName + "'s move:\n");
			
			// Read players cell entry
			move.setRow(scanner.nextInt());  		
			move.setCol(scanner.nextInt());  
			
			// Check for invalid entry
			// Invalid values/syntax?
			if (move.getRow() < MIN_PLAYER_ENTRY || move.getRow() > MAX_PLAYER_ENTRY 
			|| move.getCol() < MIN_PLAYER_ENTRY || move.getCol() > MAX_PLAYER_ENTRY){
				
				invalidEntry = true;
				
				// Print error info
				System.out.print("Invalid move. You must place at a");
				System.out.print(" cell within {0,1,2} {0,1,2}.\n");
				
				
			} else {

				// No error then entry is valid
				invalidEntry = false;
			}
			
		} while (invalidEntry);
		
	}
	
}
