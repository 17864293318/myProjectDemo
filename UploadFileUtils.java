package com.example.demo.controller;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName : UploadFileUtils
 * @Description: 文件上传工具类
 * @Create Time: 2019/3/21 11:43
 * @Author Name: liusw
 * @Version : 1.0
 * ##文件上传限制(请确认所有拦截点项目均配置文件上传规则）
 * spring.http.multipart.enabled=true(默认开启)
 * spring.http.multipart.file-size-threshold=0
 * #spring.http.multipart.location=
 * spring.http.multipart.max-file-size=20Mb
 * spring.http.multipart.max-request-size=20Mb
 */
public class UploadFileUtils {

    /**
     * @param mfile        文件
     * @param limitSize    限制文件大小（单位:M）
     * @param saveRootPath 文件上传服务器根路径（eg:"D:/Tomcat 7.0/webapps/article-resource:opt/public/"）
     * @param saveFolder   文件上传文件夹（eg:"/upload/Img"）
     * @return 文件存储的相对路径
     */
    public static String uploadFile(MultipartFile mfile, int limitSize, String saveRootPath, String saveFolder) throws IOException {
        long imgSize = limitSize * 1024 * 1024;
        // 判断文件大小是否超过设定的值
        if (mfile.getSize() <= imgSize) {
            String filename = mfile.getOriginalFilename();
            assert filename != null;
            String postFix = filename.substring(filename.lastIndexOf("."));
            //文件上传相对路径
            String uploadPath = getFileSaveDir(saveFolder);

            File directory = new File(saveRootPath + uploadPath);
            if (!directory.exists()) {
                boolean mkdirs = directory.mkdirs();
                if (mkdirs) {
                    System.out.println("创建目录成功");
                }
            }
            String fileName = String.valueOf(System.currentTimeMillis());
            File file = new File(directory, fileName + postFix);
            mfile.transferTo(file);
            return uploadPath;
        } else {
            return "over";
        }
    }

    /**
     * 拼接文件存储路径
     */
    public static String getFileSaveDir(String saveFolder) {
        StringBuilder savePath = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        Date date = new Date();
        String ym = sdf.format(date);
        return savePath.append(saveFolder).append("/").append(ym).append("/").toString();
    }
}


