package com.demo.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.demo.pojo.Homework;
import com.demo.pojo.User;
import com.demo.service.UserService;
import com.demo.service.impl.HomeworkAnswerImpl;
import com.demo.service.impl.HomeworkImpl;
import com.demo.service.impl.StudentServiceImpl;
import com.demo.utils.EnumCode;
import com.demo.utils.ResultUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Auther: qiuxinfa
 * @Date: 2019/11/24
 * @Description: com.demo.controller
 */
@RestController
@RequestMapping("homework")
public class HomeworkController {
   
    @Autowired
    private HomeworkImpl homeworkServie;
    @Autowired
    private HomeworkAnswerImpl homeworkAnswer;
    @Autowired
    private UserService userService;
    @Autowired
    private StudentServiceImpl studentsservice;
    @GetMapping("/list")
    public Object getListByPage(Integer startPage,Integer pageSize,String name,Integer id,String userId,String model,String major,String clazz){
        Page<Homework> page = new Page<>(startPage,pageSize);
        List<Homework> list = homeworkServie.getListFileByPagee(page,id,name,userId,model, major, clazz);
        List<User> users = userService.findAllUser(new User());
        Map<String,String> idUserNameMap =new HashMap<>();
        users.forEach(u->{
            idUserNameMap.put(u.getId(),u.getUsername());
        });
        list = list.stream().filter(l->l.getName()==null||l.getName().contains(name)).collect(Collectors.toList());
        for (Homework Homework : list) {
            Homework.setUserName(idUserNameMap.get(Homework.getUserId()));
        }
        return ResultUtil.result(EnumCode.OK.getValue(),"请求成功",list,page.getTotal());
    }

    @PostMapping("/delete")
    public Object delete(Homework Homework){
        Integer[] ids = Homework.getIds();
        if (null == ids || ids.length == 0) {
            return ResultUtil.result(EnumCode.BAD_REQUEST.getValue(), EnumCode.BAD_REQUEST.getText());
        }
        return homeworkServie.delete(ids);
    }

    @PostMapping("/add")
    public Object delete(String name, String desc, String date, String clazz, String major,Integer fileId,String userId)throws Exception {
        Date d = StringUtils.isEmpty(date) ?null:new SimpleDateFormat("yyyy-MM-dd").parse(date);
        Homework homework = new Homework(null,name,desc,fileId,major,clazz,d,userId,null,null);

        homeworkServie.add(homework);
        return ResultUtil.result(EnumCode.OK.getValue(),"请求成功",homework,null);
    }

    @PostMapping("/edit")
    public Object edit(Homework Homework){
      return null;
    }


    @GetMapping("/score/stats")
    public Object stats(String  clazzId,Integer  homeworkId){

        List list =homeworkAnswer.scoreStats(homeworkId);
        return ResultUtil.result(EnumCode.OK.getValue(),"请求成功",list,1);
    }

}
