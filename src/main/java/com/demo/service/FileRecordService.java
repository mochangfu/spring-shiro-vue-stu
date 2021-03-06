package com.demo.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.demo.pojo.FileRecord;

import java.util.List;

/**
 * @Auther: qiuxinfa
 * @Date: 2019/11/24
 * @Description: com.demo.service
 */
public interface FileRecordService extends IService<FileRecord>{
    List<FileRecord> getListFileByPagee(Page<FileRecord> page, Integer id,String uploadUserId,String model,String instituteId, String majorId, String classId);

    Object deleteFile(Integer[] ids);

     Object addFileRecord(FileRecord fileRecord);
}
