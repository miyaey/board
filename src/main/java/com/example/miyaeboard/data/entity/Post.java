package com.example.miyaeboard.data.entity;

import com.example.miyaeboard.data.common.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    //조회수 추가
    @Column(columnDefinition = "integer default 0", nullable = false) //생성 되면 0으로 시작하고, null이면 안된다
    private int readCnt;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Comment> commentList;

    @ManyToOne
    private Member author;

    @ManyToMany
    Set<Member> voter;
}
