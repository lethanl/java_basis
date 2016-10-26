package cn.thc.logging;

import java.util.logging.*;

/**
 * Created by thc on 2016/10/26
 */
public class MemoryHandlerTest {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("cn.thc.logging.MemoryHandlerTest");
        ConsoleHandler handler = new ConsoleHandler();
        MemoryHandler memoryHandler = new MemoryHandler(handler,10, Level.ALL);
        logger.addHandler(memoryHandler);
        logger.setUseParentHandlers(false);
        LogRecord record1 = new LogRecord(Level.SEVERE,"This is a severe message");
        LogRecord record2 = new LogRecord(Level.WARNING,"This is a warning message");
        logger.log(record1);
        logger.log(record2);
    }
}
