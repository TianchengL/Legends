/**
 * concrete class for fire spell
 */
public class FireSpell extends Spell{


    public FireSpell(String name, int price, int minLevel, int damage, int mana) {
        super(name, price, minLevel, damage, mana);
    }

    //cast fire spell
    //calculate damage according to formula and deteriorate enemy defense
    @Override
    public boolean cast(Hero hero, Character character) {
        if(hero.getMana() < this.getMana())
            return false;

        hero.setMana(hero.getMana() - this.getMana());
        double damage = this.getDamageRange() + (hero.getDexterity()/10000)*this.getDamageRange();

        //enemy skill deterioration
        if(character instanceof Monster){
            int reduce = (int) (((Monster) character).getDefenseStats() * 0.1);
            ((Monster) character).setDefenseStats(((Monster) character).getDefenseStats() - reduce);
            double realDamage = ((Monster) character).takeDamage(damage);
            System.out.println("You have casted a Fire Spell, Dealt " + (int)realDamage + " real damage. " + "enemy "
                    + character.getName() + " defense "
                            + " reduced " + reduce + ".");
        }

        return true;
    }

    public SpellType getSpellType(){
        return SpellType.FIRE_SPELL;
    }
}
