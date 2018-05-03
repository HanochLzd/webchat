package com.jxau.soft.webchat.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * NAME   :  EmailSystem/com.amayadream.emailsystem.util
 * Date   :  2015.12.04 22:58
 * 文件名处理
 *
 * @author Amayadream
 */
public class FileUtil {
    /**
     * 按照分隔符将地址字符串切割开,然后拼装成FIle数组
     *
     * @param files 字符串
     * @param split 分隔符,可以为正则
     * @return File[]
     */
    public static File[] getFileArrayByString(String files, String split) {
        String[] aa = files.split(split);
        File[] attachments = new File[aa.length];
        for (int i = 0; i < aa.length; i++) {
            attachments[i] = new File(aa[i]);
        }
        return attachments;
    }

    static String getFilePrefix(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
