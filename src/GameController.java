import java.util.Iterator;
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
    private final PlayerTeam team;
    private final Market market;
    private final Map<Integer, Hero> heroes;

    public GameController(Scanner input, HeroSelectionController hs) {
        this.input = input;
        this.team = hs.getPlayerTeam();
        this.board = new Board();
        this.market = new Market();
        this.heroes = team.getTeam();
        this.init();
    }

    //init map, random set player to a common cell or market cell
    public void init() {
        Cell[][] map = this.board.getCells();
        this.board.setCells("X", 7, 0);
        this.team.setRowCol(7, 0);

        //set each hero initial pos
        heroes.get(0).setHeroPos(0, 0);
        heroes.get(1).setHeroPos(3,3);
        heroes.get(2).setHeroPos(6,6);
    }

    //player game according to user input
    public void playGame() {
        while (true) {
            this.board.printBoard();
            Cell[][] cells = this.board.getCells();
            this.showMapInfo();
            String s = this.input.next();
            if ("w".equalsIgnoreCase(s)) {
                //change team to hero
                int row = this.team.getRow() - 1;
                int col = this.team.getCol();
                this.makeMove(cells, row, col);
            }
            else if ("s".equalsIgnoreCase(s)) {
                int row = this.team.getRow() + 1;
                int col = this.team.getCol();
                this.makeMove(cells, row, col);
            }
            else if ("a".equalsIgnoreCase(s)) {
                int row = this.team.getRow();
                int col = this.team.getCol() - 1;
                this.makeMove(cells, row, col);
            }
            else if ("d".equalsIgnoreCase(s)) {
                int row = this.team.getRow();
                int col = this.team.getCol() + 1;
                this.makeMove(cells, row, col);
            }
            else if ("m".equalsIgnoreCase(s)) {
                if (cells[this.team.getRow()][this.team.getCol()] instanceof HeroNexus) {
                    System.out.println("Which hero want to enter market? Enter Hero ID");
                    this.team.displayName();
                    int id = Integer.parseInt(UtilCheckInput.checkInput(this.input,
                            1, this.team.getTeamSize()));
                    this.trading(this.input, this.team.getHero(id - 1));
                }
                else {
                    System.out.println("This is not a market Cell!");
                }
            }
            else if ("e".equalsIgnoreCase(s)) {
                System.out.println("Please select ID of hero to enter his inventory ");
                this.team.displayName();
                int id = Integer.parseInt(UtilCheckInput.checkInput(this.input, 1,
                        this.team.getTeamSize()));
                Hero hero = this.team.getHero(id - 1);
                hero.getInventory().changeEquipment(this.input);
            }
            else if ("i".equalsIgnoreCase(s)) {
                for (Integer integer : this.team.getTeam().keySet()) {
                    System.out.println(this.team.getHero(integer).getName());
                    this.team.getHero(integer).disPlay();
                }
            }
            //finish current hero turn
            else if ("f".equalsIgnoreCase(s)) {

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
    public void fight(Scanner input) {
        if (Math.random() > 0.5) {
            Fight f = new Fight(this.team);
            f.roundPlay(input);
        }
    }

    private void showMapInfo() {
        System.out.println("w = move up | s = move down | a = move left | d = move right");
        System.out.println("e = heroes inventory | i = info | m = Enter Market(Only when you at Market Cell)");
        System.out.println("q = quit the game");
        System.out.println("Hero could change their equipment or drink available potion in inventory menu");
    }

    //current turn hero move to next cell
    public void makeMove(Cell[][] cells, int row,int col) {
        if (this.checkBorder(row, col)) {
            if (cells[row][col] instanceof InaccessibleCell) {
                System.out.println("cannot enter # (inaccessible)!");
            }
            else if (cells[row][col] instanceof CommonCell) {
                //X refers to hero
                this.board.setCells("X", row, col);
                this.board.setCells(this.team.checkPosType(cells), this.team.getRow(), this.team.getCol());
                this.team.setRowCol(row, col);
                this.fight(this.input);
            }
            else if (cells[row][col] instanceof CaveCell) {
                this.board.setCells("X", row, col);
                this.board.setCells(this.team.checkPosType(cells), this.team.getRow(), this.team.getCol());
                this.team.setRowCol(row, col);
                this.fight(this.input);
            }
            else if (cells[row][col] instanceof KoulouCell) {
                this.board.setCells("X", row, col);
                this.board.setCells(this.team.checkPosType(cells), this.team.getRow(), this.team.getCol());
                this.team.setRowCol(row, col);
                this.fight(this.input);
            }
            else if (cells[row][col] instanceof BushCell) {
                this.board.setCells("X", row, col);
                this.board.setCells(this.team.checkPosType(cells), this.team.getRow(), this.team.getCol());
                this.team.setRowCol(row, col);
                this.fight(this.input);
            }
            else if (cells[row][col] instanceof HeroNexus) {
                this.board.setCells("X", row, col);
                this.board.setCells(this.team.checkPosType(cells), this.team.getRow(), this.team.getCol());
                this.team.setRowCol(row, col);
                System.out.println("You have entered a market place!");
            }
            else if (cells[row][col] instanceof MonsterNexus) {
                this.board.setCells("X", row, col);
                this.board.setCells(this.team.checkPosType(cells), this.team.getRow(), this.team.getCol());
                this.team.setRowCol(row, col);
                this.end();
            }
        }
        else {
            System.out.println("You cannot move to outside of the board!");
        }
    }

    private boolean checkBorder(int row, int col) {
        return row < 8 && row >= 0 && col < 8 && col >= 0;
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
        System.out.println("Hero team have destroy the monster's nexus");
        System.out.println("The game is over, Thanks for playing");
        System.exit(0);
    }
}