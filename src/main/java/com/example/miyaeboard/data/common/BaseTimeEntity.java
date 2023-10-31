package com.example.miyaeboard.data.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass //BaseTimeEntity가 JPA 엔티티의 공통 매핑 정보를 포함하는 클래스임을 의미
public class BaseTimeEntity {
    @Column(name = "created_date")
    private LocalDateTime createdDate;   // 생성일시

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;  // 최종 수정일시

    @PrePersist   //JPA 엔티티가 저장(Insert)되기 전에 실행할 메서드를 지정
    public void prePersist() {
        this.createdDate = LocalDateTime.now();
    }

    @PreUpdate   //JPA 엔티티가 수정(Update)되기 전에 실행할 메서드를 지정
    public void preUpdate() {
        this.modifiedDate = LocalDateTime.now();
    }

}
