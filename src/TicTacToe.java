import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;


public class TicTacToe {
	
	private static final int INITIALIZE_TO_ZERO = 0;
	private static final int COMMAND = 0;
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
	private static final int NORMAL_TERMINATION = 0;
	private static final int RESET_ALL_STATS_NUMBER_ARGS = 1;
	private static final int RESET_STATS_NUMBER_ARGS = 2;
	private static final int DISPLAY_ALL_PLAYERS_NUMBER_ARGS = 1;
	private static final int DISPLAY_PLAYER_NUMBER_ARGS = 2;
	private static final int PLAYER_DOES_NOT_EXIST = -1;
	private static final int PLAY_GAME_NUMBER_ARGS = 3;
	private static final int NO_INPUT = 0;
	private static final int ONE_ARGUMENT = 2;
	
	// Create Scanner object to read from System.in
	public static Scanner scanner = new Scanner(System.in);
	
	// Create Trace object to print traces to a text file
	Trace trace = new Trace();
	
	// Create PlayerManager object to handle player management. 
	// Pass trace object to constructor to enable tracing
	PlayerManager playerManager = new PlayerManager(scanner, trace);
	
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
		
		trace.traceToFile(Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
		
		
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
		
		trace.traceToFile(Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
		
		MyScanner myScanner; 
		
		// If no input return false
		if(!scanner.hasNextLine()){
			
			return false;
			
		}
		
		// Read the next line
		String userInput = scanner.nextLine();
		
		// Use MyScanner class to break down into seperate arguments
		myScanner = new MyScanner(userInput, trace);
		
		trace.getTraceWriter().println("number of args = " + myScanner.getNumberOfWords());
		
		for(int index = INITIALIZE_TO_ZERO; index < myScanner.getNumberOfWords(); index++){
			
			trace.getTraceWriter().println("wordArray[" + index + "] = " + myScanner.getWordArray()[index] + "...");
			
		}
		
		// If there are no arguments return false
		// TODO: do I need this?
		if(myScanner.getNumberOfWords() == NO_INPUT){
			
			return false;
			
		}
		
		// Get the user command from MyScanner Class
		String command = myScanner.getWordArray()[COMMAND];
		
		trace.getTraceWriter().println("command = " + command + "....");
		
		// Call appropriate method for each command
		switch (command){
			
			// Command #1
			case EXIT:
			
				exit();
				break;
				
			// Command #2 
			case ADD_PLAYER: 
				
				if(myScanner.getNumberOfWords() != ADD_PLAYER_NUMBER_ARGS){
					
					System.out.print("Incorrect number of arguments supplied to command.\n");
					return false;

				}
				
				playerManager.addPlayer(new HumanPlayer(myScanner.getWordArray()[1], myScanner.getWordArray()[2], myScanner.getWordArray()[3], scanner, trace));
				break;

			// Command #3
			case REMOVE_PLAYER:
				
				if(myScanner.getNumberOfWords() == NO_ARGUMENTS){
					
					playerManager.removeAllPlayers();
					break;
					
				} else if(myScanner.getNumberOfWords() == ONE_ARGUMENT){
					
					playerManager.removePlayer(myScanner.getWordArray()[1]);
					break;
					
				}
					
				System.out.print("Incorrect number of arguments supplied to command.\n");
				return false;
					
						
			// Command #4
			case EDIT_PLAYER:
				
				if(myScanner.getNumberOfWords() != UPDATE_PLAYER_NUMBER_ARGS){
					
					System.out.print("Incorrect number of arguments supplied to command.\n");
					return false;

				}
			
				playerManager.editPlayer(myScanner.getWordArray()[1], myScanner.getWordArray()[2], myScanner.getWordArray()[3]);	
				break;
				
			// Command #5
			case RESET_STATS:
				
				
				if(myScanner.getNumberOfWords() == RESET_ALL_STATS_NUMBER_ARGS){
					
					playerManager.resetStats();
					break;
					
				}if(myScanner.getNumberOfWords() == RESET_STATS_NUMBER_ARGS){
					
					// TODO magic number
					playerManager.resetStats(myScanner.getWordArray()[1]);
					break;
					
				} else {
					
					System.out.print("Incorrect number of arguments supplied to command.\n");
					break;
					
				}

			
			// Command #6
			case DISPLAY_PLAYER:
				
				if(myScanner.getNumberOfWords() == DISPLAY_ALL_PLAYERS_NUMBER_ARGS){
					
					playerManager.displayAllPlayers();
					break;
					
				}
				if(myScanner.getNumberOfWords() == DISPLAY_PLAYER_NUMBER_ARGS){
					
					// TODO: magic number
					playerManager.displayPlayer(myScanner.getWordArray()[1]);
					break;
					
				}
				
				System.out.print("Incorrect number of arguments supplied to command.\n");
				return false;
							
			// Command #7
			case RANKINGS:
			
				playerManager.displayRanking();
				break;
			
			// Command #8
			// TODO test
			case PLAY_GAME:
				
				if(myScanner.getNumberOfWords() != PLAY_GAME_NUMBER_ARGS){
					
					System.out.print("Incorrect number of arguments supplied to command.\n");
					return false;
					
				}
				
				int playerOneIndex = playerManager.indexOf(myScanner.getWordArray()[1]);
				int playerTwoIndex = playerManager.indexOf(myScanner.getWordArray()[2]);
				
				if(playerOneIndex == PLAYER_DOES_NOT_EXIST || playerTwoIndex == PLAYER_DOES_NOT_EXIST){
					
					System.out.print("The player does not exist.\n");
					return false;
					
				} 
				
				Player playerOne;
				Player playerTwo;
				
				if(playerManager.getPlayerArray().get(playerOneIndex) instanceof HumanPlayer){
					
					playerOne = new HumanPlayer(playerManager.getPlayerArray().get(playerOneIndex), trace, scanner);
					
				} else {
					
					playerOne = new AIPlayer(playerManager.getPlayerArray().get(playerOneIndex), trace);
					
				}
				
				if(playerManager.getPlayerArray().get(playerTwoIndex) instanceof HumanPlayer){
					
					playerTwo = new HumanPlayer(playerManager.getPlayerArray().get(playerTwoIndex), trace, scanner);
					
				} else {
					
					playerTwo = new AIPlayer(playerManager.getPlayerArray().get(playerTwoIndex), trace);
					
				}
						
				gameManager.playGame(playerOne, playerTwo);
				
				playerManager.updatePlayer(playerOneIndex, gameManager.getPlayerOne());
				playerManager.updatePlayer(playerTwoIndex, gameManager.getPlayerTwo());
				

				
				break;
				
			// Command #9 
			case ADD_AI_PLAYER: 
				
					
				if(myScanner.getNumberOfWords() != ADD_PLAYER_NUMBER_ARGS){
						
					System.out.print("Incorrect number of arguments supplied to command.\n");
					return false;

				}
					
				playerManager.addPlayer(new AIPlayer(myScanner.getWordArray()[1], myScanner.getWordArray()[2], myScanner.getWordArray()[3], trace));
				break;			
				
			default:
	
				System.out.print("'" + command + "' is not a valid command.\n");
				
				trace.getTraceWriter().print("Invalid command '" + myScanner.getWordArray()[COMMAND] + "' not found.\n");
				return false;
				
		}
		
		return true;
		
	}
	
	// Command #1: exit
	public void exit(){
		
		trace.traceToFile(Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
		
		
		playerManager.saveStateToFile();
		System.out.print("\n");
		System.exit(NORMAL_TERMINATION);

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
