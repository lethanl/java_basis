package cn.thc.exception;

/**
 * Created by thc on 2017/2/15
 */
public class ExceptionTest {
    public static void main(String[] args) {
//        test1();
        test2();
    }

    public static void test1() {
        int[] ints = new int[]{1, 2, 3, 4};
        System.out.println("before++++++++++++++++++++");
        System.out.println(ints[4]);
        System.out.println("after+++++++++++++++++++++");
    }

    public static void test2() {
        int[] ints = new int[]{1, 2, 3, 4};
        System.out.println("before++++++++++++++++++++");
        try {
            System.out.println(ints[4]);
            System.out.println("after+++++++++++++++++++++");
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        System.out.println("after exception!!");
    }
}
