package cn.thc.multithread;

/**
 * Created by thc on 2016/12/19
 */
//参考：http://www.cnblogs.com/GnagWang/archive/2011/02/27/1966606.html
//一、当两个并发线程访问同一个对象object中的这个synchronized(this)同步代码块时，一个时间内只能有一个线程得到执行。另一个线程必须等待当前线程执行完这个代码块以后才能执行该代码块。
public class SynchronizedTest {
}

class Thread1 implements Runnable {

    @Override
    public void run() {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "synchronized loop " + i);
            }
        }
    }

    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        Thread ta = new Thread(thread1, "A");
        Thread tb = new Thread(thread1, "B");
        ta.start();
        tb.start();
    }
}

class Thread2 {

    public void m4t1() {
        synchronized (this) {
            int i = 5;
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void m4t2() {
        int i = 5;
        while (i-- > 0) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final Thread2 myt2 = new Thread2();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                myt2.m4t1();
            }
        },"t1");
        Thread t2 = new Thread(new Runnable() {
        @Override
        public void run() {
            myt2.m4t2();
        }
    },"t2");
    t1.start();
    t2.start();
    }

}