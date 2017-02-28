package cn.thc.handleDb.jdbc;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.logging.Logger;

/**
 * Created by thc on 2016/11/30
 */
//参考：http://lavasoft.blog.51cto.com/62575/265073/
public class SimpleDataSource implements DataSource {
    private static final String driverClassName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8";
    private static final String user = "root";
    private static final String pswd = "";

    //连接池
    private static LinkedList<Connection> pool =  (LinkedList<Connection>)Collections.synchronizedList(new LinkedList<Connection>());//此处有问题，LinkedList不能这样线程安全
    private static SimpleDataSource instance = new SimpleDataSource();

    static {
        try{
            Class.forName(driverClassName);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public SimpleDataSource() {
    }

    /**
     * 获取数据源单例
     * @return 数据源单例
     */
    public SimpleDataSource instance(){
        if (instance == null) {
            instance = new SimpleDataSource();
        }
        return instance;
    }

    /**
     * 获取一个数据库连接
     *
     * @return 一个数据库连接
     * @throws SQLException
     */
    @Override
    public Connection getConnection() throws SQLException {
        synchronized (pool){
            if(pool.size() > 0)
                return pool.removeFirst();
            else
                return makeConnection();
        }
    }

    /**
     * 连接归池
     *
     * @param conn
     */
    public static void freeConnection(Connection conn) {
        pool.addLast(conn);
    }

    private Connection makeConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pswd);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }
}
