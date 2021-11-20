import java.util.HashMap;
import java.util.Map;

/**
 * player team is also player
 * which contains several amount of heroes
 */
public class PlayerTeam extends Player implements Team

{
    private int row;
    private int col;
    private final Map<Integer, Hero> team;

    public PlayerTeam(String name) {
        super(name);
        this.team = new HashMap<>();
    }

    public int getHeroID(Hero hero){
        for (Integer id : this.team.keySet()) {
            if(team.get(id) == hero){
                return id;
            }
        }
        return -1;
    }

    //when hero win a fight, it will gain some money and exp
    public void battleBonus(int level) {
        for (Integer index : this.team.keySet()) {
            Hero hero = this.team.get(index);
            if (hero.isAlive()) {
                hero.setBonus(2, 100 * level);
            }
        }
    }

    //after faint, hero regain some HP and Mana
    public void regain() {
        for (Integer index : this.team.keySet()) {
            Hero hero = this.team.get(index);
            if (hero.isAlive()) {
                hero.setHP((int)(hero.getHP() + hero.getHP() * 0.1));
                hero.setMana((int)(hero.getMana() + hero.getMana() * 0.1));
            }
        }
    }

    //return true if all hero in this team are faint
    public boolean isAllFaint() {
        for (Integer i : this.team.keySet()) {
            if (this.team.get(i).isAlive()) {
                return false;
            }
        }
        return true;
    }

    public void displayName() {
        System.out.format("%-4s %20s%n", "ID", "Name");
        System.out.println("=========================");
        for (Integer integer : this.team.keySet()) {
            int id = integer + 1;
            System.out.format("%-4s %20s%n", id, this.team.get(integer).getName());
        }
    }

    //display stats of heroes that in this team
    public void disPlayStats() {
        System.out.println("\nHero Stats\n");
        System.out.format("%-20s %4s %7s %11s %13s %11s %10s %7s %10s %7s%n",
                "Name", "HP", "Mana", "Strength", "Agility", "Dexterity", "Money", "Exp", "Defense", "Level");
        System.out.println("=============================================================" +
                "===============================================");
        for (Integer index : this.team.keySet()) {
            Hero hero = this.team.get(index);
            System.out.format("%-20s %4s %7s %11s %13s %11s %10s %7s %10s %7s%n",
                    hero.getName(), hero.getHP(), (int)hero.getMana(), (int)hero.getStrength(),
                    (int)hero.getAgility(), (int)hero.getDexterity(), hero.getMoney(),
                    hero.getExp(), hero.getDefense(), hero.getLevel());
        }
        System.out.println("\n");
    }

    //check if all heroes has reached monster base, if so hero win
    public boolean isWin(){
        for (Integer i : team.keySet()) {
            if(!team.get(i).isReachEnemyBase()){
                return false;
            }
        }
        return true;
    }

    //getter and setter
    public int getTeamSize() {
        return this.team.size();
    }
    public Hero getHero(int index) {
        return this.team.get(index);
    }
    public void addHero(int key, Hero hero) {
        this.team.put(key, hero);
    }
    public Map<Integer, Hero> getTeam() {
        return this.team;
    }
    public int getRow() {
        return this.row;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public int getCol() {
        return this.col;
    }
    public void setCol(int col) {
        this.col = col;
    }
    public void setRowCol(int row, int col) {
        this.row = row;
        this.col = col;
    }


//    @Override
//    public Hero makeTurn(int i) {
//        for (Integer index : team.keySet()) {
//            if(i == getTeamSize()-1){
//                return team.get(0);
//            }else{
//                team.get(i+1);
//            }
//
//        }
//    }
}