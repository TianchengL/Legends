import java.util.*;

/**
 * mvc pattern,
 * game controller control over all gaming precess
 */
public class GameController extends RpgGame
{
    private final Board board;
    private final Scanner input;
    private final PlayerTeam playerTeam;
    private final MonsterTeam monsterTeam;
    private final Market market;
    private final Map<Integer, Hero> heroes;
    private final List<Monster> monsters;
    private int roundNum;

    public GameController(Scanner input, HeroSelectionController hs) {
        this.input = input;
        this.playerTeam = hs.getPlayerTeam();
        this.monsterTeam = new MonsterTeam(this.playerTeam);
        this.board = new Board(8,8);
        this.market = new Market();
        this.heroes = playerTeam.getTeam();
        this.monsters = monsterTeam.getMonsters();
        this.roundNum = 0;


        this.init();
    }

    //init map, random set player to a common cell or market cell
    public void init() {
        Cell[][] map = this.board.getCells();
//        this.board.setCells("X", 7, 0);
//        this.playerteam.setRowCol(7, 0);

        //set each hero initial pos
        heroes.get(0).setHeroPos(map[map.length-1][0], "H1", map.length - 1, 0 );
        heroes.get(1).setHeroPos(map[map.length-1][3], "H2", map.length - 1, 3 );
        heroes.get(2).setHeroPos(map[map.length -1][6], "H3", map.length - 1, 6 );
        heroes.get(0).setCellExplored(map, map.length - 1, 0);
        heroes.get(1).setCellExplored(map, map.length - 1, 3);
        heroes.get(2).setCellExplored(map, map.length - 1, 6);

        //initialize monster pos
        monsters.get(0).setMonsterPos(map[0][0], "M ", 0, 0);
        monsters.get(1).setMonsterPos(map[0][3], "M ", 0, 3);
        monsters.get(2).setMonsterPos(map[0][6], "M ", 0, 6);

    }

    //player game according to user input
    public void playGame() {

        Hero hero = this.heroes.get(0);
        boolean alreadyMoved = false;
        boolean play = true;
        int countTurn = 0;
        while (play) {
            System.out.println();
            this.board.printBoard();
            Cell[][] cells = this.board.getCells();
            System.out.println("In hero "+hero.name+ "'s round");
            System.out.println();
            this.showMapInfo();
            playerTeam.displayName();
            hero.disPlay();
            String s = this.input.next();
            double kBoost = hero.getStrength() * 0.1;
            double bBoost = hero.getDexterity()*0.1;
            double cBoost = hero.getAgility()*0.1;
            int row, col;
            switch (s.toLowerCase()){

                case "w":
                    if(!alreadyMoved) {
                        ///check and remove the boost before make a move
                        this.checkBoost(hero, kBoost, cBoost, bBoost);
                        row = hero.getRow() - 1;
                        col = hero.getCol();
                        if (hero.canAttack(monsters) != null) {
                            System.out.println("You cannot pass by a monster without attacking it!");
                        } else {
                            if (!hero.makeMove(this.playerTeam, cells, hero, row, col, kBoost, bBoost, cBoost))
                                continue;
                            alreadyMoved = true;
                        }
                    }
                    break;
                case "a":
                    if(!alreadyMoved) {
                        this.checkBoost(hero, kBoost, cBoost, bBoost);
                        row = hero.getRow();
                        col = hero.getCol() - 1;
                        if (!hero.makeMove(this.playerTeam, cells, hero, row, col, kBoost, bBoost, cBoost)) continue;
                        alreadyMoved = true;
                    }
                    break;
                case "s":
                    if(!alreadyMoved) {
                        this.checkBoost(hero, kBoost, cBoost, bBoost);
                        row = hero.getRow() + 1;
                        col = hero.getCol();
                        if (!hero.makeMove(this.playerTeam, cells, hero, row, col, kBoost, bBoost, cBoost)) continue;
                        alreadyMoved = true;
                    }
                    break;
                case "d":
                    if(!alreadyMoved) {
                        row = hero.getRow();
                        col = hero.getCol() + 1;
                        if (!hero.makeMove(this.playerTeam, cells, hero, row, col, kBoost, bBoost, cBoost)) continue;
                        alreadyMoved = true;
                    }
                    break;
                case "k":
                    if(!alreadyMoved) {
                        if (hero.canAttack(monsters) != null) {
                            alreadyMoved = true;
                            hero.showInfoBattle();
                            System.out.println(Message.fight);
                            System.out.println("Attack Monster!");
                            Monster monster = hero.canAttack(monsters);
                            hero.attack(monster);

                        } else {
                            System.out.println("There is no monster in your attack range!");
                            continue;
                        }
                    }
                    break;
                case "c":
                    if(!alreadyMoved) {
                        System.out.println("Cast Spell!");
                        if (hero.canAttack(monsters) != null) {

                            Monster monster = hero.canAttack(monsters);
                            if(!hero.castSpell(input, monster)){
                                continue;
                            }else{
                                alreadyMoved = true;
                            }
                        } else {
                            System.out.println("There is no monster in your attack range!");
                            continue;
                        }
                    }
                    break;
                case "t":
                    if(!alreadyMoved) {
                        System.out.println("Please select row you want to teleport");
                        String r = UtilCheckInput.checkInput(input, 1, 8);
                        System.out.println("Please select col you want teleport");
                        String c = UtilCheckInput.checkInput(input, 1, 8);
                        row = Integer.parseInt(r) - 1;
                        col = Integer.parseInt(c) - 1;
                        if (!hero.telePort(cells, row, col, playerTeam, hero)) continue;
                        alreadyMoved = true;
                    }
                    break;
                case "b":
                    if(!alreadyMoved) {
                        System.out.println("Back to base...");
                        hero.backToBase(playerTeam, cells, hero);
                        alreadyMoved = true;
                    }
                    break;
                case "m":
                    if (cells[hero.getRow()][hero.getCol()] instanceof HeroNexusCell) {
                        this.trading(this.input, hero);
                    }
                    else {
                        System.out.println("This is not a market Cell!");
                    }
                    break;
                case "e":
                    System.out.println("Checking Inventory...");
                    hero.getInventory().changeEquipment(this.input);
                    break;
                case "i":
                    for (Integer integer : this.playerTeam.getTeam().keySet()) {
                        System.out.println(this.playerTeam.getHero(integer).getName());
                        this.playerTeam.getHero(integer).disPlay();
                    }
                    break;
                case "f":
                    countTurn++;

                    if (countTurn == 3) {
                        countTurn = 0;
                        roundNum++;
                        this.addNewMonster(cells);
                        //If all heroes made a move, then all monsters move forward

                        List<Hero> heroes = new ArrayList<>(this.playerTeam.getTeam().values());
                        this.actionAllMonsters(heroes, monsters, cells);

                        //if hero die respawn in base
                        if(!hero.isAlive()){
                            hero.backToBase(playerTeam, cells, hero);
                        }
                        //reset to first hero
                        hero = playerTeam.getHero(0);
                    } else {
                        hero = playerTeam.getHero(playerTeam.getHeroID(hero) + 1);
                    }
                    alreadyMoved = false;
                    //player team regain
                    playerTeam.regain();
                    break;
                case "q":
                    play = false;
                    break;
                default:
                    System.out.println("\n Invalid Input \n");

            }

        }
        System.out.println("Good Bye!");
    }

    //make monster move
    public void actionAllMonsters(List<Hero> heroes, List<Monster> m, Cell[][] cells){
        System.out.println(Message.demon1);
        System.out.println("Monsters' round!");
        for (Monster monster : m) {
            if(monster.canAttack(heroes) != null) {
                Hero h = monster.canAttack(heroes);
                monster.attack(h);
            }else{
                int row = monster.getRow() + 1;
                int col = monster.getCol();
                monster.makeMove(cells, row, col);
            }
        }
    }

    //Check hero current boost state, if there is any boost, remove it before make a move
    public void checkBoost(Hero h, double k, double c, double b){
        if(h.getB()){
            h.setDexterity(h.getDexterity() - b);
            h.setB(false);
        }
        else if (h.getC()){
            h.setAgility(h.getAgility() - c);
            h.setC(false);
        }
        else if (h.getK()){
            h.setStrength(h.getStrength() - k);
            h.setK(false);
        }

    }


    //every 4 round add 3 new monsters
    public void addNewMonster(Cell[][] cells){
        //every 2 round renew monster
        if(roundNum %  4 == 0){
            MonsterTeam newMonsters = new MonsterTeam(playerTeam);
            List<Monster> newMTeam = newMonsters.getMonsters();
            newMTeam.get(0).setMonsterPos(cells[0][0], "M ", 0, 0);
            newMTeam.get(1).setMonsterPos(cells[0][3], "M ", 0, 3);
            newMTeam.get(2).setMonsterPos(cells[0][6], "M ", 0, 6);
            monsters.addAll(newMTeam);
            System.out.println("The monsters have respawned");
        }
    }


    //enter market
    public void trading(Scanner input, Hero hero) {
        this.market.sellBuyItem(input, hero);
    }

    private void showMapInfo() {
        System.out.println("w = move up | s = move down | a = move left | d = move right");
        System.out.println("f = finish current hero turn");
        System.out.println("e = heroes inventory | i = info | m = Enter Market(Only when you at Market Cell)");
        System.out.println("k = attack | c = cast spell");
        System.out.println("t = teleport | b = back to base");
        System.out.println("q = quit the game");
        System.out.println("Hero could change their equipment or drink available potion in inventory menu");
    }

    public void end(){
        System.out.println(Message.win);
        System.out.println("Congratulation!");
        System.out.println("Hero playerteam have destroy the monster's nexus");
        System.out.println("The game is over, Thanks for playing");
        System.exit(0);
    }
}