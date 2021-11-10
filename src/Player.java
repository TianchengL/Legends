/**
 * abstraction of player
 *
 */
public abstract class Player
{
    public String name;
    public int ID;

    public Player( String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "P";
    }
}