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

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_file_record")
@AllArgsConstructor
@NoArgsConstructor
public class FileRecord  implements Serializable {

    private Integer id;

    private String name;//文件原名
    @TableField("file_name")
    private String fileName;//文件保存名
    @TableField("upload_time")
    private Date uploadTime;
    @TableField("upload_user_id")
    private String uploadUserId;
    @TableField("descrip")
    private String descrip;
    @TableField("model")
    private String model;
    @TableField("institute_id")
    private String instituteId;
    @TableField("major_id")
    private String majorId;
    @TableField("class_id")
    private String classId;

    @TableField(exist = false)
    private Integer[] ids;          //批量删除的id
    @TableField(exist = false)
    private String userName;          //批量删除的id
}
