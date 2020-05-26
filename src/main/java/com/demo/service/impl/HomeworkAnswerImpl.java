package com.demo.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.demo.mapper.HomeworkAnswerMapper;
import com.demo.pojo.HomeworkAnswer;
import com.demo.utils.EnumCode;
import com.demo.utils.ResultUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: qiuxinfa
 * @Date: 2019/11/24
 * @Description: com.demo.service.impl
 */
@Service
public class HomeworkAnswerImpl extends ServiceImpl<HomeworkAnswerMapper, HomeworkAnswer>  {



    public List<HomeworkAnswer> getListFileByPagee(Page<HomeworkAnswer> page, Integer id,String name,String userId,Integer homeworkId) {
        return super.baseMapper.getListByPage(page,id,name,homeworkId,  userId);
    }


    public Object delete(Integer[] ids) {
        //逐个删除
        for (Integer id : ids){
            baseMapper.deleteById(id);
        }
        return ResultUtil.result(EnumCode.OK.getValue(), "删除成功");
    }

    public Object add(HomeworkAnswer Homework) {
        super.baseMapper.insertRecord(Homework);
        return ResultUtil.result(EnumCode.OK.getValue(),"新增成功");
    }

    public Object edit(HomeworkAnswer Homework) {
        super.baseMapper.updateById(Homework);
        return ResultUtil.result(EnumCode.OK.getValue(),"新增成功");
    }
}
