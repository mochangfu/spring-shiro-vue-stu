package com.demo.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.demo.pojo.HomeworkAnswer;
import com.demo.pojo.User;
import com.demo.service.UserService;
import com.demo.service.impl.HomeworkAnswerImpl;
import com.demo.service.impl.StudentServiceImpl;
import com.demo.utils.EnumCode;
import com.demo.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        HomeworkAnswer homeworkAnswer = new HomeworkAnswer(null,homeworkId,fileId,null,userId,null,null,null);

        this.homeworkAnswer.add(homeworkAnswer);
        return ResultUtil.result(EnumCode.OK.getValue(),"请求成功",homeworkAnswer,null);
    }

    @PostMapping("/edit")
    public Object edit(HomeworkAnswer homeworkAnswer1)
    {
        homeworkAnswer.edit(homeworkAnswer1);
      return null;
    }

}
