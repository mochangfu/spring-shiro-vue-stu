package com.demo.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.demo.mapper.HomeworkMapper;
import com.demo.pojo.Homework;
import com.demo.pojo.HomeworkAnswer;
import com.demo.pojo.Student;
import com.demo.utils.EnumCode;
import com.demo.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: qiuxinfa
 * @Date: 2019/11/24
 * @Description: com.demo.service.impl
 */
@Service
public class HomeworkImpl extends ServiceImpl<HomeworkMapper, Homework>  {

    @Autowired
    private  HomeworkAnswerImpl homeworkAnswer;
    @Autowired
    private StudentServiceImpl studentsservice;

    public List<Homework> getListFileByPagee(Page<Homework> page, Integer id,String name,String userId,String model, String majorId, String classId) {
        return super.baseMapper.getListByPage(page,id,name,model,  majorId,  classId,userId);
    }


    public Object delete(Integer[] ids) {
        //逐个删除
        for (Integer id : ids){
            baseMapper.deleteById(id);
        }
        return ResultUtil.result(EnumCode.OK.getValue(), "删除成功");
    }

    @Transactional
    public Object add(Homework homework) {

        super.baseMapper.insertRecord(homework);
        List<Student> students = studentsservice.getListByPage(new Page<>(1,100),null);
        students = students.stream().filter(s->homework.getClassId().equals(s.getClazzId())).collect(Collectors.toList());
        if(homework.getId()!=null){
            students.forEach(s->{
                HomeworkAnswer answer =new HomeworkAnswer(null,homework.getId(),null,null,s.getId(),null,null,s.getName());
                homeworkAnswer.add(answer);
            });

        }
        students.forEach(s->{

        });


        return ResultUtil.result(EnumCode.OK.getValue(),"新增成功");
    }
}
