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

}
