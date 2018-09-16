package com.wzl.myshop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by thinkpad on 2018/9/15.
 */

@Controller
public class UploadController {

    private static final String UPLOAD_PATH = "/static/upload/";

    /**
     * 文件上传
     * @param dropFile  Dropzone
     * @param editorFile    wangeditor
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="upload", method = RequestMethod.POST)
    public Map<String, Object> upload(MultipartFile dropFile, MultipartFile editorFile, HttpServletRequest request) {
        Map<String, Object> result = new HashMap();

        //前端上传的文件
        MultipartFile myFile = dropFile == null ? editorFile : dropFile;

        //获取文件后缀
        String filename = myFile.getOriginalFilename();
        String fileSuffix = filename.substring(filename.lastIndexOf("."));
        System.out.println(fileSuffix);

        System.out.println("filename");
        //文件存放路径
        String filePath = request.getSession().getServletContext().getRealPath(UPLOAD_PATH);
        System.out.println(filePath);
        File file = new File(filePath);
        //判断路径是否存在，如果不存在则创建文件夹
        if (!file.exists()) {
            System.out.println("mkdir");
            file.mkdir();
        }

        //将文件写入目标目录
        file = new File(filePath, UUID.randomUUID()+fileSuffix);

        try {
            System.out.println("dropfileeee");
            myFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(dropFile);
        System.out.println(editorFile);

        //wangeditor上传
        if (dropFile == null) {
            String servePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
            System.out.println(servePath);
            result.put("errno", 0);
            result.put("data", new String[]{servePath + UPLOAD_PATH + file.getName()});
        }

        //dropzone上传
        else{
            result.put("fileName", UPLOAD_PATH+file.getName());
        }




        return result;
    }
}
