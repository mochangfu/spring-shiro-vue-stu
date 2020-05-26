package com.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.demo.pojo.CourseTeacher;
import com.demo.pojo.Grade;

import java.util.List;

/**
 * @Auther: qiuxinfa
 * @Date: 2019/11/26
 * @Description: com.demo.mapper
 */
public interface GradeMapper extends BaseMapper<Grade>{
    List<Grade> viewStudent(CourseTeacher courseTeacher);
}
