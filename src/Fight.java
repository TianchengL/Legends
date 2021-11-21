
import java.util.Scanner;
import java.util.List;
import java.util.Map;

/**
 * this class maintain the battle mechanism
 * once player enter common cell, they will have 50 percent chance to
 * automatically enter the fight
 */
public class Fight
{
    private Hero hero;
    private Monster monster;

    public Fight(Hero hero, Monster monster) {
        this.hero = hero;
        this.monster = monster;


    }
    //continues to play until one team all of their member faint
//    public void roundPlay( Scanner input) {
//        System.out.println("You have encountered monsters!");
//        while (this.isWin() == null) {
//            this.hero.showInfoBattle();
//            System.out.println("\n");
//            this.monster.displayStats();
//            this.playerTurn(input);
//            this.monsterTurn();
//            System.out.println("\nThis round End, Alive heroes regain some HP, Mana, money and Exp");
//            this.playerTeam.regain();
//            this.playerTeam.battleBonus(this.monsters.get(0).getLevel());
//        }
//        if (this.isWin()) {
//            System.out.println("Player Win!!!");
//            System.out.println("reviving faint hero...");
//            for (Integer i : this.heroes.keySet()) {
//                if (!this.heroes.get(i).isAlive()) {
//                    this.heroes.get(i).revive();
//                }
//            }
//        }
//        else if (!this.isWin()) {
//            System.out.println("Monster Win!!!");
//            System.out.println("All heroes reset HP to half of the origin!");
//            for (Integer i : this.playerTeam.getTeam().keySet()) {
//                this.playerTeam.getTeam().get(i).revive();
//            }
//        }
//    }
//
//    //monster take action to attack hero
//    private void monsterTurn() {
//        if (hero.isAlive() && monster.isAlive()) {
//            monster.attack(hero);
//        }
//    }
//
//    //Player entered pvp area
//    public void enterFight(Hero hero, Monster m, Scanner input) {
//        System.out.println( "Hero " + hero.getName() + " has entered fight. ");
//        while (true) {
//            System.out.println("Please select what would you like to do");
//            System.out.println("a = attack | s = cast spell | c = change equipment/use item | i = hero info");
//            String s = input.next();
//            if ("a".equalsIgnoreCase(s)) {
//                hero.attack(m);
//                this.hero.showInfoBattle();
//                this.monster.displayStats();
//                break;
//            }
//            if ("s".equalsIgnoreCase(s)) {
//                List<Spell> spells = hero.getInventory().getSpellList();
//                if (!spells.isEmpty()) {
//                    hero.getInventory().printSpells();
//                    System.out.println("Please select Spell ID to cast");
//                    int id = Integer.parseInt(UtilCheckInput.checkInput(input, 1, spells.size()));
//                    spells.get(id - 1).cast(hero, m);
//                    hero.showInfoBattle();
//                    this.monster.displayStats();
//                    break;
//                }
//                System.out.println("There is no spell!");
//            }
//            else {
//                if ("c".equalsIgnoreCase(s)) {
//                    hero.getInventory().changeEquipment(input);
//                    this.hero.showInfoBattle();
//                    this.monster.displayStats();
//                    break;
//                }
//                if (!"i".equalsIgnoreCase(s)) {
//                    continue;
//                }
//                hero.showInfoBattle();
//            }
//        }
//    }
//
//    //player turn to beat monster
//    private void playerTurn(Scanner input) {
//        if (hero.isAlive() && monster.isAlive()) {
//                this.enterFight(hero, monster, input);
//        }
//
//    }
//
//    //return true if hero win, false monster win, no winner return null
//    public Boolean isWin() {
//        if (this.hero.isAlive() && !this.monster.isAlive()) {
//            return true;
//        }
//        if (this.monster.isAlive() && !this.hero.isAlive()) {
//            return false;
//        }
//        return null;
//    }
}