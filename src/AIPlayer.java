
public class AIPlayer extends Player {

	public AIPlayer(Player player, Trace trace, GameBoard gameBoard) {
		super(player, trace, gameBoard);
	}
	
	public AIPlayer(Player player, Trace trace) {
		super(player, trace);
	}
	
	public AIPlayer(String userName, String familyName, String givenName, Trace trace){
		super(userName, familyName, givenName, trace);
	}

	//@Override
	public void makeMove() {

		
		trace.traceToFile(Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
		
		// Print players move
		System.out.print(givenName + "'s move:\n");
		
		int col = INITIALIZE_TO_ZERO;
		int row = INITIALIZE_TO_ZERO;
		
		
		for(int index = INITIALIZE_TO_ZERO; index < NUMBER_OF_CELLS; index++){
			
			move = new Move(row,col);
			
			if(gameBoard.checkGrid(move.getRow(), move.getCol()) == EMPTY_CELL){
				
				gameBoard.setGrid(move.getRow(), move.getCol(), symbol);
				return;
				
			}
			
			if(col == 2){
				
				row++;
				col = INITIALIZE_TO_ZERO;
				
			}
			
			col++;
			
		}
		
	}
	
}
