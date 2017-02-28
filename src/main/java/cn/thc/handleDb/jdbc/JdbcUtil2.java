package cn.thc.handleDb.jdbc;

import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * Created by thc on 2016/11/29
 */
//http://www.oschina.net/code/snippet_1170991_46213
public class JdbcUtil2 {

    private static Properties ps = new Properties();

    private static final String db_setting = "/db.properties";

    static {
        try{
            if(JdbcUtil2.class.getResourceAsStream(db_setting) == null){
                System.out.println("配置文件不存在");
            }else {
                ps.load(JdbcUtil2.class.getResourceAsStream(db_setting));
                //加载驱动
                Class.forName(ps.getProperty("driverClassName"));
                System.out.println("驱动加载成功");
            }
        }catch (Exception e){

        }
    }

    private JdbcUtil2(){

    }

    private static JdbcUtil2 jdbcUtil2 = new JdbcUtil2();

    public static JdbcUtil2 getInstance(){
        return jdbcUtil2;
    }

    public static JdbcUtil2 getInstance(Class<?> clazz) throws IOException{
        if(clazz.getResourceAsStream(db_setting) == null){
            throw new RuntimeException("配置文件不存在dd");
        }
        //加载驱动
        try{
            Class.forName(ps.getProperty("driverClassName"));
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return jdbcUtil2;
    }

    public Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(ps.getProperty("url"),ps.getProperty("username"),ps.getProperty("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    private BaseDaoJdbc baseDaoJdbc = new BaseDaoJdbcImpl();

    public BaseDaoJdbc getBaseDaoJdbc(){
        return baseDaoJdbc;
    }

    public <E> E query(String sql,Class<E> resultClass,Object...obj){
        ResultSet rs = baseDaoJdbc.queryAll(sql,getConnection(),obj);
        try {
            if(resultClass == Map.class){
                if (rs.next()) {
                    return (E) getResultMap(rs);
                }
            }else if(resultClass == List.class){
                return (E) getResultList(rs);
            }else {
                throw new RuntimeException(""+resultClass +" 该类型目前还没有做扩展!");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                baseDaoJdbc.closeAll(rs,rs.getStatement(),rs.getStatement().getConnection());
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public int executeUpdate(String sql,Object... obj){
        int k = 0;
        k = getBaseDaoJdbc().executeUpdate(sql,getConnection(),obj);
        return k;
    }

    /**
     * 解析ResultSet表列数据
     * @param rs
     * @return
     * @throws SQLException
     */
    private Map<String,Object> getResultMap(ResultSet rs) throws SQLException{
        Map<String,Object> rawMap = new HashMap<String,Object>();
        ResultSetMetaData rsmd = rs.getMetaData();//表对象信息
        int count = rsmd.getColumnCount();//列数
        // 遍历之前需要调用 next()方法
        for (int i = 0; i < count; i++) {
            String key = rsmd.getColumnLabel(i);
            Object value = rs.getObject(key);
            rawMap.put(key,value);
        }
        return rawMap;
    }

    /**
     * 解析ResultSet 表数据
     * @param rs
     * @return
     * @throws SQLException
     */
    private List<Map<String,Object>> getResultList(ResultSet rs) throws SQLException{
        List<Map<String,Object>> rawList = new ArrayList<Map<String,Object>>();
        while(rs.next()){
            Map<String,Object> rawMap = getResultMap(rs);
            rawList.add(rawMap);
        }
        return rawList;
    }

}

interface BaseDaoJdbc{
    /**
     * 关闭所有连接
     * @param conn
     * @param stmt
     * @param rst
     * @return
     */
    public boolean closeAll(ResultSet rst,Statement stmt , Connection conn);
    /**
     * 关闭连接对象
     * @param conn
     * @return
     */
    public boolean closeConnection(Connection conn);
    /**
     * 关闭执行sql对象
     * @param stmt
     * @return
     */
    public boolean closeStatement(Statement stmt);
    /**
     * 关闭结果集
     * @param rst
     * @return
     */
    public boolean closeResultSet(ResultSet rst);
    /**
     * 增删改
     * @param sql
     * @param conn
     * @param obj
     * @return
     */
    public int executeUpdate(String sql,Connection conn,Object...obj);
    /**
     * 查询所有
     * @param sql
     * @param conn
     * @param obj
     * @return
     */
    public ResultSet queryAll(String sql,Connection conn, Object... obj);
}


class BaseDaoJdbcImpl implements BaseDaoJdbc{

    @Override
    public boolean closeAll(ResultSet rst, Statement stmt, Connection conn) {
        boolean flag = false;
        try{
            if (rst != null) {
                rst.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
                flag = true;
            }
        }catch (SQLException e){

        }
        return flag;
    }

    @Override
    public boolean closeConnection(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean closeStatement(Statement stmt) {
        if(stmt!=null){
            if(stmt instanceof PreparedStatement){
                try {
                    ((PreparedStatement) stmt).close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public boolean closeResultSet(ResultSet rst) {
        if(rst!=null){
            try {
                rst.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public int executeUpdate(String sql, Connection conn, Object... obj) {
        int i = 0 ;
        PreparedStatement psts = null ;
        try {
            psts = conn.prepareStatement(sql) ;
            if(obj!=null && obj.length>0){
                for(int j=0;j<obj.length;j++){
                    psts.setObject((j+1), obj[j]) ;
                }
            }
            i = psts.executeUpdate() ;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            closeAll(null, psts, conn);
        }
        return  i ;
    }

    @Override
    public ResultSet queryAll(String sql, Connection conn, Object... obj) {
        PreparedStatement psts = null ;
        ResultSet rs = null ;
        try {
            psts = conn.prepareStatement(sql) ;
            if(obj!=null && obj.length>0){
                for(int j=0;j<obj.length;j++){
                    psts.setObject((j+1), obj[j]) ;
                }
            }
            rs = psts.executeQuery() ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  rs ;
    }
}

class JdbcUtil2Test{

    public static void main(String[] args) {
        JdbcUtil2 jdbcUtil2 = JdbcUtil2.getInstance();
        BaseDaoJdbc baseDaoJdbc = new BaseDaoJdbcImpl();
        String sql = "select * from t_user";
        ResultSet resultSet = baseDaoJdbc.queryAll(sql,jdbcUtil2.getConnection(),null);
        try {
            while(resultSet.next()){
                System.out.println(resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}