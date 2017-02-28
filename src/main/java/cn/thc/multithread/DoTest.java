package cn.thc.multithread;

/**
 * Created by thc on 2016/12/20
 */
public class DoTest {
}

//参考：http://blog.csdn.net/dabing69221/article/details/17426953
class YieldTest extends Thread {

    public YieldTest(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println("" + this.getName() + "---------" + i);
            // 当i为30时，该线程就会把CPU时间让掉，让其他或者自己的线程执行（也就是谁先抢到谁执行）
            if (i == 30) {
                this.yield();
            }
        }
    }

    public static void main(String[] args) {
        YieldTest yieldTest1 = new YieldTest("张三");
        YieldTest yieldTest2 = new YieldTest("李四");
        yieldTest1.start();
        yieldTest2.start();
    }

}

//参考：http://uule.iteye.com/blog/1101994
class JoinTest implements Runnable {

    private static int a = 0;

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            a = a + 1;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new JoinTest();
        Thread t = new Thread(r);
        t.start();
        //t.join();
        for (int i = 0; i < 3000; i++) {
            System.out.println(i);
        }
        System.out.println(a);
    }
}

class RunnableImpl implements Runnable {

    @Override
    public void run() {
        try {
            System.out.println("start------------");
            Thread.sleep(2000);
            System.out.println("end--------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread t = new Thread(new RunnableImpl());
        t.start();
        try {
            t.join(1000);
            System.out.println("joinFinish");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}





