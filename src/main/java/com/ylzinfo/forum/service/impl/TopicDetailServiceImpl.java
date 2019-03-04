package com.ylzinfo.forum.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ylzinfo.forum.dto.TopicDTO;
import com.ylzinfo.forum.entity.Topic;
import com.ylzinfo.forum.entity.TopicDetail;
import com.ylzinfo.forum.mapper.TopicDetailMapper;
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

    @Override
    public TopicDTO getContent(Long id) {
        Topic topic = topicService.getById(id);
        if (topic == null) {
            return null;
        }
        TopicDetail topicDetail = topicDetailService.getOne(Wrappers.<TopicDetail>lambdaQuery()
                .eq(TopicDetail::getTopicId, id));
        if (topicDetail == null) {
            return null;
        }
        TopicDTO topicDTO = new TopicDTO();
        BeanUtil.copyProperties(topic, topicDTO);
        BeanUtil.copyProperties(topicDetail, topicDTO);
        return topicDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateTopic(TopicDTO topicDTO) {
        topicDTO.setUpdateTime(new Date());
        return topicService.updateById(topicDTO)
                && topicDetailService.update(Wrappers.<TopicDetail>lambdaUpdate()
                .set(TopicDetail::getContent, topicDTO.getContent())
                .eq(TopicDetail::getTopicId, topicDTO.getId()));
    }
}
