import java.util.Iterator;
import java.util.Arrays;
import java.util.List;

/**
 * the view of hero selection controller
 * User will see a list of heroes that they could choose
 */
public class HeroSelectionView
{
    public void showInstruction() {
        System.out.println("To select hero, simply type the initial of heroType and hit enter. " +
                "Then type hero ID and hit enter!" +
                "\nThere are three kinds of hero and you could pick 1-3 heroes as for your hero team" +
                "\nGet ready to beat Monsters!");
        System.out.println("==================================================================================");
    }

    //show warrior info
    public void showWarriorInfo() {
        System.out.println("\nWarriors:");
        List<Warrior> list = CharacterFactory.getWarriorsInstance();
        String[][] printHeroInfo;
        printHeroInfo = this.printHeroInfo(list);
        for (String[] strings : printHeroInfo) {
            System.out.println(Arrays.toString(strings));
        }
    }

    //show sorcerer info
    public void showSorcerersInfo() {
        System.out.println("\nSorcerers: ");
        List<Sorcerer> list = CharacterFactory.getSorcerersInstance();
        String[][] printHeroInfo;
        printHeroInfo = this.printHeroInfo(list);
        for (String[] strings : printHeroInfo) {
            System.out.println(Arrays.toString(strings));
        }
    }

    //show paladin info
    public void showPaladinInfo() {
        System.out.println("\nPaladins");
        List<Paladin> list = CharacterFactory.getPaladinsInstance();
        String[][] printHeroInfo;
        printHeroInfo = this.printHeroInfo(list);
        for (final String[] strings : printHeroInfo) {
            System.out.println(Arrays.toString(strings));
        }
    }

    //put hero in 2d array for format purpose
    private String[][] printHeroInfo(List<? extends Hero> list) {
        String[][] str = new String[list.size() + 1][11];
        str[0][0] = "ID";
        str[0][1] = "Name";
        str[0][2] = "Level";
        str[0][3] = "HP";
        str[0][4] = "Mana";
        str[0][5] = "Strength";
        str[0][6] = "Agility";
        str[0][7] = "Dexterity";
        str[0][8] = "Defense";
        str[0][9] = "Wallet";
        str[0][10] = "Exp";
        int r = 1;
        for (Hero hero : list) {
            str[r][0] = String.valueOf(hero.getID());
            str[r][1] = hero.getName();
            str[r][2] = String.valueOf(hero.getLevel());
            str[r][3] = String.valueOf(hero.getHP());
            str[r][4] = String.valueOf(hero.getMana());
            str[r][5] = String.valueOf(hero.getStrength());
            str[r][6] = String.valueOf(hero.getAgility());
            str[r][7] = String.valueOf(hero.getDexterity());
            str[r][8] = String.valueOf(hero.getDefense());
            str[r][9] = String.valueOf(hero.getMoney());
            str[r][10] = String.valueOf(hero.getExp());
            ++r;
        }
        return str;
    }
}