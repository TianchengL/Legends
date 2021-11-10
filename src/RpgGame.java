import java.util.Scanner;

/**
 * extends Game
 * Rpg game needs to have fight, trading and makeMove method
 */
public abstract class RpgGame extends Game
{
    abstract void fight(final Scanner p0);

    abstract void trading(final Scanner p0, final Hero p1);

    abstract void makeMove(final Cell[][] p0, final int p1, final int p2);
}