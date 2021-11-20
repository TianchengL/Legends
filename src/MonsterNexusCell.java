public class MonsterNexusCell extends Cell{

    public MonsterNexusCell(String value){
        super(value);
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
