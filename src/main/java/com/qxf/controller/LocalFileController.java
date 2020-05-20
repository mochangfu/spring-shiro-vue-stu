package com.qxf.controller;

import com.qxf.ftp.UploadUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: qiuxinfa
 * @Date: 2019/11/15
 * @Description: com.qxf.controller
 */
@RestController
@RequestMapping
public class LocalFileController extends BaseController {

    Map<String, String> uploadFile;
    @ResponseBody
    @RequestMapping(value = "/file/uploadFile", method = RequestMethod.POST)
    public String uploadImg(HttpServletRequest request)throws Exception {
        uploadFile = new HashMap<String, String>();
        uploadFile = UploadUtil.uploadFile(request, "file");
        return uploadFile.get("fileName");
    }
    @ResponseBody
    @GetMapping(value = "/file/downloadFile/{filename:.+}")
    public void getFile(@PathVariable("filename") String filename, HttpServletRequest request,HttpServletResponse response) {
        new HashMap<String, String>();
         UploadUtil.downloadFile("file",filename,request,response);
    }

    @ResponseBody
    @RequestMapping(value = "/exam/uploadFile", method = RequestMethod.POST)
    public String uploadExamFile(HttpServletRequest request)throws Exception {
        uploadFile = new HashMap<String, String>();
        uploadFile = UploadUtil.uploadFile(request, "exam/file");
        return uploadFile.get("fileName");
    }

    @GetMapping(value = "/exam/{filename:.+}")
    public String getExamFile(@PathVariable("filename") String filename,HttpServletRequest request,HttpServletResponse response) throws IOException {
        UploadUtil.readFile( "exam/file",  filename,  response);
        return null;
    }

}
