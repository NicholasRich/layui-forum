package com.ylzinfo.forum.controller;

import com.ylzinfo.forum.dto.ResultDTO;
import com.ylzinfo.forum.entity.User;
import com.ylzinfo.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("index")
    public String index() {
        return "/user/index";
    }

    @GetMapping("{id}/home")
    public String home(@PathVariable Long id, Model model) {
        model.addAttribute("userId", id);
        return "/user/home";
    }

    @GetMapping("{id}")
    @ResponseBody
    public ResultDTO<User> getUser(@PathVariable Long id) {
        User user = userService.getById(id);
        user.setPassword(null);
        return new ResultDTO<User>().dataSuccess(user);
    }
}
