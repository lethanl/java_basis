package cn.thc.dateandtime.jodatime;

import org.joda.time.*;
import org.joda.time.chrono.CopticChronology;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by thc on 2016/11/25
 */
//导入jar包joda time
public class DoTest {

    public static void main(String[] args) {

        //long sts = 1479961136000l;
        //long ets = 1480043936000l;
        //System.out.println(yearsBetweenByDay(sts,ets));
        //
        //System.out.println("间隔天数：" + daysBetween(sts,ets));
        //System.out.println("间隔天数：" + daysBetweenByDay(sts,ets));
        //
        //chronology();

        //dateTimeTest();

        //System.currentTimeMillis();

        dateTimeTest();

        //System.out.println(getAppointDay());

        //formatDate();

        //handleWithJDK();

//        System.out.println(getAgeByBirthday(186163200000l));
//        System.out.println(getAgeByBirthday(-444988800000l));
    }

    //LocalDate
    public static void localDateTest(){

    }

    //LocalTime
    public static void localTimeTest(){

    }

    //DateTime
    public static void dateTimeTest(){

        //得到当前时间
        DateTime dateTime1 = new DateTime();

        //指定时间
        DateTime dateTime2 = new DateTime(2012, 12, 15, 18, 23,55);

        //将long转化
        DateTime dateTime3 = new DateTime(1479961136000l);

        LocalDate localDate = new LocalDate(1479961136000l);


        System.out.println(dateTime1);
        System.out.println(dateTime2);
        System.out.println(dateTime3);
        System.out.println(dateTime3.toString("yyyy-MM-dd"));
        System.out.println(localDate);

    }

    //chronology
    public static void chronology(){
        Chronology coptic = CopticChronology.getInstance();
        System.out.println(coptic);
    }

    //求两个日期间隔天数（以实际为计算单位）
    public static int daysBetween(long sts,long ets){
        DateTime starts = new DateTime(sts);
        DateTime endts = new DateTime(ets);
        return Days.daysBetween(starts,endts).getDays();
    }

    //求两个日期间隔天数（以天为计算单位）
    public static int daysBetweenByDay(long sts,long ets){
        LocalDate starts = new LocalDate(sts);
        LocalDate endts = new LocalDate(ets);
        return Days.daysBetween(starts,endts).getDays();
    }

    //求两个日期间隔年数（以天为计算单位）
    public static int yearsBetweenByDay(long sts,long ets){
        LocalDate starts = new LocalDate(sts);
        LocalDate endts = new LocalDate(ets);
        return Years.yearsBetween(starts,endts).getYears();
    }

    //获取18天之后的某天在下个月的当前周的第一天日期
    public static String getAppointDay(){
        return new DateTime().plusDays(18).plusMonths(1).dayOfWeek().withMinimumValue().toString("yyyy-MM-dd HH:mm:ss");
    }

    //时间格式化
    public static void formatDate(){

        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

        //时间解析
        DateTime dateTime = DateTime.parse("2012-12-21 23:22:45",dateTimeFormatter);
        System.out.println(dateTime);

        //时间格式化，输出==> 2012/12/21 23:22:45 Fri
        String str = dateTime.toString("yyyy/MM/dd HH:mm:ss EE");
        System.out.println(str);

        //格式化带Locale，输出==> 2012年12月21日 23:22:45 星期五
        String string_c = dateTime.toString("yyyy年MM月dd日 HH:mm:ss EE", Locale.CHINESE);
        System.out.println(string_c);

    }

    //与jdk互操作
    public static void handleWithJDK(){

        //通过jdk时间对象构造
        Date date = new Date();
        DateTime dateTime = new DateTime(date);

        Calendar calendar = Calendar.getInstance();
        DateTime dateTime1 = new DateTime(calendar);

        // Joda-time 各种操作.....
        dateTime = dateTime.plusDays(1) // 增加天
                .plusYears(1)// 增加年
                .plusMonths(1)// 增加月
                .plusWeeks(1)// 增加星期
                .minusMillis(1)// 减分钟
                .minusHours(1)// 减小
                .minusSeconds(1);// 减秒数

        // 计算完转换成jdk 对象
        Date date2 = dateTime.toDate();
        Calendar calendar2 = dateTime.toCalendar(Locale.CHINA);
        System.out.println(calendar2);

    }

    //计算生日
    public static int getAgeByBirthday(long birthday){
        return Years.yearsBetween(new LocalDate(birthday),new LocalDate()).getYears();
    }

}
