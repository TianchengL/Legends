import java.util.Scanner;


/**
 * Main class for the Legend game
 */
public class Main
{
    public static void main(final String[] args) {
        final Scanner input = new Scanner(System.in);
        System.out.println(Message.welcome);
        System.out.println(Message.hero+Message.vs+Message.monster);
        System.out.println("Welcome To Legends!");
        String filepath = "ConfigFiles/backGroundMusic.wav";
        Audio musicObject = new Audio();
        musicObject.playMusic(filepath);
        final HeroSelectionController hs = new HeroSelectionController(input);
        final GameController g = new GameController(input, hs);
        g.playGame();
    }
}