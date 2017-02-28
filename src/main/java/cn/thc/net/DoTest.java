package cn.thc.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by thc on 2016/11/18
 */
//参考：java网络编程第四版
public class DoTest {

    public static void main(String[] args) {

        inetAddressTest();

    }

    //InteAddress类
    public static void inetAddressTest(){

        try {
            //InetAddress address = InetAddress.getByName("www.oreilly.com");
            //System.out.println(address);
            //InetAddress address2 = InetAddress.getByName("23.42.98.208");
            //System.out.println(address2.getHostName());
            InetAddress[] addresses = InetAddress.getAllByName("www.oreilly.com");
            for (InetAddress inetAddress : addresses) {
                System.out.println(inetAddress);
            }
            //查询本机ip地址
            InetAddress me = InetAddress.getLocalHost();
            System.out.println(me);

            InetAddress ia = InetAddress.getByName("208.201.239.100");
            System.out.println(ia.getCanonicalHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }


}


