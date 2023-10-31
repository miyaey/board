package com.example.miyaeboard.data.controller;

import com.example.miyaeboard.data.dto.CommentRequest;
import com.example.miyaeboard.data.dto.PostRequest;
import com.example.miyaeboard.data.entity.Post;
import com.example.miyaeboard.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@RequestMapping("/post")
@RequiredArgsConstructor
@Controller //이걸 붙여줘야 페이지와 매핑이 됨
public class PostController {
    private final PostService postService;

    @GetMapping("/list") //"/post/list"와 같음
    public String list(Model model){  //Model은 데이터베이스에서 뷰로 전달할때 사용하는 객체
        List<Post> postList = this.postService.getList();
        model.addAttribute("postList", postList); //attribute 모델에 잇는 속성 postlist.html과 연결하려고 씀
        return "post_list";
    }

    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, CommentRequest commentRequest){
        Post post = this.postService.getPost(id);
        model.addAttribute("post", post);
        return "post_detail";
    }

    @GetMapping("/create")
    public String postCreate(PostRequest postRequest){
        return "post_from";
    }

}
