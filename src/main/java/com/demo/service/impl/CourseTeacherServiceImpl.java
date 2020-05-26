package com.demo.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.demo.mapper.CourseTeacherMapper;
import com.demo.pojo.CourseTeacher;
import com.demo.service.CourseTeacherService;
import org.springframework.stereotype.Service;

/**
 * @Auther: qiuxinfa
 * @Date: 2019/11/25
 * @Description: com.demo.service.impl
 */
@Service
public class CourseTeacherServiceImpl extends ServiceImpl<CourseTeacherMapper,CourseTeacher> implements CourseTeacherService {
}
