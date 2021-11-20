/**
 * common cell class extend cell
 */
public class CommonCell extends Cell
{
    public CommonCell(String value) {
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