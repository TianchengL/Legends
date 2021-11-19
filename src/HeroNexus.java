public class HeroNexus extends Cell{

    public HeroNexus(int row, int col, String value){
        super(row,col,value);
    }

    @Override
    public CellType getCellType() {
        return CellType.HERONEXUS_CELL;
    }

    @Override
    public String toString() {
        return "HN";
    }
}
