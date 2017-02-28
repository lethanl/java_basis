package cn.thc.internetworm;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by thc on 2016/11/18
 */
//爬虫
//commons-httpclient-3.1.jar之前旧版本
public class DoTest {

    public static void main(String[] args) throws IOException {

        //httpClient("http://baidu.com");

        downloadPage("https://www.baidu.com/");

    }

    public static void urlTest(String path){
        try {
            URL url = new URL(path);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void httpClient(String path){
        try {
            //创建一个客户端，类似打开一个浏览器
            HttpClient httpClient = new HttpClient();

            //创建一个get方法，类似在浏览器中输入一个地址
            //GetMethod getMethod = new GetMethod(path);

            PostMethod getMethod = new PostMethod(path);

            //回车，获取响应状态
            int statusCode = httpClient.executeMethod(getMethod);

            //查看命中情况，可获得的东西有很多，比如head,cookies等
            System.out.println("response=" + getMethod.getResponseBodyAsString());

            //释放
            getMethod.releaseConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void downloadPage(String path) throws IOException {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(path);
        GetMethod getMethod = new GetMethod(path);
        int statusCode = httpClient.executeMethod(getMethod);
        if (statusCode == HttpStatus.SC_OK) {
            inputStream = getMethod.getResponseBodyAsStream();
            outputStream = new FileOutputStream("E://d.txt");
            int tempByte = -1;
            while ((tempByte = inputStream.read()) > 0){
                outputStream.write(tempByte);
            }
            if(inputStream != null){
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

}
