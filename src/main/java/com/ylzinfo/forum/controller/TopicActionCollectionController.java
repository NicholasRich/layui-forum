package com.ylzinfo.forum.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ylzinfo.forum.dto.ResultDTO;
import com.ylzinfo.forum.entity.UserTopicAction;
import com.ylzinfo.forum.service.UserTopicActionService;
import com.ylzinfo.forum.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("collection")
public class TopicActionCollectionController {
    @Autowired
    private UserTopicActionService userTopicActionService;

    @GetMapping("find")
    @ResponseBody
    public ResultDTO<Long> find(Long topicId) {
        UserTopicAction action = userTopicActionService.getOne(Wrappers.<UserTopicAction>lambdaQuery()
                .eq(UserTopicAction::getTopicId, topicId)
                .eq(UserTopicAction::getUserId, UserUtil.getUserId())
                .eq(UserTopicAction::getUserTopicType, "COLLECTION"));
        return new ResultDTO<Long>().dataSuccess(action == null ? null : action.getId());
    }

    @PostMapping("delete")
    @ResponseBody
    public ResultDTO remove(Long topicId) {
        if (!userTopicActionService.remove(Wrappers.<UserTopicAction>lambdaQuery()
                .eq(UserTopicAction::getTopicId, topicId)
                .eq(UserTopicAction::getUserId, UserUtil.getUserId()))) {
            return new ResultDTO().fail("取消失败");
        }
        return new ResultDTO().success("取消成功");
    }

    @PostMapping("insert")
    @ResponseBody
    public ResultDTO add(Long topicId) {
        UserTopicAction action = UserTopicAction.getInstance(topicId, UserUtil.getUserId(), "COLLECTION");
        if (!userTopicActionService.save(action)) {
            return new ResultDTO().fail("收藏失败");
        }
        return new ResultDTO<Long>().dataSuccess(action.getId(), "收藏成功");
    }
}
