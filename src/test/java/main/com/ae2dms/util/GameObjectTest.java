package main.com.ae2dms.util;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameObjectTest {

    GameObject wall;
    GameObject floor;
    GameObject crate;
    GameObject diamond;
    GameObject keeper;
    GameObject keeperRed;
    GameObject door;
    GameObject ice;
    GameObject crateOnDiamond;
    GameObject debugObject;


    @Before
    public void setUp() {
        wall = GameObject.WALL;
        floor = GameObject.FLOOR;
        crate = GameObject.CRATE;
        diamond = GameObject.DIAMOND;
        keeper = GameObject.KEEPER;
        keeperRed = GameObject.KEEPERRED;
        door = GameObject.DOOR;
        ice = GameObject.ICE;
        crateOnDiamond = GameObject.CRATE_ON_DIAMOND;
        debugObject = GameObject.DEBUG_OBJECT;
    }

    @Test
    public void fromCharTest() {
        assertEquals(wall, GameObject.fromChar('w'));
        assertEquals(floor, GameObject.fromChar(' '));
        assertEquals(crate, GameObject.fromChar('c'));
        assertEquals(diamond, GameObject.fromChar('d'));
        assertEquals(keeper, GameObject.fromChar('s'));
        assertEquals(keeperRed, GameObject.fromChar('h'));
        assertEquals(door, GameObject.fromChar('m'));
        assertEquals(ice, GameObject.fromChar('i'));
        assertEquals(crateOnDiamond, GameObject.fromChar('o'));
        assertEquals(debugObject, GameObject.fromChar('='));
    }

    @Test
    public void getCharSymbolTest() {
        assertEquals('W', wall.getCharSymbol());
        assertEquals(' ', floor.getCharSymbol());
        assertEquals('C', crate.getCharSymbol());
        assertEquals('D', diamond.getCharSymbol());
        assertEquals('S', keeper.getCharSymbol());
        assertEquals('H', keeperRed.getCharSymbol());
        assertEquals('M', door.getCharSymbol());
        assertEquals('I', ice.getCharSymbol());
        assertEquals('O', crateOnDiamond.getCharSymbol());
        assertEquals('=', debugObject.getCharSymbol());
    }
}