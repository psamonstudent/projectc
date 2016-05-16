import java.util.List;


public class TicTacToeTest {
	
	private static final int INITIALIZE_TO_ZERO = 0;
	TicTacToe gameSystem;
	
	public TicTacToeTest(TicTacToe gameSystem){
		
		this.gameSystem = gameSystem;
		
	}
	
	public void testGame(){
		
		addRandomPlayers(10);
		
		List<Player> alph = gameSystem.playerManager.getAlphabeticArray();
		List<Player> top = gameSystem.playerManager.getTopTenArray();
		
		gameSystem.trace.getTraceWriter().println("ALPHABETIC ARRAY:");
		
		for(Player player: alph){
			
			gameSystem.trace.getTraceWriter().println(alph.toString());
			
		}
		
		gameSystem.trace.getTraceWriter().println("\nTOP TEN ARRAY:");
		
		for(Player player: top){
			
			gameSystem.trace.getTraceWriter().println(top.toString());
			
		}
		
	}
	
	public void addRandomPlayers(int numberOfPlayers){
		
		for(int index = INITIALIZE_TO_ZERO; index < numberOfPlayers; index++){
		
			gameSystem.playerManager.addPlayer(new RandomPlayer());
			
		}
		
	}

}
