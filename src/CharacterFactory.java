import java.util.List;

/**
 * return a list of hero or monster contain with singleton pattern
 */
public class CharacterFactory
{
    public static List<Warrior> warriors;
    public static List<Sorcerer> sorcerers;
    public static List<Paladin> paladins;
    public static List<Dragon> dragons;
    public static List<Exoskeleton> exoskeletons;
    public static List<Spirit> spirits;
    private static final Parser parser;

    public static List<Warrior> getWarriorsInstance() {
        if (CharacterFactory.warriors == null) {
            CharacterFactory.warriors = CharacterFactory.parser.parseWarrior("Warriors.txt");
        }
        return CharacterFactory.warriors;
    }

    public static List<Sorcerer> getSorcerersInstance() {
        if (CharacterFactory.sorcerers == null) {
            CharacterFactory.sorcerers = CharacterFactory.parser.parseSorcerer("Sorcerers.txt");
        }
        return CharacterFactory.sorcerers;
    }

    public static List<Paladin> getPaladinsInstance() {
        if (CharacterFactory.paladins == null) {
            CharacterFactory.paladins = CharacterFactory.parser.parsePaladin("Paladins.txt");
        }
        return CharacterFactory.paladins;
    }

    public static List<Dragon> getDragonsInstance() {
        if (CharacterFactory.dragons == null) {
            CharacterFactory.dragons = CharacterFactory.parser.parseDragon("Dragons.txt");
        }
        return CharacterFactory.dragons;
    }

    public static List<Exoskeleton> getExoskeletonsInstance() {
        if (CharacterFactory.exoskeletons == null) {
            CharacterFactory.exoskeletons = CharacterFactory.parser.parseExoskeleton("Exoskeletons.txt");
        }
        return CharacterFactory.exoskeletons;
    }

    public static List<Spirit> getSpiritsInstance() {
        if (CharacterFactory.spirits == null) {
            CharacterFactory.spirits = CharacterFactory.parser.parseSpirit("Spirits.txt");
        }
        return CharacterFactory.spirits;
    }

    static {
        parser = new Parser();
    }
}