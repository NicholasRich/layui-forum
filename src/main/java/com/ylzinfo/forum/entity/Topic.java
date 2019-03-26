package com.ylzinfo.forum.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ylzinfo.forum.enums.BelongTypeEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("cm_topic")
public class Topic implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String userId;
    private String title;
    private String specialColumn;
    private BelongTypeEnum belongType;
    private String belongProduct;
    private Long fileId;
    private String topicType;
    private String marrow;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date updateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date endTime;
    private String version;

    @TableField(exist = false)
    private Integer replyNum;
    @TableField(exist = false)
    private Long detailId;
    @TableField(exist = false)
    private String content;
    @TableField(exist = false)
    private Long topId;
    @TableField(exist = false)
    private Long collectionId;
}
