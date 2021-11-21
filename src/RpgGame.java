import java.util.Scanner;

/**
 * extends Game
 * Rpg game needs to have fight, trading and makeMove method
 */
public abstract class RpgGame extends Game
{
    //abstract void attack(Scanner p0);

    abstract void trading(Scanner p0, Hero p1);

    //abstract void makeMove(PlayerTeam playerTeam, Cell[][] p0, Hero hero, int p1, int p2);
}