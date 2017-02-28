/**
 * Created by thc on 2016/12/27
 */
public class DoTest {

    public static void main(String[] args) {
        B b = new B();
        C c = new C();
        D d = new D();
        if(d instanceof A){

        }

    }

}


interface  A{

}

class B implements A{

}

class C implements A{

}

class D extends C{

}