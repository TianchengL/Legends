/**
 * concrete class for Paladin
 */
public class Paladin extends Hero{

    //favored skill is strength and dexterity
    public Paladin(String name, double mana, double strength, double agility, double dexterity, int money, int exp) {
        super(name, mana, strength, agility,dexterity, money, exp);
        super.skill = new StrengthAndDexterity();
    }

    @Override
    public HeroType getType() {
        return HeroType.PALADINS;
    }


}
