/**
 * Copyright (C), 2015-2020, lianfankeji
 * FileName: FileRecord
 * Author: 25414
 * Date: 2020/5/22 2:08
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Homework implements Serializable {

    private Integer id;
    private String name;//作业名
    private String descrip;
    private Integer fileId;//文件保存名
    private String majorId;
    private String classId;
    private Date postTime;//新增时间
    private String userId;          //
    private Integer[] ids;          //批量删除的id
    private String userName;

}
