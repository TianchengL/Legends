import java.util.List;

/**
 * an abstraction of concrete monster which needs to implement Character class
 */
public abstract class Monster extends Character{

    private int ID;
    private final int level;
    private int damage;
    private int defenseStats;
    private double dodgeChance;
    private int HP;
    enum MonsterType{
        DRAGON, EXOSKELETON, SPIRIT;
    }

    public Monster(String name, int level, int damage, int defenseStats, double dodgeChance) {
        super(name);
        this.level = level;
        this.damage = damage;
        this.defenseStats = defenseStats;
        this.dodgeChance = dodgeChance;
        this.HP = 100 * this.level;
    }

    //set current monster pos
    public void setMonsterPos(Cell cell, String name, int row, int col){
        cell.setCellMonPos(name);
        this.setRow(row);
        this.setCol(col);
    }

    //monster move
    public void makeMove(Cell[][] cells, int row, int col){
       // String m = String.valueOf(mNum);
        if (UtilCheckInput.checkBorder(row, col)){
            System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzz");
            if(cells[row][col] instanceof  HeroNexusCell) {
                System.out.println("Monsters have destroy all the hero nexus, players lost");
                System.out.println("The game is over");
                System.exit(0);
            }
            else{
                cells[row][col].setCellMonPos("M ");
                cells[this.getRow()][this.getCol()].resetMCell();
                //update hero pos
                this.setPos(row, col);
            }
        }
    }





    //attack opponent
    public void attack(Character op){
        if(op instanceof Hero){
            if(((Hero) op).dodge()){
                System.out.println("\nMonster " + this.getName() + " missed attack!");
            }else{
                double realDamage = ((Hero) op).takeDamage(this.damage);
                System.out.println("\nMonster " + this.getName() + " dealt " + Math.round(realDamage) +
                        " real damage to " + op.getName() );
            }
        }
    }

    //real damage calculate = damage - defense*0.001
    public double takeDamage(double damage){
        double realDamage = damage - this.getDefenseStats() * 0.001;
        if(realDamage > 0){
            this.setHP((int) (getHP() - realDamage));
        }
        return realDamage;
    }

    //return true if dodge success
    public boolean dodge(){
        double num = Math.random();
        return num < dodgeChance * 0.01;
    }

    @Override
    public Hero canAttack(List<? extends Character> heroes) {
        for(Character hero: heroes){
            //a monster is on the upper row of the hero (left, up, or right)
            if(((Hero)hero).isAlive()){
                if(this.getRow() - hero.getRow() == -1 && (this.getCol() - hero.getCol() <= 1 || this.getCol() - hero.getCol() <= -1)){
                    return (Hero)hero;
                }
                //a monster is on the same row of the hero (left, same cell, or right)
                else if(this.getRow() - hero.getRow() == 0 && (this.getCol() - hero.getCol() <= 1 || this.getCol() - hero.getCol() <= -1)){
                    return (Hero)hero;
                }
            }
        }
        return null;
    }

    //return true if this monster still alive
    public boolean isAlive(){
        return this.getHP() > 0;
    }


    public void displayStats() {
        System.out.println("Monster Stats");
        System.out.format("%-18s %7s %10s %12s %18s %8s%n", "Name", "HP", "Damage", "Defense", "Dodge Chance", "Level");
        System.out.println("======================================================================================");

        System.out.format("%-18s %7s %10s %12s %18s %8s%n", this.getName(), this.getHP(),
                    this.getDamage(), this.getDefenseStats(), (int)this.getDodgeChance(), this.getLevel());


    }

    //getter and setter
    public int getLevel() {
        return level;
    }
    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
    public int getHP() {
        return HP;
    }
    public void setHP(int HP) {
        this.HP = HP;
    }
    public int getDefenseStats() {
        return defenseStats;
    }
    public void setDefenseStats(int defenseStats) {
        this.defenseStats = defenseStats;
    }
    public double getDodgeChance() {
        return dodgeChance;
    }
    public void setDodgeChance(double dodgeChance) {
        this.dodgeChance = dodgeChance;
    }
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
}
