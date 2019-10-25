package reporting;


import org.apache.log4j.Logger;

public class Log {

    public static Logger logger = Logger.getLogger(Log.class);

    public static void error(String message) {
        logger.error(message);
    }

    public static void error(String message, Throwable throwable) {
        logger.error(message, throwable);
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void info(Object obj) {
        logger.info(obj);
    }

    public static void debug(String message) {
        logger.debug(message);
    }

    public static void debug(Object obj) {
        logger.debug(obj);
    }


    public static void warn(String message) {
        logger.warn(message);
    }

    public static void trace(String message) {
        logger.trace(message);
    }

    public static void log(String message){
        logger.info(message);
    }

}
