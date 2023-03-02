package com.example.community.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

//自动生成setter、getter、toString这些
@Data
@TableName("tb_house")
public class House {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer buildingId;
    private Integer ownerId;
    private String buildingName;
    private String ownerName;
    private String floor;
    private String houseNum;
    private String description;
    private String status;
    private String remark;
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

}
