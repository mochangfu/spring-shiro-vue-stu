package com.demo.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.demo.pojo.Grade;
import com.demo.service.GradeService;
import com.demo.utils.EnumCode;
import com.demo.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: qiuxinfa
 * @Date: 2019/11/28
 * @Description: com.demo.controller
 */
@RestController
@RequestMapping("/grade")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    //老师：查看某门课程的学生，参数id为课程-老师中间表的id
    @GetMapping("/viewStudent")
    public Object viewStudent(Integer startPage, Integer pageSize,String id){
        Page<Grade> page = new Page<>(startPage,pageSize);
        List<Grade> list = gradeService.viewStudent(page,id);
        return ResultUtil.result(EnumCode.OK.getValue(),"请求成功",list,page.getTotal());
    }

    //老师：给某个学生的某门课程打分
    @PostMapping("/update")
    public Object updateScore(Grade grade){
       return gradeService.updateScore(grade);
    }
}
