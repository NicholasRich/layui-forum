package com.ylzinfo.forum.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ylzinfo.forum.dto.TopicDTO;
import com.ylzinfo.forum.entity.Topic;
import com.ylzinfo.forum.entity.TopicDetail;
import com.ylzinfo.forum.entity.UserTopicAction;
import com.ylzinfo.forum.mapper.TopicMapper;
import com.ylzinfo.forum.service.TopicDetailService;
import com.ylzinfo.forum.service.TopicService;
import com.ylzinfo.forum.service.UserTopicActionService;
import com.ylzinfo.forum.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements TopicService {
    @Autowired
    private TopicDetailService topicDetailService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private UserTopicActionService userTopicActionService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insert(TopicDTO topicDTO) {
        topicService.save(topicDTO);
        UserTopicAction action = UserTopicAction.getInstance(topicDTO.getId(), UserUtil.getUserId(), "PUBLISH");
        userTopicActionService.save(action);
        topicDetailService.save(TopicDetail.getInstance(topicDTO.getId(), topicDTO.getContent()));
        return topicDTO.getId();
    }

    @Override
    public TopicDTO getDetail(Long id) {
        Topic topic = topicService.getById(id);
        TopicDetail detail = topicDetailService.getOne(Wrappers.<TopicDetail>lambdaQuery()
                .eq(TopicDetail::getTopicId, id));
        TopicDTO topicDTO = new TopicDTO();
        BeanUtil.copyProperties(topic, topicDTO);
        topicDTO.setDetailId(detail.getId());
        topicDTO.setContent(detail.getContent());
        return topicDTO;
    }
}
