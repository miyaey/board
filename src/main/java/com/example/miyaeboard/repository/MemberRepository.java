package com.example.miyaeboard.repository;

import com.example.miyaeboard.data.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}
