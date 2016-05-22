import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class PlayerManager {
	
	// Constants
	private static final int INITIALIZE_TO_ZERO = 0;
	private static final int EQUALS = 0;
	private static final int MAX_PLAYERS = 100;
	private static final int PLAYER_DOES_NOT_EXIST = -1;
	private static final int TENTH_PLAYER = 9;
	private static final int NUMBER_OF_TOP_TEN_PLAYERS = 10;
	private static final String YES = "y";
	private static final String NO = "n";
	
	Trace trace;
	Scanner scanner;
	
	// Player arrays
	private List<Player> playerArray = new ArrayList<Player>();
	
	// saved state file name
	String saveFile = "saveFile";
	
	// Constructor
	public PlayerManager(Scanner scanner,Trace trace) {
		
		this.trace = trace;
		this.scanner = scanner;
		
	}
	
	// Accessors & Mutators
	public List<Player> getPlayerArray(){
		return playerArray;
	}
	public void setPlayerArray(List<Player> playerArray){
		this.playerArray = playerArray;
	}

	
	// Output array state to file
	public void saveStateToFile(){	
		
		trace.traceToFile(Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
		
		// TODO: test
		try {
			
			// Create new output stream
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(saveFile));

			// Write number of playerArray objects to file
			outputStream.writeInt(playerArray.size());
			
			// write each player from alphabetic array to file
			for(int index = INITIALIZE_TO_ZERO; index < playerArray.size(); index++){
				
				outputStream.writeObject(playerArray.get(index));
				
			}
			
			// Close output stream
			outputStream.close();
			
			
		} catch (FileNotFoundException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}
	
	
	// retrieve state from file
	public void recoverStateFromFile(){
		
		trace.traceToFile(Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
		
		//TODO: test
		try {
			
			// Create new input stream
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(saveFile));
			
			// Get number of alphabetic array objects
			int playerArraySize = inputStream.readInt();
			
			// Read each object from file 
			for(int index = INITIALIZE_TO_ZERO; index < playerArraySize; index++){
				
				try {
					
					playerArray.add((Player) inputStream.readObject());
					
				} catch (ClassNotFoundException e) {
					
					// TODO Auto-generated catch block
					
					e.printStackTrace();
				}
				
			}
			
			inputStream.close();
			
		} catch (FileNotFoundException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}
	
	
	// Add player to both arrays
	public void addPlayer(Player player){
		
		trace.traceToFile(Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
		
		
		if(playerArray.size() == MAX_PLAYERS){
			System.out.println("Maximum amount of players.");
			return;
		}
		
		
		if(!addPlayerToPlayerArray(player)){
			
			System.out.println("The username has already been used.");
			return;
			
		}
		
	}
	
	// Remove all players
	public void removeAllPlayers(){
		
		trace.traceToFile(Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
		
		// Prompt user for confirmation
		System.out.print("Are you sure you want to remove all players? (y/n)\n");
			
		// Get user response
		String response = scanner.nextLine();
			
		// If yes
		if(response.equals(YES)){
					
			playerArray.clear();
			return;
				
		// If no, exit
		} else if(response.equals(NO)){
					
			return;
					
		} else {
					
			System.out.print("Incorrect response. Response does not match: y or n.");
		
		}

	}
	
	// Remove player from both arrays
	public void removePlayer(String userName){
		
		trace.traceToFile(Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
		
		int index = indexOf(userName);
		
		if(index == PLAYER_DOES_NOT_EXIST){
			
			System.out.println("The player does not exist.");
			return;
			
		}
		
		playerArray.remove(index);
		
	}
	
	
	// Update player name in both arrays
	public void editPlayer(String userName, String familyName, String givenName){
		
		trace.traceToFile(Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
		
		
		int index = indexOf(userName);
		
		if(index == PLAYER_DOES_NOT_EXIST){
			
			System.out.println("The player does not exist.");
			return;
			
		} else if(playerArray.get(index) instanceof HumanPlayer){
			
			HumanPlayer temp = new HumanPlayer(playerArray.get(index), trace, scanner);			
			temp.setFamilyName(familyName);
			temp.setGivenName(givenName);
			playerArray.set(index, temp);
			
		} else {
			
			AIPlayer temp = new AIPlayer(playerArray.get(index), trace);
			temp.setFamilyName(familyName);
			temp.setGivenName(givenName);
			playerArray.set(index, temp);
			
		}	
		
	}
	
	// Reset stats
	public void resetStats(String userName){
		
		trace.traceToFile(Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
		
		
		//TODO: finish method & create resetAll stats method
		
		int index = indexOf(userName);
		
		if(index == PLAYER_DOES_NOT_EXIST){
			
			System.out.println("The player does not exist.");
			return;
			
		}
		
		// TODO: test
		playerArray.get(index).setGamesPlayed(INITIALIZE_TO_ZERO);
		playerArray.get(index).setGamesWon(INITIALIZE_TO_ZERO);
		playerArray.get(index).setGamesDrawn(INITIALIZE_TO_ZERO);
		playerArray.get(index).updateRatios();
		
	}
	
	
	// Reset all stats
	public void resetStats(){
		
		trace.traceToFile(Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
		
		
		// Prompt user for confirmation
		System.out.print("Are you sure you want to reset all player statistics? (y/n)\n");
			
		// Get user response
		String response = scanner.nextLine();
			
		// If yes
		if(response.equals(YES)){
					
			for(Player player: playerArray){
				
				player.setGamesPlayed(INITIALIZE_TO_ZERO);
				player.setGamesWon(INITIALIZE_TO_ZERO);
				player.setGamesDrawn(INITIALIZE_TO_ZERO);
				player.updateRatios();
				
			}
			
			return;
				
		// If no, exit
		} else if(response.equals(NO)){
					
			return;
					
		} else {
					
			System.out.print("Incorrect response. Response does not match: y or n.");
		
		}
		
	}
	
	// Display player
	public void displayPlayer(String userName){
		
		trace.traceToFile(Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
		
		trace.getTraceWriter().println("userName = " + userName);
		
		int index = indexOf(userName);
		
		trace.getTraceWriter().println("index = " + index);
		
		if(index == PLAYER_DOES_NOT_EXIST){
			
			System.out.print("The player does not exist.\n");
			return;
			
		}
		
		System.out.print(playerArray.get(index).getUserName() + "," + playerArray.get(index).getFamilyName() + "," + playerArray.get(index).getGivenName() + ",");
		System.out.print(playerArray.get(index).getGamesPlayed() + " games," + playerArray.get(index).getGamesWon() + " wins," + playerArray.get(index).getGamesDrawn() + " draws\n");
		
	}
	
	// Display all players
	public void displayAllPlayers(){
		
		trace.traceToFile(Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
		
		
		for(Player player: playerArray){
			
			System.out.print(player.getUserName() + "," + player.getFamilyName() + "," + player.getGivenName() + ",");
			System.out.print(player.getGamesPlayed() + " games," + player.getGamesWon() + " wins," + player.getGamesDrawn() + " draws\n");
			
		}
		
	}
	
	// Display rankings
	public void displayRanking(){
		
		trace.traceToFile(Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
		
		
		Player[] topTen = new Player[10];
		
		for(Player top: topTen){
			
			top = null;
			
		}
		
		for(Player player: playerArray){
			
			trace.getTraceWriter().print("player.getWinningRatio() = " + player.getWinningRatio() + ", last player = ");
			
			if(topTen[TENTH_PLAYER] == null){
				
				trace.getTraceWriter().println("null");
				
			} else {
				
				trace.getTraceWriter().println(topTen[TENTH_PLAYER].getWinningRatio());
				
			}
			trace.getTraceWriter().flush();
			
			if(topTen[TENTH_PLAYER] == null || topTen[TENTH_PLAYER].getWinningRatio() <= player.getWinningRatio()){
				
				// Add player to top ten
				for(int index = INITIALIZE_TO_ZERO; index < NUMBER_OF_TOP_TEN_PLAYERS; index++){
					
					trace.getTraceWriter().println("player.getWinningRatio() = " + player.getWinningRatio());
					
					if(topTen[index] == null){
						
						topTen[index] = player;
						break;
						
					} else if(player.getWinningRatio() > topTen[index].getWinningRatio() || 
							(player.getWinningRatio() == topTen[index].getWinningRatio() &&
							player.getDrawingRatio() > topTen[index].getDrawingRatio()) ||
							(player.getWinningRatio() == topTen[index].getWinningRatio() &&
							player.getDrawingRatio() == topTen[index].getDrawingRatio() &&
							player.getUserName().compareToIgnoreCase(topTen[index].getUserName()) < EQUALS)){
						
						for(int position = TENTH_PLAYER; position > index; position--){
							
							topTen[position] = topTen[position - 1];
							
						}
						
						topTen[index] = player;
						break;
						
					}
					
				}
				
			}
			
		}
		
		System.out.print(" WIN  | DRAW | GAME | USERNAME\n");
		
		for(Player topPlayer: topTen){
			
			if(topPlayer == null){
				
				return;				
				
			}

			System.out.printf("%4d%% |%4d%% |%3d   | %s\n", 
			(int) Math.round(topPlayer.getWinningRatio()), 
			(int) Math.round(topPlayer.getDrawingRatio()), 
			topPlayer.getGamesPlayed(), topPlayer.getUserName());
			
		}
		
	}
	
	

	// Add player to alphabetic array. If userName exists return false;
	private boolean addPlayerToPlayerArray(Player player){
		
		trace.traceToFile(Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
		
		for(int index = INITIALIZE_TO_ZERO; index < playerArray.size(); index++){
			
			if(player.getUserName().compareToIgnoreCase(playerArray.get(index).getUserName()) < EQUALS){
				
				playerArray.add(index, player);
				return true;
				
			} else if(player.getUserName().compareToIgnoreCase(playerArray.get(index).getUserName()) == EQUALS){
				
				return false;
				
			}
			
		}
		
		playerArray.add(player);
		return true;
		
	}
	
	public void updatePlayer(int index, Player player){
		
		if(player instanceof HumanPlayer){
			
			playerArray.set(index, new HumanPlayer(player, trace, scanner));
			
		} else {
			
			playerArray.set(index, new AIPlayer(player, trace));
			
		}
		
	}
	
	public int indexOf(Object obj){
		
		trace.traceToFile(Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
		
		for(int index = INITIALIZE_TO_ZERO; index < playerArray.size(); index++){
			
			if(playerArray.get(index).equals(obj)){
				
				return index;
				
			}
			
		}
		
		return PLAYER_DOES_NOT_EXIST;
		
	}

	
}
