import java.util.*;
public class Game {
    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<Player> tiedPlayers = new ArrayList<Player>();
    private ArrayList<Player> winList = new ArrayList<Player>();
    private ArrayList<Player> tieBreakerList = new ArrayList<Player>();
    private int turns = 0;
    private int currentTurn = 1;
    private boolean isWinner = false;
    private boolean isTieBreaker = false;
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
            if ((humanPlayers + botPlayers) > 1) {
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
        for (currentTurn = 1; currentTurn <= turns; currentTurn++) {
            if (currentTurn == turns) {
                winCalculation();
            }
            if (!isWinner && !isTieBreaker) {
                System.out.println("Round " + currentTurn);
                gameRun(players);
            }
            else if (!isWinner && isTieBreaker) {
                System.out.println("TIEBREAKER!!!");
                gameRun(tieBreakerList);
                isTieBreaker = false;
            }
        }
        if (currentTurn == turns+1 && !isWinner) {
            System.out.println("Tiebreaking calculation invoked");
            winCalculation();
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
            while (tiedPlayers.size() > 0) {
                turnSelection();
                scorer();
                for (int j = tiedPlayers.size() - 1; j >= 0; j--) {
                    if (tiedPlayers.get(j).getCondition() != Conditions.TIE) {
                        tiedPlayers.remove(j);
                    }
                }
        }
    }
    public void turnSelection() {
        for (Player player : tiedPlayers) {
            if (player.getHumanStatus()) {
                System.out.println(player.getName() + "'s Turn!");
            }
            player.playerTurn();
        }
    }
    public void winCalculation() {
        for (Player player : players) {
            if (player.getScore() == (turns - 1)) {
                winList.add(player);
            }
        }
        if (winList.size() > 0) {
            isWinner = true;
            for (Player player : winList) {
                System.out.println(player.getName() + " wins in the best of " + turns + " game!");
                isWinner = true;
            }
        }
        else {
            for (Player player : players) {

                if (player.getScore() == (turns - 2)) {
                    tieBreakerList.add(player);
                }
            }
            isTieBreaker = true;
        }
    }
    public void scorer() throws Exception {
        ArrayList<Player> unscoredPlayers = new ArrayList<Player>();
        ArrayList<PlayerBracket> scoringBracket = new ArrayList<PlayerBracket>();
        unscoredPlayers.addAll(tiedPlayers);
        while (unscoredPlayers.size() > 0) {
            int randomIndex = (int) (Math.random() * (unscoredPlayers.size() - 1));
            Player player1 = unscoredPlayers.get(randomIndex);
            unscoredPlayers.remove(randomIndex);
            randomIndex = (int) (Math.random() * (unscoredPlayers.size() - 1));
            try {
                Player player2 = unscoredPlayers.get(randomIndex);
                unscoredPlayers.remove(randomIndex);
                scoringBracket.add(new PlayerBracket(player1, player2));
            }
            catch (Exception e) {
                scoringBracket.add(new PlayerBracket(player1));
            }
        }
        for (int i = 0; i < scoringBracket.size(); i++) {
            if (scoringBracket.get(i).getBracket().size() == 2) {
                Conditions condition = scoringBracket.get(i).selectionCheck();
                switch (condition) {
                    case WIN:
                        System.out.println(scoringBracket.get(i).getPlayer1().getName() + " beats " + scoringBracket.get(i).getPlayer2().getName() + " in round " + currentTurn + "!");
                        scoringBracket.get(i).getPlayer1().addScore();
                        scoringBracket.get(i).getPlayer1().setCondition(Conditions.WIN);
                        scoringBracket.get(i).getPlayer2().setCondition(Conditions.LOSS);
                        //System.out.println("Loop execution: " + i);
                        break;
                    case LOSS:
                        System.out.println(scoringBracket.get(i).getPlayer1().getName() + " loses to " + scoringBracket.get(i).getPlayer2().getName() + " in round " + currentTurn + "!");
                        scoringBracket.get(i).getPlayer2().addScore();
                        scoringBracket.get(i).getPlayer2().setCondition(Conditions.WIN);
                        scoringBracket.get(i).getPlayer1().setCondition(Conditions.LOSS);
                        //System.out.println("Loop execution: " + i);
                        break;
                    case TIE:
                        System.out.println(scoringBracket.get(i).getPlayer1().getName() + " ties with " + scoringBracket.get(i).getPlayer2().getName() + " in round " + currentTurn + ". Their round will be replayed.");
                        scoringBracket.get(i).getPlayer1().setCondition(Conditions.TIE);
                        scoringBracket.get(i).getPlayer2().setCondition(Conditions.TIE);
                        //System.out.println("Loop execution: " + i);
                        break;
                    case Exception:
                        throw new Exception("Bad selection check at class PlayerBracket");
                }
            }
            else if (scoringBracket.get(i).getBracket().size() == 1) {
                System.out.println(scoringBracket.get(i).getPlayer1().getName() + " has a bye in round " + currentTurn + ".");
                scoringBracket.get(i).getPlayer1().addScore();
                scoringBracket.get(i).getPlayer1().setCondition(Conditions.WIN);
            }
        }
    }


}

