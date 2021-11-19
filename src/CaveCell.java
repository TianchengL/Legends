public class CaveCell extends Cell{
    public CaveCell(int row, int col, String value){
        super(value);
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
