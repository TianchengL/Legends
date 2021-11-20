/**
 * an abstract class for hero and monster or any other character in game
 */
public abstract class Character {

    public String name;

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

    private int row;
    private int col;

   // public abstract boolean canAttack();   //judge if there is an opposite character in its attack range



}
