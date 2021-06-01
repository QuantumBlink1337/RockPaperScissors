
/*
    This class defines the PlayerBracket object.
*/
import java.util.*;

public class PlayerBracket {
    private Player player1;
    private Player player2;
    private ArrayList<Player> bracket = new ArrayList<Player>();
    /*
        A PlayerBracket either has one Player object or two Player objects.
        Use this constructor if you're making one with two Player objects. 
    */
    public PlayerBracket(Player p1, Player p2) {
        player1 = p1;
        player2 = p2;
        bracket.add(player1);
        bracket.add(player2);
    }
    /*
        If you only have one Player object, use this constructor. 
    */
    public PlayerBracket(Player p1) {
        player1 = p1;
        bracket.add(player1);
    }
    /*
        Generic getter methods
    */

    public ArrayList<Player> getBracket() {
        return bracket;
    }
    public Player getPlayer1() {
        return player1;
    }
    public Player getPlayer2() {
        return player2;
    }
    /*
        This method compares the two Player object's Selections in a PlayerBracket object
        and returns the corresponding Condition.
        Precondition: The PlayerBracket object has two Player objects.
        Postcondition: A Condition enumerator is returned. 
    */
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
        return Conditions.IllegalPlayerBracketCompareException;
    }
}
