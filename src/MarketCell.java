/**
 * concrete class for cell that represent market
 */
public class MarketCell extends Cell {

    public MarketCell(int row, int col, String value) {
        super(value);

    }


    public CellType getCellType(){
        return CellType.MARKET_CELL;
    }

    @Override
    public String toString() {
        return "M";
    }
}
