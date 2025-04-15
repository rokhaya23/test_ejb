package entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MyAppLogger {

        private static final Logger logger = LogManager.getLogger(MyAppLogger.class);

        public static void main(String[] args) {
            // Logic for your application goes here.
            logger.trace("1.This is a TRACE message.");
            logger.debug("2.This is a DEBUG message.");
            logger.info("3.This is an INFO message.");
            logger.warn("4.This is a WARN message.");
            logger.error("5.This is an ERROR message.");

    }

}
