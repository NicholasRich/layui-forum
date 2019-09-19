package com.ylzinfo.forum.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ylzinfo.forum.dto.ResultDTO;
import com.ylzinfo.forum.entity.Topic;
import com.ylzinfo.forum.service.TopicService;
import com.ylzinfo.forum.util.UploadUtil;
import com.ylzinfo.forum.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("topic")
public class TopicController {
    @Autowired
    private TopicService topicService;
    @Autowired
    private HttpServletRequest request;

    @RequestMapping("list")
    public String list() {
        return "system/topic_list";
    }

    @PostMapping("insert")
    @ResponseBody
    public ResultDTO insert(Topic topic) {
        topic.setUserId(UserUtil.getUserId());
        Long id = topicService.insert(topic);
        if (id != null) {
            return new ResultDTO().actionSuccess(request.getContextPath() + "/topic/detail/" + id);
        }
        return new ResultDTO().fail("发帖失败");
    }

    @GetMapping("add")
    public String add() {
        return "jie/add";
    }

    @GetMapping("detail/{id}")
    public String detail(Model model, @PathVariable Long id, Long page, Long replyId) {
        model.addAttribute("id", id);
        model.addAttribute("page", page);
        model.addAttribute("replyId", replyId);
        return "jie/detail";
    }

    @GetMapping("content/{id}")
    @ResponseBody
    public ResultDTO<Topic> content(@PathVariable Long id) {
        return new ResultDTO<Topic>().dataSuccess(topicService.getDetail(id, UserUtil.getUserId()));
    }

    @PostMapping("image/upload")
    @ResponseBody
    public ResultDTO upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String preUrl = request.getContextPath();
        return new ResultDTO().imageSuccess(UploadUtil.imageUpload(multipartFile, preUrl));
    }

    @PostMapping("delete")
    @ResponseBody
    public ResultDTO delete(Long topicId) {
        if (topicService.delete(topicId)) {
            return new ResultDTO().success("删除成功");
        }
        return new ResultDTO().fail("删除失败");
    }

    @PostMapping("marrow")
    @ResponseBody
    public ResultDTO marrow(Long topicId) {
        if (topicService.update(Wrappers.<Topic>lambdaUpdate()
                .eq(Topic::getId, topicId)
                .set(Topic::getMarrow, "MARROW"))) {
            return new ResultDTO().success("加精成功");
        } else {
            return new ResultDTO().fail("加精失败");
        }
    }

    @PostMapping("cancelMarrow")
    @ResponseBody
    public ResultDTO cancelMarrow(Long topicId) {
        if (topicService.update(Wrappers.<Topic>lambdaUpdate()
                .eq(Topic::getId, topicId)
                .set(Topic::getMarrow, "NOMARROW"))) {
            return new ResultDTO().success("取消成功");
        } else {
            return new ResultDTO().fail("取消失败");
        }
    }

    @GetMapping(value = {"user/publish/{userId}", "user/publish"})
    @ResponseBody
    public ResultDTO<IPage<Topic>> getPublish(Long page, @PathVariable(required = false) Long userId) {
        return new ResultDTO<IPage<Topic>>().dataSuccess(topicService.getPublish(userId == null ? UserUtil.getUserId() : userId, page));
    }

    @GetMapping("collection")
    @ResponseBody
    public ResultDTO<IPage<Topic>> getCollection(Long page) {
        return new ResultDTO<IPage<Topic>>().dataSuccess(topicService.getCollection(UserUtil.getUserId(), page));
    }

}
