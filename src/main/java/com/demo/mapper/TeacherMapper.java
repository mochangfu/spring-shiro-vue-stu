package com.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.demo.pojo.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: qiuxinfa
 * @Date: 2019/11/24
 * @Description: com.demo.mapper
 */
public interface TeacherMapper extends BaseMapper<Teacher>{
    List<Teacher> getListByPage(Page<Teacher> page, @Param("name") String name);

    List<Teacher> findAllTeacher();
}
