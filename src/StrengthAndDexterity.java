/**
 * concrete class
 * hero that favored in strength and dexterity should
 * init this class
 */
public class StrengthAndDexterity implements Skill
{
    @Override
    public double addStrength(double strength) {
        strength += strength * 0.1;
        return strength;
    }

    @Override
    public double addAgility(double agility) {
        agility += agility * 0.05;
        return agility;
    }

    @Override
    public double addDexterity(double dexterity) {
        dexterity += dexterity * 0.1;
        return dexterity;
    }
}