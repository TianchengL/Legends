/**
 * concrete class for warrior
 */
public class Warrior extends Hero{

    //favored skill is strength and agility
    public Warrior(String name, double mana, double strength, double agility, double dexterity, int money, int exp) {
        super(name, mana, strength, agility,dexterity, money, exp);
        super.skill = new StrengthAndAgility();
    }

    @Override
    public HeroType getType() {
        return HeroType.WARRIORS;
    }



}
