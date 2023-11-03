package com.example.miyaeboard.data.dto;

import com.example.miyaeboard.data.common.BaseTimeEntity;
import com.example.miyaeboard.data.entity.Comment;
import com.example.miyaeboard.data.entity.Member;
import com.example.miyaeboard.data.entity.Post;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
@Data
@Getter
@Setter
@NoArgsConstructor
@Component
public class PostResponse extends BaseTimeEntity {//응답 받을 때 DB에서 오는 것, Entity와 비슷
    private Integer id;
    private String subject;
    private String content;
    private List<Comment> commentList;
    private Member author;
    private int readCnt;
    private Set<MemberResponse> voter;

//    public PostResponse(Post entity) {
//        this.id = entity.getId();
//        this.subject = entity.getSubject();
//        this.content = entity.getContent();
//        this.readCnt = entity.getReadCnt();
//        this.author = entity.getAuthor();
//        this.commentList = entity.getCommentList();
//    }
}
