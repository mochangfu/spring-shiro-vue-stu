package com.demo.controller;

import com.demo.pojo.QuestionExamRecord;
import com.demo.service.QuestionExamRecordService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 考试记录表(QuestionExamRecord)表控制层
 *
 * @author makejava
 * @since 2020-02-15 16:56:57
 */
@RestController
@RequestMapping("exam/record")
public class QuestionExamRecordController {
    /**
     * 服务对象
     */
    @Resource
    private QuestionExamRecordService questionExamRecordService;

    //插入考试记录
    @PostMapping("/add")
    public Object add(QuestionExamRecord questionExamRecord) {
        return this.questionExamRecordService.add(questionExamRecord);
    }

}