/**
 * an abstract class for hero and monster or any other character in game
 */
public abstract class Character {

    public String name;
    //current character position
    private int row;
    private int col;


    public Character(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public int getCol() {
        return col;
    }
    public void setCol(int col) {
        this.col = col;
    }
    public void setPos(int row, int col){
        this.row = row;
        this.col = col;
    }



    public abstract boolean canAttack(Team team);   //judge if there is an opposite character in its attack range



}
