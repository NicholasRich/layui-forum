package com.ylzinfo.forum.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ylzinfo.forum.entity.Topic;
import com.ylzinfo.forum.entity.TopicDetail;
import com.ylzinfo.forum.mapper.TopicDetailMapper;
import com.ylzinfo.forum.mapper.TopicMapper;
import com.ylzinfo.forum.service.TopicDetailService;
import com.ylzinfo.forum.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class TopicDetailServiceImpl extends ServiceImpl<TopicDetailMapper, TopicDetail> implements TopicDetailService {
    @Autowired
    private TopicService topicService;
    @Autowired
    private TopicDetailService topicDetailService;
    @Autowired
    private TopicMapper topicMapper;

    @Override
    public Topic getContent(Long id) {
        return topicMapper.getContent(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateTopic(Topic topic) {
        topic.setUpdateTime(new Date());
        return topicService.updateById(topic)
                && topicDetailService.update(Wrappers.<TopicDetail>lambdaUpdate()
                .set(TopicDetail::getContent, topic.getContent())
                .eq(TopicDetail::getTopicId, topic.getId()));
    }
}
