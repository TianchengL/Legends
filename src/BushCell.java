// Bush cell class extends to cell class
public class BushCell extends Cell{

    public BushCell(String value){
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
