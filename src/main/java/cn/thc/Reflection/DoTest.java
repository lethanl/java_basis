package cn.thc.Reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by thc on 2016/11/18
 */
//参考：http://wiki.jikexueyuan.com/project/java-reflection/java-classes.html
//反射
public class DoTest {

    public static void main(String[] args) throws ClassNotFoundException {

        //获取Class对象两种方法
        //Class aClass = DoTest.class;
        Class aClass = Class.forName("cn.thc.Reflection.DoTest");

        //得到类的全路径名
        String className = aClass.getName();
        System.out.println(className);
        //得到类名
        String simpleName = aClass.getSimpleName();
        System.out.println(simpleName);

        //修饰符
        int modifiers = aClass.getModifiers();
        System.out.println(modifiers);

        //使用 java.lang.reflect.Modifier 类中的方法来检查修饰符的类型
        Modifier.isAbstract(modifiers);
        Modifier.isFinal(modifiers);
        Modifier.isInterface(modifiers);
        Modifier.isNative(modifiers);
        Modifier.isPrivate(modifiers);
        Modifier.isProtected(modifiers);
        Modifier.isPublic(modifiers);
        Modifier.isStatic(modifiers);
        Modifier.isStrict(modifiers);
        Modifier.isSynchronized(modifiers);
        Modifier.isTransient(modifiers);
        Modifier.isVolatile(modifiers);

        //获取包信息
        Package  pge = aClass.getPackage();
        System.out.println(pge);

        //获取父类
        Class superClass = aClass.getSuperclass();
        System.out.println(superClass);

        //实现的接口
        Class[] interfaces = aClass.getInterfaces();
        System.out.println(interfaces.length);
        for (Class anInterface : interfaces) {
            System.out.println(anInterface);
        }

        //构造方法
        Constructor[] constructors = aClass.getConstructors();

        //方法
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }

        //变量
        Field[] fields = aClass.getFields();

        //注解
        Annotation[] annotations = aClass.getAnnotations();

    }

}
