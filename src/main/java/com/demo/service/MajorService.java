package com.demo.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.demo.pojo.Major;

import java.util.List;

/**
 * @Auther: qiuxinfa
 * @Date: 2019/11/24
 * @Description: com.demo.service
 */
public interface MajorService extends IService<Major>{
    public List<Major> getListByPage(Page<Major> page, String name);

    public Object addMajor(Major major);

    public Object deleteMajor(String[] ids);

    public List<Major> findAllMajor(String instituteId);
}
