package com.example.miyaeboard.data.dto;

import com.example.miyaeboard.data.common.BaseTimeEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CommentResponse extends BaseTimeEntity {
    private Integer id;
    private String content;
    private PostResponse post;
    private MemberResponse author;
    private Set<MemberResponse> voter;
}
