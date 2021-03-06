package com.demo.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.demo.pojo.Grade;

import java.util.List;

/**
 * @Auther: qiuxinfa
 * @Date: 2019/11/26
 * @Description: com.demo.service
 */
public interface GradeService extends IService<Grade>{
    List<Grade> viewStudent(Page<Grade> page, String id);
    Object updateScore(Grade grade);
}
