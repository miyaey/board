package com.example.miyaeboard.data.dto;

import com.example.miyaeboard.data.entity.Post;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRequest { //회원은 글제목과 내용 2개만 입력하기 때문에 이 두개만 request

    @NotEmpty(message = "제목은 필수항목입니다") //Validation 어노테이션 (컨트롤러에서 확인)
    @Size(max=200)
    private String subject;

    @NotEmpty(message = "내용은 필수항목입니다")
    private String content;

    public PostRequest(Post entity) {

        this.subject = entity.getSubject();
        this.content = entity.getContent();
    }
}
