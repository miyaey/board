package com.example.miyaeboard.repository;

import com.example.miyaeboard.data.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findBySubjectLike(String subject);
    Page<Post> findAll(Pageable pageable); //Pageable 한 페이지에 몇개씩 보여줄건지
    Page<Post> findBySubjectContaining(String kw, Pageable pageable); //키워드를 검색해서 찾으려고
}
