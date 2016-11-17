package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Class BaseLogger
 */
abstract class BaseLogger extends Observable implements Observer
{
    private final String whiteColor = "\033[37m";
    private final String blueColor = "\033[34m";
    private final String yellowColor = "\033[33m";
    private final String redColor = "\033[31m";

    String path;
    String datePattern;

    /**
     * Info log
     *
     * @param message
     */
    public void info(String message)
    {
        process(message, "info", blueColor);
    }

    /**
     * Warning log
     *
     * @param message
     */
    public void warning(String message)
    {
        process(message, "warning", yellowColor);
    }

    /**
     * Critical log
     *
     * @param message
     */
    public void critical(String message)
    {
        process(message, "critical", redColor);
    }

    /**
     * Persist log and notify observers
     *
     * @param message
     * @param level
     * @param color
     */
    private void process(String message, String level, String color)
    {
        System.out.println(format(message,level,color));
        persist(format(message, level, ""));
        setChanged();
        notifyObservers(message);
    }

    /**
     * Format log output
     *
     * @param message
     * @param level
     * @param color
     *
     * @return String
     */
    private String format(String message, String level, String color)
    {
        Date now = new Date();
        SimpleDateFormat formater = new SimpleDateFormat(datePattern);

        return String.format(
                "[%s][%s%s%s] %s",
                formater.format(now),
                color,
                level.toUpperCase(),
                !Objects.equals(color, "") ? whiteColor : "",
                message
        );
    }

    /**
     * Persist log
     */
    private void persist(String log)
    {
        if (checkPath()) {
            try {
                PrintWriter writer = new PrintWriter(
                        new FileWriter(path, true)
                );
                writer.println(log);
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Check path and file exists or create them
     *
     * @return boolean
     */
    private boolean checkPath()
    {
        File logFile = new File(path);

        if (!logFile.exists()) {
            if (logFile.getParentFile().mkdirs() || logFile.getParentFile().exists()) {
                try {
                    return logFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            return true;
        }

        return false;
    }
}
