package com.demo.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.demo.mapper.HomeworkAnswerMapper;
import com.demo.pojo.HomeworkAnswer;
import com.demo.utils.EnumCode;
import com.demo.utils.ResultUtil;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Auther: qiuxinfa
 * @Date: 2019/11/24
 * @Description: com.demo.service.impl
 */
@Service
public class HomeworkAnswerImpl extends ServiceImpl<HomeworkAnswerMapper, HomeworkAnswer>  {



    public List<HomeworkAnswer> getListFileByPagee(Page<HomeworkAnswer> page, Integer id,String name,String userId,Integer homeworkId) {
        return super.baseMapper.getListByPage(page,id,name,homeworkId,  userId);
    }
    public List<ScoreStats> scoreStats(Integer homeworkId) {
        Page<HomeworkAnswer> page = new Page<>(1,1000);
        List<HomeworkAnswer>  list =super.baseMapper.getListByPage(page,null,null,homeworkId,  null);
        Map<String,ScoreStats> map = new HashMap();
        list.forEach(l->{
            if(l.getScore()==null){
                if(map.get("未交")==null)map.put("未交",new ScoreStats("未交",0,0.0,6));
                map.get("未交").addCount(1);
            }else if(l.getScore()<60){
                if(map.get("低于60")==null)map.put("低于60",new ScoreStats("低于60",0,0.0,5));
                map.get("低于60").addCount(1);
            }else if(l.getScore()>=60&&l.getScore()<70){
                if(map.get("60-69")==null)map.put("60-69",new ScoreStats("60-69",0,0.0,4));
                map.get("60-69").addCount(1);
            }else if(l.getScore()>=60&&l.getScore()<80){
                if(map.get("70-79")==null)map.put("70-79",new ScoreStats("70-79",0,0.0,3));
                map.get("70-79").addCount(1);
            }else if(l.getScore()>=80&&l.getScore()<90){
                if(map.get("80-89")==null)map.put("80-89",new ScoreStats("80-89",0,0.0,2));
                map.get("80-89").addCount(1);
            } else if(l.getScore()>=90&&l.getScore()<100){
                if(map.get("90-100")==null)map.put("90-100",new ScoreStats("90-100",0,0.0,1));
                map.get("90-100").addCount(1);
            }
        });
        map.forEach((k,v)->{
            v.setPercent(v.getCount()/list.size()*1.0);
        });
        List<ScoreStats> scoreStats =new ArrayList<>(map.values());
        scoreStats.sort(Comparator.comparing(ScoreStats::getScoreInt));
        return scoreStats;
    }

    public Object delete(Integer[] ids) {
        //逐个删除
        for (Integer id : ids){
            baseMapper.deleteById(id);
        }
        return ResultUtil.result(EnumCode.OK.getValue(), "删除成功");
    }

    public Object add(HomeworkAnswer Homework) {
        super.baseMapper.insertRecord(Homework);
        return ResultUtil.result(EnumCode.OK.getValue(),"新增成功");
    }

    public Object edit(HomeworkAnswer Homework) {
        super.baseMapper.update(Homework);
        return ResultUtil.result(EnumCode.OK.getValue(),"新增成功");
    }


}
