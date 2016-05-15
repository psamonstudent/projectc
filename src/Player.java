
public abstract class Player {
	
	// Constants
	private static final int PERCENTAGE_MULTIPLIER = 100;
	
	// Instance variables
	protected String userName;
	protected String familyName;
	protected String givenName;
	protected int gamesPlayed;
	protected int gamesWon;
	protected int gamesDrawn;
	private float winningRatio;
	private float drawingRatio;
	
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
	
	// Caclulate winningRatio and drawingRatio
	public void updateRatios(){
		
		winningRatio = PERCENTAGE_MULTIPLIER * gamesWon / (float) gamesPlayed;
		drawingRatio = PERCENTAGE_MULTIPLIER * gamesDrawn / (float) gamesPlayed;
		
	}
	
	// Make move method
	public abstract void makeMove();
	
}
