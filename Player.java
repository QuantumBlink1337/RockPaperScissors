import java.util.Scanner;
public class Player {
    private String name;
    private Selections selection;
    private int score = 0;
    private Scanner scanner = new Scanner(System.in);
    private boolean isHuman;
    // stores final selection values
    public enum Selections {
        ROCK,
        PAPER,
        SCISSORS
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

    // make a selection
    public void playerTurn() {
        if (this.getHumanStatus()) {
            System.out.println("Rock, paper, or scissors?");
            String playerSelection = scanner.nextLine().toLowerCase().replace(" ", "");
            System.out.println(playerSelection);
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
        else {
            int randomSelection = (int) (Math.random() * 2);
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

