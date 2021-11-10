import java.util.Iterator;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * read configFiles from ConfigFiles directory
 * and return it as a list
 */
public class Parser
{
    private final String file;

    public Parser() {
        this.file = System.getProperty("user.dir") + "/src/ConfigFiles/";
    }

    private List<String> readFile(String path, String name) {
        List<String> list = new ArrayList<>();
        try {
            Scanner s2 = new Scanner(new File(path, name));
            while (s2.hasNextLine()) {
                list.add(s2.nextLine());
            }
            s2.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        list.removeIf(s -> s.equals(""));
        return list;
    }

    public List<Warrior> parseWarrior(String fileName) {
        List<String> str = this.readFile(this.file, fileName);
        Iterator<String> iter = str.iterator();
        ArrayList<Warrior> list = new ArrayList<Warrior>();
        iter.next();
        int i = 1;
        while (iter.hasNext()) {
            String data = iter.next();
            String[] s = data.split("\\s+");
            Warrior warrior = new Warrior(s[0], Double.parseDouble(s[1]),
                    Double.parseDouble(s[2]),
                    Double.parseDouble(s[3]),
                    Double.parseDouble(s[4]),
                    Integer.parseInt(s[5]),
                    Integer.parseInt(s[6]));
            warrior.setID(i);
            list.add(warrior);
            ++i;
        }
        return list;
    }

    public List<Sorcerer> parseSorcerer(String fileName) {
        List<String> str = this.readFile(this.file, fileName);
        Iterator<String> iter = str.iterator();
        ArrayList<Sorcerer> list = new ArrayList<Sorcerer>();
        iter.next();
        int i = 1;
        while (iter.hasNext()) {
            String data = iter.next();
            String[] s = data.split("\\s+");
            Sorcerer sorcerer = new Sorcerer(s[0], Double.parseDouble(s[1]),
                    Double.parseDouble(s[2]),
                    Double.parseDouble(s[3]),
                    Double.parseDouble(s[4]),
                    Integer.parseInt(s[5]),
                    Integer.parseInt(s[6]));
            sorcerer.setID(i);
            list.add(sorcerer);
            ++i;
        }
        return list;
    }

    public List<Paladin> parsePaladin(String fileName) {
        List<String> str = this.readFile(this.file, fileName);
        Iterator<String> iter = str.iterator();
        ArrayList<Paladin> list = new ArrayList<Paladin>();
        iter.next();
        int i = 1;
        while (iter.hasNext()) {
            String data = iter.next();
            String[] s = data.split("\\s+");
            Paladin paladin = new Paladin(s[0], Double.parseDouble(s[1]),
                    Double.parseDouble(s[2]),
                    Double.parseDouble(s[3]), Double.parseDouble(s[4]),
                    Integer.parseInt(s[5]),
                    Integer.parseInt(s[6]));
            paladin.setID(i);
            list.add(paladin);
            ++i;
        }
        return list;
    }

    public List<Spirit> parseSpirit(String fileName) {
        List<String> str = this.readFile(this.file, fileName);
        Iterator<String> iter = str.iterator();
        ArrayList<Spirit> list = new ArrayList<Spirit>();
        iter.next();
        while (iter.hasNext()) {
            String data = iter.next();
            String[] s = data.split("\\s+");
            Spirit spirit = new Spirit(s[0], Integer.parseInt(s[1]),
                    Integer.parseInt(s[2]),
                    Integer.parseInt(s[3]),
                    Double.parseDouble(s[4]));
            list.add(spirit);
        }
        return list;
    }

    public List<Exoskeleton> parseExoskeleton(String fileName) {
        List<String> str = this.readFile(this.file, fileName);
        Iterator<String> iter = str.iterator();
        ArrayList<Exoskeleton> list = new ArrayList<Exoskeleton>();
        iter.next();
        while (iter.hasNext()) {
            String data = iter.next();
            String[] s = data.split("\\s+");
            Exoskeleton exoskeleton = new Exoskeleton(s[0],
                    Integer.parseInt(s[1]),
                    Integer.parseInt(s[2]),
                    Integer.parseInt(s[3]),
                    Double.parseDouble(s[4]));
            list.add(exoskeleton);
        }
        return list;
    }

    public List<Dragon> parseDragon(String fileName) {
        List<String> str = this.readFile(this.file, fileName);
        Iterator<String> iter = str.iterator();
        ArrayList<Dragon> list = new ArrayList<Dragon>();
        iter.next();
        while (iter.hasNext()) {
            String data = iter.next();
            String[] s = data.split("\\s+");
            Dragon dragon = new Dragon(s[0], Integer.parseInt(s[1]),
                    Integer.parseInt(s[2]),
                    Integer.parseInt(s[3]),
                    Double.parseDouble(s[4]));
            list.add(dragon);
        }
        return list;
    }

    public List<Potion> parsePotion(String fileName) {
        List<String> str = this.readFile(this.file, fileName);
        Iterator<String> iter = str.iterator();
        ArrayList<Potion> list = new ArrayList<Potion>();
        iter.next();
        int i = 1;
        while (iter.hasNext()) {
            String data = iter.next();
            String[] s = data.split("\\s+");
            if ("All".equalsIgnoreCase(s[4])) {
                s[4] = s[5];
            }
            String[] attributes = s[4].split("/");
            Potion potion = new Potion(s[0], Integer.parseInt(s[1]),
                    Integer.parseInt(s[2]),
                    Integer.parseInt(s[3]), attributes);
            potion.setID(i++);
            list.add(potion);
        }
        return list;
    }

    public List<Armor> parseArmor(String fileName) {
        List<String> str = this.readFile(this.file, fileName);
        Iterator<String> iter = str.iterator();
        ArrayList<Armor> list = new ArrayList<Armor>();
        iter.next();
        int i = 1;
        while (iter.hasNext()) {
            String data = iter.next();
            String[] s = data.split("\\s+");
            Armor armor = new Armor(s[0], Integer.parseInt(s[1]),
                    Integer.parseInt(s[2]), Integer.parseInt(s[3]));
            armor.setID(i);
            list.add(armor);
            ++i;
        }
        return list;
    }

    public List<Weapon> parseWeapon(String fileName) {
        List<String> str = this.readFile(this.file, fileName);
        Iterator<String> iter = str.iterator();
        List<Weapon> list = new ArrayList<Weapon>();
        iter.next();
        int i = 1;
        while (iter.hasNext()) {
            String data = iter.next();
            String[] s = data.split("\\s+");
            Weapon weapon = new Weapon(s[0], Integer.parseInt(s[1]),
                    Integer.parseInt(s[2]),
                    Integer.parseInt(s[3]),
                    Integer.parseInt(s[4]));
            weapon.setID(i);
            list.add(weapon);
            ++i;
        }
        return list;
    }

    public List<FireSpell> parseFireSpell(String fileName) {
        List<String> str = this.readFile(this.file, fileName);
        Iterator<String> iter = str.iterator();
        List<FireSpell> list = new ArrayList<FireSpell>();
        iter.next();
        int i = 1;
        while (iter.hasNext()) {
            String data = iter.next();
            String[] s = data.split("\\s+");
            FireSpell fireSpell = new FireSpell(s[0],
                    Integer.parseInt(s[1]),
                    Integer.parseInt(s[2]),
                    Integer.parseInt(s[3]),
                    Integer.parseInt(s[4]));
            fireSpell.setID(i++);
            list.add(fireSpell);
        }
        return list;
    }

    public List<IceSpell> parseIceSpell( String fileName) {
        List<String> str = this.readFile(this.file, fileName);
        Iterator<String> iter = str.iterator();
        List<IceSpell> list = new ArrayList<IceSpell>();
        iter.next();
        int i = 1;
        while (iter.hasNext()) {
            String data = iter.next();
            String[] s = data.split("\\s+");
            IceSpell iceSpell = new IceSpell(s[0],
                    Integer.parseInt(s[1]),
                    Integer.parseInt(s[2]),
                    Integer.parseInt(s[3]),
                    Integer.parseInt(s[4]));
            iceSpell.setID(i++);
            list.add(iceSpell);
        }
        return list;
    }

    public List<LightingSpell> parseLightSpell( String fileName) {
        List<String> str = this.readFile(this.file, fileName);
        Iterator<String> iter = str.iterator();
        List<LightingSpell> list = new ArrayList<LightingSpell>();
        iter.next();
        int i = 1;
        while (iter.hasNext()) {
            String data = iter.next();
            String[] s = data.split("\\s+");
            LightingSpell lightingSpell = new LightingSpell(s[0],
                    Integer.parseInt(s[1]),
                    Integer.parseInt(s[2]),
                    Integer.parseInt(s[3]),
                    Integer.parseInt(s[4]));
            lightingSpell.setID(i++);
            list.add(lightingSpell);
        }
        return list;
    }
}