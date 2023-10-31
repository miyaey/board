package com.example.miyaeboard.data.controller;

import com.example.miyaeboard.data.entity.Post;
import com.example.miyaeboard.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
    private final PostService postService;

    @GetMapping
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page){
        Page<Post> paging = this.postService.getPage(page);
        model.addAttribute("paging", paging);
        return "listpage";
    }
}
