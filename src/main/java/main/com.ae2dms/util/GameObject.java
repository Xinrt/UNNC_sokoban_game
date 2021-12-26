package main.com.ae2dms.util;

/**
 * Store game objects and assign unique character to each object
 */
public enum GameObject {
    WALL('W'),
    FLOOR(' '),
    CRATE('C'),             // boxes
    DIAMOND('D'),           // aim point
    KEEPER('S'),            // character
    DOOR('M'),              // door
    KEEPERRED('H'),           // the second keeper
    ICE('I'),                // ice
    CRATE_ON_DIAMOND('O'),  // arrive
    DEBUG_OBJECT('=');

    public final char symbol;
    /**
     * assign values to final variable
     *
     * @param symbol        a character
     */
    GameObject(final char symbol) {
        this.symbol = symbol;
    }

    /**
     * Transfer game object from character to game object
     *
     * @param c         A character
     * @return          A game object
     */
    public static GameObject fromChar(char c) {
        for (GameObject t : GameObject.values()) {
            if (Character.toUpperCase(c) == t.symbol) {
                return t;
            }
        }
        return WALL;
    }

    /**
     * Get char symbol of a game object
     *
     * @return      A character
     */
    public char getCharSymbol() {
        return symbol;
    }
}