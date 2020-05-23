package com.qxf.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qxf.mapper.FileRecordMapper;
import com.qxf.pojo.FileRecord;
import com.qxf.service.FileRecordService;
import com.qxf.utils.EnumCode;
import com.qxf.utils.ResultUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: qiuxinfa
 * @Date: 2019/11/24
 * @Description: com.qxf.service.impl
 */
@Service
public class FileRecordServiceImpl extends ServiceImpl<FileRecordMapper, FileRecord> implements FileRecordService {


  @Override
    public List<FileRecord> getListFileByPagee(Page<FileRecord> page, Integer id,String uploadUserId,String model,String instituteId, String majorId, String classId) {
        return super.baseMapper.getListByPage(page,id,uploadUserId,model,instituteId,  majorId,  classId);
    }

    @Override
    public Object deleteFile(Integer[] ids) {
        //逐个删除
        for (Integer id : ids){
            baseMapper.deleteById(id);
        }
        return ResultUtil.result(EnumCode.OK.getValue(), "删除成功");
    }

    @Override
    public Object addFileRecord(FileRecord fileRecord) {
        super.baseMapper.insertFileRecord(fileRecord);
        return ResultUtil.result(EnumCode.OK.getValue(),"新增成功");
    }
}
