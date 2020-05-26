package com.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.demo.pojo.FileRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: qiuxinfa
 * @Date: 2019/11/26
 * @Description: com.demo.mapper
 */
public interface FileRecordMapper extends BaseMapper<FileRecord>{
    List<FileRecord> getListByPage(Page<FileRecord> page, @Param("id") Integer id, @Param("uploadUserId") String uploadUserId,
                                   @Param("model") String model,@Param("instituteId") String instituteId, @Param("majorId") String majorId,@Param("classId") String classId);
   void insertFileRecord(FileRecord fileRecord);
}
