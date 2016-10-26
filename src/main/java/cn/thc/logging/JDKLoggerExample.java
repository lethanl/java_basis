package cn.thc.logging;

import java.util.logging.Logger;

/**
 * Created by thc on 2016/10/26
 */
public class JDKLoggerExample {

    private static Logger logger = MyLoggerUtil.setLoggerHandler(Logger.getLogger("cn.thc.logging"));

    public static void main(String[] args) {
        logger.info("JDK Logger is logging infomation at INFO Level");
        logger.fine("ddd");
    }

}
