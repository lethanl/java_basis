package cn.thc.io;

import java.io.File;
import java.io.IOException;

/**
 * Created by thc on 2016/12/5
 */
//http://davidisok.iteye.com/blog/2106489
public class DoTest {

    public static void main(String[] args) {

    }

    public static void createFile(){
        File file = new File("E:/create.txt");
        try {
            file.createNewFile();//当不存在时创建文件
            System.out.println("该分区大小：" + file.getTotalSpace()/(1024*1024*1024) + "G");
            file.mkdir();//创建目录
            System.out.println("file name ： " + file.getName());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
