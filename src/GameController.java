import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * mvc pattern,
 * game controller control over all gaming precess
 */
public class GameController extends RpgGame
{
    private final Board board;
    private final Scanner input;
    private final PlayerTeam playerTeam;
    private MonsterTeam monsterTeam;
    private final Market market;
    private final Map<Integer, Hero> heroes;
    private List<Monster> monsters;

    public GameController(Scanner input, HeroSelectionController hs) {
        this.input = input;
        this.playerTeam = hs.getPlayerTeam();
        this.monsterTeam = new MonsterTeam(this.playerTeam);
        this.board = new Board(8,8);
        this.market = new Market();
        this.heroes = playerTeam.getTeam();
        this.monsters = monsterTeam.getMonsters();

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
        monsters.get(0).setMonsterPos(map[0][0], "M", 0, 0);
        monsters.get(1).setMonsterPos(map[0][3], "M", 0, 3);
        monsters.get(2).setMonsterPos(map[0][6], "M", 0, 6);

    }

    //player game according to user input
    public void playGame() {

        Hero hero = this.heroes.get(0);
        boolean alreadyMoved = false;
        int count = 1;
        while (true) {
            System.out.println();
            this.board.printBoard();
            Cell[][] cells = this.board.getCells();
            this.showMapInfo();
            if(count% 12 == 0){
                MonsterTeam newMonsters = new MonsterTeam(playerTeam);
                List<Monster> newMTeam = newMonsters.getMonsters();
                for(int i=0; i< newMTeam.size();i++){
                    Monster m = newMTeam.get(i);
                    monsters.add(m);
                }
                System.out.println("The monsters have respawned");
            }
            System.out.println("In hero "+hero.name+ "'s round");
            String s = this.input.next();

            if(!alreadyMoved) {
                if ("w".equalsIgnoreCase(s)) {
                    //change playerteam to hero
                    int row = hero.getRow() - 1;
                    int col = hero.getCol();
                    hero.makeMove(this.playerTeam, cells, hero, row, col);
                } else if ("s".equalsIgnoreCase(s)) {
                    int row = hero.getRow() + 1;
                    int col = hero.getCol();
                    hero.makeMove(this.playerTeam, cells, hero, row, col);
                } else if ("a".equalsIgnoreCase(s)) {
                    int row = hero.getRow();
                    int col = hero.getCol() - 1;
                    hero.makeMove(this.playerTeam, cells, hero, row, col);
                } else if ("d".equalsIgnoreCase(s)) {
                    int row = hero.getRow();
                    int col = hero.getCol() + 1;
                    hero.makeMove(this.playerTeam, cells, hero, row, col);
                }  //attack
                else if ("k".equalsIgnoreCase(s)){
                    //hero attack
                    System.out.println("Attack Monster!");
                    Monster monster = hero.canAttack(this.playerTeam);
                    hero.attack(monster);

                }else if("c".equalsIgnoreCase(s)){
                    System.out.println("Cast Spell!");
                    Monster monster = hero.canAttack(this.playerTeam);
                    hero.castSpell(input, monster);
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
                if (playerTeam.getHeroID(hero) == 2) {
                    //If all heroes made a move, then all monsters move forward
                    for(int i =0; i< monsters.size();i++){
                        Monster m = monsters.get(i);
                        int row = m.getRow();
                        int col = m.getCol();
                        m.makeMove(monsterTeam,cells,row+1,col);
                    }
                    hero = playerTeam.getHero(0);
                } else {
                    hero = playerTeam.getHero(playerTeam.getHeroID(hero) + 1);
                }
                alreadyMoved = false;
                //monster make move



            }

            else {
                if ("q".equalsIgnoreCase(s)) {
                    break;
                }
                System.out.println("\nInvalid Input!\n");
            }

        }
        System.out.println("Good Bye!");
    }

    //enter market
    public void trading(Scanner input, Hero hero) {
        this.market.sellBuyItem(input, hero);
    }

    //0.5 chance to enter fight
    public void attack(Scanner input) {

    }

    private void showMapInfo() {
        System.out.println("w = move up | s = move down | a = move left | d = move right");
        System.out.println("f = finish current hero turn");
        System.out.println("e = heroes inventory | i = info | m = Enter Market(Only when you at Market Cell)");
        System.out.println("k = attack | c = cast spell");
        System.out.println("q = quit the game");
        System.out.println("Hero could change their equipment or drink available potion in inventory menu");
    }


    //initial player position cannot be blocked in all direction
    private int[] validPlayerPos(Cell[][] cells) {
        int r = (int)(Math.random() * 8);
        int c = (int)(Math.random() * 8);
        while (true) {
            if (!(cells[r][c] instanceof InaccessibleCell)) {
                if (r - 1 >= 0 && !(cells[r - 1][c] instanceof InaccessibleCell)) {
                    break;
                }
                if ((r + 1 < cells.length && !(cells[r + 1][c] instanceof InaccessibleCell)) ||
                        (c - 1 >= 0 && !(cells[r][c - 1] instanceof InaccessibleCell))
                        || (c + 1 < cells.length && !(cells[r][c + 1] instanceof InaccessibleCell))) {
                    continue;
                }
            }
            r = (int)(Math.random() * 8);
            c = (int)(Math.random() * 8);
        }
        return new int[] { r, c };
    }

    public void end(){
        System.out.println("Congratulation!");
        System.out.println("Hero playerteam have destroy the monster's nexus");
        System.out.println("The game is over, Thanks for playing");
        System.exit(0);
    }
}