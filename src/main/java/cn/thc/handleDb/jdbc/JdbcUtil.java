package cn.thc.handleDb.jdbc;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by thc on 2016/11/28
 */
//http://www.jianshu.com/p/2bd5809e6298
//http://www.oschina.net/code/snippet_1170991_46213
public class JdbcUtil {

    private static Properties prop = new Properties();

    static {

        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Class.forName(prop.getProperty("driverClassName"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("username"),prop.getProperty("password"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void close(Connection conn, ResultSet rs, PreparedStatement ps){
        if(rs != null){
            try {
                rs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                if(ps != null){
                    try {
                        ps.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        if(conn != null){
                            try {
                                conn.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

}

class MyJdbcTemplate{

    // 操作,增删改,传入sql语句和参数,参数和sql之间要一一对应
    public static boolean execute(String sql,Object... obj){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        conn = JdbcUtil.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++){
                ps.setObject(i+1,obj[i]);
            }
            if(ps.execute()){
                JdbcUtil.close(conn,rs,ps);
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        JdbcUtil.close(conn,rs,ps);
        return false;
    }

    // 查询返回的是一个list集合,也可以用来查询单个,取list.get(0);
    public static <T> List<T> query(String sql,Class<T> clz,Object... params){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //用来存储对象
        List<T> list = new ArrayList<T>();
        conn = JdbcUtil.getConnection();
        try{
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i+1,params[i]);
            }
            rs = ps.executeQuery();
            //内省机制，获取javabean中的属性方法
            BeanInfo beanInfo = Introspector.getBeanInfo(clz, Object.class);
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
            while (rs.next()){
                //实例化一个对象，用来存储获取到的值
                T obj = clz.newInstance();
                for (PropertyDescriptor descriptor : descriptors) {
                    //获取属性set方法
                    Method writeMethod = descriptor.getWriteMethod();
                    //获取属性名称
                    String name = descriptor.getName();
                    writeMethod.invoke(obj,rs.getObject(name));
                }
                list.add(obj);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        JdbcUtil.close(conn,rs,ps);
        return list;
    }

}

/*

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'thc1', 'thc1', '1');
INSERT INTO `t_user` VALUES ('2', 'thc3', 'thc3', '2');
INSERT INTO `t_user` VALUES ('3', 'thc4', 'thc4', '3');
INSERT INTO `t_user` VALUES ('5', 'thc4', 'thc4', '0');
INSERT INTO `t_user` VALUES ('6', 'ss', 'ss', '0');
INSERT INTO `t_user` VALUES ('7', 'ss', 'ss', '0');
INSERT INTO `t_user` VALUES ('8', 'sd', 'ds', '1');
DROP TRIGGER IF EXISTS `insert_tb_user`;
DELIMITER ;;
CREATE TRIGGER `insert_tb_user` AFTER INSERT ON `t_user` FOR EACH ROW begin
set @num = (select count(1) from t_user where version = '0');
set @name = new.name;
if @num >= 1 then
insert into tb_user(name) values(@name);
SIGNAL SQLSTATE '02000' SET MESSAGE_TEXT = 'Warning: c > 100!';
end if;
end
;;
DELIMITER ;
 */
class User{
    private int id;
    private String name;
    private String sex;
    private int version;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", version=" + version +
                '}';
    }
}

class UserDao{

    public void save(User user){

        String sql = "inser into t_user values(null,?,?,?)";
        try {
            MyJdbcTemplate.execute(sql,new Object[]{user.getName(),user.getSex(),user.getVersion()});
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void delete(User user){

        String sql = "delete from t_user where id = ?";
        try {
            MyJdbcTemplate.execute(sql,new Object[]{user.getId()});
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void update(User user){

        String sql = "update t_user set name = ?,age = ?,version = ?";
        try {
            MyJdbcTemplate.execute(sql,new Object[]{user.getName(),user.getSex(),user.getVersion()});
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public User find(User user){

        String sql = "select * from t_user where id = ?";
        try{
            return MyJdbcTemplate.query(sql,user.getClass(),new Object[]{user.getId()}).get(0);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<User> findAll(){

        String sql = "select * from t_user";
        try{
            return MyJdbcTemplate.query(sql,User.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}


class JdbcUtilTest{

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        List<User> user = userDao.findAll();
        for (User user1 : user) {
            System.out.println(user1);
        }
    }

}
