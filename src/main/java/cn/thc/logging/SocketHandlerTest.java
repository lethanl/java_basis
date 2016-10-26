package cn.thc.logging;

import java.util.logging.Logger;
import java.util.logging.SocketHandler;

/**
 * Created by thc on 2016/10/26
 */
public class SocketHandlerTest {

    private SocketHandler handler = null;

    private static Logger logger = Logger.getLogger("cn.thc.lgging");

    public SocketHandlerTest(String host,int port) {
        try{
            handler = new SocketHandler(host,port);
            logger.addHandler(handler);
            logger.info("SocketHandle运行成功……");
        }catch (Exception e){
            logger.severe("请检查地址和端口是否正确......");

            StringBuilder sb = new StringBuilder();
            sb.append(e.toString()).append("\n");
            for (StackTraceElement element:e.getStackTrace()){
                sb.append("\tat ").append(element).append("\n");
            }
            logger.severe(sb.toString());
        }
    }

    public static void main(String[] args) {
        new SocketHandlerTest("localhost",8080);
    }
}
