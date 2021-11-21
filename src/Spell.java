/**
 *abstraction for each concrete type of spell
 */
public abstract class Spell extends Item implements castable{

    public int damageRange;
    public int mana;

    enum SpellType{
        ICE_SPELL, FIRE_SPELL, LIGHTNING_SPELL;
    }

    public Spell(String name, int price, int minLevel, int damage, int mana) {
        super(name, price, minLevel);
        this.damageRange = damage;
        this.mana = mana;
    }

    public void display(){
        System.out.format("%-15s %15s %12s %15s %15s %15s%n",
                this.getSpellType(), this.getName(), this.getPrice(),this.getMinLevel(),
                this.getDamageRange(), this.getMana());
    }


    public int getDamageRange() {
        return damageRange;
    }
    public void setDamageRange(int damageRange) {
        this.damageRange = damageRange;
    }
    public int getMana() {
        return mana;
    }
    public void setMana(int mana) {
        this.mana = mana;
    }
    public ItemType getType(){
        return ItemType.SPELL;
    }

    //every spell needs to implement cast method
    public abstract boolean cast(Hero hero, Character character);

    //get each spell type
    public abstract SpellType getSpellType();
}
