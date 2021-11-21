/**
 * concrete class for lightning spell
 */
public class LightingSpell extends Spell{


    public LightingSpell(String name, int price, int minLevel, int damage, int mana) {
        super(name, price, minLevel, damage, mana);
    }

    public SpellType getSpellType(){
        return SpellType.LIGHTNING_SPELL;
    }


    @Override
    public boolean cast(Hero hero, Character character) {
        if(hero.getMana() < this.getMana())
            return false;
        hero.setMana(hero.getMana() - this.getMana());
        double damage = this.getDamageRange() + (hero.getDexterity()/10000)*this.getDamageRange();

        //enemy skill deterioration
        if(character instanceof Monster){
            int reduce = (int) (((Monster) character).getDodgeChance() * 0.1);
            ((Monster) character).setDodgeChance(((Monster) character).getDodgeChance() - reduce);
            double realDamage = ((Monster) character).takeDamage(damage);
            System.out.println("You have casted a Light Spell, Dealt " + (int)realDamage + " real damage. "
                    + "enemy " +  character.getName() + " dodge chance " +
                    " reduced " + reduce + ".");
        }

        return true;
    }
}
