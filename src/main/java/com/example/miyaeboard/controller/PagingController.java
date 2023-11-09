package com.example.miyaeboard.controller;

import com.example.miyaeboard.data.entity.Post;
import com.example.miyaeboard.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@RequestMapping("/post")
@RequiredArgsConstructor
@Controller
public class PagingController {
    private final PostService postService;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0")
    int page, @RequestParam(value = "kw", required = false) String kw){
        Page<Post> paging = postService.getPage(kw, page);
        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw); //검색어를 뷰로 전달
        return "post_list";
    }
}
