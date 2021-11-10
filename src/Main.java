import java.util.Scanner;

/**
 * Main class for the Legend game
 */
public class Main
{
    public static void main(final String[] args) {
        final Scanner input = new Scanner(System.in);
        System.out.println("Welcome To Legends!");
        final HeroSelectionController hs = new HeroSelectionController(input);
        final GameController g = new GameController(input, hs);
        g.playGame();
    }
}