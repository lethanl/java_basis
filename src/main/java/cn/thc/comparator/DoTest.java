package cn.thc.comparator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by thc on 2016/11/11
 */
//参考：http://blog.csdn.net/dm_vincent/article/details/40391387
//      http://blog.csdn.net/itmyhome1990/article/details/8952722
public class DoTest {

    public static void main(String[] args) {

        final List<Person> persons = Arrays.asList(
            new Person("John", 20),
            new Person("Greg", 35),
            new Person("Sara", 21),
            new Person("Jane", 21)
        );


        List<Person> ascendingAge = persons.stream().sorted().collect(Collectors.toList());
        System.out.println(ascendingAge);

        //Comparable测试
        //Student stu[] = {
        //        new Student("张三",22,80f)
        //        ,new Student("李四",23,83f)
        //        ,new Student("王五",21,80f)
        //};
        //
        //Arrays.sort(stu);
        //for (Student student : stu) {
        //    System.out.println(student);
        //}

        //Comparator测试
        //Student1 stu[] = {new Student1("张三",23)
        //        ,new Student1("李四",26)
        //        ,new Student1("王五",22)};
        //
        //Arrays.sort(stu,new MyComparator());//对象数组进行排序操作
        //
        //List<Student1> list = new ArrayList<Student1>();
        //list.add(new Student1("zhangsan",31));
        //list.add(new Student1("lisi",30));
        //list.add(new Student1("wangwu",35));
        //Collections.sort(list,new MyComparator());//List集合进行排序操作
        //
        //for (int i = 0; i < stu.length; i++) {
        //    Student1 s = stu[i];
        //    System.out.println(s);
        //}
        //
        //System.out.println("***********");
        //
        //for (int i = 0; i < list.size(); i++) {
        //    Student1 student1 = list.get(i);
        //    System.out.println(student1);
        //}
    }


}

class Person implements Comparable<Person>{
    private final String name;
    private final int age;
    public Person(final String theName, final int theAge) {
        name = theName;
        age = theAge;
    }
    public String getName() { return name; }
    public int getAge() { return age; }
    public int ageDifference(final Person other) {
        return age - other.age;
    }
    public String toString() {
        return String.format("%s - %d", name, age);
    }

    @Override
    public int compareTo(Person person) {
        return 0;
    }
}

//Comparable接口
//要求：定义一个学生类，里面有姓名，年龄，成绩三个属性，要求按成绩由高到低排序，如果成绩相等，则按照年龄由低到高排序。
class Student implements Comparable<Student>{

    private String name;
    private int age;
    private float score;

    public Student(String name, int age, float score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    @Override
    public int compareTo(Student stu) {//覆写compareTo方法实现排序规则的应用
        if(this.score>stu.score){
            return -1;
        }else if(this.score<stu.score){
            return 1;
        }else{
            if(this.age>stu.age){
                return 1;
            }else if(this.age<stu.age){
                return -1;
            }else{
                return 0;
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                '}';
    }
}

class Student1{
    private String name;
    private int age;

    public Student1(String name,int age ){
        this.name = name;
        this.age = age;
    }

    public String toString(){
        return "姓名："+this.name+", 年龄："+this.age;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}

//Compartor
//如果一个类已经开放完成，但是在此类建立的初期并没有实现Comparable接口，此时肯定是无法进行对象排序操作的，所以为了解决这一的问题，
// java又定义了另一个比较器的操作接口 Comparator 此接口定义在java.util包中
class MyComparator implements Comparator<Student1>{

    @Override
    public int compare(Student1 o1, Student1 o2) {
        if(o1.getAge() > o2.getAge()){
            return 1;
        }else if(o1.getAge() < o2.getAge()){
            return -1;
        }else {
            return 0;
        }
    }
}

//分析比较器的排序原理
//实际上比较器的操作，就是经常听到的二叉树的排序算法。
//排序的基本原理：使用第一个元素作为根节点，之后如果后面的内容比根节点小，则放在左子树，如果内容比根节点的内容要大，则放在右子树。
class BinaryTree{

    class Node{//// 声明一个节点类

        private Comparable data;//保存具体内容
        private Node left;//保存左子树
        private Node right;//保存右子树

        public Node(Comparable data) {
            this.data = data;
        }

        public void addNode(Node newNode){
            // 确定是放在左子树还是右子树
            if(newNode.data.compareTo(this.data) < 0){//内容小，放在左子树
                if(this.left == null){
                    this.left = newNode;// 直接将新的节点设置成左子树
                }else {
                    this.left.addNode(newNode);// 继续向下判断
                }
            }
            if(newNode.data.compareTo(this.data) >= 0){// 放在右子树
                if(this.right == null){
                    this.right = newNode;// 没有右子树则将此节点设置成右子树
                }else {
                    this.right.addNode(newNode);// 继续向下判断
                }
            }
        }

        public void printNode(){// 输出的时候采用中序遍历
            if(this.left != null){
                this.left.printNode();//输出左子树
            }
            System.out.println(this.data + "\t");
            if(this.right != null){
                this.right.printNode();
            }
        }

    }

    private Node root;//根元素

    public void add(Comparable data){// 加入元素
        Node newNode = new Node(data);//定义新的节点
        if(root == null){// 没有根节点
            root = newNode;// 第一个元素作为根节点
        }else {
            root.addNode(newNode);// 确定是放在左子树还是放在右子树
        }
    }

    public void print(){
        this.root.printNode();//通过节点输出
    }



    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        bt.add(8);
        bt.add(3);
        bt.add(3);
        bt.add(10);
        bt.add(9);
        bt.add(1);
        bt.add(5);
        bt.add(5);
        System.out.println("排序后结果：");
        bt.print();
    }
}
