package cn.thc.enumtest;

/**
 * Created by thc on 2016/11/23
 */
public class DoTest {

    // 1. 定义枚举类型
    public enum Light{
        /*利用构造函数传参利用构造函数传参
        * 通过括号赋值,而且必须有带参构造器和属性和方法，否则编译出错
        * 赋值必须是都赋值或都不赋值，不能一部分赋值一部分不赋值
        * 如果不赋值则不能写构造器，赋值编译也出错
        * */
        RED("红色"),GREEN("绿色"),YELLOW("黄色");

        private String clor;

        Light(String clor) {
            this.clor = clor;
        }

        public String getClor() {
            return clor;
        }

        public void setClor(String clor) {
            this.clor = clor;
        }

        @Override
        public String toString() {
            return "Light{" +
                    "clor='" + clor + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        testTraversalEnum();
    }

    //演示枚举类型的遍历
    private static void testTraversalEnum(){

        Light[] allLight = Light.values();
        for (Light light : allLight) {
            System.out.println(light);
        }

    }

}
