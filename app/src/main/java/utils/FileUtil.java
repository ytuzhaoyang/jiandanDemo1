package utils;

import java.io.File;

/**
 * Created by zhaoyang on 16/8/21.
 */
public class FileUtil {

    /**
     * 获取文件夹的大小
     * @param file
     * @return
     */
    public static double getDirSize(File file) {
        //判断文件是否存在
        if (file.exists()) {
            //如果是目录则递归计算文件大小的总和
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                double size = 0;
                for (File file1 : files) {
                   size += getDirSize(file1);
                }
                return size;
            }else {
                return (double)file.length();
            }
        }else {
            return 0.0;
        }
    }
}
