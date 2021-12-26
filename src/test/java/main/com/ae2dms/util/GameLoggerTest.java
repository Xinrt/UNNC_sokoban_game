package main.com.ae2dms.util;

import main.com.ae2dms.util.GameLogger;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Logger;

public class GameLoggerTest {

    private GameLogger gameLogger;

    @Before
    public void setUp() throws Exception {
        gameLogger = new GameLogger();
    }

    @Test
    public void info() {
        gameLogger.info("This is logger info test");
    }

    @Test
    public void warning() {
        gameLogger.warning("This is logger warning test");
    }

    @Test
    public void severe() {
        gameLogger.warning("This is logger server test");
    }
}