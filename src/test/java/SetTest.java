import java.util.*;

/**
 * Created by thc on 2017/1/4
 */
public class SetTest {

    public static void main(String[] args) {

//        List<String> sList = new ArrayList<String>();
//        List<String> list = structureList(10);
//        sList.add("test1");
//        sList.add(0,"test2");
//        System.out.println(sList.indexOf("test2"));
//        Iterator ite = sList.iterator();
//        while (ite.hasNext()){
//            System.out.println(ite.next());
//        }

//        Map<String,String> map1 = new HashMap<String, String>();
//        Map<String,String> map2 = new HashMap<String, String>();
//        map1.put("1","test1");
//        map2.put("1","test1");
//        map2.put("2","test2");
//        System.out.println(map2.remove("test1"));


    }

    //构造指定大小String集合
    public static List<String> structureList(int size) {
        List<String> sList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            sList.add("test" + i);
        }
        return sList;
    }

}

class Student implements Comparable<Student> {

    public String name;
    public int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }


    @Override
    public int compareTo(Student o) {
        if (o.score < this.score)
            return 1;
        else if (o.score > this.score)
            return -1;
        else
            return 0;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("name:");
        sb.append(name);
        sb.append(" ");
        sb.append("score:");
        sb.append(score);
        return sb.toString();
    }

    public static void main(String[] args) {

        TreeMap map = new TreeMap();
        Student s1 = new Student("1",100);
        Student s2 = new Student("2",99);
        Student s3 = new Student("3",97);
        Student s4 = new Student("4",91);

        map.put(s1,new StudentDetailInfo(s1));
        map.put(s2,new StudentDetailInfo(s2));
        map.put(s3,new StudentDetailInfo(s3));
        map.put(s4,new StudentDetailInfo(s4));

        //打印分数位于 S4 和 S2 之间的人
        Map map1=((TreeMap)map).subMap(s4, s1);
        for (Iterator iterator = map1.keySet().iterator();iterator.hasNext();) {
            Student key = (Student) iterator.next();
            System.out.println(key+"->"+map.get(key));
        }
        System.out.println("subMap end");
    }
}

class StudentDetailInfo{
    Student s;

    public StudentDetailInfo(Student s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return s.name + "'s detail information";
    }
}