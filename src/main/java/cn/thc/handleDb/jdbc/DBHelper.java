package cn.thc.handleDb.jdbc;

import java.sql.*;

/**
 * Created by thc on 2016/11/28
 */
//http://hzy3774.iteye.com/blog/1689525
//http://www.cnblogs.com/hongten/archive/2011/03/29/1998311.html
public class DBHelper {

    public static final String url = "jdbc:mysql://127.0.0.1/test";
    public static final String name = "com.mysql.jdbc.Driver";
    public static final String user = "root";
    public static final String password = "";

    public Connection conn = null;
    public PreparedStatement pst = null;

    public DBHelper(String sql) {
        try{
            Class.forName(name);//指定连接类型
            conn = DriverManager.getConnection(url, user, password);//获取连接
            pst = conn.prepareStatement(sql);//准备执行语句
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void close(){
        try {
            this.conn.close();
            this.pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

class Demo{

    static String sql = null;
    static DBHelper dbHelper = null;
    static ResultSet ret = null;

    public static void main(String[] args) {

        sql = "select * from t_user";
        DBHelper dbHelper = new DBHelper(sql);

        try {
            ret = dbHelper.pst.executeQuery();
            while (ret.next()){
                int id = ret.getInt(1);
                String name = ret.getString(2);
                String sex = ret.getString(3);
                System.out.println("id = " + id);
                System.out.println("name = " + name);
                System.out.println("sex = " + sex);
                System.out.println("============================");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}