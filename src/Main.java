import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.Scanner;

/**
 * Main class for the Legend game
 */
public class Main
{
    public static void main(final String[] args) {
        final Scanner input = new Scanner(System.in);
        System.out.println(Message.welcome);
        System.out.println("Welcome To Legends!");
        String filepath = "backGroundMusic.wav";
//        Audio musicObject = new Audio();
//        musicObject.playMusic(filepath);
        final HeroSelectionController hs = new HeroSelectionController(input);
        final GameController g = new GameController(input, hs);
        System.out.println(Message.fight);
        g.playGame();
    }
}