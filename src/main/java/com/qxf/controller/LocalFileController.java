package com.qxf.controller;

import com.qxf.ftp.UploadUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * @Auther: qiuxinfa
 * @Date: 2019/11/15
 * @Description: com.qxf.controller
 */
@RestController
@RequestMapping(value = "/vue_shiro_photo/userImg")
public class LocalFileController extends BaseController {

    /**
     * 下载用户头像
     */
    @ResponseBody
    @GetMapping(value = "/getFtpFile/{filename}")
    public void getFile(@PathVariable("filename") String filename,HttpServletResponse response) {
        new HashMap<String, String>();
         UploadUtil.getFileFromftp("vue_shiro_photo/userImg"+filename,response);
    }


    @GetMapping(value = "/{filename}")
    public String readImg(@PathVariable("filename") String filename,HttpServletRequest request,HttpServletResponse response) throws IOException {

        ServletOutputStream out = null;
        FileInputStream ips = null;
        String url = "G:\\ftpserver\\vue_shiro_photo\\userImg\\"+filename+".jpg";
        try {
//获取图片存放路径 
            File file = new File(url);
            if(!file.exists()) {
                return null;
            }

            ips = new FileInputStream(file);
            response.setContentType("multipart/form-data");
            out = response.getOutputStream();
//读取文件流  
            int len = 0;
            byte[] buffer = new byte[1024 * 10];
            while ((len = ips.read(buffer)) != -1){
                out.write(buffer,0,len);
            }
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            out.close();
            ips.close();
        }
        return null;
    }


}
