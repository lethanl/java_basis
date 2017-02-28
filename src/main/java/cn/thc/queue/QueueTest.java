package cn.thc.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by thc on 2016/12/24
 */
//http://blog.csdn.net/qq_27093465/article/details/52264026
public class QueueTest {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        queue.offer("1");
        queue.offer("2");
        queue.offer("3");
        //打印元素个数
        System.out.println("queue.size() "+queue.size());
        //遍历打印所有的元素,按照插入的顺序打印
        for (String s : queue) {
            System.out.println(s);
        }

        System.out.println("queue.size()  " + queue.size());//queue.size()  3   上面只是简单循环，没改变队列

        String getOneFrom1 = queue.element();
        System.out.println("getOneFrom1  " + getOneFrom1);//getOneFrom1  1          因为使用前端而不移出该元素
        System.out.println("queue.size()  " + queue.size());//queue.size()  3       队列变啦才怪

        String getOneFrom2 = queue.peek();
        System.out.println("getOneFrom2  " + getOneFrom2);//getOneFrom2  1          因为使用前端而不移出该元素
        System.out.println("queue.size()  " + queue.size());//queue.size()  3       队列变啦才怪

        String getOneFrom3 = queue.poll();
        System.out.println("getOneFrom3  " + getOneFrom3);//getOneFrom3  1          获取并移出元素
        System.out.println("queue.size()  " + queue.size());//queue.size()  2       队列变啦
    }
}
