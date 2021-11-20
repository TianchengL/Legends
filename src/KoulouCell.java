public class KoulouCell extends Cell{

    public KoulouCell(String value){
        super(value);
    }

    @Override
    public CellType getCellType() {
        return CellType.KOULOU_CELL;
    }

    @Override
    public String toString() {
        return "K";
    }
}
