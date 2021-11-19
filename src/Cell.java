import java.util.Arrays;

/**
 * abstract class for each cell, inaccessible,
 * marketCell and common cell needs to extend this class
 */
public class Cell
{

    private String value;
    private final String[][] pos;

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

    //print each cell
    public void printCell(){
        for (String[] po : pos) {
            for (int c = 0; c < pos[0].length; c++) {
                System.out.print(po[c]);
            }
            System.out.println();
        }
    }

    //used to set Hero and monster's position
    //pos = 0 represent left of the cell, 1 is right of the cell
    public void setCellHeroPos(String hero){
        this.pos[1][1] = " " + hero;
    }
    public void setCellMonPos(String m){
        this.pos[1][3] = " " + m;
    }
    //clear hero cell
    public void resetHeroCell(){
        this.pos[1][1] = "   ";
    }
    //clear monster cell
    public void resetMCell(){
        this.pos[1][3] = "   ";
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
//        //Cell n = new Cell(1, 1, "N");
//        n.setCellHeroPos("H1");
//        n.setCellMonPos("M1");
//        n.resetHeroCell();
//        n.resetMCell();
//        n.printCell();

    }
}