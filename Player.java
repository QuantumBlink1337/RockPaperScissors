import java.util.Scanner;
public class Player {
    private String name;
    private Selections selection;
    private int score = 0;
    private Scanner scanner = new Scanner(System.in);
    private boolean isHuman;
    private Conditions condition = Conditions.TIE;
    // stores final selection values
    public enum Selections {
        ROCK,
        PAPER,
        SCISSORS;
    }
    // if being constructed by actual user
    public Player() {
        System.out.println("What's your name?");
        name = scanner.nextLine();
        isHuman = true;
    }

    // if being constructed by game object (for bot)
    public Player(String Name, boolean humanity) {
        name = Name;
        isHuman = humanity;
    }


    // getters
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


    public boolean equals (Player player) {
        if (this.name.equals(player.name)) {
            return true;
        }
        return false;
    }


    public Conditions selectionCheck (Player player) {
        if (this.selection == Selections.ROCK) {
            if (player.selection == Selections.PAPER) {
                return Conditions.LOSS;
            }
            if (player.selection == Selections.SCISSORS) {
                return Conditions.WIN;
            }
            else {
                return Conditions.TIE;
            }
        }
        if (this.selection == Selections.PAPER) {
          if (player.selection == Selections.SCISSORS) {
              return Conditions.LOSS;
          }  
          if (player.selection == Selections.ROCK) {
              return Conditions.WIN;
          }
          else {
              return Conditions.TIE;
          }
        }
        if (this.selection == Selections.SCISSORS) {
            if (player.selection == Selections.ROCK) {
                return Conditions.LOSS;
            }
            if (player.selection == Selections.PAPER) {
                return Conditions.WIN;
            }
            else {
                return Conditions.TIE;
            }
        }
        return Conditions.Exception;
    }
    // make a selection
    /*
        Post-Condition: selection variable is initialized for a player object
    */
    public void playerTurn() {
        if (this.getHumanStatus()) {
            System.out.println("Rock, paper, or scissors?");
            String playerSelection = scanner.nextLine().toLowerCase().replace(" ", "");
            switch (playerSelection) {
                case "rock":
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
        // allows for a bot to make a selection, defined by random numbers
        else {
            int randomSelection = (int) (Math.random() * 2);
            System.out.println("random selection: " + randomSelection);
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

