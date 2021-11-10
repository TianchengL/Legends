/**
 * common cell class extend cell
 */
public class CommonCell extends Cell
{
    public CommonCell(int row, int col,  String value) {
        super(row, col, value);
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