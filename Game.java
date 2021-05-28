import java.util.*;
public class Game {
    private ArrayList<Player> players = new ArrayList<Player>();
    private int turns = 0;
    private Scanner scanner = new Scanner(System.in);
    public Game() {
        boolean turnLock = false;
        while (turnLock) {
            System.out.println("Best out of..."); 
            int Turns = scanner.nextInt();
            if (Turns % 2 == 1) {
                turnLock = true;
                turns = Turns;

            }
            else {
                System.out.println("You need to pick an odd number so there can be a tiebreaker round.");
            }
        }

        System.out.println("How many human players are playing?"); 
        int humanPlayers = scanner.nextInt();
        for (int i = 1; i <= humanPlayers; i++) {
            players.add(new Player());
            if (i < 3) {
                System.out.println("Next player!");
            }
        }
        boolean playerLock = false;
        int botPlayers = 0;
        while (!playerLock) {
            System.out.println("How many bots do you want in your game?");
            botPlayers = scanner.nextInt();
            if (botPlayers >= players.size()) {
                playerLock = true;
            }
            else {
                System.out.println("A game has to have more than 1 person!"); 
            }
        }
        for (int i = 1; i <= botPlayers; i++) {
            String botName = "Bot" + i;
            players.add(new Player(botName, false));
        }

    }
    public int getPlayerCount() {
        return players.size();
    }
    public int getTurnCount() {
        return turns;
    }
    public void turnSelection() {
        for (Player player : players) {
            if (player.getHumanStatus()) {
                player.playerTurn();
            }
            
        }
    }
}
