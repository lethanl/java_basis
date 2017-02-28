package cn.thc.handleDb.jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by thc on 2016/11/30
 */
public class DoTest {

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(properties.get("user"));
        try {
            DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8",properties);
            //Driver driver = DriverManager.getDriver("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8");
            //System.out.println(driver);
            //System.out.println(DriverManager.getLoginTimeout());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
