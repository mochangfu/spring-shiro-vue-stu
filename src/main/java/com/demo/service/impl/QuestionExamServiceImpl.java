package com.demo.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.demo.pojo.QuestionExam;
import com.demo.mapper.QuestionExamDao;
import com.demo.service.QuestionExamService;
import com.demo.utils.EnumCode;
import com.demo.utils.ResultUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * 考试安排(QuestionExam)表服务实现类
 *
 * @author makejava
 * @since 2020-02-15 16:56:56
 */
@Service("questionExamService")
public class QuestionExamServiceImpl extends ServiceImpl<QuestionExamDao,QuestionExam> implements QuestionExamService {

    @Resource
    private QuestionExamDao questionExamDao;

    @Override
    public List<QuestionExam> getListByPage(Page<QuestionExam> page, String name) {
        return super.baseMapper.getListByPage(page,name);
    }

    @Override
    public QuestionExam getExamById(String id) {
        return super.baseMapper.getExamById(id);
    }

    @Override
    public Object add(QuestionExam exam) {
        exam.setId(UUID.randomUUID().toString().replace("-",""));
        exam.setTotalScore(0);  //初始分数为0分，后面根据试卷题目计算总分，再更新
        exam.setPaperId(UUID.randomUUID().toString().replace("-",""));
        int cnt = super.baseMapper.add(exam);
        if(cnt > 0){
            return ResultUtil.result(EnumCode.OK.getValue(),"新增成功");
        }else {
            return ResultUtil.result(EnumCode.BAD_REQUEST.getValue(),"新增失败");
        }

    }

    @Override
    public Object delete(String[] ids) {
        //逐个删除
        for (String id : ids){
            questionExamDao.deleteExamById(id);
        }

        return ResultUtil.result(EnumCode.OK.getValue(), "删除成功");
    }

}