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
    public void makeMove(){

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
    public boolean canAttack(Team playerTeam) {
        for(Hero hero: ((PlayerTeam) playerTeam).getTeam().values()){
            //a monster is on the upper row of the hero (left, up, or right)
            if(this.getRow() - hero.getRow() == -1 && (this.getCol() - hero.getCol() <= 1 || this.getCol() - hero.getCol() <= -1)){
                return true;
            }
            //a monster is on the same row of the hero (left, same cell, or right)
            else if(this.getRow() - hero.getRow() == 0 && (this.getCol() - hero.getCol() <= 1 || this.getCol() - hero.getCol() <= -1)){
                return true;
            }
        }
        return false;
    }

    //return true if this monster still alive
    public boolean isAlive(){
        return this.getHP() > 0;
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
