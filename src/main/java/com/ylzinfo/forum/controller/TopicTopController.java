package com.ylzinfo.forum.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ylzinfo.forum.dto.ResultDTO;
import com.ylzinfo.forum.entity.TopicTop;
import com.ylzinfo.forum.service.TopicTopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("topicTop")
public class TopicTopController {
    @Autowired
    private TopicTopService topicTopService;

    @PostMapping("insert")
    @ResponseBody
    public ResultDTO insert(TopicTop topicTop) {
        if (topicTopService.save(topicTop)) {
            return new ResultDTO().success("置顶成功");
        }
        return new ResultDTO().fail("置顶失败");
    }

    @PostMapping("delete")
    @ResponseBody
    public ResultDTO delete(Long topicId) {
        if (topicTopService.remove(Wrappers.<TopicTop>lambdaQuery()
                .eq(TopicTop::getTopicId, topicId))) {
            return new ResultDTO().success("取消成功");
        }
        return new ResultDTO().fail("取消失败");
    }
}