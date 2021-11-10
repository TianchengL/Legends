import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.util.List;

/**
 * market controller
 * control user to buy item
 */
public class Market
{
    public final List<Weapon> weapons;
    public final List<Armor> armors;
    public final List<Potion> potions;
    public final List<IceSpell> iceSpells;
    public final List<FireSpell> fireSpells;
    public final List<LightingSpell> lightingSpells;

    public Market() {
        this.weapons = ItemFactory.getWeaponsInstance();
        this.armors = ItemFactory.getArmorsInstance();
        this.potions = ItemFactory.getPotionsInstance();
        this.iceSpells = ItemFactory.getIceSpellsInstance();
        this.fireSpells = ItemFactory.getFireSpellsInstance();
        this.lightingSpells = ItemFactory.getLightingSpellsInstance();
    }

    //according to user input to buy or sell item
    public void sellBuyItem(Scanner input, Hero hero) {
        System.out.println("\nYou have entered Market!");
        while (true) {
            System.out.println("\nPlease select s/S for sell, b/B to buy item, q/Q to quit market");
            hero.disPlay();
            String s = input.next();
            if ("b".equalsIgnoreCase(s)) {
                System.out.println("Please select what type of items you want to buy");
                System.out.println("remember your level must greater or equal to item minLevel");
                System.out.println("Enter w = weapon, p = potion, a = armor, s = spell, q = quit market");
                s = input.next();
                if ("w".equalsIgnoreCase(s)) {
                    this.printWeapon();
                    this.buyItem(hero, this.weapons, input);
                }
                else if ("p".equalsIgnoreCase(s)) {
                    this.printPotion();
                    this.buyItem(hero, this.potions, input);
                }
                else if ("a".equalsIgnoreCase(s)) {
                    this.printArmor();
                    this.buyItem(hero, this.armors, input);
                }
                else if ("s".equalsIgnoreCase(s)) {
                    System.out.println("What kind of spells you want to buy?");
                    System.out.println("f = fireSpell(damage + reduce enemy defence)" +
                            "\ni = iceSpell(damage + reduce enemy damage)" +
                            "\nl = lightning Spell(damage + reduce enemy dodge chance)");
                    final String spell = input.next();
                    if ("f".equalsIgnoreCase(spell)) {
                        this.printFireSpell();
                        this.buyItem(hero, this.fireSpells, input);
                    }
                    else if ("i".equalsIgnoreCase(spell)) {
                        this.printIceSpell();
                        this.buyItem(hero, this.iceSpells, input);
                    }
                    else {
                        if (!"l".equalsIgnoreCase(spell)) {
                            continue;
                        }
                        this.printLightSpell();
                        this.buyItem(hero, this.lightingSpells, input);
                    }
                }
                else {
                    if ("q".equalsIgnoreCase(s)) {
                        System.out.println("See you next time! ");
                        break;
                    }
                }
            }
            else if ("s".equalsIgnoreCase(s)) {
                hero.getInventory().printInventory();
                System.out.println("Please select what type of item you want to sell");
                System.out.println("w = weapon | a = armor | p = potion | s = spell | q = quit market");
                final String str = input.next();
                if ("w".equalsIgnoreCase(str)) {
                    if (!hero.getInventory().getWeaponList().isEmpty()) {
                        System.out.println("Please select ID of your weapon");
                        int amount = hero.getInventory().getWeaponList().size();
                        int id = Integer.parseInt(UtilCheckInput.checkInput(input, 1, amount));
                        Weapon weapon = hero.getInventory().removeWeapon(id - 1);
                        int price = weapon.getPrice();
                        hero.setMoney(hero.getMoney() + price / 2);
                        hero.getInventory().printInventory();
                    }
                    else {
                        System.out.println("There is nothing that you can sell!");
                    }
                }
                else if ("a".equalsIgnoreCase(str)) {
                    if (!hero.getInventory().getArmorList().isEmpty()) {
                        System.out.println("Please select ID of your armor");
                        int amount = hero.getInventory().getArmorList().size();
                        int id = Integer.parseInt(UtilCheckInput.checkInput(input, 1, amount));
                        Armor armor = hero.getInventory().removeArmor(id - 1);
                        int price = armor.getPrice();
                        hero.setMoney(hero.getMoney() + price / 2);
                        hero.getInventory().printInventory();
                    }
                    else {
                        System.out.println("There is nothing that you can sell!");
                    }
                }
                else if ("p".equalsIgnoreCase(str)) {
                    if (!hero.getInventory().getPotionList().isEmpty()) {
                        System.out.println("Please select ID of your potion");
                        int amount = hero.getInventory().getPotionList().size();
                        int id = Integer.parseInt(UtilCheckInput.checkInput(input, 1, amount));
                        Potion potion = hero.getInventory().removePotion(id - 1);
                        int price = potion.getPrice();
                        hero.setMoney(hero.getMoney() + price / 2);
                        hero.getInventory().printInventory();
                    }
                    else {
                        System.out.println("There is nothing that you can sell!");
                    }
                }
                else if ("s".equalsIgnoreCase(str)) {
                    if (!hero.getInventory().getSpellList().isEmpty()) {
                        System.out.println("Please select id of Spell that you want sell");
                        int amount = hero.getInventory().getSpellList().size();
                        int id = Integer.parseInt(UtilCheckInput.checkInput(input, 1, amount));
                        Spell spell2 = hero.getInventory().removeSpell(id - 1);
                        int price = spell2.getPrice();
                        hero.setMoney(hero.getMoney() + price / 2);
                        hero.getInventory().printInventory();
                    }
                    else {
                        System.out.println("There is nothing that you can sell!");
                    }
                }
                else {
                    if ("q".equalsIgnoreCase(s)) {
                        System.out.println("See you next time! ");
                        break;
                    }
                }
            }
            else {
                if ("q".equalsIgnoreCase(s)) {
                    System.out.println("See you next time! ");
                    break;
                }
            }
        }
    }

    private void buyItem(Hero hero, List<? extends Item> items,  Scanner input) {
        System.out.println("To buy item, enter the item's ID ");
        int id = Integer.parseInt(UtilCheckInput.checkInput(input, 1, items.size()));
        Item item = items.get(id - 1);
        if (item.getPrice() > hero.getMoney()) {
            System.out.println("You dont have enough money!");
        }
        else if (item.getMinLevel() > hero.getLevel()) {
            System.out.println("You are level is too low to buy this item, Please go hunt monsters and Level up!");
        }
        else {
            hero.getInventory().addItem(item);
            hero.setMoney(hero.getMoney() - item.getPrice());
            System.out.println("show inventory");
            hero.getInventory().printInventory();
        }
    }

    private String[][] addWeapon() {
        String[][] str = new String[this.weapons.size() + 1][6];
        str[0][0] = "ID";
        str[0][1] = "Name";
        str[0][2] = "Price";
        str[0][3] = "MinLevel";
        str[0][4] = "Damage";
        str[0][5] = "Required Hands";
        int r = 1;
        for ( Weapon weapon : this.weapons) {
            str[r][0] = String.valueOf(weapon.getID());
            str[r][1] = weapon.getName();
            str[r][2] = String.valueOf(weapon.getPrice());
            str[r][3] = String.valueOf(weapon.getMinLevel());
            str[r][4] = String.valueOf(weapon.getDamageInflict());
            str[r][5] = String.valueOf(weapon.getRequireHands());
            ++r;
        }
        return str;
    }

    private String[][] addArmor() {
        String[][] str = new String[this.armors.size() + 1][5];
        str[0][0] = "ID";
        str[0][1] = "Name";
        str[0][2] = "Price";
        str[0][3] = "MinLevel";
        str[0][4] = "Damage Prevention";
        int r = 1;
        for ( Armor armor : this.armors) {
            str[r][0] = String.valueOf(armor.getID());
            str[r][1] = armor.getName();
            str[r][2] = String.valueOf(armor.getPrice());
            str[r][3] = String.valueOf(armor.getMinLevel());
            str[r][4] = String.valueOf(armor.getDamageReduction());
            ++r;
        }
        return str;
    }

    private String[][] addPotion() {
        String[][] str = new String[this.potions.size() + 1][6];
        str[0][0] = "ID";
        str[0][1] = "Name";
        str[0][2] = "Price";
        str[0][3] = "MinLevel";
        str[0][4] = "Attribute Increase";
        str[0][5] = "Attribute Affect";
        int r = 1;
        for (Potion potion : this.potions) {
            str[r][0] = String.valueOf(potion.getID());
            str[r][1] = potion.getName();
            str[r][2] = String.valueOf(potion.getPrice());
            str[r][3] = String.valueOf(potion.getMinLevel());
            str[r][4] = String.valueOf(potion.getIncreaseAmount());
            str[r][5] = Arrays.toString(potion.getAttributeAffect());
            ++r;
        }
        return str;
    }

    private String[][] addSpell(List<? extends Spell> spells) {
        final String[][] str = new String[spells.size() + 1][6];
        str[0][0] = "ID";
        str[0][1] = "Name";
        str[0][2] = "Price";
        str[0][3] = "MinLevel";
        str[0][4] = "Damage";
        str[0][5] = "Mana Cost";
        int r = 1;
        for (Spell spell : spells) {
            str[r][0] = String.valueOf(spell.getID());
            str[r][1] = spell.getName();
            str[r][2] = String.valueOf(spell.getPrice());
            str[r][3] = String.valueOf(spell.getMinLevel());
            str[r][4] = String.valueOf(spell.getDamageRange());
            str[r][5] = String.valueOf(spell.getMana());
            ++r;
        }
        return str;
    }

    public void printIceSpell() {
        String[][] s = this.addSpell(this.iceSpells);
        System.out.println("IceSpell List");
        for ( String[] strings : s) {
            System.out.println(Arrays.toString(strings));
        }
    }

    public void printLightSpell() {
        String[][] s = this.addSpell(this.lightingSpells);
        System.out.println("Lightning Spell List");
        for (String[] strings : s) {
            System.out.println(Arrays.toString(strings));
        }
    }

    public void printFireSpell() {
        String[][] s = this.addSpell(this.fireSpells);
        System.out.println("FireSpell List");
        for ( String[] strings : s) {
            System.out.println(Arrays.toString(strings));
        }
    }

    public void printPotion() {
        String[][] p = this.addPotion();
        System.out.println("Potion List");
        for ( String[] strings : p) {
            System.out.println(Arrays.toString(strings));
        }
    }

    public void printWeapon() {
        String[][] w = this.addWeapon();
        System.out.println("Weapon List");
        for (String[] strings : w) {
            System.out.println(Arrays.toString(strings));
        }
    }

    public void printArmor() {
        String[][] a = this.addArmor();
        System.out.println("Armor List");
        for (String[] strings : a) {
            System.out.println(Arrays.toString(strings));
        }
    }
}