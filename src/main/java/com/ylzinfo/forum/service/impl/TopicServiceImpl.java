package com.ylzinfo.forum.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long add(TopicDTO topicDTO) {
        topicService.save(topicDTO);
        TopicDetail topicDetail = new TopicDetail();
        topicDetail.setContent(topicDTO.getContent());
        topicDetail.setTopicId(topicDTO.getId());
        topicDetailService.save(topicDetail);
        return topicDTO.getId();
    }

    @Override
    public TopicDTO getDetail(Long id) {
        Topic topic = topicService.getById(id);
        TopicDetail detail = topicDetailService.getOne(new QueryWrapper<TopicDetail>()
                .eq("topic_id", id));
        TopicDTO topicDTO = new TopicDTO();
        BeanUtil.copyProperties(topic, topicDTO);
        topicDTO.setDetailId(detail.getId());
        topicDTO.setContent(detail.getContent());
        return topicDTO;
    }
}
