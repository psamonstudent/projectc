
public class RandomPlayer extends Player{
	
	// Constants
	private static final int A = 97;
	private static final int Z = 122;
	private static final int MIN_CHARS = 5;
	private static final int MAX_CHARS = 12;
	private static final int MIN_GAMES = 0;
	private static final int MAX_GAMES = 99;
	private static final int INITIALIZE_TO_ZERO = 0;

	public RandomPlayer() {
		
		userName = randomName();
		familyName = randomName();
		givenName = randomName();
		gamesPlayed = randomInt(MIN_GAMES, MAX_GAMES);
		gamesWon = randomInt(MIN_GAMES, gamesPlayed);
		gamesDrawn = randomInt(MIN_GAMES, (gamesPlayed - gamesWon));
		updateRatios();
		
	}
	
	// Create random name
	public String randomName(){
		
		int length = randomInt(MIN_CHARS, MAX_CHARS);
		
		char[] name = new char[length];
		
		for(int index = INITIALIZE_TO_ZERO; index < length; index++){
			
			name[index] = (char) randomInt(A, Z);
			
		}
		
		return new String(name);
		
	}
	
	// Create random integer
	public int randomInt(int min, int max){
		
		return (int) (min + Math.floor(Math.random() * (max - min + 1)));
		
	}
	
	@Override
	public void makeMove() {
		// TODO Auto-generated method stub
		
	}
	
	

}
