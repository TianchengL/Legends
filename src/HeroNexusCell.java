// HeroNexusCell class extends to the cell class
public class HeroNexusCell extends Cell{
    public HeroNexusCell(String value){
        super(value);
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
