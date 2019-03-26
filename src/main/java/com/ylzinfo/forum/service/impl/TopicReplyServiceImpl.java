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
import java.util.List;

@Service
public class TopicReplyServiceImpl extends ServiceImpl<TopicReplyMapper, TopicReply> implements TopicReplyService {
    @Autowired
    private TopicReplyService topicReplyService;
    @Autowired
    private TopicService topicService;

    @Override
    public IPage<TopicReply> getReplyPage(Long topicId, Long page) {
        IPage<TopicReply> iPage = topicReplyService.page(new Page<>(page, 10), Wrappers.<TopicReply>lambdaQuery()
                .eq(TopicReply::getTopicId, topicId)
                .eq(TopicReply::getAdoption, "NOADOPTION"));
        if (page <= 1) {
            TopicReply adoption = topicReplyService.getOne(Wrappers.<TopicReply>lambdaQuery()
                    .eq(TopicReply::getTopicId, topicId)
                    .eq(TopicReply::getAdoption, "ADOPTION"));
            if (adoption != null) {
                List<TopicReply> records = iPage.getRecords();
                if (records.size() > 0) {
                    records.remove(records.size() - 1);
                }
                records.add(0, adoption);
                iPage.setRecords(records);
            }
        }
        return iPage;
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
}
