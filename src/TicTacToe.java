import java.util.Scanner;


public class TicTacToe {
	
	Scanner scanner = new Scanner(System.in);
	PlayerManager playerManager = new PlayerManager();
	GameManager gameManager = new GameManager(scanner, playerManager);
	
	

}
