import java.util.*;
public class Game {
    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<Player> tiedPlayers = new ArrayList<Player>();
    private int turns = 0;
    private int currentTurn = 1;
    private Scanner scanner = new Scanner(System.in);

    public Game() throws Exception {
        boolean turnLock = false;
        while (!turnLock) {
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
            if (i < humanPlayers) {
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
        tiedPlayers = players;
        for (int i = 1; i <= turns; i++) {
            while (tiedPlayers.size() > 1) {
                turnSelection();
                winCheck();
            }
            currentTurn++;
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
            player.playerTurn();
        }
    }
    public void winCheck() throws Exception {
        for (int k = 0; k < players.size() - 1; k++) {
            for (int j = 0; j < players.size(); j++) {
                String condition = players.get(k).selectionCheck(players.get(j));
                if (!players.get(k).equals(players.get(j))) {
                switch (condition) {
                    case "Win":
                        System.out.println(players.get(k).getName() + " beats " + players.get(j).getName() + " in round " + currentTurn + "!");
                        players.get(k).addScore();
                        break;
                    case "Loss":
                        System.out.println(players.get(j).getName() + " beats " + players.get(k).getName() + " in round " + currentTurn + "!");
                        players.get(j).addScore();
                        break;
                    case "Tie":
                        System.out.println(players.get(j).getName() + " ties with " + players.get(k).getName() + " in round " + currentTurn + "!");
                        break;
                    case "Exception":
                        throw new Exception ("Bad selection check");
                    }
                }
            }
        }
    }
}
