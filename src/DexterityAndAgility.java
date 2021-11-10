/**
 * concrete class for Skill which could be injected
 * to needed classes as strategy pattern. Skill favored on dexterity and agility
 */
public class DexterityAndAgility implements Skill
{
    public double addStrength(double strength) {
        strength += strength * 0.05;
        return strength;
    }

    public double addAgility(double agility) {
        agility += agility * 0.1;
        return agility;
    }

    public double addDexterity(double dexterity) {
        dexterity += dexterity * 0.1;
        return dexterity;
    }
}