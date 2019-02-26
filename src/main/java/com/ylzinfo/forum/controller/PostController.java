package com.ylzinfo.forum.controller;

import com.ylzinfo.forum.entity.Post;
import com.ylzinfo.forum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @RequestMapping("get")
    @ResponseBody
    public List<Post> list() {
        return postService.list();
    }
}
