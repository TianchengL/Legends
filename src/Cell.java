import java.util.Arrays;

/**
 * abstract class for each cell, inaccessible,
 * marketCell and common cell needs to extend this class
 */
public class Cell
{
    private int row;
    private int col;
    private String value;
    private String[][] pos;

    public Cell(int row, int col, String value) {
        this.row = row;
        this.col = col;
        this.value = value;
        pos = new String[3][5];
        initCell();
    }

    //init start cell
    private void initCell(){
        for(int r = 0; r < pos.length; r++ ){
            for(int c = 0; c < pos[0].length; c++){
                //even row
                if( r % 2 == 0){
                    //even col
                    if( c % 2 == 0 ){
                        pos[r][c] = this.value;
                    }else{
                        pos[r][c] = " - ";
                    }
                }else{
                    if(c == 0 || c == pos[0].length - 1){
                        pos[r][c] = "|";
                    }else if( c == pos[0].length / 2){
                        pos[r][c] = " ";
                    }else{
                        pos[r][c] = "   ";
                    }
                }
            }
        }
    }

    //print each cell
    public void printCell(){
        for (String[] po : pos) {
            for (int c = 0; c < pos[0].length; c++) {
                System.out.print(po[c]);
            }
            System.out.println();
        }
    }

    //used to set Hero and monster's position
    //pos = 0 represent left of the cell, 1 is right of the cell
    public void setCellPos(int pos){

    }


    //getter and setter
    public int getRow() {
        return this.row;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public int getCol() {
        return this.col;
    }
    public void setCol(int col) {
        this.col = col;
    }
    public CellType getCellType() {
        return CellType.COMMON_CELL;
    }
    public void setCellValue(String str) {
        this.value = str;
    }
    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return " ";
    }

    enum CellType
    {
        COMMON_CELL,
        INACCESSIBLE_CELL,
        MARKET_CELL,
        CAVE_CELL,
        BUSH_CELL,
        KOULOU_CELL,
        HERONEXUS_CELL,
        MONSTERNEXUS_CELL;

    }

    public static void main(String[] args) {
        Cell n = new Cell(1, 1, "N");
        n.printCell();

    }
}