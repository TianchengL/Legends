/**
 * represent inaccessible cell
 */
public class InaccessibleCell extends Cell {


    public InaccessibleCell(String value) {
        super(value);
    }


    public CellType getCellType(){
        return CellType.INACCESSIBLE_CELL;
    }


    @Override
    public String toString() {
        return "#";
    }
}
