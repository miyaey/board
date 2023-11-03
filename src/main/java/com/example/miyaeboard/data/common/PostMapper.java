package com.example.miyaeboard.data.common;

import com.example.miyaeboard.data.dto.PostRequest;
import com.example.miyaeboard.data.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapper implements EntityMapper<PostRequest, Post> {

    @Override
    public Post toEntity(PostRequest postRequest) {
        Post post = new Post();
        post.setSubject(postRequest.getSubject());
        post.setContent(post.getContent());
        post.setCreatedDate(post.getCreatedDate());
        post.setModifiedDate(post.getModifiedDate());
        return post;
    }
}

