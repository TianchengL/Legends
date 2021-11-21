import java.util.Arrays;

/**
 * abstract class for each cell, inaccessible,
 * marketCell and common cell needs to extend this class
 */
public class Cell
{

    private String value;
    private final String[][] pos;
    //check if this cell contains hero or is monster
    private boolean isHero, isMonster;
    //check if this cell has been explored
    private boolean isExplored;


    public Cell(String value) {
        this.value = value;
        pos = new String[3][5];
        initCell();
    }

    //init start cell
    private void initCell(){
        for(int r = 0; r < pos.length; r++ ){
            for(int c = 0; c < pos[0].length; c++){
                //even row
                if( r % 2 == 0){
                    //even col
                    if( c % 2 == 0 ){
                        pos[r][c] = this.value;
                    }else{
                        pos[r][c] = " - ";
                    }
                }else{
                    if(c == 0 || c == pos[0].length - 1){
                        pos[r][c] = "|";
                    }else if( c == pos[0].length / 2){
                        pos[r][c] = " ";
                    }else{
                        pos[r][c] = "   ";
                    }
                }
            }
        }
    }

    public String[][] getPos() {
        return pos;
    }
    public boolean isHero() {
        return isHero;
    }
    public void setIsHero(boolean hero) {
        isHero = hero;
    }
    public boolean isMonster() {
        return isMonster;
    }
    public void setIsMonster(boolean monster) {
        isMonster = monster;
    }
    public boolean isExplored() {
        return isExplored;
    }
    public void setExplored(boolean explored) {
        isExplored = explored;
    }

    //used to set Hero and monster's position
    //pos = 0 represent left of the cell, 1 is right of the cell
    public void setCell(String str, int row, int col){
        this.pos[row][col] = str;
    }
    public void setCellHeroPos(String hero){
        this.pos[1][1] = " " + hero;
        this.setIsHero(true);
    }
    public void setCellMonPos(String m){
        this.pos[1][3] = " " + m;
        this.setIsMonster(true);
    }
    //clear hero cell
    public void resetHeroCell(){
        this.pos[1][1] = "   ";
        this.setIsHero(false);
    }
    //clear monster cell
    public void resetMCell(){
        this.pos[1][3] = "   ";
        this.setIsMonster(false);
    }

    //getter and setter
    public CellType getCellType() {
        return CellType.COMMON_CELL;
    }
    public void setCellValue(String str) {
        this.value = str;
    }
    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return " ";
    }

    enum CellType
    {
        COMMON_CELL,
        INACCESSIBLE_CELL,
        MARKET_CELL,
        CAVE_CELL,
        BUSH_CELL,
        KOULOU_CELL,
        HERONEXUS_CELL,
        MONSTERNEXUS_CELL;

    }

    public static void main(String[] args) {
        Cell n = new Cell( "N");
        Cell m = new Cell("M");
        n.setCellHeroPos("H1");
        n.setCellMonPos("M1");
        //n.resetHeroCell();
        //n.resetMCell();
        // n.printCell();
//        m.printCell();

    }
}