public class MonsterNexus extends Cell{

    public MonsterNexus(int row, int col, String value){
        super(row,col,value);
    }

    @Override
    public CellType getCellType() {
        return CellType.MONSTERNEXUS_CELL;
    }

    @Override
    public String toString() {
        return "MN";
    }
}
