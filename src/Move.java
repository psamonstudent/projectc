
public class Move {
	
	// Instance variables
	private int row;
	private int col;
	
	// Constructor
	public Move(int row, int col){
		this.row = row;
		this.col = col;
	}
	
	// Accessors and Mutators
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	
}
