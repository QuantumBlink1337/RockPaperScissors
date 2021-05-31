/*
    This is the main method of the program. 
    It greets the user, creates a Game object within a while loop, and allows for the
    user to play another game. 
*/ 

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Driver {
    public static void main (String args[]) throws Exception {
        boolean repeat = false;
        Scanner sc = new Scanner(System.in);
        /*
            This will run for as long as the user doesn't terminate the loop by
            saying no.
        */
        while (!repeat) {
            System.out.println("Welcome to Super Rock Paper Scissors!");
            TimeUnit.SECONDS.sleep(3);
            Game game = new Game();
            game.roundHandler();
            TimeUnit.SECONDS.sleep(5);
            System.out.println("Play again? Yes or no.");
            String choice = sc.nextLine().toLowerCase().replace(" ", "");
            if (choice.equals("no")) {
                System.out.println("Thanks for playing!");
                repeat = true;
            }
        }
        sc.close();
    }
}