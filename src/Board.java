/**
 * Board object used to init bunch of cells
 * print cells on command line
 */
public class Board {

    private Cell[][] cells;
    private final int row;
    private final int col;
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_PURPLE = "\u001B[35m";


    public Board(int row, int col){
        this.row = row;
        this.col = col;
        initCells(row, col);
    }

    //random create 20% block cell, 50% common cell, 30% market cell
    public void initCells(int row, int col){
        cells = new Cell[row][col];
        int size = row * col;
        int numBlock = (int)Math.round(size * 0.2);
        int numMarket = (int)Math.round(size * 0.3);

        while(size > 0){
            int r = (int) (Math.random() * row);
            int c = (int) (Math.random() * col);

            if(numBlock > 0 && cells[r][c] == null){
                cells[r][c] = new InaccessibleCell(r, c, "#");
                numBlock--;
                size--;
            }else if(numMarket > 0 && cells[r][c] == null){
                cells[r][c] = new MarketCell(r, c, "M");
                numMarket--;
                size--;
            }else if(cells[r][c] == null){
                cells[r][c] = new CommonCell(r,c, " ");
                size--;
            }
        }

    }

    //print current board
    public void printBoard(){

        for( int r = 0; r < row; r++){
            for( int c = 0; c < col; c++) {
                System.out.print(ANSI_PURPLE + "||  " + ANSI_RESET);
                if(cells[r][c] instanceof InaccessibleCell){
                    System.out.print(ANSI_RED + cells[r][c].getValue() + ANSI_RESET);
                }else if(cells[r][c] instanceof MarketCell && !cells[r][c].getValue().equals("P")){
                    System.out.print(ANSI_GREEN + cells[r][c].getValue() + ANSI_RESET);
                }else{
                    System.out.print(ANSI_CYAN + cells[r][c].getValue() + ANSI_RESET);
                }
                System.out.print("  ");
            }
            System.out.print(ANSI_PURPLE + "||"+ ANSI_RESET);
            System.out.println();
        }
    }

    //set each hero initial lane
    public void setHeroLane(){}

    public void setMonsterLane(){};

    public void setCells(String str, int rol, int col){
        cells[rol][col].setCellValue(str);
    }
    public Cell[][] getCells() {
        return cells;
    }

}
