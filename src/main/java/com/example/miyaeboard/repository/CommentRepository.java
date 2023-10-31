package com.example.miyaeboard.repository;

import com.example.miyaeboard.data.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
