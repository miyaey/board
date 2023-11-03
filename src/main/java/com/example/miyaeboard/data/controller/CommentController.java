package com.example.miyaeboard.data.controller;

import com.example.miyaeboard.data.dto.CommentRequest;
import com.example.miyaeboard.data.dto.CommentResponse;
import com.example.miyaeboard.data.dto.PostResponse;
import com.example.miyaeboard.data.entity.Post;
import com.example.miyaeboard.service.CommentService;
import com.example.miyaeboard.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/comment")
@RequiredArgsConstructor
@Controller
public class CommentController {

    private final PostService postService;
    private final CommentService commentService;

    @PostMapping("/create/{id}")
    public String createComment(Model model, @PathVariable("id") Integer id, @Valid CommentRequest commentRequest,
                                BindingResult bindingResult) {
        PostResponse postResponse = this.postService.getPost(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("post", postResponse);
            return "post_detail";
        }
        CommentResponse commentResponse = this.commentService.create(postResponse, commentRequest.getContent());
        // 댓글 저장
//        this.commentService.create(post, commentRequest.getContent());
        return String.format("redirect:/post/detail/%s#comment_%s", commentResponse.getPost().getId(), commentResponse.getId());
    }

}
