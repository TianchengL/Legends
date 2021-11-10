/**
 * castable interface for spell cast,
 * needs to be implemented by each spell concrete class
 */
public interface castable
{
    void cast(Hero hero, Character c);
}