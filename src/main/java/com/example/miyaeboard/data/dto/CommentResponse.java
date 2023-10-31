package com.example.miyaeboard.data.dto;

import com.example.miyaeboard.data.common.BaseTimeEntity;

import java.util.Set;

public class CommentResponse extends BaseTimeEntity {
    private Integer id;
    private String content;
    private PostResponse postResponse;
    private MemberResponse author;
    private Set<MemberResponse> voter;
}
