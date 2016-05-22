
public class GameBoard {
	
	private static final int GRID_SIZE = 3;
	public static final int LAST_ROW = 2;
	public static final int LAST_COL = 2;

	private static final int INITIALIZE_TO_ZERO = 0;

	private static final char EMPTY_CELL = ' ';
	
	private char[][] grid = new char[GRID_SIZE][GRID_SIZE];
	
	// Constructor 
	public GameBoard(){
		
		initializeGrid();
		
	}
	
	public char[][] getGrid(){
		
		return grid;
		
	}
	
	public char checkGrid(int row, int col){
		
		return grid[row][col];
		
	}
	
	public void setGrid(Move move, char symbol){
		
		grid[move.getRow()][move.getCol()] = symbol;
		
	}
	
	public void initializeGrid(){
		
		for(int row = INITIALIZE_TO_ZERO; row < GRID_SIZE; row++){		
			
			for(int col = INITIALIZE_TO_ZERO; col < GRID_SIZE; col++){
				
				//Initialize cell to space character
				grid[row][col] = EMPTY_CELL;
				
			}
			
		}
		
	}
	
	public void printGrid(){
		
		//Iterate through each row
		for(int row = INITIALIZE_TO_ZERO; row < GRID_SIZE; row++){			
		
			//Iterate through each column
			for(int col = INITIALIZE_TO_ZERO; col < GRID_SIZE; col++){
				
				//Print cell value
				System.out.print(grid[row][col]);
				
				//If column 0 or 1 Print vertical line
				if(col < LAST_COL)
					
					System.out.print("|");
					
			}
			
			//If row 0 or 1 Print horizontal line
			if(row < LAST_ROW)
				
				System.out.print("\n-----\n");
		}
		
		//Print newline at end of grid
		System.out.print("\n");
		
	}
	
}
