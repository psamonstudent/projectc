import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;


public class TicTacToe {
	
	private static final int INITIALIZE_TO_ZERO = 0;
	private static final int MAX_INPUT_WORDS = 3;
	private static final int COMMAND = 0;
	private static final int USER_NAME = 0;
	private static final int FAMILY_NAME = 1;
	private static final int GIVEN_NAME = 2;
	private static final int PLAYER_ONE = 0;
	private static final int PLAYER_TWO = 1;
	private static final int ADD_PLAYER_NUMBER_ARGS = 4;
	private static final String EXIT = "exit";
	private static final String ADD_PLAYER = "addplayer";
	private static final String REMOVE_PLAYER = "removeplayer";
	private static final String EDIT_PLAYER = "editplayer";
	private static final String RESET_STATS = "resetstats";
	private static final String DISPLAY_PLAYER = "displayplayer";
	private static final String RANKINGS = "rankings";
	private static final String PLAY_GAME = "playgame";
	private static final String ADD_AI_PLAYER = "addaiplayer";
	private static final int NO_ARGUMENTS = 1;
	private static final int UPDATE_PLAYER_NUMBER_ARGS = 4;
	
	// Create Scanner object to read from System.in
	public static Scanner scanner = new Scanner(System.in);
	
	// Create Trace object to print traces to a text file
	Trace trace = new Trace();
	
	// Create PlayerManager object to handle player management. 
	// Pass trace object to constructor to enable tracing
	PlayerManager playerManager = new PlayerManager(trace);
	
	// Create GameManager object to handle gameplay. 
	// Pass scanner object to enable reading from System.in
	// Pass trace object to constructor to enable tracing
	// Pass playerManager object to enable player access
	GameManager gameManager = new GameManager(scanner, trace, playerManager);
	
	/* Comment: 		Welcome players and process input
	*  Precondition: 	NA
	*  Postcondition: 	Game is finished */
	public void run(){
		
		System.out.print("Welcome to Tic Tac Toe!\n");
		System.out.print("\n>");
		processUserInput();
			
	}
	
	/* Comment: 		Prompt user for input and process commands
	*  Precondition: 	Game has started
	*  Postcondition: 	exit command has not been issued */
	public void processUserInput(){
		
		// While exit command has not been issued, keep playing
		while(true){
			
			// Check validity of command and run
			if(checkCommand())
				
				// New command prompt
				System.out.print("\n>");


		}
		
	}
	
	/* Comment: 		Check validity of command and process actions
	*  Precondition: 	Game is in play
	*  Postcondition: 	Returns true if command is valid, false if invalid. */
	public boolean checkCommand(){
		
		List<String> userArguments = new ArrayList<String>();
	
		// Read next line of user input
		String userInput = scanner.nextLine();
		
		// Create new scanner to read userInput string and use the delimiters " " & ","
		Scanner scanInput = new Scanner(userInput).useDelimiter(" |,");
		
		// If no input, return invalid command
		if(!scanInput.hasNext()){
			
			return false;
			
		}
		
		// Otherwise read command
		String command = scanInput.next();
		int index = INITIALIZE_TO_ZERO;
		
		// Read three user input strings separated by commas, if empty, data is null
		while(scanInput.hasNext()){
				
			userArguments.add(scanInput.next());
			
		}
		
		// Call appropriate method for each command
		switch (userArguments.get(COMMAND)){
			
			// Command #1
			case EXIT:
			
				gameManager.exit();
				break;
				
			// Command #2 
			case ADD_PLAYER: 
				
				if(userArguments.size() != ADD_PLAYER_NUMBER_ARGS){
					
					System.out.print("Incorrect number of arguments supplied to command.\n");
					return false;

				}
				
				playerManager.addPlayer(new HumanPlayer(userArguments.get(1), userArguments.get(2), userArguments.get(3)));
				break;

			// Command #3
			case REMOVE_PLAYER:
				
				if(userArguments.size() == NO_ARGUMENTS){
					
					playerManager.removeAllPlayers();
					
				}
					
				playerManager.removePlayer(userArguments.get(1));
				break;
						
			// Command #4
			case EDIT_PLAYER:
				
				if(userArguments.size() != UPDATE_PLAYER_NUMBER_ARGS){
					
					System.out.print("Incorrect number of arguments supplied to command.\n");
					return false;

				}
			
				playerManager.updatePlayer(userArguments.get(1), userArguments.get(2), userArguments.get(3));	
				break;
				
			// Command #5
			case RESET_STATS:
					
				playerManager.resetStats(data[USER_NAME]);
				break;
			
			// Command #6
			case DISPLAY_PLAYER:
						
				playerManager.displayPlayer(data[USER_NAME]);		
				break;
							
			// Command #7
			case RANKINGS:
			
				playerManager.displayRanking();
				break;
			
			// Command #8
			case PLAY_GAME:
				
				gameManager.playGame(data[PLAYER_ONE], data[PLAYER_TWO]);
				break;
				
			// Command #9 
			case ADD_AI_PLAYER: 
					
				if(userArguments.size() != ADD_PLAYER_NUMBER_ARGS){
						
					System.out.print("Incorrect number of arguments supplied to command.\n");
					return false;

				}
					
				playerManager.addPlayer(new AIPlayer(userArguments.get(1), userArguments.get(2), userArguments.get(3)););
				break;			
				
			default:
				
				// TODO: Print invalid command
				return false;
				
		}
		
		return true;
		
	}
	
	
	// Main method
	public static void main(String[] args){
		
		// Create test object
		TicTacToeTest test = new TicTacToeTest();
		
		// Test game
		test.testGame();
		
		// Close PrintWriter
		test.trace.getTraceWriter().close();
		
	}
	
}
