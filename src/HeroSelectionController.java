import java.util.Iterator;
import java.util.Map;
import java.util.List;
import java.util.Scanner;

/**
 * this class control user to select the heroes that they want
 * and add them to player team
 */
public class HeroSelectionController
{
    private final Scanner input;
    private final HeroSelectionView view;
    private final List<Warrior> warriors;
    private final List<Paladin> paladins;
    private final List<Sorcerer> sorcerers;
    private PlayerTeam team;
    private int boardSize;

    public HeroSelectionController(Scanner input) {
        this.warriors = CharacterFactory.getWarriorsInstance();
        this.paladins = CharacterFactory.getPaladinsInstance();
        this.sorcerers = CharacterFactory.getSorcerersInstance();
        this.input = input;
        this.view = new HeroSelectionView();
        this.selectHero();
    }

    //select hero name, board size and number of hero in the team
    public void selectHero() {
        System.out.println("please choose a name for your hero team!");
        String name = this.input.next();
        this.team = new PlayerTeam(name);
        System.out.println("Please select board size. Enter a number between 5 - 10. It will create n x n matrix board");
        this.boardSize = Integer.parseInt(UtilCheckInput.checkInput(input, 5, 10));
        System.out.println("Please enter how many hero you want to add to your team!(1-3)");
        int heroNum = Integer.parseInt(UtilCheckInput.checkInput(input, 1, 3));
        this.addToTeam(heroNum);
    }

    public int getBoardSize() {
        return this.boardSize;
    }
    public PlayerTeam getPlayerTeam() {
        return this.team;
    }

    //add selected hero to player team
    private void addToTeam(int heroNum) {
        this.view.showInstruction();
        this.view.showWarriorInfo();
        this.view.showPaladinInfo();
        this.view.showSorcerersInfo();
        for (int i = 0; i < heroNum; ++i) {
            int index = i + 1;
            System.out.println("Please choose your " + index + " hero type");
            String heroType = this.checkHeroType(this.input);
            System.out.println("Next, please choose hero ID");
            String heroId = UtilCheckInput.checkInput(this.input, 1, 6);
            int id = Integer.parseInt(heroId);
            if (this.checkDuplicate(this.getHero(heroType, id))) {
                System.out.println("Cannot choose same hero!");
                --i;
            }
            else {
                this.team.addHero(i, this.getHero(heroType, id));
            }
        }
    }

    //cannot enter same hero twice
    private boolean checkDuplicate(Hero hero) {
        Map<Integer, Hero> t = this.team.getTeam();
        for (Integer integer : t.keySet()) {
            if (t.get(integer).equals(hero)) {
                return true;
            }
        }
        return false;
    }

    //return hero in according to different type
    private Hero getHero(String heroType, int heroId) {
        if ("w".equalsIgnoreCase(heroType)) {
            for (Warrior warrior : warriors) {
                if (warrior.getID() == heroId) {
                    return warrior;
                }
            }
        }
        else if ("p".equalsIgnoreCase(heroType)) {
            for (Paladin paladin : paladins) {
                if (paladin.getID() == heroId) {
                    return paladin;
                }
            }
        }
        else if ("s".equalsIgnoreCase(heroType)) {
            for (Sorcerer sorcerer : sorcerers) {
                if (sorcerer.getID() == heroId) {
                    return sorcerer;
                }
            }
        }
        return null;
    }

    //check type of hero
    private String checkHeroType(Scanner input) {
        String str;
        while (true) {
            str = input.next();
            if ("w".equalsIgnoreCase(str) || "s".equalsIgnoreCase(str) || "p".equalsIgnoreCase(str)) {
                break;
            }
            System.out.println("Please enter correct heroType! ");
        }
        return str;
    }
}