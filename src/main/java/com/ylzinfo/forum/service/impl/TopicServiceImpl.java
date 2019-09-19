package com.ylzinfo.forum.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ylzinfo.forum.entity.Topic;
import com.ylzinfo.forum.entity.TopicDetail;
import com.ylzinfo.forum.entity.TopicGiveLike;
import com.ylzinfo.forum.entity.TopicReply;
import com.ylzinfo.forum.entity.TopicTop;
import com.ylzinfo.forum.entity.TopicWarmChannel;
import com.ylzinfo.forum.entity.UserTopicAction;
import com.ylzinfo.forum.mapper.TopicMapper;
import com.ylzinfo.forum.service.TopicDetailService;
import com.ylzinfo.forum.service.TopicGiveLikeService;
import com.ylzinfo.forum.service.TopicReplyService;
import com.ylzinfo.forum.service.TopicService;
import com.ylzinfo.forum.service.TopicTopService;
import com.ylzinfo.forum.service.TopicWarmChannelService;
import com.ylzinfo.forum.service.UserTopicActionService;
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
    @Autowired
    private TopicTopService topicTopService;
    @Autowired
    private TopicReplyService topicReplyService;
    @Autowired
    private TopicWarmChannelService topicWarmChannelService;
    @Autowired
    private TopicGiveLikeService topicGiveLikeService;
    @Autowired
    private TopicMapper topicMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insert(Topic topic) {
        topicService.save(topic);
        UserTopicAction action = UserTopicAction.getInstance(topic.getId(), topic.getUserId(), "PUBLISH");
        userTopicActionService.save(action);
        topicDetailService.save(TopicDetail.getInstance(topic.getId(), topic.getContent()));
        return topic.getId();
    }

    @Override
    public Topic getDetail(Long id, Long userId) {
        return topicMapper.getDetail(id, userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long topicId) {
        return topicService.removeById(topicId)
                && topicDetailService.remove(Wrappers.<TopicDetail>lambdaQuery()
                .eq(TopicDetail::getTopicId, topicId))
                && topicTopService.remove(Wrappers.<TopicTop>lambdaQuery()
                .eq(TopicTop::getTopicId, topicId))
                && userTopicActionService.remove(Wrappers.<UserTopicAction>lambdaQuery()
                .eq(UserTopicAction::getTopicId, topicId))
                && topicReplyService.remove(Wrappers.<TopicReply>lambdaQuery()
                .eq(TopicReply::getTopicId, topicId))
                && topicWarmChannelService.remove(Wrappers.<TopicWarmChannel>lambdaQuery()
                .eq(TopicWarmChannel::getTopicId, topicId))
                && topicGiveLikeService.remove(Wrappers.<TopicGiveLike>lambdaQuery()
                .eq(TopicGiveLike::getTopicId, topicId));
    }

    @Override
    public IPage<Topic> getPublish(Long userId, Long page) {
        return topicMapper.getPublish(new Page(page, 10), userId);
    }

    @Override
    public IPage<Topic> getCollection(Long userId, Long page) {
        return topicMapper.getCollection(new Page(page, 10), userId);
    }

}
