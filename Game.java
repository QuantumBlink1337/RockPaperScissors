/*
    This class defines the Game object.
*/

import java.util.*;
import java.util.concurrent.TimeUnit;
public class Game {
    /*
        All lists are initialized but empty to begin with. They'll be built over the course of the program. 
    /*
        players will be built by placing all Player objects created inside it. It should never be changed and
        acts as a reference for tiedPlayers, winList, and tieBreakerList to be built from.
    */ 
    private ArrayList<Player> players = new ArrayList<Player>(); 
    /*
        tiedPlayers will contain all players to start (recall that all Players start as Conditions.TIE) and as their conditions change, 
        they'll be removed from tiedPlayers. 
    */
    private ArrayList<Player> tiedPlayers = new ArrayList<Player>();
    /*
        At the final round, a for loop will run and check all Players score. If the score is high enough (detailed below) they'll be
        placed into winList. 
    */
    private ArrayList<Player> winList = new ArrayList<Player>();
    /*
        Similar in function to winList. A Player's score that is indictative of a tie will
        result in them being added to tieBreakerList.
    */
    private ArrayList<Player> tieBreakerList = new ArrayList<Player>();

    private int rounds = 0;
    private int currentRound = 1;
    /*
        Helper booleans to help loop flow.
    */
    private boolean isWinner = false;
    private boolean isTieBreaker = false;

    private Scanner scanner = new Scanner(System.in);

    public Game() throws Exception {
        boolean turnLock = false;
        /*
            Prompts user to define how many rounds they'd like their game to be.
            Loop breaks when user inputs an odd number. (Odd number allows for tiebreaker) 
        */
        while (!turnLock) {
            System.out.println("How many rounds do you want to play? Enter an odd number."); 
            int rounds = scanner.nextInt();
            if (rounds % 2 == 1) {
                turnLock = true;
                rounds = rounds;

            }
            else {
                System.out.println("You need to pick an odd number so there can be a tiebreaker round.");
            }
        }
        /*
            Runs a for loop to add to players for the amount the user specifies.
        */
        System.out.println("How many human players are playing?"); 
        int humanPlayers = scanner.nextInt(); 
        for (int i = 1; i <= humanPlayers; i++) {
            players.add(new Player()); // human constructor for Player
            TimeUnit.SECONDS.sleep(2);
            if (i < humanPlayers) {
                System.out.println("Next player!");
            }
        }
        boolean playerLock = false;



        int botPlayers = 0;
        while (!playerLock) {
            System.out.println("How many bots do you want in your game?");
            botPlayers = scanner.nextInt();
            /*
                Ensures a player cannot start a game with only one player.
                This isn't ran on human players because you could have 0 humans and 2 bots. 
            */
            if ((humanPlayers + botPlayers) > 1) {
                playerLock = true;
            }
            else {
                System.out.println("A game has to have more than 1 person!"); 
            }
        }
        /*
            Same thing as human players but for bots. 
        */ 
        for (int i = 1; i <= botPlayers; i++) {
            String botName = "Bot" + i;
            players.add(new Player(botName, false)); // bot constructor for Player
        }
        /*
            This is the main game execution. 
            Runs a for loop for as many rounds the user specified.
            It double checks to see if the current round is equal to total rounds and if so, immediately begins win calculation (short circuiting game running)
            Otherwise, provided that there's no tie and no winner found, the game is actually played with the total amount of players. 
            If there is a tie, then it runs the game with tieBreakerList built in winCalculation.
        */
        for (currentRound = 1; currentRound <= rounds; currentRound++) {
            if (currentRound == rounds) {
                winCalculation();
            }
            if (!isWinner && !isTieBreaker) {
                System.out.println("Round " + currentRound);
                gameRun(players);
            }
            else if (!isWinner && isTieBreaker) {
                System.out.println("TIEBREAKER!!!");
                gameRun(tieBreakerList);
                isTieBreaker = false;
            }
        }
        /*
            This calculates winning players in the event that a tiebreaking round was played. 
        */ 
        if (currentRound == rounds+1 && !isWinner) {
            //System.out.println("Tiebreaking calculation invoked");
            winCalculation();
        }
    }

    /*
        Generic getter methods
    */
    public int getPlayerCount() {
        return players.size();
    }
    public int getRoundCount() {
        return rounds;
    }
    /*
        This method builds a list of tiedPlayers from the original players list,
        then calls roundselection. After that, it uses scorer. Then, it
        evalutates the tiedPlayers list with a for loop and removes players from it 
        that have their condition to something other than TIE. 
        This process continues until there are no remaining Players in tiedPlayers. 
    */
    public void gameRun(ArrayList<Player> p) throws Exception {
            tiedPlayers.addAll(p);
            while (tiedPlayers.size() > 0) {
                roundselection();
                scorer();
                for (int j = tiedPlayers.size() - 1; j >= 0; j--) {
                    if (tiedPlayers.get(j).getCondition() != Conditions.TIE) {
                        tiedPlayers.remove(j);
                    }
                }
        }
    }
    /*
        This method allows for both human and bot players to make their turn. 
        It checks all players in tiedPlayers and calls .playerTurn on them from the Player class.
        If they're a human player, they have some text prompting them to make a selection as well as
        print statements to block out the console log to prevent screen peeking.  
    */
    public void roundselection() throws InterruptedException {
        for (Player player : tiedPlayers) {
            if (player.getHumanStatus()) {
                System.out.println(player.getName() + "'s Turn!");
                player.playerTurn();
                for (int i = 0; i < 40; i++) { // 40 times is arbitrary, change depending on preference and/or screen size/resolution
                    System.out.println("");
                }
            }
            else {
                player.playerTurn();
            }
        }
    }
    public void winCalculation() {
        for (Player player : players) {
            if (player.getScore() == (rounds - 1)) {
                winList.add(player);
            }
        }
        if (winList.size() > 0) {
            isWinner = true;
            for (Player player : winList) {
                System.out.println(player.getName() + " wins in the best of " + rounds + " game!");
                isWinner = true;
            }
        }
        else {
            for (Player player : players) {

                if (player.getScore() == (rounds - 2)) {
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
            System.out.println("Random Index at player1 " + randomIndex);
            Player player1 = unscoredPlayers.get(randomIndex);
            unscoredPlayers.remove(randomIndex);
            try {
                randomIndex = (int) (Math.random() * (unscoredPlayers.size() - 1));
                System.out.println("Random Index at player2 " + randomIndex);
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
                        System.out.println(scoringBracket.get(i).getPlayer1().getName() + " beats " + scoringBracket.get(i).getPlayer2().getName() + " in round " + currentRound + "!");
                        scoringBracket.get(i).getPlayer1().addScore();
                        scoringBracket.get(i).getPlayer1().setCondition(Conditions.WIN);
                        scoringBracket.get(i).getPlayer2().setCondition(Conditions.LOSS);
                        //System.out.println("Loop execution: " + i);
                        break;
                    case LOSS:
                        System.out.println(scoringBracket.get(i).getPlayer1().getName() + " loses to " + scoringBracket.get(i).getPlayer2().getName() + " in round " + currentRound + "!");
                        scoringBracket.get(i).getPlayer2().addScore();
                        scoringBracket.get(i).getPlayer2().setCondition(Conditions.WIN);
                        scoringBracket.get(i).getPlayer1().setCondition(Conditions.LOSS);
                        //System.out.println("Loop execution: " + i);
                        break;
                    case TIE:
                        System.out.println(scoringBracket.get(i).getPlayer1().getName() + " ties with " + scoringBracket.get(i).getPlayer2().getName() + " in round " + currentRound + ". Their round will be replayed.");
                        scoringBracket.get(i).getPlayer1().setCondition(Conditions.TIE);
                        scoringBracket.get(i).getPlayer2().setCondition(Conditions.TIE);
                        //System.out.println("Loop execution: " + i);
                        break;
                    case Exception:
                        throw new Exception("Bad selection check at class PlayerBracket");
                }
            }
            else if (scoringBracket.get(i).getBracket().size() == 1) {
                System.out.println(scoringBracket.get(i).getPlayer1().getName() + " has a bye in round " + currentRound + ".");
                scoringBracket.get(i).getPlayer1().addScore();
                scoringBracket.get(i).getPlayer1().setCondition(Conditions.WIN);
            }
        }
    }


}

