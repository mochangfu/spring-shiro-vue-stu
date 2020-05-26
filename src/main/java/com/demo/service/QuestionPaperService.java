package com.demo.service;

import com.demo.pojo.ExamVo;

import java.util.List;
import java.util.Map;

/**
 * 试卷(QuestionPaper)表服务接口
 *
 * @author makejava
 * @since 2020-02-15 16:56:56
 */
public interface QuestionPaperService{

    Map<String,List<?>> getPaperById(String paperId);

    Object add(ExamVo exam);
}