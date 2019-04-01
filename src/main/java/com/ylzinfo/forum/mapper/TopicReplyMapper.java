package com.ylzinfo.forum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ylzinfo.forum.entity.TopicReply;
import org.apache.ibatis.annotations.Param;

public interface TopicReplyMapper extends BaseMapper<TopicReply> {
    IPage<TopicReply> getUserReply(Page<TopicReply> page, @Param("userId") String userId);
}
