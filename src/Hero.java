import java.util.List;
import java.util.Scanner;

/**
 * abstract class for all three types of hero
 * it abstracts common method or attribute for the concrete hero class
 */
public abstract class Hero extends Character{

    protected Skill skill;
    private double strength,agility, dexterity;
    private int HP;
    private double mana;
    private int money;
    private int exp;
    private int defense;
    private int ID;
    private int level;
    private final Inventory inventory;
    //hero current equipped weapon and armor
    private Weapon weapon;
    private Armor armor;

    //true if this hero has reached enemy nexus
    private boolean reachEnemyBase;
    private boolean kBoosted;
    private boolean cBoosted;
    private boolean bBoosted;


    enum HeroType{
        WARRIORS, SORCERERS, PALADINS;
    }

    public Hero(String name, double mana, double strength, double agility, double dexterity, int money, int exp) {
        super(name);
        this.HP = 100;
        this.level = 1;
        this.defense = 0;
        this.mana = mana;
        this.strength = strength;
        this.agility = agility;
        this.dexterity = dexterity;
        this.money = money;
        this.exp = exp;
        inventory = new Inventory(this);
        this.kBoosted =false;
        this.cBoosted = false;
        this.bBoosted = false;
    }

    //Strategy pattern for hero skills
    public void addHeroSkill(){
        this.strength = skill.addStrength(strength);
        this.agility = skill.addAgility(agility);
        this.dexterity = skill.addDexterity(dexterity);
    }



    //get hero inventory
    public Inventory getInventory() {
        return inventory;
    }

    //attack enemy
    public void attack(Character character){
        double damage;
        if(character instanceof Monster){
            if(((Monster) character).dodge()){
                System.out.println("You missed attack!");
            }else{
                if(this.weapon == null){
                    damage = this.getStrength() * 0.05;
                }else{
                    damage = (this.getStrength() + this.weapon.getDamageInflict()) * 0.05;
                }
                double realDamage = ((Monster) character).takeDamage(damage);
                System.out.println(this.getName() + " attacked " + character.getName() +"," +
                        "Dealt " + Math.round(realDamage) + " real damage");
            }
        }
    }

    //hero cast Spell
    public boolean castSpell(Scanner input, Monster m){
        List<Spell> spells = this.getInventory().getSpellList();
        if (!spells.isEmpty()) {
            this.getInventory().printSpells();
            System.out.println("Please select Spell ID to cast");
            int id = Integer.parseInt(UtilCheckInput.checkInput(input, 1, spells.size()));
            if(!spells.get(id - 1).cast(this, m)){
                return false;
            }
            spells.remove(spells.get(id-1));
            this.showInfoBattle();
            m.displayStats();
        }else{
            System.out.println("There is no spell!");
        }
        return true;
    }

    //according to index to equip weapon
    //if successful equipped return true
    public void equipWeapon(int index){
        List<Weapon> list = this.getInventory().getWeaponList();
        if(index - 1 < 0 || index - 1 >= list.size()){
            System.out.println("not valid index");
            return;
        }
        //if other weapon has equipped, unequipped it
        for (Weapon weapon : list) {
            if(weapon.getEquip()){
                weapon.setEquip(false);
            }
        }
        Weapon w = list.get(index - 1);
        w.setEquip(true);
        this.weapon = w;
        System.out.println("Equipped!");
    }

    //equip armor
    public void equipArmor(int index){
        List<Armor> list = this.getInventory().getArmorList();
        if(index - 1 < 0 || index - 1 >= list.size()){
            System.out.println("not valid index");
            return;
        }
        for (Armor armor : list) {
            if(armor.getEquip()){
                armor.setEquip(false);
                this.setDefense( 0 );
            }
        }
        Armor armor = list.get(index - 1);
        this.setDefense(armor.getDamageReduction());
        armor.setEquip(true);
        this.armor = armor;
        System.out.println("Equipped!");
    }

    //drink the potion and remove from hero inventory
    public void drinkPotion(int index){
        List<Potion> list = this.getInventory().getPotionList();
        if(index - 1 < 0 || index - 1 >= list.size()){
            System.out.println("not valid index");
            return;
        }
        Potion potion = list.get(index - 1);
        int amount = potion.getIncreaseAmount();
        for (String s : potion.getAttributeAffect()) {
            //Health/Mana/Strength/Dexterity/Defense/Agility
            if("Health".equalsIgnoreCase(s)){
                this.setHP(getHP() + amount);
            }else if("Mana".equalsIgnoreCase(s)){
                this.setMana(getMana() + amount);
            }else if("Strength".equalsIgnoreCase(s)){
                this.setStrength(getStrength() + amount);
            }else if("Dexterity".equalsIgnoreCase(s)){
                this.setDexterity(getDexterity() + amount);
            }else if("Defense".equalsIgnoreCase(s)){
                this.setDefense(getDefense() + amount);
            }else if("Agility".equalsIgnoreCase(s)){
                this.setAgility(getAgility() + amount);
            }
        }
        this.getInventory().removePotion(index - 1);
        System.out.println("Hero ability has grown");
    }

    //take the damage that attacked by enemy
    public double takeDamage(double damage){
        double realDamage = damage - this.getDefense() * 0.001;
        this.setHP(this.HP -= realDamage);
        return realDamage;
    }


    //return true if dodge success
    //random number < agility * 0.0002
    public boolean dodge() {
        double num = Math.random();
        return num < this.agility * 0.0002;
    }


    //return true if this hero still alive
    public boolean isAlive(){
        return this.getHP() > 0;
    }

    //revive this hero, get back half HP
    //AND this hero loss half of his money
    public void revive(){
        this.HP = this.level * 100;
    }

    //add specified money and exp for extendable purpose
    //also check level up
    public void setBonus(int exp, int money){
        this.exp += exp;
        this.money += money;
        if(this.exp > 10*this.getLevel()){
            levelUp();
        }
    }

    //if hero can level up change his stats
    public void levelUp(){
        this.level += 1;
        this.HP = 100 * this.level;
        this.mana *= 1.1;
        addHeroSkill();
        System.out.println("Hero " + this.getName() + " level up!");
    }

    /**
     * teleport to the selected cell position that has been explored before
     * @param row of hero is sending to
     * @param col that the current hero is sending to
     * @param playerTeam current player team
     * @param hero current hero
     * @return true if teleport success
     */
    public boolean telePort(Cell[][] cells, int row, int col, PlayerTeam playerTeam, Hero hero){
        String heroNum = String.valueOf(playerTeam.getHeroID(hero) + 1);

        int prevCol = hero.getCol();
        //if selected teleport lane is the same lane
        if(prevCol + 1 == col || prevCol == col || prevCol - 1 == col){
            System.out.println("cannot teleport to same lane");
            return false;
        }else{
            //if selected cell not contain hero and has been explored
            if(cells[row][col].isExplored() && !cells[row][col].isHero()){
                cells[row][col].setCellHeroPos("H" + heroNum);
                cells[hero.getRow()][hero.getCol()].resetHeroCell();
                hero.setPos(row, col);
                return true;
            }else{
                System.out.println("cannot move to the cell that has not been explored");
            }
        }
        return false;
    }

    //hero back to his nexus
    public void backToBase(PlayerTeam playerTeam, Cell[][] cells, Hero hero){

        //get current hero number
        String heroNum = String.valueOf(playerTeam.getHeroID(hero) + 1);

        //set hero position to base according to hero number
        if(playerTeam.getHeroID(hero) == 0){
            if(cells.length - 1 == hero.getRow() && hero.getCol() == 0){
                cells[cells.length - 1][0].setCellHeroPos("H" + heroNum);
            }else{
                cells[cells.length - 1][0].setCellHeroPos("H" + heroNum);
                cells[hero.getRow()][hero.getCol()].resetHeroCell();
                hero.setPos(7, 0);
            }
        }else if(playerTeam.getHeroID(hero) == 1){
            if(cells.length - 1 == hero.getRow() && hero.getCol() == 3){
                cells[cells.length - 1][3].setCellHeroPos("H" + heroNum);
            }else {
                cells[cells.length - 1][3].setCellHeroPos("H" + heroNum);
                cells[hero.getRow()][hero.getCol()].resetHeroCell();
                hero.setPos(7, 3);
            }
        }else if(playerTeam.getHeroID(hero) == 2){
            if(cells.length - 1 == hero.getRow() && hero.getCol() == 6){
                cells[cells.length - 1][6].setCellHeroPos("H" + heroNum);
            }else {
                cells[cells.length - 1][6].setCellHeroPos("H" + heroNum);
                cells[hero.getRow()][hero.getCol()].resetHeroCell();
                hero.setPos(7, 6);
            }
        }
    }


    //current turn hero move to next cell
    public boolean makeMove(PlayerTeam playerTeam, Cell[][] cells, Hero hero, int row, int col,double kBoost,double bBoost,double cBoost) {
        //current turn hero num
        String heroNum = String.valueOf(playerTeam.getHeroID(hero) + 1);
        if (UtilCheckInput.checkBorder(row, col)) {
            if (cells[row][col] instanceof InaccessibleCell) {
                System.out.println("cannot enter # (inaccessible)!");
                return false;
            }
            else if (cells[row][col] instanceof HeroNexusCell) {
                cells[row][col].setCellHeroPos("H" + heroNum);
                cells[hero.getRow()][hero.getCol()].resetHeroCell();
                //update hero pos
                hero.setPos(row, col);
                //set this cell and same row cell as explored
                setCellExplored(cells, row, col);
                System.out.println("You have entered a market place!");
            }
            else if (cells[row][col] instanceof MonsterNexusCell) {
                cells[row][col].setCellHeroPos("H" + heroNum);
                cells[hero.getRow()][hero.getCol()].resetHeroCell();
                //update hero pos
                hero.setPos(row, col);
                //set this cell and same row cell as explored
                setCellExplored(cells, row, col);
                System.out.println(Message.win);
                System.out.println("Congratulation!");
                System.out.println("This Hero have destroy the monster's nexus");
                this.setReachEnemyBase(true);
            }else if(cells[row][col] instanceof BushCell){
                System.out.println("The dexterity of "+ hero.getName() +" has boosted" );
                //when hero receive boost, turn the boost state to true
                hero.setB(true);

                hero.setDexterity(hero.getDexterity() + bBoost);
                cells[row][col].setCellHeroPos("H" + heroNum);
                cells[hero.getRow()][hero.getCol()].resetHeroCell();
                //update hero pos
                hero.setPos(row, col);
                //set this cell and same row cell as explored
                setCellExplored(cells, row, col);
            }
            else if(cells[row][col] instanceof CaveCell){
                System.out.println("The agility of "+ hero.getName() +" has boosted" );
                hero.setC(true);

                hero.setAgility(hero.getAgility() + cBoost);
                cells[row][col].setCellHeroPos("H" + heroNum);
                cells[hero.getRow()][hero.getCol()].resetHeroCell();
                //update hero pos
                hero.setPos(row, col);
                //set this cell and same row cell as explored
                setCellExplored(cells, row, col);
            }
            else if(cells[row][col] instanceof KoulouCell){
                System.out.println("The strength of "+ hero.getName() +" has boosted" );
                hero.setK(true);

                hero.setStrength(hero.getStrength()+kBoost);
                cells[row][col].setCellHeroPos("H" + heroNum);
                cells[hero.getRow()][hero.getCol()].resetHeroCell();
                //update hero pos
                hero.setPos(row, col);
                //set this cell and same row cell as explored
                setCellExplored(cells, row, col);
            }
            else{
//                hero.setDexterity(hero.getDexterity() - bBoost);
//                hero.setAgility(hero.getAgility() - cBoost);
//                hero.setStrength(hero.getStrength()-kBoost);
                cells[row][col].setCellHeroPos("H" + heroNum);
                cells[hero.getRow()][hero.getCol()].resetHeroCell();
                //update hero pos
                hero.setPos(row, col);
                //set this cell and same row cell as explored
                setCellExplored(cells, row, col);
            }
        }
        else {
            System.out.println("You cannot move to outside of the board!");
            return false;
        }
        return true;
    }

    //set current cell and its neighbor cell explored
    public void setCellExplored(Cell[][] cells, int row, int col){
        cells[row][col].setExplored(true);
        //if current cell left is not out of bound and is not Block Cell
        if(col - 1 >= 0 &&  !(cells[row][col-1] instanceof InaccessibleCell)){
            cells[row][col-1].setExplored(true);
        }
        //if current cell right is not out of bound and is not block cel
        if(col + 1 <= cells[0].length - 1 &&  !(cells[row][col+1] instanceof InaccessibleCell)){
            cells[row][col+1].setExplored(true);
        }
    }


    //getter and setter
    public double getMana() {
        return mana;
    }
    public boolean getK(){
        return this.kBoosted;
    }

    public boolean getC(){
        return this.cBoosted;
    }

    public boolean getB(){
        return this.bBoosted;
    }
    public int getMoney() {
        return money;
    }
    public int getExp() {
        return exp;
    }
    public void setMana(double mana) {
        this.mana = mana;
    }
    public void setMoney(int money) {
        this.money = money;
    }
    public void setExp(int exp) {
        this.exp = exp;
    }
    public double getStrength() {
        return strength;
    }
    public void setStrength(double strength) {
        this.strength = strength;
    }
    public double getAgility() {
        return agility;
    }
    public void setAgility(double agility) {
        this.agility = agility;
    }
    public double getDexterity() {
        return dexterity;
    }
    public void setDexterity(double dexterity) {
        this.dexterity = dexterity;
    }
    public int getHP() {
        return HP;
    }
    public int resetHP(){
        return getLevel()*100;
    }
    public void setHP(int HP) {
        this.HP = HP;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getDefense() {
        return defense;
    }
    public void setDefense(int defense) {
        this.defense = defense;
    }
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public void setK(boolean k){
        this. kBoosted = k;
    }

    public void setB(boolean b){
        this.bBoosted = b;
    }

    public void setC(boolean c){
        this.cBoosted =c;
    }
    public void setReachEnemyBase(boolean reachEnemyBase) {
        this.reachEnemyBase = reachEnemyBase;
    }
    public boolean isReachEnemyBase() {
        return reachEnemyBase;
    }

    public abstract HeroType getType();

//    //set hero position
    public void setHeroPos(Cell cell, String name, int row, int col){
        cell.setCellHeroPos(name);
        this.setRow(row);
        this.setCol(col);
    }

    public String checkPosType(Cell[][] cells) {
        if (cells[this.getRow()][this.getCol()] instanceof InaccessibleCell) {
            return "#";
        }
        if (cells[this.getRow()][this.getCol()] instanceof HeroNexusCell) {
            return "HN";
        }
        if(cells[this.getRow()][this.getCol()] instanceof CaveCell){
            return "C";
        }
        if(cells[this.getRow()][this.getCol()] instanceof KoulouCell){
            return "K";
        }
        if(cells[this.getRow()][this.getCol()] instanceof BushCell){
            return "B";
        }
        if(cells[this.getRow()][this.getCol()] instanceof PlainCell){
            return "P";
        }

        if(cells[this.getRow()][this.getCol()] instanceof MonsterNexusCell){
            return "MN";
        }


        return " ";
    }


    public void disPlay() {
        System.out.format("%-20s %4s %7s %11s %13s %11s %10s %7s %10s %7s%n",
                "Name", "HP", "Mana", "Strength", "Agility","Dexterity", "Money", "Exp", "Defense", "Level");
        System.out.println("=====================================================================" +
                "=======================================");
        System.out.format("%-20s %4s %7s %11s %13s %11s %10s %7s %10s %7s%n",
                getName(),getHP(),(int)getMana(),(int)getStrength(),(int)getAgility(),
                (int)getDexterity(), getMoney(), getExp(), getDefense(), getLevel());
    }

    //show info during the fight
    public void showInfoBattle(){
        System.out.println();

        System.out.format("%-20s %4s %7s %12s %25s  %25s%n",
                "Name", "HP", "Mana", "Level", "Equipped Weapon","Armor");
        System.out.println("=====================================================================");
        String wName;
        String aName;
        if(this.weapon == null){
            wName = "No weapon equipped!";
        }else{
            wName = weapon.getName();
        }
        if(this.armor == null){
            aName = "No weapon equipped!";
        }else{
            aName = armor.getName();
        }
        System.out.format("%-20s %4s %7s %12s %25s %25s%n",
                this.getName(), this.getHP(), this.getMana(), this.getLevel(), wName,aName);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Hero){
            return ((Hero) obj).getType() == this.getType() && ((Hero) obj).getID() == this.getID();
        }
        return false;
    }


    @Override
    public Monster canAttack(List<? extends Character> monsters) {
        for(Character monster: monsters){
            if(((Monster)monster).isAlive()){
            //a monster is on the upper row of the hero (left, up, or right)
                if(monster.getRow() - this.getRow() == -1 && (monster.getCol() - this.getCol() <= 1 || monster.getCol() - this.getCol() <= -1)){
                    return (Monster)monster;
                }
                //a monster is on the same row of the hero (left, same cell, or right)
                else if(monster.getRow() - this.getRow() == 0 && (monster.getCol() - this.getCol() <= 1 || monster.getCol() - this.getCol() <= -1)){
                    return (Monster)monster;
                }
            }
        }
        return null;
    }


}
