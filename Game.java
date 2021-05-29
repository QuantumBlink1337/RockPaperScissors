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
        // Game excutation
        for (int i = 1; i <= turns; i++) {
            // System.out.println("Size of tiedplayers before while loop " + tiedPlayers.size());
            // System.out.println("Size of players before while loop " + players.size());
            // while (tiedPlayers.size() > 1) {
            //     turnSelection();
            //     winCheck(tiedPlayers);
            //     System.out.println("Size of tiedplayers " + tiedPlayers.size());
            //     for (int j = tiedPlayers.size() - 1; j >= 0; j--) {
            //         System.out.println("player condition " + tiedPlayers.get(j).getCondition());
            //         if (tiedPlayers.get(j).getCondition() != Conditions.TIE); {
            //             System.out.println("Removed player " + tiedPlayers.get(j).getName());
            //             tiedPlayers.remove(j);
            //         }
            //     }
            //     System.out.println("Size of tiedplayers after tie check" + tiedPlayers.size());
            // }
            gameRun(players);
            currentTurn++;
        }
    }
    public int getPlayerCount() {
        return players.size();
    }
    public int getTurnCount() {
        return turns;
    }
    public void gameRun(ArrayList<Player> p) throws Exception {
            tiedPlayers.addAll(p);
            System.out.println("Size of tiedplayers before while loop " + tiedPlayers.size());
            System.out.println("Size of players before while loop " + players.size());
            while (tiedPlayers.size() > 1) {
                turnSelection();
                winCheck(tiedPlayers);
                System.out.println("Size of tiedplayers " + tiedPlayers.size());
                for (int j = tiedPlayers.size() - 1; j >= 0; j--) {
                    System.out.println("player condition " + tiedPlayers.get(j).getCondition());
                    if (tiedPlayers.get(j).getCondition() != Conditions.TIE); {
                        System.out.println("Removed player " + tiedPlayers.get(j).getName());
                        tiedPlayers.remove(j);
                    }
                }
                System.out.println("Size of tiedplayers after tie check" + tiedPlayers.size());
        }
    }
    public void turnSelection() {
        for (Player player : tiedPlayers) {
            player.playerTurn();
        }
    }
    public void winCheck(ArrayList<Player> t) throws Exception {
        ArrayList<Player> unscoredPlayers = new ArrayList<Player>();
        unscoredPlayers.addAll(t);
        for (int k = 0; k < unscoredPlayers.size() - 1; k++) {
            for (int j = 0; j < unscoredPlayers.size(); j++) {
                Conditions condition = unscoredPlayers.get(k).selectionCheck(unscoredPlayers.get(j));
                if (!unscoredPlayers.get(k).equals(unscoredPlayers.get(j))) {
                switch (condition) {
                    case WIN:
                        System.out.println(unscoredPlayers.get(k).getName() + " beats " + unscoredPlayers.get(j).getName() + " in round " + currentTurn + "!");
                        players.get(k).addScore();
                        unscoredPlayers.get(j).setCondition(Conditions.LOSS);
                        unscoredPlayers.get(k).setCondition(Conditions.WIN);
                        break;
                    case LOSS:
                        System.out.println(unscoredPlayers.get(k).getName() + " loses to " + unscoredPlayers.get(j).getName() + " in round " + currentTurn + "!");
                        players.get(j).addScore();
                        unscoredPlayers.get(j).setCondition(Conditions.WIN);
                        unscoredPlayers.get(k).setCondition(Conditions.LOSS);
                        break;
                    case TIE:
                        System.out.println(unscoredPlayers.get(j).getName() + " ties with " + unscoredPlayers.get(k).getName() + " in round " + currentTurn + "!");
                        break;
                    case Exception:
                        throw new Exception ("Bad selection check");
                    }
                }
            }
        }
    }
}
