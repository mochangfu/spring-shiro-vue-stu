package com.qxf.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.qxf.pojo.FileRecord;
import com.qxf.pojo.User;
import com.qxf.service.FileRecordService;
import com.qxf.service.UserService;
import com.qxf.utils.EnumCode;
import com.qxf.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Auther: qiuxinfa
 * @Date: 2019/11/24
 * @Description: com.qxf.controller
 */
@RestController
@RequestMapping("fileRecord")
public class FileRecordController {
   
    @Autowired
    private FileRecordService fileRecordService;
    @Autowired
    private UserService userService;

    @GetMapping("/exam/list")
    public Object getListByPage(Integer startPage,Integer pageSize,String name,Integer id,String uploadUserId,String model,String instituteId,String majorId){
        Page<FileRecord> page = new Page<>(startPage,pageSize);
        List<FileRecord> list = fileRecordService.getListFileByPagee(page,id,uploadUserId,model, instituteId, majorId,null);
        List<User> users = userService.findAllUser(new User());
        Map<String,String> idUserNameMap =new HashMap<>();
        users.forEach(u->{
            idUserNameMap.put(u.getId(),u.getUsername());
        });
        list = list.stream().filter(l->l.getName().contains(name)).collect(Collectors.toList());
        for (FileRecord fileRecord : list) {
            fileRecord.setUserName(idUserNameMap.get(fileRecord.getUploadUserId()));
        }
        return ResultUtil.result(EnumCode.OK.getValue(),"请求成功",list,page.getTotal());
    }

    @PostMapping("/delete")
    public Object deleteFileRecord(FileRecord fileRecord){
        Integer[] ids = fileRecord.getIds();
        if (null == ids || ids.length == 0) {
            return ResultUtil.result(EnumCode.BAD_REQUEST.getValue(), EnumCode.BAD_REQUEST.getText());
        }
        return fileRecordService.deleteFile(ids);
    }

}
