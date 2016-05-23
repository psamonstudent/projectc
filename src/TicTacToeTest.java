import java.util.List;


public class TicTacToeTest extends TicTacToe {
	
	private static final int INITIALIZE_TO_ZERO = 0;
	TicTacToe gameSystem;
	
	
	public void testGame(){
		
		trace.traceToFile(Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
		
		//addRandomPlayers(97);	
		
		
		run();
		
		//printPlayers();
		
		
		
		//
		

	}
	
	public void addRandomPlayers(int numberOfPlayers){
		
		trace.traceToFile(Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
		
		for(int index = INITIALIZE_TO_ZERO; index < numberOfPlayers; index++){
		
			playerManager.addPlayer(new RandomPlayer(trace));
			
		}
		
	}
	
	public void printPlayers(){
		
		trace.getTraceWriter().println("ALPHABETIC ARRAY:");
		
		for(Player player: playerManager.getPlayerArray()){
			
			trace.getTraceWriter().println(player.toString());
			
		}
		
	}

}
