import java.util.Arrays;

/**
 * abstract class for each cell, inaccessible,
 * marketCell and common cell needs to extend this class
 */
public abstract class Cell
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

    public void printCell(){
        for (String[] po : this.pos) {
            System.out.println(Arrays.toString(po));
        }
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
        MARKET_CELL;
    }
}