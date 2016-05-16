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
	
	Trace trace;
	
	// Player arrays
	List<Player> alphabeticArray = new ArrayList<Player>();
	List<Player> topTenArray = new ArrayList<Player>();
	
	// saved state file name
	String saveFile = "saveFile";
	
	
	public PlayerManager(Trace trace) {
		
		this.trace = trace;
		
	}
	
	

	// Accessors and mutators
	public List<Player> getAlphabeticArray() {
		return alphabeticArray;
	}




	public void setAlphabeticArray(List<Player> alphabeticArray) {
		this.alphabeticArray = alphabeticArray;
	}




	public List<Player> getTopTenArray() {
		return topTenArray;
	}




	public void setTopTenArray(List<Player> topTenArray) {
		this.topTenArray = topTenArray;
	}




	// Output array state to file
	public void saveStateToFile(){	
		
		trace.trace(Thread.currentThread(), null);
		
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
		
		trace.trace(Thread.currentThread(), null);
		
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
		
		trace.trace(Thread.currentThread(), null);
		
		if(!addPlayerToAlphabeticArray(player)){
			
			// TODO: print user already exists
			return;
			
		} else {
			
			addPlayerToTopTenArray(player);
			
		}
		
	}
	
	
	// Remove player from both arrays
	public void removePlayer(Player player){
		
		trace.trace(Thread.currentThread(), null);
		
		if(!removePlayerFromArray(player, alphabeticArray)){
			
			// TODO: print user does not exist
			return;
			
		} else {
			
			removePlayerFromArray(player, topTenArray);
			
		}
		
	}
	
	// Update player in both arrays
	public void updatePlayer(Player player){
		
		trace.trace(Thread.currentThread(), null);
		
		if(!updatePlayerInAlphabeticArray(player)){
			
			// TODO: print user does not exist
			return;
			
		} else {
			
			updatePlayerInTopTenArray(player);		
			
		}
		
	}
	
	// Add player to alphabetic array. If userName exists return false;
	private boolean addPlayerToAlphabeticArray(Player player){
		
		trace.trace(Thread.currentThread(), null);
		
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
		
		trace.trace(Thread.currentThread(), null);
		
		for(int index = INITIALIZE_TO_ZERO; index < topTenArray.size(); index++){
			
			if(player.getWinningRatio() < topTenArray.get(index).getWinningRatio()){
				
				topTenArray.add(index, player);
				return;
				
			} else if(player.getWinningRatio() == topTenArray.get(index).getWinningRatio() &&
					player.getDrawingRatio() < topTenArray.get(index).getDrawingRatio()){
				
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
	
	// Remove player from array. If userName does not exist return false;
	private boolean removePlayerFromArray(Player player, List<Player> list){
		
		trace.trace(Thread.currentThread(), null);
		
		for(int index = INITIALIZE_TO_ZERO; index < list.size(); index++){
			
			if(player.getUserName().compareToIgnoreCase(list.get(index).getUserName()) == EQUALS){
				
				list.remove(index);
				return true;
				
			}
			
		}
		
		return false;
		
	}
	
	
	// Update player in alphabetic array. If userName exists return false;
	private boolean updatePlayerInAlphabeticArray(Player player){
		
		trace.trace(Thread.currentThread(), null);
		
		// TODO
		
		for(int index = INITIALIZE_TO_ZERO; index < alphabeticArray.size(); index++){
				
			if(player.getUserName().compareToIgnoreCase(alphabeticArray.get(index).getUserName()) == EQUALS){
				
				alphabeticArray.set(index, player);
				return true;
				
			}
			
		}
		
		return false;
		
	}
	
	// Update player in top ten array. 
	private void updatePlayerInTopTenArray(Player player){
		
		trace.trace(Thread.currentThread(), null);
		
		// TODO
		
		removePlayerFromArray(player, topTenArray);
		addPlayerToTopTenArray(player);
		
	}
	

	
}
