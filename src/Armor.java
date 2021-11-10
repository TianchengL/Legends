
/**
 * Armor class represent each armor item,
 * implements Equitable interface to indicate if this armor has been equipped
 */
public class Armor extends Item implements Equipable
{
    public int damageReduction;
    private boolean equip;

    public Armor(String name, int price, int minLevel, int damageReduction) {
        super(name, price, minLevel);
        this.damageReduction = damageReduction;
    }

    public int getDamageReduction() {
        return this.damageReduction;
    }
    public void setDamageReduction(int damageReduction) {
        this.damageReduction = damageReduction;
    }

    public Item.ItemType getType() {
        return Item.ItemType.ARMOR;
    }

    //display stats of armor
    void display() {
        System.out.format("%-15s %10s %12s %30s %6s%n",
                this.getName(), this.getPrice(), this.getMinLevel(), this.getDamageReduction(), this.getEquip());
    }

    public boolean getEquip() {
        return this.equip;
    }
    public void setEquip(Boolean equip) {
        this.equip = equip;
    }
}