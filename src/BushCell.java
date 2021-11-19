public class BushCell extends Cell{

    public BushCell(int row, int col, String value){
        super(value);
    }

    @Override
    public Cell.CellType getCellType() {
        return CellType.BUSH_CELL;
    }

    @Override
    public String toString() {
        return "B";
    }
}
