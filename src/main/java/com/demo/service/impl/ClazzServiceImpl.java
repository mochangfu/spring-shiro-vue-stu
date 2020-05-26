package com.demo.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.demo.exception.MyException;
import com.demo.mapper.ClazzMapper;
import com.demo.pojo.Clazz;
import com.demo.pojo.Student;
import com.demo.service.ClazzService;
import com.demo.service.StudentService;
import com.demo.utils.EnumCode;
import com.demo.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: qiuxinfa
 * @Date: 2019/11/24
 * @Description: com.demo.service.impl
 */
@Service
public class ClazzServiceImpl extends ServiceImpl<ClazzMapper,Clazz> implements ClazzService {
    @Autowired
    private StudentService studentService;

    @Override
    public List<Clazz> getListByPage(Page<Clazz> page, String name) {
        return super.baseMapper.getListByPage(page,name);
    }

    @Override
    public Object addClazz(Clazz clazz) {
        Map<String,Object> map = new HashMap<>();
        map.put("name",clazz.getName().trim());
        map.put("major_id",clazz.getMajorId().trim());
        map.put("institute_id",clazz.getInstituteId());
        List<Clazz> list = super.selectByMap(map);
        if(list != null && list.size()>0){
            throw new MyException(ResultUtil.result(EnumCode.BAD_REQUEST.getValue(), "该班级已存在", null));
        }
        super.baseMapper.insert(clazz);
        return ResultUtil.result(EnumCode.OK.getValue(),"新增成功");
    }

    @Override
    public List<Clazz> getAllClazz(String majorId) {
        return super.baseMapper.getAllClazz(majorId);
    }

    @Override
    public Object deleteClazz(String[] ids) {
        Map<String,Object> map;
        for (String id : ids) {
            //根据班级查询学生，如果有学生则不能删除
            map = new HashMap<>();
            map.put("clazz_id",id);
            List<Student> list = studentService.selectByMap(map);
            if(list != null && list.size()>0){
                return ResultUtil.result(EnumCode.BAD_REQUEST.getValue(), "选择的班级中有学生存在，删除失败",null);
            }
        }
        //逐个删除
        for (String id : ids){
            baseMapper.deleteById(id);
        }

        return ResultUtil.result(EnumCode.OK.getValue(), "删除成功");
    }
}
