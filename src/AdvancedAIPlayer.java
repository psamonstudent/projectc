
public class AdvancedAIPlayer extends Player {
	
	private static final char PLAYER_ONE_SYMBOL = 0;

	public AdvancedAIPlayer(){
		/* do not change this method, make sure to add
		   public Player() to Player class as well
		 */
	}

	@Override
	public Move makeMove(char[][] gameBoard) {
		// TODO Auto-generated method stub
		return null;
	} 
	
/*	private char[][] savedGridState = new char[GRID_SIZE][GRID_SIZE];
	private int 
	
	@Override
	public Move makeMove(char[][] gameBoard) {

		// TODO:
		trace.traceToFile(Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
		
		// Print players move
		System.out.print(givenName + "'s move:\n");
		
		
		if(symbol == PLAYER_ONE_SYMBOL){
			
			// Advanced AI Player is player one
			if(numberOfMoves == 0){
				
				numberOfMoves++;
				return new Move(1,1);
				
			}
			
			
		} else {
			
			// Advanced AI Player is player two
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		for(int index = INITIALIZE_TO_ZERO; index < NUMBER_OF_CELLS; index++){
			
			move = new Move(row,col);
			
			if(gameBoard[row][col] == EMPTY_CELL){
				
				return new Move(row, col);
				
			}
			
			if(col == 2){
				
				row++;
				col = INITIALIZE_TO_ZERO;
				
			}
			
			col++;
			
		}
		
		// TODO
		return null;
		
		
		
		
	}*/
	
}
