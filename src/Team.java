/**
 * abstraction of player
 *
 */
public abstract class Team
{
    public String name;
    public int ID;

    public Team( String name) {
        this.name = name;
    }
    public Team(){

    }

    @Override
    public String toString() {
        return "T";
    }
}