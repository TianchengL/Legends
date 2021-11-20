import java.util.Scanner;

/**
 * util class for the purpose of checking user valid input
 */
public class UtilCheckInput
{
    public static String checkInput(Scanner input,  int lowerBound,  int upperBound) {
        String s;
        for (s = input.next(); !checkValid(s, lowerBound, upperBound); s = input.next()) {
            System.out.println("Please select a valid number!");
        }
        return s;
    }

    public static Boolean checkValid( String input,  int lowerBound,  int upperBound) {
        int in;
        try {
            in = Integer.parseInt(input);
        }
        catch (NumberFormatException e) {
            return false;
        }
        return in >= lowerBound && in <= upperBound;
    }

    public static boolean checkBorder(int row, int col) {
        return row < 8 && row >= 0 && col < 8 && col >= 0;
    }
}