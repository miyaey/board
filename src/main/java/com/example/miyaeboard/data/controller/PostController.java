package com.example.miyaeboard.data.controller;

import com.example.miyaeboard.data.dto.CommentRequest;
import com.example.miyaeboard.data.dto.PostRequest;
import com.example.miyaeboard.data.entity.Post;
import com.example.miyaeboard.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RequestMapping("/post")
@RequiredArgsConstructor
@Controller //이걸 붙여줘야 페이지와 매핑이 됨
public class PostController {
    private final PostService postService;

    @GetMapping("/list") //"/post/list"와 같음
    public String list(Model model) {  //Model은 데이터베이스에서 뷰로 전달할때 사용하는 객체
        List<Post> postList = this.postService.getList();
        model.addAttribute("postList", postList); //attribute 모델에 잇는 속성 postlist.html과 연결하려고 씀
        return "post_list";
    }

    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, CommentRequest commentRequest) {
        Post post = this.postService.getPost(id);
        model.addAttribute("post", post);
        return "post_detail";
    }

    @GetMapping("/create")
    public String postCreate(PostRequest postRequest) {
        return "post_form";
    }

    //11/01 과제 create 만들어 보기
    @PostMapping("/create") //@Valid는 우리가 Postrequest에 validation 체크하게 해놨음 거기랑 매핑이 되는거임
    public String postCreate(@Valid PostRequest postRequest, BindingResult bindingResult) { //Bindingresult에 연결이 되는 것

        if (bindingResult.hasErrors()) { //hasErros 우리가 Postrequest에 해놓은 글자수나 null이면 안되는 경우 에러 발생
            return "post_form";
        }
        this.postService.create(postRequest.getSubject(), postRequest.getContent()); //에러가 없다면 받은 subject, content 출력
        return "redirect:/post/listpage";
    }

    @GetMapping("update/{id}") //수정 버튼을 눌렀을 때 id를 가진 URL로 옮겨라(post form) 그 후에 PostMapping
    public String postUpdate(PostRequest postRequest, @PathVariable("id") Integer id) {
        Post post = this.postService.getPost(id);
        postRequest.setSubject(post.getSubject());
        postRequest.setContent(post.getContent());
        return "post_form";
    }

    @PostMapping("update/{id}")
    public String postUpdate(@Valid PostRequest postRequest, BindingResult bindingResult, @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "post_form";
        }
        Post post = this.postService.getPost(id);
        this.postService.update(post, postRequest.getSubject(), postRequest.getContent());
        return String.format("redirect:/post/detail/%s", id); //%s는 스트링값
    }

    @GetMapping("/delete/{id}")
    public String postdelete(@PathVariable Integer id) {
        postService.delete(id);
        return "redirect:/post/listpage";
    }
}

