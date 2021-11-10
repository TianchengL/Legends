/**
 * could be implemented by item that will have some use time limitation
 * leave this class hero for extendable purpose
 */
public interface usable {

    boolean getUsed();
    void setUse(boolean use);
}
