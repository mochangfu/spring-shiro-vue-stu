package com.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.demo.pojo.Homework;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: qiuxinfa
 * @Date: 2019/11/26
 * @Description: com.demo.mapper
 */
public interface HomeworkMapper extends BaseMapper<Homework>{
    List<Homework> getListByPage(Page<Homework> page, @Param("id") Integer id, @Param("name") String name, @Param("model") String model,
                                 @Param("majorId") String majorId, @Param("classId") String classId,@Param("userId") String userId);
   public void insertRecord(Homework fileRecord);
}
