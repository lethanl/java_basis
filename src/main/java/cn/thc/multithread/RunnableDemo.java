package cn.thc.multithread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created by thc on 2016/12/12
 */
//参考：http://www.runoob.com/java/java-multithreading.html
public class RunnableDemo implements Runnable {

    private Thread t;

    private String threadName;

    public RunnableDemo(String threadName) {
        this.threadName = threadName;
        System.out.println("Creating " + threadName);
    }

    @Override
    public void run() {
        System.out.println("Running " +  threadName );
        try {
            for(int i = 4; i > 0; i--) {
                System.out.println("Thread: " + threadName + ", " + i);
                // 让线程睡眠一会
                Thread.sleep(50);
            }
        }catch (InterruptedException e) {
            System.out.println("Thread " +  threadName + " interrupted.");
        }
        System.out.println("Thread " +  threadName + " exiting.");
    }

    public void start(){
        System.out.println("Starting " +  threadName );
        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }
}

class TestThread{
    public static void main(String[] args) {
        RunnableDemo R1 = new RunnableDemo("Thread-1");
        R1.start();
        RunnableDemo R2 = new RunnableDemo("Thread-2");
        R2.start();
    }
}

// 文件名 : DisplayMessage.java
// 通过实现 Runnable 接口创建线程
class DisplayMessage implements Runnable{

    private String message;

    public DisplayMessage(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        while (true){
            System.out.println(message);
        }
    }
}

// 文件名 : GuessANumber.java
// 通过继承 Thread 类创建线程
class GuessANumber extends Thread{

    private int number;

    public GuessANumber(int number) {
        this.number = number;
    }

    public void run(){
        int counter = 0;
        int guess = 0;
        do{
            guess = (int) (Math.random() * 100 + 1);
            System.out.println(this.getName() + " guesses " + guess);
            counter++;
        }while(guess != number);
        System.out.println("** Correct!" + this.getName() + "in" + counter + "guesses.**");
    }
}


class ThreadClassDemo {

    public static void main(String[] args) {
        Runnable hello = new DisplayMessage("Hello");
        Thread thread1 = new Thread(hello);
        thread1.setDaemon(true);
        thread1.setName("hello");
        System.out.println("Starting hello thread...");
        thread1.start();

        Runnable bye = new DisplayMessage("GoodBye");
        Thread thread2 = new Thread(bye);
        thread2.setPriority(Thread.MIN_PRIORITY);
        thread2.setDaemon(true);
        System.out.println("Starting goodbye thread...");
        thread2.start();

        System.out.println("Starting thread3...");
        Thread thread3 = new GuessANumber(27);
        thread3.start();
        try {
            thread3.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }
        System.out.println("Starting thread4...");
        Thread thread4 = new GuessANumber(75);

        thread4.start();
        System.out.println("main() is ending...");
    }

}


class CallableThreadTest implements Callable<Integer> {

    public static void main(String[] args) {
        CallableThreadTest ct = new CallableThreadTest();
        FutureTask<Integer> ft = new FutureTask<Integer>(ct);
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " 的循环变量i的值" + i);
            if(i==20){
                new Thread(ft,"有返回的线程").start();
            }
        }

    }

    @Override
    public Integer call() throws Exception {
        int i = 0;
        for(; i < 100; i++){
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
        return i;
    }


}