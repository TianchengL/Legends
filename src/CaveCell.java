public class CaveCell extends Cell{
    public CaveCell(int row, int col, String value){
        super(row,col,value);
    }

    @Override
    public CellType getCellType() {
        return CellType.CAVE_CELL;
    }

    @Override
    public String toString() {
        return "C";
    }
}
