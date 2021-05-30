import java.util.Scanner;
public class Player {
    private String name;
    private Selections selection;
    private int score = 0;
    private Scanner scanner = new Scanner(System.in);
    private boolean isHuman;
    private Conditions condition = Conditions.TIE;
    // stores final selection values
    
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

