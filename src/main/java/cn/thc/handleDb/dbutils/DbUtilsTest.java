package cn.thc.handleDb.dbutils;

import cn.thc.handleDb.jdbc.SimpleDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by thc on 2016/11/30
 */
public class DbUtilsTest {

    public static void main(String[] args) {
        queryOfInsert();
    }

    //插入查询
    public static void queryOfInsert(){
        Connection conn;
        QueryRunner queryRunner = new QueryRunner();
        ArrayHandler arrayHandler = new ArrayHandler();
        try {

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8","root","");

            String sql = "INSERT INTO table1(field1,field2,x) values(?,?,?);";
            Object[] objects = queryRunner.insert(conn,sql,arrayHandler,"test","man","test");
            System.out.println(objects);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
    }
    }

    //使用QueryRunner类实现CRUD
    //start

    /**测试表
       create table users(
           id int primary key auto_increment,
           name varchar(40),
           password varchar(40),
           email varchar(60),
           birthday date
       );
       */

    @Test
    public void add(){
        QueryRunner queryRunner = new QueryRunner(new SimpleDataSource());
    }

    //end

}
