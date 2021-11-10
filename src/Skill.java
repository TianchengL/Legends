/**
 * strategy pattern for hero's skill which could inject into hero
 * class. Each concrete hero should implement the concrete skill according to their different
 * favored skill
 */
public interface Skill
{
    double addStrength(double p);
    double addAgility(double p);
    double addDexterity(double p);
}