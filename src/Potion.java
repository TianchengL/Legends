import java.util.Arrays;

/**
 * concrete class for potion
 */
public class Potion extends Item implements usable {

    private int increaseAmount;
    private String[] attributeAffect;
    private boolean isUsed;


    public Potion(String name, int price, int minLevel, int increaseAmount, String[] attributeAffect) {
        super(name, price, minLevel);
        this.increaseAmount = increaseAmount;
        this.attributeAffect = attributeAffect;
    }

    //getter and setter
    public int getIncreaseAmount() {
        return increaseAmount;
    }
    public void setIncreaseAmount(int increaseAmount) {
        this.increaseAmount = increaseAmount;
    }
    public String[] getAttributeAffect() {
        return attributeAffect;
    }
    public void setAttributeAffect(String[] attributeAffect) {
        this.attributeAffect = attributeAffect;
    }
    public ItemType getType(){
        return ItemType.POTION;
    }

    //display potion info
    @Override
    void display() {
        System.out.format("%-15s  %10s %8s %20s %40s%n",
                this.getName(), this.getPrice(), this.getMinLevel(), this.getIncreaseAmount()
                ,Arrays.toString(this.getAttributeAffect()));
    }

    @Override
    public boolean getUsed() {
        return this.isUsed;
    }

    @Override
    public void setUse(boolean use) {
        this.isUsed = use;
    }
}
