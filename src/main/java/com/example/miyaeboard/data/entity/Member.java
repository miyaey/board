package com.example.miyaeboard.data.entity;

import com.example.miyaeboard.data.common.BaseTimeEntity;
import com.example.miyaeboard.data.common.MemberRole;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;             // 회원 번호 (PK)

    @Column(unique = true)
    private String nickname;      //

    @Column(name = "password")
    private String password;     // 비밀번호

    @Column(name = "name")
    private String name;         // 이름

    @Enumerated(EnumType.STRING)
    private MemberRole memberRole = MemberRole.USER;

    @Builder
    public Member(String nickname, String password, String name){
        this.nickname = nickname;
        this.password = password;
        this.name = name;
    }
}


