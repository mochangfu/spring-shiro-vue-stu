package com.demo.mapper;

import com.demo.pojo.QuestionExamRecord;

/**
 * 考试记录表(QuestionExamRecord)表数据库访问层
 *
 * @author makejava
 * @since 2020-02-15 16:56:57
 */
public interface QuestionExamRecordDao {
    int add(QuestionExamRecord questionExamRecord);
}