package com.ylzinfo.forum.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ylzinfo.forum.entity.Topic;
import com.ylzinfo.forum.entity.TopicReply;
import com.ylzinfo.forum.mapper.TopicReplyMapper;
import com.ylzinfo.forum.service.TopicReplyService;
import com.ylzinfo.forum.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class TopicReplyServiceImpl extends ServiceImpl<TopicReplyMapper, TopicReply> implements TopicReplyService {
    @Autowired
    private TopicReplyService topicReplyService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private TopicReplyMapper topicReplyMapper;

    @Override
    public IPage<TopicReply> getReplyPage(Long topicId, Long page) {
        return topicReplyMapper.getReplyPage(new Page<>(page, 10), topicId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean adoption(TopicReply topicReply) {
        return topicReplyService.update(Wrappers.<TopicReply>lambdaUpdate()
                .set(TopicReply::getAdoption, "ADOPTION")
                .eq(TopicReply::getId, topicReply.getId()))
                && topicService.update(Wrappers.<Topic>lambdaUpdate()
                .set(Topic::getTopicType, "FINISH")
                .set(Topic::getUpdateTime, new Date())
                .eq(Topic::getId, topicReply.getTopicId()));
    }

    @Override
    public IPage<TopicReply> getUserReply(Long userId, Long page) {
        return topicReplyMapper.getUserReply(new Page<>(page, 10), userId);
    }

    @Override
    public IPage<TopicReply> getMessage(Long userId, Long page) {
        return topicReplyMapper.getMessage(new Page<>(page, 10), userId);
    }
}
