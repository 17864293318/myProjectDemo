package com.fzc.springboot.main;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author a
 * @title: UploadFileModel
 * @projectName springboot
 * @description: TODO
 * @date 2019/4/814:31
 */
public class UploadFileModel {

    /**
     * @param mfile        文件
     * @param saveRootPath 文件上传服务器根路径（eg:"D:/Tomcat 7.0/webapps/article-resource:opt/public/"）
     * @param saveFolder   文件上传文件夹（eg:"/upload/Img"）
     * @return 文件存储的相对路径
     */


    private static String updateFile(MultipartFile mfile, String saveRootPath, String saveFolder) {
        // 文件的后缀
        String postFix = getFilePostFix(mfile.getOriginalFilename());
        //文件上传相对路径
        String uploadPath = getFileSaveDir(saveFolder);

        File directory = new File(saveRootPath + uploadPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        String fileName = new StringBuffer().append(String.valueOf(System.currentTimeMillis())).append(postFix).toString();
        File file = new File(directory, fileName);

        try {
            mfile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //返回文件上传路径
        return uploadPath;
    }

    /**
     * 拼接文件存储路径
     */
    public static String getFileSaveDir(String saveFolder) {
        StringBuilder savePath = new StringBuilder();
        LocalDate now = LocalDate.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyyMM");
        String date = now.format(timeFormatter);
        return savePath.append(saveFolder).append("/").append(date).append("/").toString();
    }

    //获取文件后缀（文件类型）
    public static String getFilePostFix(String fileName){
        int lastIndex = fileName.lastIndexOf(".");
        String postFix = fileName.substring(lastIndex);
        if (lastIndex < 0) {
            return null;
        }
        return postFix;
    }


}
