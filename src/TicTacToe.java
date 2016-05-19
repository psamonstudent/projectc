import java.io.PrintWriter;

import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;


public class TicTacToe {
	
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
	
	
	// Main method
	public static void main(String[] args){
	
		TicTacToe gameSystem = new TicTacToe();
		
		TicTacToeTest testGame = new TicTacToeTest(gameSystem);
		
		testGame.testGame();
		
		//gameSystem.run();
		
	}
	
}
