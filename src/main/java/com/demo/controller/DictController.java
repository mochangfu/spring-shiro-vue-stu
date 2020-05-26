package com.demo.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.demo.pojo.SysDict;
import com.demo.service.DictService;
import com.demo.utils.EnumCode;
import com.demo.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: qiuxinfa
 * @Date: 2019/11/23
 * @Description: 字典
 */
@RestController
@RequestMapping("dict")
public class DictController {

    @Autowired
    private DictService dictService;

    //查询字典列表
    @GetMapping(value = "/list")
    public Object findDictByPage(Integer startPage,Integer pageSize,SysDict dict) {
        Page<SysDict> page = new Page<SysDict>(startPage,pageSize);
        List<SysDict> list = dictService.findDictByPage(page,dict);
        return ResultUtil.result(EnumCode.OK.getValue(), "请求成功", list, page.getTotal());
    }

    //根据字典类别查询字典下拉列表
    @GetMapping("/findListByDictTypeCode")
    public Object findListByDictTypeCode(String dictTypeCode){
        List<SysDict> list = dictService.findListByDictTypeCode(dictTypeCode);
        return ResultUtil.result(EnumCode.OK.getValue(),"请求成功",list);
    }

    //根据字典类别查询字典下拉列表
    @PostMapping("/add")
    public Object addDict(SysDict dict){
        return dictService.addDict(dict);
    }
}
