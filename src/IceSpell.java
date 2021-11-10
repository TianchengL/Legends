/**
 * concrete class for ice spell
 */
public class IceSpell extends Spell{


    public IceSpell(String name, int price, int minLevel, int damage, int mana) {
        super(name, price, minLevel, damage, mana);
    }

    public SpellType getSpellType(){
        return SpellType.ICE_SPELL;
    }

    @Override
    public void cast(Hero hero, Character character) {
        hero.setMana(hero.getMana() - this.getMana());
        double damage = this.getDamageRange() + (hero.getDexterity()/10000)*this.getDamageRange();

        //enemy skill deterioration
        if(character instanceof Monster){
            int reduce = (int) (((Monster) character).getDamage() * 0.1);
            ((Monster) character).setDamage(((Monster) character).getDamage() - reduce);
            double realDamage = ((Monster) character).takeDamage(damage);
            System.out.println("You have casted a Ice Spell, Dealt " + (int)realDamage + " real damage " + "enemy " +
                    character.getName() + " defense " +
                    " reduced " + reduce + ".");
        }


    }

}
