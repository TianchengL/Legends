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

        //initialize monster pos
        monsters.get(0).setMonsterPos(map[0][0], "M ", 0, 0);
        monsters.get(1).setMonsterPos(map[0][3], "M ", 0, 3);
        monsters.get(2).setMonsterPos(map[0][6], "M ", 0, 6);

    }

    //player game according to user input
    public void playGame() {

        Hero hero = this.heroes.get(0);
        boolean alreadyMoved = false;
        int countTurn = 0;
        while (true) {
            System.out.println();
            this.board.printBoard();
            Cell[][] cells = this.board.getCells();
            System.out.println("In hero "+hero.name+ "'s round");
            System.out.println();
            this.showMapInfo();
            hero.disPlay();
            String s = this.input.next();
            double kBoost = hero.getStrength() * 0.1;
            double bBoost = hero.getDexterity()*0.1;
            double cBoost = hero.getAgility()*0.1;
            if(!alreadyMoved) {
                if ("w".equalsIgnoreCase(s)) {
                    //change playerteam to hero
                    int row = hero.getRow() - 1;
                    int col = hero.getCol();
                    hero.makeMove(this.playerTeam, cells, hero, row, col,kBoost,bBoost,cBoost);
                } else if ("s".equalsIgnoreCase(s)) {
                    int row = hero.getRow() + 1;
                    int col = hero.getCol();
                    hero.makeMove(this.playerTeam, cells, hero, row, col,kBoost,bBoost,cBoost);
                } else if ("a".equalsIgnoreCase(s)) {
                    int row = hero.getRow();
                    int col = hero.getCol() - 1;
                    hero.makeMove(this.playerTeam, cells, hero, row, col,kBoost,bBoost,cBoost);
                } else if ("d".equalsIgnoreCase(s)) {
                    int row = hero.getRow();
                    int col = hero.getCol() + 1;
                    hero.makeMove(this.playerTeam, cells, hero, row, col,kBoost,bBoost,cBoost);
                }  //attack
                else if ("k".equalsIgnoreCase(s)){
                    //hero attack
                    hero.showInfoBattle();
                    System.out.println("Attack Monster!");
                    Monster monster = hero.canAttack(monsters);
                    hero.attack(monster);

                }else if("c".equalsIgnoreCase(s)){
                    System.out.println("Cast Spell!");
                    Monster monster = hero.canAttack(monsters);
                    hero.castSpell(input, monster);
                }else if("t".equalsIgnoreCase(s)){
                    System.out.println("Please select row you want to teleport");
                    String r = UtilCheckInput.checkInput(input, 1, 8);
                    System.out.println("Please select col you want teleport");
                    String c = UtilCheckInput.checkInput(input, 1, 8);
                    int row = Integer.parseInt(r) - 1;
                    int col = Integer.parseInt(c) - 1;
                    if(!hero.telePort(cells, row, col, playerTeam, hero))
                        continue;
                }else if("b".equalsIgnoreCase(s)){
                    System.out.println("Back to base...");
                    hero.backToBase(playerTeam, cells, hero);
                }
                alreadyMoved = true;
            } else if ("m".equalsIgnoreCase(s)) {
                if (cells[hero.getRow()][hero.getCol()] instanceof HeroNexusCell) {
                    this.trading(this.input, hero);
                }
                else {
                    System.out.println("This is not a market Cell!");
                }
            }
            else if ("e".equalsIgnoreCase(s)) {
                System.out.println("Checking Inventory...");
                hero.getInventory().changeEquipment(this.input);
            }
            else if ("i".equalsIgnoreCase(s)) {
                for (Integer integer : this.playerTeam.getTeam().keySet()) {
                    System.out.println(this.playerTeam.getHero(integer).getName());
                    this.playerTeam.getHero(integer).disPlay();
                }
            }
            //finish current hero turn
            else if ("f".equalsIgnoreCase(s)) {
                countTurn++;

                if (countTurn == 3) {
                    countTurn = 0;
                    roundNum++;
                    this.addNewMonster(cells);
                    //If all heroes made a move, then all monsters move forward

                    //add new monster
                    this.moveAllMonster(monsters, cells);
                    //monster attack
                    List<Hero> heroes = new ArrayList<>(this.playerTeam.getTeam().values());
                    for (Monster monster : monsters) {
                        Hero h = monster.canAttack(heroes);
                        monster.attack(h);
                    }
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

            } else {
                if ("q".equalsIgnoreCase(s)) {
                    break;
                }
                System.out.println("\nInvalid Input!\n");
            }
        }
        System.out.println("Good Bye!");
    }

    //make monster move
    public void moveAllMonster(List<Monster> m, Cell[][] cells){

        for (Monster monster : m) {
            int row = monster.getRow() + 1;
            int col = monster.getCol();
            monster.makeMove(cells, row, col);
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
        System.out.println("Congratulation!");
        System.out.println("Hero playerteam have destroy the monster's nexus");
        System.out.println("The game is over, Thanks for playing");
        System.exit(0);
    }
}