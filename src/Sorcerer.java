/**
 * concrete class sorcerer
 */
public class Sorcerer extends Hero{

    //favored skill is Dexterity and agility
    public Sorcerer(String name, double mana, double strength, double agility, double dexterity, int money, int exp) {
        super(name, mana, strength, agility,dexterity, money, exp);
        super.skill = new DexterityAndAgility();
    }

    @Override
    public HeroType getType() {
        return HeroType.SORCERERS;
    }


}
