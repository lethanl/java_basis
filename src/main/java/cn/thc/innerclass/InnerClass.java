package cn.thc.innerclass;

/**
 * Created by thc on 2016/11/10
 */
public class InnerClass {
}

/*
    内部类是指在一个外部类的内部再定义一个类。内部类作为外部类的一个成员，并且依附于外部类而存在的。
    内部类可为静态，可用protected和private修饰（而外部类只能使用public和缺省的包访问权限）。
    内部类主要有以下几类：成员内部类、局部内部类、静态内部类、匿名内部类

    为什么需要内部类？

　　典型的情况是，内部类继承自某个类或实现某个接口，内部类的代码操作创建其的外围类的对象。所以你可以认为内部类提供了某种进入其外围类的窗口。使用内部类最吸引人的原因是：
　　每个内部类都能独立地继承自一个（接口的）实现，所以无论外围类是否已经继承了某个（接口的）实现，对于内部类都没有影响。如果没有内部类提供的可以继承多个具体的或抽象的类的能力，
    一些设计与编程问题就很难解决。从这个角度看，内部类使得多重继承的解决方案变得完整。接口解决了部分问题，而内部类有效地实现了“多重继承”。
 */



//A.成员内部类

//作为外部类的一个成员存在，与外部类的属性、方法并列。
class Outer1 {

    private static int i = 1;
    private int j = 10;
    private int k = 20;

    public static void outer_f1() {

    }

    public void outer_f2() {

    }

    // 成员内部类中，不能定义静态成员
    // 成员内部类中，可以访问外部类的所有成员
    class Inner1 {
        // static int inner_i = 100; //内部类中不允许定义静态变量
        int j = 100; // 内部类和外部类的实例变量可以共存
        int inner_i = 1;


        void inner_f1() {
            System.out.println(i);
            //在内部类中访问内部类自己的变量直接用变量名
            System.out.println(j);
            //在内部类中访问内部类自己的变量也可以用this.变量名
            System.out.println(this.j);
            //在内部类中访问外部类中与内部类同名的实例变量用外部类名.this.变量名
            System.out.println(Outer1.this.j);
            //如果内部类中没有与外部类同名的变量，则可以直接用变量名访问外部类变量
            System.out.println(k);
            outer_f1();
            outer_f2();
        }
    }

    //外部类的非静态方法访问成员内部类
    public void outer_f3(){
        Inner1 inner = new Inner1();
        inner.inner_f1();
    }

    // 外部类的静态方法访问成员内部类，与在外部类外部访问成员内部类一样
    public static void outer_f4() {
        //step1 建立外部类对象
        Outer1 out = new Outer1();
        //step2 根据外部类对象建立内部类对象
        Inner1 inner = out.new Inner1();
        //step3 访问内部类的方法
        inner.inner_f1();
    }


    public static void main(String[] args) {
        //outer_f4();
        // 该语句的输出结果和下面三条语句的输出结果一样
        //如果要直接创建内部类的对象，不能想当然地认为只需加上外围类Outer的名字，
        //就可以按照通常的样子生成内部类的对象，而是必须使用此外围类的一个对象来
        //创建其内部类的一个对象：
        //Outer.Inner outin = out.new Inner()
        //因此，除非你已经有了外围类的一个对象，否则不可能生成内部类的对象。因为此
        //内部类的对象会悄悄地链接到创建它的外围类的对象。如果你用的是静态的内部类，
        //那就不需要对其外围类对象的引用。
        Outer1 out = new Outer1();
        Outer1.Inner1 outin = out.new Inner1();
        outin.inner_f1();
    }

    /*
    注意：内部类是一个编译时的概念，一旦编译成功，就会成为完全不同的两类。
    对于一个名为outer的外部类和其内部定义的名为inner的内部类。编译完成后出现outer.class和outer$inner.class两类。
     */
}





//B. 局部内部类

//在方法中定义的内部类称为局部内部类。与局部变量类似，局部内部类不能有访问说明符，因为它不是外围类的一部分，但是它可以访问当前代码块内的常量，和此外围类所有的成员。
class Outer2{

    private int s = 0;
    private int out_i = 1;

    public void f(final int k){

        final int s = 20;
        int i = 1;
        final int j = 10;

        //定义在方法内部
        class Inner2{

            int s = 300; // 可以定义与外部类同名的变量

            // static int m = 20; //不可以定义静态变量
            Inner2(int k) {
                inner_f(k);
            }

            int inner_i = 100;

            void inner_f(int k) {
                //如果内部类没有与外部类同名的变量，在内部类中可以直接访问外部类的实例变量
                System.out.println(out_i);
                //可以访问外部类的局部变量(即方法内的变量)，但是变量必须是final的
                System.out.println(j);
                //System.out.println(i);
                //如果内部类中有与外部类同名的变量，直接用变量名访问的是内部类的变量
                System.out.println(s);
                //用this.变量名访问的也是内部类变量
                System.out.println(this.s);
                //用外部类名.this.内部类变量名访问的是外部类变量
                System.out.println(Outer2.this.s);
            }
        }
        new Inner2(k);
    }

    public static void main(String[] args) {
        // 访问局部内部类必须先有外部类对象
        Outer2 outer2 = new Outer2();
        outer2.f(3);
    }
}




//C.静态内部类(嵌套类)：

// （注意：前两种内部类与变量类似，所以可以对照参考变量）

/*
　　如果你不需要内部类对象与其外围类对象之间有联系，那你可以将内部类声明为static。这通常称为嵌套类（nested class）。想要理解static应用于内部类时的含义，你就必须记住，
    普通的内部类对象隐含地保存了一个引用，指向创建它的外围类对象。然而，当内部类是static的时，就不是这样了。嵌套类意味着：

　　1. 要创建嵌套类的对象，并不需要其外围类的对象。
　　2. 不能从嵌套类的对象中访问非静态的外围类对象。
 */
class Outer3{

    private static int i = 1;
    private int j = 10;

    public static void outer_f1(){

    }

    public void outer_f2(){

    }

    // 静态内部类可以用public,protected,private修饰
    // 静态内部类中可以定义静态或者非静态的成员
    static class Inner3{

        static int inner_i = 100;
        int inner_j = 200;

        static void inner_f1(){
            //静态内部类只能访问外部类的静态成员(包括静态变量和静态方法)
            System.out.println("Outer.i" + i);
            outer_f1();
        }

        void inner_f2() {
            // 静态内部类不能访问外部类的非静态成员(包括非静态变量和非静态方法)
            // System.out.println("Outer.i"+j);
            // outer_f2();
        }

    }

    public void outer_f3(){
        // 外部类访问内部类的静态成员：内部类.静态成员
        System.out.println(Inner3.inner_i);
        Inner3.inner_f1();
        // 外部类访问内部类的非静态成员:实例化内部类即可
        Inner3 inner = new Inner3();
        inner.inner_f2();
    }

    public static void main(String[] args) {
        new Outer3().outer_f3();
    }

    /*
    　　生成一个静态内部类不需要外部类成员：这是静态内部类和成员内部类的区别。静态内部类的对象可以直接生成：Outer.Inner in = new Outer.Inner();
    而不需要通过生成外部类对象来生成。这样实际上使静态内部类成为了一个顶级类(正常情况下，你不能在接口内部放置任何代码，但嵌套类可以作为接口的一部分，
    因为它是static 的。只是将嵌套类置于接口的命名空间内，这并不违反接口的规则）

     */

}


//D：匿名内部类

/*
    简单地说：匿名内部类就是没有名字的内部类。什么情况下需要使用匿名内部类？如果满足下面的一些条件，使用匿名内部类是比较合适的：
　　·只用到类的一个实例。
　　·类在定义后马上用到。
　　·类非常小（SUN推荐是在4行代码以下）
　　·给类命名并不会导致你的代码更容易被理解。

    在使用匿名内部类时，要记住以下几个原则：
　　·匿名内部类不能有构造方法。
　　·匿名内部类不能定义任何静态成员、方法和类。
　　·匿名内部类不能是public,protected,private,static。
　　·只能创建匿名内部类的一个实例。
    ·一个匿名内部类一定是在new的后面，用其隐含实现一个接口或实现一个类。
　　·因匿名内部类为局部内部类，所以局部内部类的所有限制都对其生效。
 */



