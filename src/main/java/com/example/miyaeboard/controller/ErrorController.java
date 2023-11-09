package com.example.miyaeboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/error/forbidden")
    public String showForbiddenPage() {
        return "forbidden"; // 사용자 정의 권한 오류 페이지의 뷰 이름
    }
}
