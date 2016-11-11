package cn.thc.collection.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

/**
 * Created by thc on 2016/11/9
 */
public class Test {

    public static void main(String[] args) {
        Predicate<Integer> bigerThan6 = x -> x > 6;
        System.out.println(bigerThan6.test(7));
    }

}

class DoTest{

    public static void testAnonymousClass() {
        Integer[] nums = {2, 5, 1, 6};
        Arrays.sort(nums, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1 < o2)
                    return -1;
                return 0;
            }
        });
        for (Integer n : nums) {
            System.out.println(n);
        }
    }

    public static void main(String[] args) {
        testAnonymousClass();
    }

}
