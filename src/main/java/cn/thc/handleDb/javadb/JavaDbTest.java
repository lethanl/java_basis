package cn.thc.handleDb.javadb;

import java.sql.*;
import java.util.Properties;

/**
 * Created by thc on 2016/11/30
 */
//https://www.ibm.com/developerworks/cn/java/j-lo-jse65/
//加入derby.jar文件，在jdk中包含着
public class JavaDbTest {

    public static void main(String[] args) {
        try {

            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            System.out.println("Load the embedded driver");
            Connection conn = null;
            Properties properties = new Properties();
            properties.put("user","user1");
            properties.put("password","user1");

            //create and connect the database named helloDB
            conn = DriverManager.getConnection("jdbc:derby:helloDB;create=true",properties);
            System.out.println("create and connect to helloDB");
            conn.setAutoCommit(false);

            // create a table and insert two records
            Statement s = conn.createStatement();
            s.execute("create table hellotable(name varchar(40), score int)");
            System.out.println("Created table hellotable");
            s.execute("insert into hellotable values('Ruth Cao', 86)");
            s.execute("insert into hellotable values ('Flora Shi', 92)");

            //list the two records
            ResultSet rs = s.executeQuery("SELECT name, score FROM hellotable ORDER BY score");
            System.out.println("name\t\tscore");
            while (rs.next()){
                StringBuilder builder = new StringBuilder(rs.getString(1));
                builder.append("\t");
                builder.append(rs.getInt(2));
                System.out.println(builder.toString());
            }
            //delete the table
            s.execute("drop table hellotable");
            System.out.println("Dropped table hellotable");

            rs.close();
            s.close();
            System.out.println("Closed result set and statement");
            conn.commit();
            conn.close();
            System.out.println("Committed transaction and closed connection");

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
