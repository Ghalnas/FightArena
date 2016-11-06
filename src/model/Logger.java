package model;

import java.io.*;
import java.util.Properties;

/**
 * Class Logger
 */
public class Logger extends BaseLogger
{
    private static Logger INSTANCE = null;

    /**
     * Logger construct
     */
    private Logger()
    {
        Properties properties = new Properties();
        InputStream loggerProperties = null;

        try {
            loggerProperties = new FileInputStream("app/config/logger.properties");
            properties.load(loggerProperties);
            this.path = properties.getProperty("logger.path");
            datePattern = properties.getProperty("logger.datePattern");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (loggerProperties != null) {
                    loggerProperties.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Singleton
     *
     * @return Logger
     */
    public static Logger getInstance()
    {
        if (INSTANCE == null) {
            synchronized(Logger.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Logger();
                }
            }
        }

        return INSTANCE;
    }
}
