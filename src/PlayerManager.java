import java.util.ArrayList;
import java.util.List;


public class PlayerManager {
	
	// Constants
	private static final int INITIALIZE_TO_ZERO = 0;
	private static final int EQUALS = 0;
	
	// Player arrays
	List<Player> alphabeticArray = new ArrayList<Player>();
	List<Player> topTenArray = new ArrayList<Player>();
	
	// TODO: collection
	public PlayerManager (){
		
		// TODO: read game state from binary file
		
		//alphabeticArray.add(alphabeticArrayState);
		//topTenArray.add(topTenArrayState);		
	}
	
	// Add player to both arrays
	public void addPlayer(Player player){
		
		if(!addPlayerToAlphabeticArray(player)){
			
			// TODO: print user already exists
			return;
			
		} else {
			
			addPlayerToTopTenArray(player);
			
		}
		
	}
	
	
	// Remove player from both arrays
	public void removePlayer(Player player){
		
		if(!removePlayerFromArray(player, alphabeticArray)){
			
			// TODO: print user does not exist
			return;
			
		} else {
			
			removePlayerFromArray(player, topTenArray);
			
		}
		
	}
	
	// Update player in both arrays
	public void updatePlayer(Player player){
		
		if(!updatePlayerInAlphabeticArray(player)){
			
			// TODO: print user does not exist
			return;
			
		} else {
			
			updatePlayerInTopTenArray(player);		
			
		}
		
	}
	
	// Add player to alphabetic array. If userName exists return false;
	private boolean addPlayerToAlphabeticArray(Player player){
		
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
		
		// TODO
		
		removePlayerFromArray(player, topTenArray);
		addPlayerToTopTenArray(player);
		
	}
	

	
}
