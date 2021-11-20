/**
 * common cell class extend cell
 */
public class PlainCell extends Cell
{
    public PlainCell(String value) {
        super(value);
    }

    @Override
    public CellType getCellType() {
        return CellType.COMMON_CELL;
    }

    @Override
    public String toString() {
        return " ";
    }
}