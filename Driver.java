import java.util.*;
import java.util.concurrent.TimeUnit;

public class Driver {
    public static void main (String args[]) throws Exception {
        boolean repeat = false;
        Scanner sc = new Scanner(System.in);
        while (!repeat) {
            System.out.println("Welcome to Super Rock Paper Scissors!");
            TimeUnit.SECONDS.sleep(3);
            Game game = new Game();
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