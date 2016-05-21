
public abstract class Player {
	
	// Constants
	private static final int PERCENTAGE_MULTIPLIER = 100;
	private static final int INITIALIZE_TO_ZERO = 0;
	private static final int NO_GAMES_PLAYED = 0;
	private static final String INITIALIZE_TO_NULL = null;
	
	
	// Instance variables
	protected String userName;
	protected String familyName;
	protected String givenName;
	protected int gamesPlayed;
	protected int gamesWon;
	protected int gamesDrawn;
	protected char symbol;
	private float winningRatio;
	private float drawingRatio;
	
	// Create move object
	Move move;
	
	// Copy constructor
	public Player(Player player) {
		this.userName = player.userName;
		this.familyName = player.familyName;
		this.givenName = player.givenName;
		this.gamesPlayed = player.gamesPlayed;
		this.gamesWon = player.gamesWon;
		this.gamesDrawn = player.gamesDrawn;
		this.winningRatio = player.winningRatio;
		this.drawingRatio = player.drawingRatio;
	}
	
	// New player constructor
	public Player(String userName, String familyName, String givenName) {
		
		this.userName = userName;
		this.familyName = familyName;
		this.givenName = givenName;
		gamesPlayed = INITIALIZE_TO_ZERO;
		gamesWon = INITIALIZE_TO_ZERO;
		gamesDrawn = INITIALIZE_TO_ZERO;
		updateRatios();
		
	}
	
	// No argument constructor
	public Player() {
		
		userName = INITIALIZE_TO_NULL;
		familyName = INITIALIZE_TO_NULL;
		givenName = INITIALIZE_TO_NULL;
		gamesPlayed = INITIALIZE_TO_ZERO;
		gamesWon = INITIALIZE_TO_ZERO;
		gamesDrawn = INITIALIZE_TO_ZERO;
		updateRatios();
		
	}

	// Accessors and Mutators
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public int getGamesPlayed() {
		return gamesPlayed;
	}
	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}
	public int getGamesWon() {
		return gamesWon;
	}
	public void setGamesWon(int gamesWon) {
		this.gamesWon = gamesWon;
	}
	public int getGamesDrawn() {
		return gamesDrawn;
	}
	public void setGamesDrawn(int gamesDrawn) {
		this.gamesDrawn = gamesDrawn;
	}
	public char getSymbol() {
		return symbol;
	}
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
	public float getDrawingRatio() {
		return drawingRatio;
	}
	public void setDrawingRatio(float drawingRatio) {
		this.drawingRatio = drawingRatio;
	}
	public float getWinningRatio() {
		return winningRatio;
	}
	public void setWinningRatio(float winningRatio) {
		this.winningRatio = winningRatio;
	} 
	public Move getMove(){
		return this.move;
	}
	public void setMove(Move move){
		this.move = move;
	}
	
	
	// Caclulate winningRatio and drawingRatio
	public void updateRatios(){
		
		if(gamesPlayed == NO_GAMES_PLAYED){
			
			winningRatio = NO_GAMES_PLAYED;
			drawingRatio = NO_GAMES_PLAYED;
			return;
			
		}
		
		winningRatio = PERCENTAGE_MULTIPLIER * gamesWon / (float) gamesPlayed;
		drawingRatio = PERCENTAGE_MULTIPLIER * gamesDrawn / (float) gamesPlayed;
		
	}
	
	
	// Reset stats
	public void resetStats() {
		
		gamesPlayed = INITIALIZE_TO_ZERO;
		gamesWon = INITIALIZE_TO_ZERO;
		gamesDrawn = INITIALIZE_TO_ZERO;
		updateRatios();
		
	}
	
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj == null){
			
			return false;
		
		} else if(obj instanceof String){
			
			String other = (String) obj;	
			return this.userName.equals(other);
		
		} else if(getClass() != obj.getClass()){
			
			return false;
		
		} else {
			
			Player other = (Player) obj;
			return this.userName.equals(other.getUserName());
			
		}
		
	}
	
	@Override
	public String toString() {
		
		return String.format("%12s:%6.2f:%6.2f   %12s,%12s,%3d,%3d,%3d", userName, winningRatio, drawingRatio, familyName, givenName, gamesPlayed, gamesWon, gamesDrawn);
		
	}
	
	// Make move method
	public abstract void makeMove();
	
}
