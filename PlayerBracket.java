import java.util.*;

public class PlayerBracket {
    private Player player1;
    private Player player2;
    private ArrayList<Player> bracket = new ArrayList<Player>();

    public PlayerBracket(Player p1, Player p2) {
        player1 = p1;
        player2 = p2;
        bracket.add(player1);
        bracket.add(player2);
    }
    public PlayerBracket(Player p1) {
        player1 = p1;
        bracket.add(player1);
    }
    public ArrayList<Player> getBracket() {
        return bracket;
    }
    public Player getPlayer1() {
        return player1;
    }
    public Player getPlayer2() {
        return player2;
    }

    public Conditions selectionCheck() {
        if (player1.getSelection() == Selections.ROCK) {
            if (player2.getSelection() == Selections.PAPER) {
                return Conditions.LOSS;
            }
            if (player2.getSelection() == Selections.SCISSORS) {
                return Conditions.WIN;
            }
            else {
                return Conditions.TIE;
            }
        }
        if (player1.getSelection() == Selections.PAPER) {
          if (player2.getSelection() == Selections.SCISSORS) {
              return Conditions.LOSS;
          }  
          if (player2.getSelection() == Selections.ROCK) {
              return Conditions.WIN;
          }
          else {
              return Conditions.TIE;
          }
        }
        if (player1.getSelection() == Selections.SCISSORS) {
            if (player2.getSelection() == Selections.ROCK) {
                return Conditions.LOSS;
            }
            if (player2.getSelection() == Selections.PAPER) {
                return Conditions.WIN;
            }
            else {
                return Conditions.TIE;
            }
        }
        return Conditions.Exception;
    }
}
