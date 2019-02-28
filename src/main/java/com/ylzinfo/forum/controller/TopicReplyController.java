package com.ylzinfo.forum.controller;

import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ylzinfo.forum.dto.ResultDTO;
import com.ylzinfo.forum.entity.TopicReply;
import com.ylzinfo.forum.service.TopicReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("topicReply")
public class TopicReplyController {
    @Autowired
    private TopicReplyService topicReplyService;
    @Autowired
    private HttpServletRequest request;

    @PostMapping("insert")
    @ResponseBody
    public ResultDTO insert(TopicReply topicReply) {
        if (!topicReplyService.save(topicReply)) {
            return new ResultDTO().fail("回复失败");
        }
        Long id = topicReply.getTopicId();
        int count = topicReplyService.count(new QueryWrapper<TopicReply>()
                .eq("topic_id", id));
        int page = NumberUtil.partValue(count, 10);
        return new ResultDTO().actionSuccess(request.getContextPath() + "/topic/detail?id=" + id + "&page=" + page);
    }

    @GetMapping("replyPage")
    @ResponseBody
    public ResultDTO<List<TopicReply>> replyPage(Long topicId, Long page) {
        IPage<TopicReply> iPage = topicReplyService.page(new Page<>(page, 10), new QueryWrapper<TopicReply>()
                .eq("topic_id", topicId));
        return new ResultDTO<List<TopicReply>>().pageSuccess(iPage.getRecords(), iPage.getTotal());
    }

    @GetMapping("count")
    @ResponseBody
    public ResultDTO<Integer> count(Long topicId) {
        return new ResultDTO<Integer>().dataSuccess(topicReplyService.count(new QueryWrapper<TopicReply>()
                .eq("topic_id", topicId)));
    }
}
