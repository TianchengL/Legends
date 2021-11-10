/**
 * concrete class for each weapon
 */
public class Weapon extends Item implements Equipable{

    private int damageInflict;
    private int requireHands;
    private boolean equip;

    public Weapon(String name, int price, int minLevel,int damageInflict, int requireHands) {
        super(name, price, minLevel);
        this.damageInflict = damageInflict;
        this.requireHands = requireHands;
    }

    //getter and setter
    public int getDamageInflict() {
        return damageInflict;
    }
    public void setDamageInflict(int damageInflict) {
        this.damageInflict = damageInflict;
    }
    public int getRequireHands() {
        return requireHands;
    }
    public void setRequireHands(int requireHands) {
        this.requireHands = requireHands;
    }
    public ItemType getType(){
        return ItemType.WEAPON;
    }

    //display this weapon info
    @Override
    void display() {
        System.out.format("%-15s %10s %12s %22s %22s %6s%n",
                this.getName(), this.getPrice(), this.getMinLevel(),
                this.getDamageInflict(),this.getRequireHands(), this.getEquip());
    }

    //indicate if this weapon has been equipped or not
    @Override
    public boolean getEquip() {
        return equip;
    }

    @Override
    public void setEquip(Boolean equip) {
        this.equip = equip;
    }
}
