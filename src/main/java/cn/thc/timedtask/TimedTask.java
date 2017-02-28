package cn.thc.timedtask;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by thc on 2016/12/21
 */
public class TimedTask {

}

//参考：http://www.admin10000.com/document/4205.html
class Task1 {
    public static void main(String[] args) {
        final long timeInterval = 1000;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    // ------- code for task to run
                    System.out.println("Hello !!");
                    // ------- ends here
                    try {
                        Thread.sleep(timeInterval);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}

class Task2{
    public static void main(String[] args) {
        TimerTask timedTask = new TimerTask(){
            @Override
            public void run(){
                //tasks to run goes here
                System.out.println("Hello !!");
          }
        };
        Timer timer = new Timer();
        long delay = 0;
        long intevalPeriod = 1*1000;
        timer.scheduleAtFixedRate(timedTask,delay,intevalPeriod);
    }
}

class Task3{
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // task to run goes here
                System.out.println("Hello !!");
            }
        };
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(runnable,0,1, TimeUnit.SECONDS);
    }
}