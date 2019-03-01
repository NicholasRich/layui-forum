package com.ylzinfo.forum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ylzinfo.forum.entity.TopicReply;

import java.util.List;

public interface TopicReplyService extends IService<TopicReply> {
    List<TopicReply> getReplyPage(Long topicId, Long page);

    boolean adoption(TopicReply topicReply);
}
