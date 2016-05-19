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
	
	Trace trace;
	
	// Player arrays
	private List<Player> alphabeticArray = new ArrayList<Player>();
	private List<Player> topTenArray = new ArrayList<Player>();
	
	// saved state file name
	String saveFile = "saveFile";
	
	
	public PlayerManager(Trace trace) {
		
		this.trace = trace;
		
	}
	
	

	// Accessors and mutators
	// TODO does this copy of the list?
	public List<Player> getAlphabeticArray() {
		return new ArrayList<Player>(this.alphabeticArray);	
	}




	public void setAlphabeticArray(List<Player> alphabeticArray) {
		this.alphabeticArray = alphabeticArray;
	}




	public List<Player> getTopTenArray() {
		return new ArrayList<Player>(this.topTenArray);
	}




	public void setTopTenArray(List<Player> topTenArray) {
		this.topTenArray = topTenArray;
	}




	// Output array state to file
	public void saveStateToFile(){	
		
		trace.traceToFile(Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), null);
		
		// TODO: test
		try {
			
			// Create new output stream
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(saveFile));

			// Write number of alphabeticArray objects to file
			outputStream.writeInt(alphabeticArray.size());
			
			// write each player from alphabetic array to file
			for(int index = INITIALIZE_TO_ZERO; index < alphabeticArray.size(); index++){
				
				outputStream.writeObject(alphabeticArray.get(index));
				
			}
			
			// Write number of topTenArray objects to file
			outputStream.writeInt(topTenArray.size());
			
			// Write each player from topten array to file
			for(int index = INITIALIZE_TO_ZERO; index < topTenArray.size(); index++){
				
				outputStream.writeObject(topTenArray.get(index));
				
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
			int alphabeticArraySize = inputStream.readInt();
			
			// Read each object from file 
			for(int index = INITIALIZE_TO_ZERO; index < alphabeticArraySize; index++){
				
				try {
					
					alphabeticArray.add((Player) inputStream.readObject());
					
				} catch (ClassNotFoundException e) {
					
					// TODO Auto-generated catch block
					
					e.printStackTrace();
				}
				
			}
			
			// Get number of top ten array objects
			int topTenArraySize = inputStream.readInt();
			
			// Read each object from file
			for(int index = INITIALIZE_TO_ZERO; index < topTenArraySize; index++){
				
				try {
					
					topTenArray.add((Player) inputStream.readObject());
					
				} catch (ClassNotFoundException e) {
					
					// TODO Auto-generated catch block
					
					e.printStackTrace();
				}
				
			}
			
			inputStream.close();

			
			//alphabeticArray.addAll(alphabeticArrayState);
			//topTenArray.addAll(topTenArrayState);	
			
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
		
		
		if(alphabeticArray.size() == MAX_PLAYERS){
			// TODO: print max amount of players
			return;
		}
		
		
		if(!addPlayerToAlphabeticArray(player)){
			
			// TODO: print user already exists
			return;
			
		} else {
			
			addPlayerToTopTenArray(player);
			
		}
		
	}
	
	// Remove all players
	public void removeAllPlayers(){
		
		// TODO: y/n check
		
		alphabeticArray.clear();
		topTenArray.clear();
		
	}
	
	// Remove player from both arrays
	public void removePlayer(String userName){
		
		trace.traceToFile(Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), null);
		
		if(!alphabeticArray.remove(userName)){
			
			// TODO: print user does not exist
			return;
			
		}
		
		topTenArray.remove(userName);

		
		/*
		if(!removePlayerFromArray(userName, alphabeticArray)){
			
			// TODO: print user does not exist
			return;
			
		} else {
			
			removePlayerFromArray(userName, topTenArray);
			
		}*/
		
	}
	
	// Update player stats in both arrays accepts type Player
	public void updatePlayer(Player player){
		
		trace.traceToFile(Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), null);
		
		int index = alphabeticArray.indexOf(player);
		
		if(index == PLAYER_DOES_NOT_EXIST){
			
			// TODO: print user does not exist
			return;
			
		} else {
			
			alphabeticArray.set(index, player);
			updatePlayerInTopTenArray(player);		
			
		}
		
	}
	
	// Update player name in both arrays
	public void updatePlayer(String userName, String familyName, String givenName){
		
		int index = alphabeticArray.indexOf(userName);
		
		if(index == PLAYER_DOES_NOT_EXIST){
			
			// TODO: print player does not exist
			return;
			
		} else if(alphabeticArray.get(index) instanceof HumanPlayer){
			
			HumanPlayer temp = new HumanPlayer(alphabeticArray.get(index));			
			temp.setFamilyName(familyName);
			temp.setGivenName(givenName);
			alphabeticArray.set(index, temp);
			topTenArray.set(index, temp);
			
		} else {
			
			AIPlayer temp = new AIPlayer(alphabeticArray.get(index));
			temp.setFamilyName(familyName);
			temp.setGivenName(givenName);
			alphabeticArray.set(index, temp);
			topTenArray.set(index, temp);
			
		}	
		
	}
	
	// Reset stats
	public void resetStats(String userName){
		
		//TODO: finish method & create resetAll stats method
		
		alphabeticArray.indexOf(userName);
		
	}
	
	
	
	// Add player to alphabetic array. If userName exists return false;
	private boolean addPlayerToAlphabeticArray(Player player){
		
		trace.traceToFile(Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), null);
		
		for(int index = INITIALIZE_TO_ZERO; index < alphabeticArray.size(); index++){
			
			if(player.getUserName().compareToIgnoreCase(alphabeticArray.get(index).getUserName()) < EQUALS){
				
				alphabeticArray.add(index, player);
				return true;
				
			} else if(player.getUserName().compareToIgnoreCase(alphabeticArray.get(index).getUserName()) == EQUALS){
				
				return false;
				
			}
			
		}
		
		alphabeticArray.add(player);
		return true;
		
	}
	
	// Add player to top ten array. 
	private void addPlayerToTopTenArray(Player player){
		
		trace.traceToFile(Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), null);
		
		for(int index = INITIALIZE_TO_ZERO; index < topTenArray.size(); index++){
			
			if(player.getWinningRatio() > topTenArray.get(index).getWinningRatio()){
				
				topTenArray.add(index, player);
				return;
				
			} else if(player.getWinningRatio() == topTenArray.get(index).getWinningRatio() &&
					player.getDrawingRatio() > topTenArray.get(index).getDrawingRatio()){
				
				topTenArray.add(index, player);
				return;
				
			} else if(player.getWinningRatio() == topTenArray.get(index).getWinningRatio() &&
					player.getDrawingRatio() == topTenArray.get(index).getDrawingRatio() &&
					player.getUserName().compareToIgnoreCase(topTenArray.get(index).getUserName()) < EQUALS){
				
				topTenArray.add(index, player);
				return;
				
			}
			
		}
		
		topTenArray.add(player);
		
	}
	
	// Update player in top ten array. 
	private void updatePlayerInTopTenArray(Player player){
		
		trace.traceToFile(Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), null);
		
		// TODO
		
		topTenArray.remove(player);
		addPlayerToTopTenArray(player);
		
	}

	
}
