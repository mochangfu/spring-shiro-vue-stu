package com.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.demo.pojo.QuestionExam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 考试安排(QuestionExam)表数据库访问层
 *
 * @author makejava
 * @since 2020-02-15 16:56:56
 */
public interface QuestionExamDao extends BaseMapper<QuestionExam>{
    List<QuestionExam> getListByPage(Page<QuestionExam> page, @Param("name") String name);

    QuestionExam getExamById(String id);

    int add(QuestionExam questionExam);

    int updateScoreByPaperId(QuestionExam questionExam);

    int deleteExamById(String id);
}