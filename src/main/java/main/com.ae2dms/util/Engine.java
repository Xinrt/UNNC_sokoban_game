package main.com.ae2dms.util;

import java.awt.*;
import java.io.IOException;

/**
 * The abstract class of a factory design pattern
 *
 * @author      Xinran TANG
 */
public abstract class Engine {
    public void moveBlueKeeper(Point delta) throws IOException{}
    public void moveRedKeeper(Point delta) throws IOException{}
    public void remove(Point keeperPosition) {}
}
