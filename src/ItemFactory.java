import java.util.List;

/**
 * factory and singleton pattern to get certain items from certain file
 */
public class ItemFactory
{
    public static List<Weapon> weapons;
    public static List<Armor> armors;
    public static List<Potion> potions;
    public static List<IceSpell> iceSpells;
    public static List<FireSpell> fireSpells;
    public static List<LightingSpell> lightingSpells;
    private static final Parser parser;

    public static List<Weapon> getWeaponsInstance() {
        if (ItemFactory.weapons == null) {
            ItemFactory.weapons = ItemFactory.parser.parseWeapon("Weaponry.txt");
        }
        return ItemFactory.weapons;
    }

    public static List<Armor> getArmorsInstance() {
        if (ItemFactory.armors == null) {
            ItemFactory.armors = ItemFactory.parser.parseArmor("Armory.txt");
        }
        return ItemFactory.armors;
    }

    public static List<Potion> getPotionsInstance() {
        if (ItemFactory.potions == null) {
            ItemFactory.potions = ItemFactory.parser.parsePotion("Potions.txt");
        }
        return ItemFactory.potions;
    }

    public static List<IceSpell> getIceSpellsInstance() {
        if (ItemFactory.iceSpells == null) {
            ItemFactory.iceSpells = ItemFactory.parser.parseIceSpell("IceSpells.txt");
        }
        return ItemFactory.iceSpells;
    }

    public static List<FireSpell> getFireSpellsInstance() {
        if (ItemFactory.fireSpells == null) {
            ItemFactory.fireSpells = ItemFactory.parser.parseFireSpell("FireSpells.txt");
        }
        return ItemFactory.fireSpells;
    }

    public static List<LightingSpell> getLightingSpellsInstance() {
        if (ItemFactory.lightingSpells == null) {
            ItemFactory.lightingSpells = ItemFactory.parser.parseLightSpell("LightningSpells.txt");
        }
        return ItemFactory.lightingSpells;
    }

    static {
        parser = new Parser();
    }
}