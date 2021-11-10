/**
 * abstract class for each item
 */
public abstract class Item {

    private String name;
    private int price;
    private int minLevel;
    private int ID;

    enum ItemType{
        WEAPON, ARMOR, POTION, SPELL;
    }

    public Item(String name, int price, int minLevel){
        this.minLevel = minLevel;
        this.name = name;
        this.price = price;
    }
    //getter and setter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getMinLevel() {
        return minLevel;
    }
    public void setMinLevel(int minLevel) {
        this.minLevel = minLevel;
    }
    public ItemType getType(){
        return null;
    }
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }

    //display stats of each item
    abstract void display();
}
