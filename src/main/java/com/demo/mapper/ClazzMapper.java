package com.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.demo.pojo.Clazz;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: qiuxinfa
 * @Date: 2019/11/24
 * @Description: com.demo.mapper
 */
public interface ClazzMapper extends BaseMapper<Clazz>{
    List<Clazz> getListByPage(Page<Clazz> page, @Param("name") String name);

    List<Clazz> getAllClazz(@Param("majorId") String majorId);
}
