import java.util.Map;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

/**
 * store same amount of monster with heroes
 * level should be equal to the highest level of hero
 */
public class MonsterTeam extends Team{

    public String name;
    public List<Monster> monster_team;
    private int winCondition = 0;


    public MonsterTeam(PlayerTeam playerTeam) {

        Map<Integer, Hero> heroes = playerTeam.getTeam();
        //find the highest level of hero in team
        int highestLevel = heroes.get(0).getLevel();
        for (Integer index : heroes.keySet()) {
            if (highestLevel < heroes.get(index).getLevel()) {
                highestLevel = heroes.get(index).getLevel();
            }
        }

        List<Dragon> dragons = CharacterFactory.getDragonsInstance();
        List<Spirit> spirits = CharacterFactory.getSpiritsInstance();
        List<Exoskeleton> exoskeletons = CharacterFactory.getExoskeletonsInstance();

        List<Monster> monsters = new ArrayList<>(spirits);
        monsters.addAll(exoskeletons);
        monsters.addAll(dragons);

        this.monster_team = new ArrayList<>();

        //keeping looping if size of monster != size of heroes
        while (this.monster_team.size() != heroes.size()) {
            int index2 = (int)(Math.random() * monsters.size());
            if (highestLevel == monsters.get(index2).getLevel()) {
                boolean used = false;
                for (Monster monster : this.monster_team) {
                    if (Objects.equals(monster.getName(), monsters.get(index2).getName())) {
                        used = true;
                        break;
                    }
                }
                if (used) {
                    continue;
                }
                this.addMonster(monsters.get(index2));
            }
        }
    }

    public boolean isAllFaint() {
        for (Monster monster : this.monster_team) {
            if (monster.isAlive()) {
                return false;
            }
        }
        return true;
    }

    public void addMonster(Monster monster) {
        this.monster_team.add(monster);
    }

    public void removeMonster(Monster monster) {
        this.monster_team.remove(monster);
    }

    public List<Monster> getMonsters() {
        return this.monster_team;
    }

    public void displayStats() {
        System.out.println("Monster Stats");
        System.out.format("%-4s %18s %7s %10s %12s %18s %8s%n", "ID", "Name", "HP", "Damage", "Defense", "Dodge Chance", "Level");
        System.out.println("======================================================================================");
        int i = 1;
        for (Monster monster : this.monster_team) {
            System.out.format("%-4s %18s %7s %10s %12s %18s %8s%n", i, monster.getName(), monster.getHP(),
                    monster.getDamage(), monster.getDefenseStats(), (int)monster.getDodgeChance(), monster.getLevel());
            ++i;
        }
    }
}