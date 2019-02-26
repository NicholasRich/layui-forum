package com.ylzinfo.forum.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

//+ id
//+ user_id 发帖人id
//+ user_name 发帖人姓名
//+ title 标题
//+ special_column 所属版块
//+ belong_type 所属类别（QUESTION:提问；SHARE：分享；DISCUSS：讨论；ADVICE：建议；NOTICE：公告；DYNAMIC：动态）
//+ file_id 文件ID
//+ topic_type 帖子类型（0：未结；1：已结；）
//+ marrow (1：精华；0：非精华）
//+ create_time 创建时间
//+ update_time 更新时间
//+ end_time 结帖时间
//+ version 版本号

@Data
@TableName("cm_topic")
public class Topic {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private String userName;
    private String title;
    private String specialColumn;
    private String belongType;
    private String belongProduct;
    private Long fileId;
    private Integer topicType;
    private Integer marrow;
    private Date createTime;
    private Date updateTime;
    private Date endTime;
    private Integer version;
}
