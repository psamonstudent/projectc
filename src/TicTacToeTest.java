import java.util.List;


public class TicTacToeTest extends TicTacToe {
	
	private static final int INITIALIZE_TO_ZERO = 0;
	TicTacToe gameSystem;
	
	
	public void testGame(){
		
		trace.traceToFile(Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), null);
		
		addRandomPlayers(110);		
		
		printPlayers();
		
		printTopTen();
		
		
		
	}
	
	public void addRandomPlayers(int numberOfPlayers){
		
		trace.traceToFile(Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), null);
		
		for(int index = INITIALIZE_TO_ZERO; index < numberOfPlayers; index++){
		
			playerManager.addPlayer(new RandomPlayer());
			
		}
		
	}
	
	public void printPlayers(){
		
		trace.getTraceWriter().println("ALPHABETIC ARRAY:");
		
		for(Player player: playerManager.getAlphabeticArray()){
			
			trace.getTraceWriter().println(player.toString());
			
		}
		
	}
	
	public void printTopTen(){
		
		trace.getTraceWriter().println("\nTOP TEN ARRAY:");
		
		for(Player player: playerManager.getTopTenArray()){
			
			trace.getTraceWriter().println(player.toString());
			
		}
		
	}

}
