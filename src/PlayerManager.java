import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class PlayerManager {
	
	// Constants
	private static final int INITIALIZE_TO_ZERO = 0;
	private static final int EQUALS = 0;
	private static final int MAX_PLAYERS = 100;
	private static final int PLAYER_DOES_NOT_EXIST = -1;
	private static final int TENTH_PLAYER = 9;
	private static final int NUMBER_OF_TOP_TEN_PLAYERS = 10;
	private static final int WIN = 2;
	private static final int DRAW = 1;
	
	Trace trace;
	
	// Player arrays
	private List<Player> playerArray = new ArrayList<Player>();
	
	// saved state file name
	String saveFile = "saveFile";
	
	// Constructor
	public PlayerManager(Trace trace) {
		
		this.trace = trace;
		
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
		
		trace.traceToFile(Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), null);
		
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
		
		trace.traceToFile(Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), null);
		
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
		
		trace.traceToFile(Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), null);
		
		
		if(playerArray.size() == MAX_PLAYERS){
			// TODO: print max amount of players
			return;
		}
		
		
		if(!addPlayerToplayerArray(player)){
			
			// TODO: print user already exists
			return;
			
		}
		
	}
	
	// Remove all players
	public void removeAllPlayers(){
		
		// TODO: y/n check
		
		playerArray.clear();
		
	}
	
	// Remove player from both arrays
	public void removePlayer(String userName){
		
		trace.traceToFile(Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), null);
		
		if(!playerArray.remove(userName)){
			
			// TODO: print user does not exist
			return;
			
		}
		
	}
	
/*	// Update player stats
	public void updatePlayer(int index, int result){
		
		Player player = playerArray.get(index);
		
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
			
	}*/
	
	
	// Update player name in both arrays
	public void editPlayer(String userName, String familyName, String givenName){
		
		int index = playerArray.indexOf(userName);
		
		if(index == PLAYER_DOES_NOT_EXIST){
			
			// TODO: print player does not exist
			return;
			
		} else if(playerArray.get(index) instanceof HumanPlayer){
			
			HumanPlayer temp = new HumanPlayer(playerArray.get(index));			
			temp.setFamilyName(familyName);
			temp.setGivenName(givenName);
			playerArray.set(index, temp);
			
		} else {
			
			AIPlayer temp = new AIPlayer(playerArray.get(index));
			temp.setFamilyName(familyName);
			temp.setGivenName(givenName);
			playerArray.set(index, temp);
			
		}	
		
	}
	
	// Reset stats
	public void resetStats(String userName){
		
		//TODO: finish method & create resetAll stats method
		
		int index = playerArray.indexOf(userName);
		
		if(index == PLAYER_DOES_NOT_EXIST){
			
			//TODO: invalid input statement
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
		
		// TODO: are you sure?
		
		for(Player player: playerArray){
			
			player.setGamesPlayed(INITIALIZE_TO_ZERO);
			player.setGamesWon(INITIALIZE_TO_ZERO);
			player.setGamesDrawn(INITIALIZE_TO_ZERO);
			player.updateRatios();
			
		}
		
	}
	
	// Display player
	public void displayPlayer(String userName){
		
		int index = playerArray.indexOf(userName);
		
		if(index == PLAYER_DOES_NOT_EXIST){
			
			System.out.print("The player does not exist.\n");
			return;
			
		}
		
		System.out.print(playerArray.get(index).getUserName() + "," + playerArray.get(index).getFamilyName() + "," + playerArray.get(index).getGivenName());
		System.out.print(playerArray.get(index).getGamesPlayed() + " games," + playerArray.get(index).getGamesWon() + " wins," + playerArray.get(index).getGamesDrawn() + " draws\n");
		
	}
	
	// Display all players
	public void displayAllPlayers(){
		
		for(Player player: playerArray){
			
			System.out.print(player.getUserName() + "," + player.getFamilyName() + "," + player.getGivenName());
			System.out.print(player.getGamesPlayed() + " games," + player.getGamesWon() + " wins," + player.getGamesDrawn() + " draws\n");
			
		}
		
	}
	
	// Display rankings
	public void displayRanking(){
		
		Player[] topTen = new Player[10];
		
		for(Player player: playerArray){
			
			if(topTen[TENTH_PLAYER] == null || topTen[TENTH_PLAYER].getWinningRatio() <= player.getWinningRatio()){
				
				// Add player to top ten
				for(int index = INITIALIZE_TO_ZERO; index < NUMBER_OF_TOP_TEN_PLAYERS; index++){
					
					if(player.getWinningRatio() > topTen[index].getWinningRatio() || 
							(player.getWinningRatio() == topTen[index].getWinningRatio() &&
							player.getDrawingRatio() > topTen[index].getDrawingRatio()) ||
							(player.getWinningRatio() == topTen[index].getWinningRatio() &&
							player.getDrawingRatio() == topTen[index].getDrawingRatio() &&
							player.getUserName().compareToIgnoreCase(topTen[index].getUserName()) < EQUALS)){
						
						for(int position = TENTH_PLAYER; position > index; position--){
							
							topTen[position] = topTen[position - 1];
							
						}
						
						topTen[index] = player;
						
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
	private boolean addPlayerToplayerArray(Player player){
		
		trace.traceToFile(Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), null);
		
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

	
}
