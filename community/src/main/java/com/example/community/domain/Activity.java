package com.example.community.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

//自动生成setter、getter、toString这些
@Data
@TableName("tb_activity")
public class Activity {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private String address;
    private String organizer;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startingTime;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endingTime;
    private String status;
    private String description;
    private String remark;
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

}
