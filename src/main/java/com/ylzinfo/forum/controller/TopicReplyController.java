package com.ylzinfo.forum.controller;

import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ylzinfo.forum.dto.ResultDTO;
import com.ylzinfo.forum.entity.TopicReply;
import com.ylzinfo.forum.service.TopicReplyService;
import com.ylzinfo.forum.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("topicReply")
public class TopicReplyController {
    @Autowired
    private TopicReplyService topicReplyService;
    @Autowired
    private HttpServletRequest request;

    @PostMapping("insert")
    @ResponseBody
    public ResultDTO<Integer> insert(TopicReply topicReply) {
        topicReply.setUserId(UserUtil.getUserId());
        if (!topicReplyService.save(topicReply)) {
            return new ResultDTO().fail("回复失败");
        }
        Long id = topicReply.getTopicId();
        int count = topicReplyService.count(Wrappers.<TopicReply>lambdaQuery()
                .eq(TopicReply::getTopicId, id));
        int page = NumberUtil.partValue(count, 10);
        return new ResultDTO<Integer>().dataSuccess(page, "回复成功");
    }

    @GetMapping("replyPage")
    @ResponseBody
    public ResultDTO<IPage<TopicReply>> replyPage(Long topicId, Long page) {
        return new ResultDTO<IPage<TopicReply>>().dataSuccess(topicReplyService.getReplyPage(topicId, page));
    }

    @PostMapping("adoption")
    @ResponseBody
    public ResultDTO adoption(TopicReply topicReply) {
        if (topicReplyService.adoption(topicReply)) {
            return new ResultDTO().success("采纳成功");
        } else {
            return new ResultDTO().fail("采纳失败");
        }
    }

    @PostMapping("delete")
    @ResponseBody
    public ResultDTO delete(Long id) {
        if (topicReplyService.removeById(id)) {
            return new ResultDTO().success("删除成功");
        }
        return new ResultDTO().fail("删除失败");
    }

    @GetMapping("user/{userId}")
    @ResponseBody
    public ResultDTO<IPage<TopicReply>> userReply(@PathVariable String userId, Long page) {
        return new ResultDTO<IPage<TopicReply>>().dataSuccess(topicReplyService.getUserReply(userId, page));
    }
}
