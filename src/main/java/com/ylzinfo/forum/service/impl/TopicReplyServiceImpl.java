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

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicReplyServiceImpl extends ServiceImpl<TopicReplyMapper, TopicReply> implements TopicReplyService {
    @Autowired
    private TopicReplyService topicReplyService;
    @Autowired
    private TopicService topicService;

    @Override
    public List<TopicReply> getReplyPage(Long topicId, Long page) {
        List<TopicReply> list = new ArrayList<>();
        if (page <= 1) {
            TopicReply adoption = topicReplyService.getOne(Wrappers.<TopicReply>lambdaQuery()
                    .eq(TopicReply::getTopicId, topicId)
                    .eq(TopicReply::getAdoption, "ADOPTION"));
            if (adoption != null) {
                list.add(adoption);
            }
        }
        IPage<TopicReply> iPage = topicReplyService.page(new Page<>(page, 10), Wrappers.<TopicReply>lambdaQuery()
                .eq(TopicReply::getTopicId, topicId)
                .eq(TopicReply::getAdoption, "NOADOPTION"));
        list.addAll(iPage.getRecords());
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean adoption(TopicReply topicReply) {
        topicReplyService.update(Wrappers.<TopicReply>lambdaUpdate()
                .set(TopicReply::getAdoption, "ADOPTION")
                .eq(TopicReply::getId, topicReply.getId()));
        topicService.update(Wrappers.<Topic>lambdaUpdate()
                .set(Topic::getTopicType, "FINISH")
                .eq(Topic::getId, topicReply.getTopicId()));
        return true;
    }
}
