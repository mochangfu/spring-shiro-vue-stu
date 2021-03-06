package com.demo.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.demo.pojo.SysDict;

import java.util.List;

/**
 * @Auther: qiuxinfa
 * @Date: 2019/11/23
 * @Description: com.demo.service
 */
public interface DictService extends IService<SysDict>{
    List<SysDict> findDictByPage(Page<SysDict> page, SysDict dict);

    List<SysDict> findListByDictTypeCode(String dictTypeCode);

    Object addDict(SysDict dict);
}
