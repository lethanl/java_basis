package cn.thc.multithread;

import java.util.concurrent.TimeUnit;

/**
 * Created by thc on 2016/12/14
 */
//参考：Effective Java中文版 第2版 并发部分
public class EffectiveTest {

}


//错误的
class StopThread1 {

    private static boolean stopRequested;

    public static void main(String[] args) throws InterruptedException {
        System.out.println(stopRequested);
        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (!stopRequested) {
                    i++;
                }
            }
        });
        backgroundThread.start();

        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;

    }

}

class StopThread2 {

    private static boolean stopRequested;

    public static synchronized boolean isStopRequested() {
        return stopRequested;
    }

    public static synchronized void setStopRequested() {
        stopRequested = true;
    }

    public static void main(String[] args) throws InterruptedException {

        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (!isStopRequested())
                    i++;
            }
        });

        backgroundThread.start();
        TimeUnit.SECONDS.sleep(1);
        setStopRequested();

    }
}

class StopThread3 {

    private static volatile boolean stopRequested;

    public static void main(String[] args) throws InterruptedException {

        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (!stopRequested)
                    i++;
            }
        });

        backgroundThread.start();
        TimeUnit.SECONDS.sleep(1);

        stopRequested = true;

    }

}
