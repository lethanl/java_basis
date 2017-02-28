package cn.thc.basis;

/**
 * Created by thc on 2016/12/9
 */
//参考：http://www.cnblogs.com/hxsyl/archive/2013/03/09/2951437.html
//参考：http://blog.chinaunix.net/xmlrpc.php?r=blog/article&uid=26808060&id=4616771
public class ConstructorsTest {

    public static void main(String[] args) {

    }

}


class Animal{

    private String name;

    public Animal(){

    }

    public Animal(String name) {
        this.name = name;
    }
}

class Dog extends Animal{


}
