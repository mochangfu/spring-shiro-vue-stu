package com.demo.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.demo.ftp.UploadUtil;
import com.demo.pojo.FileRecord;
import com.demo.pojo.HomeworkAnswer;
import com.demo.pojo.User;
import com.demo.service.FileRecordService;
import com.demo.service.UserService;
import com.demo.service.impl.HomeworkAnswerImpl;
import com.demo.service.impl.StudentServiceImpl;
import com.demo.utils.EnumCode;
import com.demo.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: qiuxinfa
 * @Date: 2019/11/24
 * @Description: com.demo.controller
 */
@RestController
@RequestMapping("homeworkAnswer")
public class HomeworkAnswerController {
   
    @Autowired
    private HomeworkAnswerImpl homeworkAnswer;
    @Autowired
    private UserService userService;

    Map<String, String> uploadFile;
    @Autowired
    private FileRecordService fileRecordService;
    @Autowired
    private StudentServiceImpl studentsservice;
    @GetMapping("/list")
    public Object getListByPage(Integer startPage,Integer pageSize,String name,Integer id,String userId,Integer homeworkId){
        Page<HomeworkAnswer> page = new Page<>(startPage,pageSize);
        List<HomeworkAnswer> list = homeworkAnswer.getListFileByPagee(page,id,null,userId,homeworkId);
        List<User> users = userService.findAllUser(new User());
        Map<String,String> idUserNameMap =new HashMap<>();
        users.forEach(u->{
            idUserNameMap.put(u.getId(),u.getUsername());
        });

        for (HomeworkAnswer homeworkAnswer : list) {
            homeworkAnswer.setUserName(idUserNameMap.get(homeworkAnswer.getUserId()));
        }
        return ResultUtil.result(EnumCode.OK.getValue(),"请求成功",list,page.getTotal());
    }

    @GetMapping("/teacherlist")
    public Object teacherlist(Integer startPage,Integer pageSize,String name,Integer id,String userId,Integer homeworkId){
        Page<HomeworkAnswer> page = new Page<>(startPage,pageSize);
        List<HomeworkAnswer> list = homeworkAnswer.getListFileByPagee(page,id,name,null,homeworkId);
        List<User> users = userService.findAllUser(new User());
        Map<String,String> idUserNameMap =new HashMap<>();
        users.forEach(u->{
            idUserNameMap.put(u.getId(),u.getUsername());
        });

        for (HomeworkAnswer homeworkAnswer : list) {
            homeworkAnswer.setUserName(idUserNameMap.get(homeworkAnswer.getUserId()));
        }
        return ResultUtil.result(EnumCode.OK.getValue(),"请求成功",list,page.getTotal());
    }
    @PostMapping("/delete")
    public Object delete(HomeworkAnswer Homework){
        Integer[] ids = Homework.getIds();
        if (null == ids || ids.length == 0) {
            return ResultUtil.result(EnumCode.BAD_REQUEST.getValue(), EnumCode.BAD_REQUEST.getText());
        }
        return homeworkAnswer.delete(ids);
    }



    @PostMapping("/add")
    public Object delete(Integer fileId, Integer homeworkId,String userId,Double score)throws Exception {


        Date d =new Date();
        HomeworkAnswer homeworkAnswer = new HomeworkAnswer(null,homeworkId,fileId,null,userId,null,null,null,null,null,null,0);

        this.homeworkAnswer.add(homeworkAnswer);
        return ResultUtil.result(EnumCode.OK.getValue(),"请求成功",homeworkAnswer,null);
    }

    @PostMapping("/edit")
    public Object edit(Integer id,Integer fileId, Integer homeworkId,String userId,Double score)
    {
        Page<HomeworkAnswer> page = new Page<>(1,30);
        HomeworkAnswer vo=   homeworkAnswer.getListFileByPagee(page,id,null,null,null).get(0);
        if(fileId!=null)vo.setFileId(fileId);
        if(score!=null)vo.setScore(score);
        homeworkAnswer.edit(vo);
      return ResultUtil.result(EnumCode.OK.getValue(),"请求成功",vo,null);
    }
    @PostMapping("/uploadFile")
    public String uploadAnswerFile(HttpServletRequest request, @RequestParam(required = false ,value = "userId" ) String userId,
                                 @RequestParam(required = false ,value = "id" ) Integer id)throws Exception {

        uploadFile = new HashMap<String, String>();
        uploadFile = UploadUtil.uploadFile(request, "file");

        String fileName = uploadFile.get("fileName");
        FileRecord fileRecord=null;
        if(fileName!=null){
            fileRecord =new FileRecord(null,uploadFile.get("name"),fileName,new Date(), userId,null,"question",null,null,null,null,null);
            fileRecordService.addFileRecord(fileRecord);
        }
       // HomeworkAnswer myAnswer = homeworkAnswer.getListFileByPagee(null,id,null,userId,id).get(0);
        edit( id, fileRecord.getId(),  null, null, null);
        return ResultUtil.result(EnumCode.OK.getValue(),"新增成功",fileRecord);
    }


}
