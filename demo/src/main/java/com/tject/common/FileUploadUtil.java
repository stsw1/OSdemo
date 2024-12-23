package com.tject.common;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * 上传文件工具类
 */
public final class FileUploadUtil {

    /**
     * 上传文件
     *
     * @param multipartFile    文件对象
     * @param basePath         基本路径（需要配置的） （例如： d:/temp）
     * @param returnPathPrefix 返回url路径的前缀 （例如：image、video、file。。。。）
     * @return
     */
    public static String upload(MultipartFile multipartFile,
                         String basePath,
                         String returnPathPrefix) {
        //根据当前日期 生成文件上传的目录
        //当前日期，转成字符串格式2024-08-09，把段中线去掉 20240809
        LocalDate now = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        //数组格式 20240809
        String strLocalDateNow = dtf.format(now);
        //处理basePath路径最后加一个 /
        basePath = basePath.endsWith("/") ? basePath : basePath + "/";
        //组装路径  例如 ：d:/temp/image/20240809
        basePath = basePath + returnPathPrefix + "/" + strLocalDateNow;
        //basePath转换成File对象
        File basePathFile = new File(basePath);
        //判断File对应的目录是否存在，不存在就创建，存在就忽略
        if (!basePathFile.exists()) {
            //mkdirs() 创建多级目录
            basePathFile.mkdirs();
        }
        //生成唯一的文件名称（uuid）作为存储文件名称
        //获取上传文件的名称（包括后缀名） 例如：bb.jpg
        String originalFilename = multipartFile.getOriginalFilename();
        int lastDotIndex = originalFilename.lastIndexOf('.');
        //获取上传文件的后缀名 例如：jpg
        String suffixName = originalFilename.substring(lastDotIndex + 1);
        //uuid去掉短中线作为 上传的新名称 例如：55baa915561311ef91557085c2002664
        String newFileName = UUID.randomUUID().toString().replaceAll("-", "");
        //组装文件的物理位置+文件名称 例如：d:/temp/image/20240809/55baa915561311ef91557085c2002664.jpg
        String newFileNamePath = basePath + "/" + newFileName + "." + suffixName;
        //上传文件
        File newFileNamePathFile = new File(newFileNamePath);
        try {
            multipartFile.transferTo(newFileNamePathFile);
        } catch (IOException e) {
            //抛出运行时异常
            throw new RuntimeException(e);
        }
        //返回文件请求的url路径，是为了请求这个文件 例如：/image/20240809/55baa915561311ef91557085c2002664.jpg
        return "/" + returnPathPrefix + "/" + strLocalDateNow + "/" + newFileName + "." + suffixName;
    }
}
