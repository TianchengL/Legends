/**
 * abstract class for each cell, inaccessible,
 * marketCell and common cell needs to extend this class
 */
public abstract class Cell
{
    private int row;
    private int col;
    private String value;

    public Cell(int row, int col, String value) {
        this.row = row;
        this.col = col;
        this.value = value;
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