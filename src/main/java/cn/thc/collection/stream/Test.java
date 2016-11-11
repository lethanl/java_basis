package cn.thc.collection.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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


//Lambda在集合中的使用
//参考：http://blog.csdn.net/dm_vincent/article/details/40340291
class HandList{

    public static void main(String[] args) {
        //测试集合的遍历
        //printList();

        //测试列表的变换
        //changeList();

        //search for element
        reusing();

    }

    //集合的遍历
    public static void printList(){

        final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");


        //方式一： 普通for循环
        //for(int i = 0; i < friends.size(); i++) {
        //    System.out.println(friends.get(i));
        //}

        //方式二： 增强for循环
        //for(String name : friends) {
        //    System.out.println(name);
        //}

        /*
         这种方式，在实现细节上使用的是iterator接口和它的hasNext()，next()方法。
         无论使用哪种for循环，它们仍然使用了外部遍历器(External Iterator)。即在for循环中，你总是有办法通过诸如break，continue等方式来控制遍历的过程。
         */

        /*
        与外部遍历器相对的，是内部遍历器(Internal Iterator)。在Java 8中，Iterable接口被增强了，现在该接口拥有一个forEach方法用来实现内部遍历器。
        forEach方法会接受一个Consumer接口类型作为参数，该接口是一个函数式接口(Functional Interface)，它是内部遍历器的实现方式。关于函数式接口，可以参考上一篇文章。
         */
        //方法三:lambda表达式
        //friends.forEach(new Consumer<String>() {
        //    public void accept(final String name) {
        //        System.out.println(name);
        //    }
        //});

        //简化lambda表达式
        //friends.forEach((final String name) -> System.out.println(name));

        //简化lambda表达式
        //friends.forEach((name) -> System.out.println(name));
        //当Lambda表达式左端只接受一个变量的时候，括号也是可以省略的：
        //iends.forEach(name -> System.out.println(name);

        //但是还有更简洁的方法，那就是使用方法引用
        friends.forEach(System.out::println);
    }


    //列表的变换
    //以将一个名字集合转换为首字母大写了的名字集合
    public static void changeList(){

        final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

        List<String> uppercaseNames = new ArrayList<String>();

        //方法一
        //for(String name:friends){
        //    uppercaseNames.add(name.toUpperCase());
        //}

        //方法二
        //friends.forEach(name -> uppercaseNames.add(name.toUpperCase()));

        //方法三
        friends.stream().map(name -> name.toUpperCase()).forEach(name -> System.out.println(name + ""));
        uppercaseNames = friends.stream().map(name -> name.toUpperCase()).collect(Collectors.toList());

        //打印出变换结果
        System.out.println(uppercaseNames);
    }

    //Lambda表达式的重用
    public static void reusing(){

        final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

        //得到名字集合中所有以N开头的名字

        //Method One
        //final List<String> startsWithN = new ArrayList<String>();
        //for (String name : friends) {
        //    if(name.startsWith("N")){
        //        startsWithN.add(name);
        //    }
        //}

        //Method second
        //final List<String> startsWithN = friends.stream().filter(name -> name.startsWith("N")).collect(Collectors.toList());

        //当需要对不止一个集合进行操作时
        //对于filter方法接受的Lambda表达式，它是符合Predicate接口类型的，因此可以声明如下：
        //final Predicate<String> startsWithN = name -> name.startsWith("N");
        //final long countFriendsStartN = friends.stream().filter(startsWithN).count();
        //其它集合
        //final long countComradesStartN = comrades.stream().filter(startsWithN).count();
        //final long countEditorsStartN = editors.stream().filter(startsWithN).count();

        //Method three
        //final long startsWithN = friends.stream().filter(checkIfStartsWith("N")).count();

        //Method four
        //final long startsWithN = friends.stream().filter(startsWithLetter.apply("N")).count();

        //Method five
        final long startsWithN = friends.stream().filter(getStartsWithLetter.apply("N")).count();

        System.out.println(startsWithN);

    }

    public static Predicate<String> checkIfStartsWith(final String letter){
        return name -> name.startsWith(letter);
    }
    //实际上，使用static来实现以上的高阶函数并不是一个好主意。可以将作用域缩小一些：
    final static Function<String, Predicate<String>> startsWithLetter =
            (String letter) -> {
                Predicate<String> checkStartsWith = (String name) -> name.startsWith(letter);
                return checkStartsWith;
            };
    //使用上面方式让代码更加复杂了，但是将它简化之后就成了下面这个样子：
    //final static Function<String,Predicate<String>> getStartsWithLetter = (String letter) -> (String name) -> name.startsWith(letter);
    //进一步简化
    final static Function<String,Predicate<String>> getStartsWithLetter = letter -> name -> name.startsWith(letter);

}


class DD{
    public static void main(String[] args) {
        final String str = "w00t";
        //str.chars().forEach(System.out::println);
        str.chars().mapToObj(ch -> Character.valueOf((char)ch)).forEach(System.out::println);
    }
}
