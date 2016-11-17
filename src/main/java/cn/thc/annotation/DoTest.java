package cn.thc.annotation;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by thc on 2016/11/15
 */
public class DoTest {

    public static void main(String[] args) {
        List<Integer> useCases = new ArrayList<Integer>();
        Collections.addAll(useCases, 47, 48, 49, 50);
        trackUseCases(useCases, PasswordUtils.class);
    }

    public static void trackUseCases(List<Integer> useCases,Class<?> cl){
        for (Method m : cl.getDeclaredMethods()) {
            UseCase uc = m.getAnnotation(UseCase.class);
            if(uc != null){
                System.out.println("Found Use Case:" + uc.id() + " " + uc.description());
                useCases.remove(new Integer(uc.id()));
            }
        }
        for (int useCase : useCases) {
            System.out.println("Warning: Missing use case-" + useCase);
        }
    }

}


class PasswordUtils{

    @UseCase(id = "47",description = "Passwords must contain at least one numeric")
    public boolean validatePassword(String password){
        return (password.matches("\\w*\\d\\w*"));
    }

    @UseCase(id = "48")
    public String encryptPassword(String password){
        return new StringBuffer(password).reverse().toString();
    }

}

//自定义类注解
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface MyAnnotationForClass{
    public String name();
    public String value();
}

//自定义方法注解
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface MyAnnotationForMethod{
    public String name();
    public String value();
}

//自定义参数注解
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@interface MyAnnotationForParameter{
    public String name();
    public String value();
}

//自定义局部变量声明
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MyAnnotationForField{
    public String name();
    public String value();
}

//类注解
@MyAnnotationForClass(name="class",  value = "Hello class")
class TheClass {

    @MyAnnotationForMethod(name="method",value ="Hello method")
    public void doSomething(){

    }

    public void doSomethingElse(@MyAnnotationForParameter(name="parameter",value = "Hello parameter") String parameter){

    }

    @MyAnnotationForField(name = "field",value = "Hello field")
    public String myField = null;

}
class HandleMyAnnotation{

    public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException {
        //访问类注解
        //doClassTest();

        //访问方法注解
        //doMethodTest();

        //访问方法参数注解
        //doParameter();

        //访问变量注解
        doField();
    }

    //访问类注解
    public static void doClassTest(){
        Class aClass = TheClass.class;
        //方法一
        //Annotation[] annotations = aClass.getAnnotations();
        //for (Annotation annotation : annotations) {
        //    if(annotation instanceof MyAnnotationForClass){
        //        MyAnnotationForClass myAnnotation = (MyAnnotationForClass) annotation;
        //        System.out.println("name: " + myAnnotation.name());
        //        System.out.println("value: " + myAnnotation.value());
        //    }
        //}

        //方法二
        Annotation annotation = aClass.getAnnotation(MyAnnotationForClass.class);
        if(annotation instanceof MyAnnotationForClass){
            MyAnnotationForClass myAnnotation = (MyAnnotationForClass) annotation;
            System.out.println("name: " + myAnnotation.name());
            System.out.println("value: " + myAnnotation.value());
        }
    }

    //访问方法注解
    public static void doMethodTest() throws NoSuchMethodException {
        Class aClass = TheClass.class;
        Method method = aClass.getMethod("doSomething",null);
        //方法一
        //Annotation[] annotations = method.getDeclaredAnnotations();
        //for (Annotation annotation : annotations) {
        //    if(annotation instanceof MyAnnotationForMethod){
        //        MyAnnotationForMethod myAnnotation = (MyAnnotationForMethod) annotation;
        //        System.out.println("name: " + myAnnotation.name());
        //        System.out.println("value: " + myAnnotation.value());
        //    }
        //}

        //方法二
        Annotation annotation = method.getAnnotation(MyAnnotationForMethod.class);
        if(annotation instanceof MyAnnotationForMethod){
            MyAnnotationForMethod myAnnotation = (MyAnnotationForMethod) annotation;
            System.out.println("name: " + myAnnotation.name());
            System.out.println("value: " + myAnnotation.value());
        }

    }

    //访问方法参数注解
    public static void doParameter() throws NoSuchMethodException {
        Class aClass = TheClass.class;
        Method method = aClass.getMethod("doSomethingElse",new Class[]{String.class});
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        Class[] parameterTypes = method.getParameterTypes();

        int i = 0;
        for (Annotation[] parameterAnnotation : parameterAnnotations) {
            Class parameterType = parameterTypes[i++];

            for (Annotation annotation : parameterAnnotation) {
                if(annotation instanceof MyAnnotationForParameter){
                    MyAnnotationForParameter myAnnotation = (MyAnnotationForParameter) annotation;
                    System.out.println("param: " + parameterType.getName());
                    System.out.println("name : " + myAnnotation.name());
                    System.out.println("value: " + myAnnotation.value());
                }
            }
        }
    }

    //访问变量注解
    public static void doField() throws NoSuchFieldException {
        Class aClass = TheClass.class;
        Field field = aClass.getField("myField");
        //Annotation[] annotations = field.getDeclaredAnnotations();
        //for (Annotation annotation : annotations) {
        //    if(annotation instanceof MyAnnotationForField){
        //        MyAnnotationForField myAnnotation = (MyAnnotationForField) annotation;
        //        System.out.println("name: " + myAnnotation.name());
        //        System.out.println("value: " + myAnnotation.value());
        //    }
        //}
        Annotation annotation = field.getAnnotation(MyAnnotationForField.class);
        if(annotation instanceof MyAnnotationForField){
            MyAnnotationForField myAnnotation = (MyAnnotationForField) annotation;
            System.out.println("name: " + myAnnotation.name());
            System.out.println("value: " + myAnnotation.value());
        }
    }

}