package com.example.community.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

//自动生成setter、getter、toString这些
@Data
@TableName("tb_lease")
public class Lease {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer buildingId;
    private Integer houseId;
    private Integer ownerId;
    private Integer salesmanId;
    private String buildingName;
    private String houseNum;
    private String ownerName;
    private String salesmanName;
    private String leaseTerm;
    private String monthlyRent;
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startingTime;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date signingDate;
    private String remark;
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

}
