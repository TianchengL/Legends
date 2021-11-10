/**
 * represent inaccessible cell
 */
public class InaccessibleCell extends Cell {


    public InaccessibleCell(int row, int col, String value) {
        super(row, col, value);
    }


    public CellType getCellType(){
        return CellType.INACCESSIBLE_CELL;
    }


    @Override
    public String toString() {
        return "#";
    }
}
