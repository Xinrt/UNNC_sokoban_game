package main.com.ae2dms.util;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Generate logs
 * displaying data and processes in the console
 */
public class GameLogger extends Logger {

    private static Logger logger = Logger.getLogger("GameLogger");
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private Calendar calendar = Calendar.getInstance();

    /**
     * Create game logger
     *
     * @throws IOException      If cannot write into file, throw exception
     */
    public GameLogger() throws IOException {
        super("com.aes2dms.sokoban", null);

        File directory = new File(System.getProperty("user.dir") + "/" + "logs");
        directory.mkdirs();

        FileHandler fh = new FileHandler(directory + "/" + GameEngine.GAME_NAME + ".log");
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
    }

    /**
     * Create formatted message
     *
     * @param message       The string message (or a key in the message catalog)
     * @return              A string logger message
     */
    private String createFormattedMessage(String message) {
        return dateFormat.format(calendar.getTime()) + " -- " + message;
    }

    /**
     * Print out the documentation of key information in the normal operation
     *
     * @param message       The string message (or a key in the message catalog)
     */
    public void info(String message) {
        logger.info(createFormattedMessage(message));
    }

    /**
     * Print out if unexpected potential errors occur
     *
     * @param message       The string message (or a key in the message catalog)
     */
    public void warning(String message) {
        logger.warning(createFormattedMessage(message));
    }

    /**
     * give messages to all registered output Handler objects.
     *
     * @param message       The string message (or a key in the message catalog)
     */
    public void severe(String message) {
        logger.severe(createFormattedMessage(message));
    }
}