import java.util.Scanner;
public class Player {
    private String name;
    private int selection = 0;
    private int score = 0;
    private Scanner scanner = new Scanner(System.in);


    // if being constructed by actual user
    public Player() {
        System.out.println("What's your name?");
        name = scanner.nextLine();
    }

    // if being constructed by game object (for bot)
    public Player(String Name) {
        name = Name;
    }


    // getters
    public String getName() {
        return name;
    }
    public int getScore() {
        return score;
    }
    public int getSelection() {
        return selection;
    }



    // setters
    public void setName(String n) {
        name = n;
    }
    public void setScore(int n) {
        score = n;
    }
    public void setSelection(int s) {
        selection = s;
    }

    // make a selection
    public void playerTurn() {
        System.out.println("Rock, paper, or scissors?");
        String playerSelection = scanner.nextLine().toLowerCase().replace(" ", "");
        // System.out.println(playerSelection);
        switch (playerSelection) {
            case "rock":
                this.setSelection(0);
            case "paper":
                this.setSelection(1);
            case "scissors":
                this.setSelection(2);
        }
    }
    
}
