package com.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.demo.pojo.HomeworkAnswer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: qiuxinfa
 * @Date: 2019/11/26
 * @Description: com.demo.mapper
 */
public interface HomeworkAnswerMapper extends BaseMapper<HomeworkAnswer>{
    List<HomeworkAnswer> getListByPage(Page<HomeworkAnswer> page, @Param("id") Integer id, @Param("name") String name, @Param("homeworkId") Integer homeworkId,
                                 @Param("userId") String userId);
   public void insertRecord(HomeworkAnswer fileRecord);
}
