package com.ylzinfo.forum.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ylzinfo.forum.dto.TopicDTO;
import com.ylzinfo.forum.entity.Topic;
import com.ylzinfo.forum.entity.TopicDetail;
import com.ylzinfo.forum.mapper.TopicMapper;
import com.ylzinfo.forum.service.TopicDetailService;
import com.ylzinfo.forum.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements TopicService {
    @Autowired
    private TopicDetailService topicDetailService;
    @Autowired
    private TopicService topicService;

    @Transactional(rollbackFor = Exception.class)
    public int add(TopicDTO topicDTO) {
        topicService.save(topicDTO);
        TopicDetail topicDetail = new TopicDetail();
        topicDetail.setContent(topicDTO.getContent());
        topicDetail.setTopicId(topicDTO.getId());
        topicDetailService.save(topicDetail);
        return 1;
    }
}
