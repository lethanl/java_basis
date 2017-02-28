package cn.thc.decompression;

import org.junit.Test;

import java.io.File;

/**
 * Created by thc on 2017/2/7
 */
public class GzUtilsTest {

    @Test
    public void testCompressFiles2Zip() throws Exception {

        //存放待压缩文件的目录
        File srcFile = new File("H:\\dd");
        //压缩后的zip文件路径
        String zipFilePath = "H:\\ss.zip";
        if (srcFile.exists()) {
            File[] files = srcFile.listFiles();
            GzUtils.compressFiles2Zip(files, zipFilePath);
        }

    }

    @Test
    public void testDecompressZip() throws Exception {
        //压缩包所在路径
        String zipFilePath = "H:\\ss.zip";
        //解压后的文件存放目录
        String saveFileDir = "H:\\";
        //调用解压方法
        GzUtils.decompressZip(zipFilePath,saveFileDir);
    }
}