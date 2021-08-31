/*
    This class defines the Player object. 
*/
package QuantumBlink.RPS;

import java.util.Scanner;
public class Player {
    private String name;
    private Selections selection;
    private int score = 0;
    private Scanner scanner = new Scanner(System.in);


    /*
        This boolean just exists so that the actual turn process is given to a human player but not a bot, as well as
        some other flavor text. 
    */
    private boolean isHuman;
    /*
        This initalizes all players to be tied. This allows for the Game object's tiedPlayers
        list to initially include all players for allowing turns and scoring.
    */ 
    private Conditions condition = Conditions.TIE; 

    // Use this constructor if you're making a human player. 
    public Player() {
        System.out.println("What's your name?");
        name = scanner.nextLine();
        isHuman = true; // Assumes that this player is a human if you're using this constructor
    }

    /* Use this constructor if making a bot player.
       *Technically* you can make human players with it, but the parameters are mostly 
       so a loop can generate names for a bot player. See Game's constructor. 
    */
    public Player(String Name, boolean humanity) {
        name = Name; // Allows for generated name insertion
        isHuman = humanity; // Since this can technically be used for humans, isHuman is left to the method call
    }


    // Getter methods

    public String getName() {
        return name;
    }
    public int getScore() {
        return score;
    }
    public Selections getSelection() {
        return selection;
    }
    public boolean getHumanStatus() {
        return isHuman;
    }
    public Conditions getCondition() {
        return condition;
    }



    // setters
    public void setName(String n) {
        name = n;
    }
    public void setScore(int n) {
        score = n;
    }
    public void setSelection(Selections s) {
        selection = s;
    }
    public void addScore() {
        score++;
    }
    public void setCondition(Conditions c) {
        condition = c;
    }

    /*
        A player is the same as another player if they have the same name.
        This probably won't be helpful if you have two players named the same thing but have different reference values.
        Currently unused in the program.

        Precondition: Both player objects are initialized.
        Postcondition: Returns true if they have the same name, and returns false if they don't. 
    */ 
    public boolean equals (Player player) {
        if (this.name.equals(player.name)) {
            return true;
        }
        return false;
    }
    /*
        This method plays a turn for a player. If invoked on a human player, it
        prompts them to make a selection. If they're a bot player, their selection is determined 
        by a random number generator. 

        Precondition: Player object is initialized. 
        Postcondition: Selection variable is initalized for the Player object. 
    */
    public void playerTurn() {
        if (this.getHumanStatus()) {
            System.out.println("Rock, paper, or scissors?");
            String playerSelection = scanner.nextLine().toLowerCase().replace(" ", ""); 
            switch (playerSelection) {
                case "rock": // To do - allow for "r", "p", "s"
                    this.setSelection(Selections.ROCK);
                    break;
                case "paper":
                    this.setSelection(Selections.PAPER);
                    break;
                case "scissors":
                    this.setSelection(Selections.SCISSORS);
                    break;
            }
        }
        else {
            int randomSelection = (int) (Math.random() * 2);
            // For debugging only
            //System.out.println("random selection: " + randomSelection);
            switch (randomSelection) {
                case 0:
                    this.setSelection(Selections.ROCK);
                    break;
                case 1:
                    this.setSelection(Selections.PAPER);
                    break;
                case 2:
                    this.setSelection(Selections.SCISSORS);
                    break;
                }
            }
        } 
    }

/*
    Written by Matt Marlow
*/ 