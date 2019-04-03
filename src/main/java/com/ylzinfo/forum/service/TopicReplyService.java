package com.ylzinfo.forum.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ylzinfo.forum.entity.TopicReply;

public interface TopicReplyService extends IService<TopicReply> {
    IPage<TopicReply> getReplyPage(Long topicId, Long page);

    boolean adoption(TopicReply topicReply);

    IPage<TopicReply> getUserReply(Long userId, Long page);
}
