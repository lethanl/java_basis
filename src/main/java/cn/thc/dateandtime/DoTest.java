package cn.thc.dateandtime;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Date;

/**
 * Created by thc on 2016/11/23
 */
public class DoTest {

    public static void main(String[] args) {
        //long ts = -663811200000l;
        //System.out.println(long2date(0,"MM/dd/yyyy HH:mm:ss"));
        //System.out.println(getAgeByLong(ts));
        //System.out.println(getAgeByBirthday(ts));

        //int num1 = 7;
        //
        //int num2 = 9;
        //
        //// 创建一个数值格式化对象
        //
        //NumberFormat numberFormat = NumberFormat.getInstance();
        //
        //// 设置精确到小数点后2位
        //
        //numberFormat.setMaximumFractionDigits(2);
        //
        //String result = numberFormat.format((float) num1 / (float) num2 * 100);
        //
        //System.out.println("num1和num2的百分比为:" + result + "%");
        daysOfTwo();

        //Date date = new Date(1479901150001l);
        //System.out.println(date);
        //System.currentTimeMillis()
    }

    //时间戳转化成日期格式
    public static String long2date(long ts,String format){
        SimpleDateFormat sdf= new SimpleDateFormat(format);
        //转为java.util.Date类型
        java.util.Date dt = new Date(ts);
        return sdf.format(dt);  //得到精确到秒的表示：08/31/2006 21:08:00
    }

    //根据时间戳计算年龄，方法一
    public static int getAgeByLong(long ts){
        //得到出生的年份
        int yearOfBir = Integer.parseInt(long2date(ts,"yyyy"));
        //得到今天的年份
        int yearOfNow = YearMonth.now().getYear();
        return yearOfNow - yearOfBir + 1;
    }

    //根据时间戳计算年龄，方法二
    public static int getAgeByBirthday(long millis){
        long nowMillis = System.currentTimeMillis();
        int age = (int) ((nowMillis - millis)/(1000*60*60*24*365.0));
        return age;
    }

    //计算两个日期相隔天数
    //利用joda time jar包
    public static void daysOfTwo(){
        DateTime starts = new DateTime(1479901150001l);
        DateTime endts = new DateTime(1479987551000l);
         int a = Days.daysBetween(starts,endts).getDays();
        System.out.println(a);
        System.out.println(starts.toString("yyyy-MM-dd"));
        System.out.println(endts.toString("yyyy-MM-dd"));
        System.out.println(starts.toString("yyyy/MM/dd").equals(endts.toString("yyyy/MM/dd")));
        System.out.println(starts);
    }

}
