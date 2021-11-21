import java.util.List;

/**
 * abstract class for all three types of hero
 * it abstracts common method or attribute for the concrete hero class
 */
public abstract class Hero extends Character{

    protected Skill skill;
    private double strength;
    private double agility;
    private double dexterity;
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

    //current hero's position
    //private int row, col;

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

    //attack to enemy
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
        this.HP = this.level * 50;
        this.money -= this.money/2;
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


    //current turn hero move to next cell
    public void makeMove(PlayerTeam playerTeam, Cell[][] cells, Hero hero,  int row,int col) {
        //current turn hero num
        String heroNum = String.valueOf(playerTeam.getHeroID(hero) + 1);

        if (UtilCheckInput.checkBorder(row, col)) {
            if (cells[row][col] instanceof InaccessibleCell) {
                System.out.println("cannot enter # (inaccessible)!");
            }
            else if (cells[row][col] instanceof HeroNexusCell) {
                cells[row][col].setCellHeroPos("H" + heroNum);
                cells[hero.getRow()][hero.getCol()].resetHeroCell();
                //update hero pos
                hero.setPos(row, col);
                System.out.println("You have entered a market place!");
            }
            else if (cells[row][col] instanceof MonsterNexusCell) {
                cells[row][col].setCellHeroPos("H" + heroNum);
                cells[hero.getRow()][hero.getCol()].resetHeroCell();
                //update hero pos
                hero.setPos(row, col);
                System.out.println("Congratulation!");
                System.out.println("The Hero have destroy the monster's nexus");
                System.out.println("Players won the game");
                this.setReachEnemyBase(true);
                System.out.println("The game is over");
                System.exit(0);
            }else{
                cells[row][col].setCellHeroPos("H" + heroNum);
                cells[hero.getRow()][hero.getCol()].resetHeroCell();
                //update hero pos
                hero.setPos(row, col);
            }
        }
        else {
            System.out.println("You cannot move to outside of the board!");
        }
    }


    //getter and setter
    public double getMana() {
        return mana;
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
    public void setReachEnemyBase(boolean reachEnemyBase) {
        this.reachEnemyBase = reachEnemyBase;
    }
    public boolean isReachEnemyBase() {
        return reachEnemyBase;
    }

    public abstract HeroType getType();
//    public int getRow() {
//        return row;
//    }
//    public void setRow(int row) {
//        this.row = row;
//    }
//    public int getCol() {
//        return col;
//    }
//    public void setCol(int col) {
//        this.col = col;
//    }

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
    public boolean canAttack(Team monsterTeam) {
        for(Monster monster: ((MonsterTeam) monsterTeam).getMonsters()){
            //a monster is on the upper row of the hero (left, up, or right)
            if(monster.getRow() - this.getRow() == -1 && (monster.getCol() - this.getCol() <= 1 || monster.getCol() - this.getCol() <= -1)){
                return true;
            }
            //a monster is on the same row of the hero (left, same cell, or right)
            else if(monster.getRow() - this.getRow() == 0 && (monster.getCol() - this.getCol() <= 1 || monster.getCol() - this.getCol() <= -1)){
                return true;
            }
        }
        return false;
    }
}
