package com.demo.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.demo.mapper.FileRecordMapper;
import com.demo.pojo.FileRecord;
import com.demo.service.FileRecordService;
import com.demo.utils.EnumCode;
import com.demo.utils.ResultUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: qiuxinfa
 * @Date: 2019/11/24
 * @Description: com.demo.service.impl
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
