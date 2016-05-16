import java.util.Scanner;


public class GameManager {
	
	Scanner scanner;
	PlayerManager playerManager;
	Trace trace;
	
	public GameManager (Scanner scanner, Trace trace, PlayerManager playerManager){
		
		this.scanner = scanner;
		this.playerManager = playerManager;
		this.trace = trace;
		//gameBoard =  TODO: get from binary file
		
	}

}
