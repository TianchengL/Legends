import java.util.Iterator;
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
    private final int boardSize;
    private final Market market;

    public GameController(Scanner input, HeroSelectionController hs) {
        this.input = input;
        this.team = hs.getPlayerTeam();
        this.boardSize = hs.getBoardSize();
        this.board = new Board(this.boardSize, this.boardSize);
        this.market = new Market();
        this.init();
    }

    //init map, random set player to a common cell or market cell
    public void init() {
        Cell[][] map = this.board.getCells();
        int[] pos = this.validPlayerPos(map);
        this.board.setCells("P", pos[0], pos[1]);
        this.team.setRowCol(pos[0], pos[1]);
    }

    //player game according to user input
    public void playGame() {
        while (true) {
            this.board.printBoard();
            Cell[][] cells = this.board.getCells();
            this.showMapInfo();
            String s = this.input.next();
            if ("w".equalsIgnoreCase(s)) {
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
                if (cells[this.team.getRow()][this.team.getCol()] instanceof MarketCell) {
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

    //player move to next cell
    public void makeMove(Cell[][] cells, int row,int col) {
        if (this.checkBorder(row, col)) {
            if (cells[row][col] instanceof InaccessibleCell) {
                System.out.println("cannot enter # (inaccessible)!");
            }
            else if (cells[row][col] instanceof CommonCell) {
                this.board.setCells("P", row, col);
                this.board.setCells(this.team.checkPosType(cells), this.team.getRow(), this.team.getCol());
                this.team.setRowCol(row, col);
                this.fight(this.input);
            }
            else if (cells[row][col] instanceof MarketCell) {
                this.board.setCells("P", row, col);
                this.board.setCells(this.team.checkPosType(cells), this.team.getRow(), this.team.getCol());
                this.team.setRowCol(row, col);
                System.out.println("You have entered a market place!");
            }
        }
        else {
            System.out.println("You cannot move to outside of the board!");
        }
    }

    private boolean checkBorder(int row, int col) {
        return row < this.boardSize && row >= 0 && col < this.boardSize && col >= 0;
    }

    //initial player position cannot be blocked in all direction
    private int[] validPlayerPos(Cell[][] cells) {
        int r = (int)(Math.random() * this.boardSize);
        int c = (int)(Math.random() * this.boardSize);
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
            r = (int)(Math.random() * this.boardSize);
            c = (int)(Math.random() * this.boardSize);
        }
        return new int[] { r, c };
    }
}