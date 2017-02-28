package cn.thc.keyword;

/**
 * Created by thc on 2016/12/27
 */
//http://www.cnblogs.com/withyou/archive/2013/06/15/3137885.html
public class InstanceofTest {

    public static void main(String[] args) {

        B1 b1 = new B1();
        B1 b2 = new B2();//多态
        C1 c = new C1();
        D1 d1 = new D1();
        D2 d2 = new D2();
        A a1 = new B2();

        //可以通过编译,因为A是可插拔的,B1的引用可以指向其子类对象,
        // 而其子类对象可能会实现了接口A(如B2),,但是运
        //行时就会检验了,结果为false     (B1可能与A发生多态)
        System.out.println(b1 instanceof A);
        System.out.println(b2 instanceof A);//结果为true     (B2已经与A发生多态)
        //System.out.println(b1 instanceof C1);    //编译失败,因为B与C之间不存在继承关系    (C1不可能与C1存在多态,没有继承关系)
        //System.out.println(d1 instanceof A);    //编译失败,因为D不可能有子类,不可能发生多态    (D1不可能与A多态,不可以有子类)
        System.out.println(d2 instanceof A);    //编译通过,结果为true     (D2实现了A,与A产生了多态)
        System.out.println(new B1() instanceof A); //B1的匿名对象,false     (B1可能与A发生多态)
        System.out.println(new B2() instanceof A);    //B1的匿名对象,true     (B2已经与A发生多态)
        //System.out.println(new B1(){} instanceof A);//B1的已经确定类体的,即匿名子类对象,不能通过编译
        System.out.println(new B2(){} instanceof A);//B2的虽未匿名子类对象,但是却属于A的实现类,所以编译通过,结果为true
        //System.out.println(new B3() instanceof A);    //抽象类是不可以进行实例化的,编译失败
        //System.out.println(new B3(){} instanceof A);//抽象类要产生匿名子类对象,必须复写所有抽象方法,编译失败
        //System.out.println(new B3(){public void test(){}} instanceof A);//非A的匿名子类对象,编译失败
        //System.out.println(new B4(){public void method(){}} instanceof A);//编译通过,结果为true
    }

}

interface A{
    void methiod();
}

class B1{

}

class B2 extends B1 implements A{

    @Override
    public void methiod() {

    }
}

abstract class B3{
    abstract void test();
}

abstract class B4 implements A{

}

class C1{

}

final class D1{

}

final class D2 implements A{

    @Override
    public void methiod() {

    }
}