import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Every hero has his own inventory
 * it stores list of items that hero own
 */
public class Inventory
{
    private final List<Armor> armorList;
    private final List<Weapon> weaponList;
    private final List<Potion> potionList;
    private final List<Spell> spellList;
    private final Hero hero;

    public Inventory(Hero hero) {
        this.hero = hero;
        this.armorList = new ArrayList<>();
        this.weaponList = new ArrayList<>();
        this.potionList = new ArrayList<>();
        this.spellList = new ArrayList<>();
    }

    //chance equipment in the hero inventory
    //according to user input
    public void changeEquipment(Scanner input) {
        while (true) {
            this.hero.disPlay();
            this.printInventory();
            System.out.println("You could choose to equip your weapon/armor or drink potion");
            System.out.println("w = equip weapon | a = equip armor | p = drink potion | q = quit");
            final String s = input.next();
            if ("w".equalsIgnoreCase(s)) {
                if (this.weaponList.isEmpty()) {
                    System.out.println("There is no weapon in your list");
                }
                else {
                    System.out.println("Please select weapon id to equip!");
                    int id = Integer.parseInt(UtilCheckInput.checkInput(input, 1,
                            this.getWeaponList().size()));
                    this.hero.equipWeapon(id);
                }
            }
            else if ("a".equalsIgnoreCase(s)) {
                if (this.armorList.isEmpty()) {
                    System.out.println("There is no armor in your list");
                }
                else {
                    System.out.println("Please select armor id to equip");
                    int id = Integer.parseInt(UtilCheckInput.checkInput(input, 1,
                            this.getArmorList().size()));
                    this.hero.equipArmor(id);
                }
            }
            else if ("p".equalsIgnoreCase(s)) {
                if (this.potionList.isEmpty()) {
                    System.out.println("There is no potion in your list");
                }
                else {
                    System.out.println("Please select potion id that you want drink");
                    int id = Integer.parseInt(UtilCheckInput.checkInput(input, 1,
                            this.getPotionList().size()));
                    this.hero.drinkPotion(id);
                }
            }
            else {
                if ("q".equalsIgnoreCase(s)) {
                    break;
                }
            }
        }
    }

    //getter and setter
    public List<Armor> getArmorList() {
        return this.armorList;
    }
    public List<Weapon> getWeaponList() {
        return this.weaponList;
    }
    public List<Potion> getPotionList() {
        return this.potionList;
    }
    public List<Spell> getSpellList() {
        return this.spellList;
    }

    //remove items from list
    public Weapon removeWeapon(int index) {
        return this.weaponList.remove(index);
    }
    public Armor removeArmor(int index) {
        return this.armorList.remove(index);
    }
    public Potion removePotion(int index) {
        return this.potionList.remove(index);
    }
    public Spell removeSpell(int index) {
        return this.spellList.remove(index);
    }

    //add item according to specific type
    public void addItem(Item item) {
        if (item instanceof Weapon) {
            this.weaponList.add((Weapon)item);
        }
        else if (item instanceof Armor) {
            this.armorList.add((Armor)item);
        }
        else if (item instanceof Potion) {
            this.potionList.add((Potion)item);
        }
        else if (item instanceof Spell) {
            this.spellList.add((Spell)item);
        }
    }

    //print all items info
    public void printInventory() {
        System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++Weapon List+++++++++++++" +
                "+++++++++++++++++++++++++++++++");
        this.printWeapon();
        System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++Armor List++++++++++++++++++++++" +
                "++++++++++++++++++++++++");
        this.printArmor();
        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++Potion List+++++++++++++++++++" +
                "++++++++++++++++++++++++++");
        this.printPotion();
        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++Spell List+++++++++++++++++++++" +
                "++++++++++++++++++++++++++");
        this.printSpells();
    }

    //print weapon info
    private void printWeapon() {
        System.out.format("%-4s %15s %10s %12s %22s %22s %6s%n",
                "ID", "Name", "Price", "minLevel", "Damage", "RequiredHands", "Equipped");
        System.out.println("===========================================================" +
                "============================================");
        for (int i = 1; i <= this.weaponList.size(); ++i) {
            System.out.print(i + "    ");
            this.weaponList.get(i - 1).display();
        }
    }

    //print potion info
    private void printPotion() {
        System.out.format("%-4s %15s %10s %8s %20s %40s%n",
                "ID", "Name", "Price", "MinLevel", "Attribute Increase", "Attribute Affect");
        System.out.println("=================================================" +
                "======================================================");
        for (int i = 1; i <= this.potionList.size(); ++i) {
            System.out.print(i + "    ");
            this.potionList.get(i - 1).display();
        }
    }

    //print armor info
    private void printArmor() {
        System.out.format("%-4s %15s %10s %12s %30s %6s%n",
                "ID", "Name", "Price", "minLevel", "Damage Reduction", "Equipped");
        System.out.println("===============================================" +
                "========================================================");
        for (int i = 1; i <= this.armorList.size(); ++i) {
            System.out.print(i + "    ");
            this.armorList.get(i - 1).display();
        }
    }

    //print spell info
    public void printSpells() {
        System.out.format("%-4s %15s %15s %12s %15s %15s %15s%n",
                "ID", "Type", "Name", "Price", "MinLevel", "Damage", "Mana Cost");
        System.out.println("===========================================" +
                "============================================================");
        for (int i = 1; i <= this.spellList.size(); ++i) {
            System.out.print(i + "    ");
            this.spellList.get(i - 1).display();
        }
    }
}