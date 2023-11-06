package com.example.miyaeboard.data.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class MainController {
//    @GetMapping("/") //루트가 들어오면 hello라고 문자열을 띄워 달라 (루트 디렉토리)
//    @ResponseBody //이걸 붙이면 그냥 이 문구를 띄워달라는 의미
//    public String index() {
//        return "hello";
//    }
//    @GetMapping("/")
//    public String root(){
//        return "redirect:/post/listpage";
//    }
    @GetMapping("/")
    public String root(){
        return "redirect:/post/list";
    }
    @GetMapping("/post/listpage")
    public String listpageing(){
        return "redirect:/post/list";
    }

}
