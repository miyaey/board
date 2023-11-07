package com.example.miyaeboard.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
@Getter
@Setter
@NoArgsConstructor
public class CommentRequest {
    @NotEmpty(message = "내용은 필수항목입니다.")
    private String content;
}
