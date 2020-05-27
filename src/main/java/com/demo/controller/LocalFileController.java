package com.demo.controller;

import com.demo.ftp.UploadUtil;
import com.demo.pojo.FileRecord;
import com.demo.service.FileRecordService;
import com.demo.utils.EnumCode;
import com.demo.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Auther: qiuxinfa
 * @Date: 2019/11/15
 * @Description: com.demo.controller
 */
@RestController
@RequestMapping
public class LocalFileController extends BaseController {

    @Autowired
            private FileRecordService fileRecordService;
    Map<String, String> uploadFile;
    @ResponseBody
    @RequestMapping(value = "/file/uploadFile", method = RequestMethod.POST)
    public FileRecord uploadImg(@RequestParam(required = false ,value = "userId" ) String userId,HttpServletRequest request)throws Exception {

        uploadFile = new HashMap<String, String>();
        uploadFile = UploadUtil.uploadFile(request, "file");

        String fileName = uploadFile.get("fileName");
        FileRecord fileRecord=null;
        if(fileName!=null){
            fileRecord =new FileRecord(null,uploadFile.get("name"),fileName,new Date(), userId,null,"question",null,null,null,null,null);
            fileRecordService.addFileRecord(fileRecord);
        }
        return fileRecord;
    }
    @ResponseBody
    @GetMapping(value = "/file/downloadFile/{filename:.+}")
    public void downloadFile(@PathVariable("filename") String filename, HttpServletRequest request,HttpServletResponse response) {
        new HashMap<String, String>();
        if(StringUtils.isEmpty(filename))return;
        List<FileRecord>  fileRecords =fileRecordService.getListFileByPagee(null,null,null,null,null,null,null);
        fileRecords =fileRecords.stream().filter(f->filename.equals(f.getFileName())).collect(Collectors.toList());
        String downloadName= null;
        if(fileRecords.size()>0)downloadName=fileRecords.get(0).getName();
         UploadUtil.downloadFile("file",filename,downloadName,request,response);
    }
    @ResponseBody
    @GetMapping(value = "/exam/downloadFile")
    public void downExamloadFile(@RequestParam("fileName") String fileName, HttpServletRequest request,HttpServletResponse response) {
        new HashMap<String, String>();
        List<FileRecord>  fileRecords =fileRecordService.getListFileByPagee(null,null,null,null,null,null,null);
        fileRecords =fileRecords.stream().filter(f->fileName.equals(f.getFileName())).collect(Collectors.toList());
        String downloadName= null;
        if(fileRecords.size()>0)downloadName=fileRecords.get(0).getName();
        UploadUtil.downloadFile("exam/file",fileName,downloadName,request,response);
    }
    @ResponseBody
    @RequestMapping(value = "/exam/uploadFile", method = RequestMethod.POST)
    public String uploadExamFile(HttpServletRequest request,  @RequestParam(required = false ,value = "userId" ) String userId,
                                 @RequestParam(required = false ,value = "instituteId" ) String instituteId,
                                 @RequestParam(required = false ,value = "majorId" ) String majorId)throws Exception {
        uploadFile = new HashMap<String, String>();
        uploadFile = UploadUtil.uploadFile(request, "exam/file");

        String fileName = uploadFile.get("fileName");
        FileRecord fileRecord=null;
        if(fileName!=null){
             fileRecord =new FileRecord(null,uploadFile.get("name"),fileName,new Date(), userId,null,"question",instituteId,majorId,null,null,null);
            fileRecordService.addFileRecord(fileRecord);
        }
        return ResultUtil.result(EnumCode.OK.getValue(),"新增成功",fileRecord);
    }

    @GetMapping(value = "/exam/{filename:.+}")
    public String getExamFile(@PathVariable("filename") String filename,HttpServletRequest request,HttpServletResponse response) throws IOException {
        UploadUtil.readFile( "exam/file",  filename,  response);
        return null;
    }

}
