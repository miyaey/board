package com.example.miyaeboard.repository;

import com.example.miyaeboard.data.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findBySubjectLike(String subject); //paging을 사용해서 list는 이제 사용 안함
    Page<Post> findAll(Pageable pageable); //Pageable 한 페이지에 몇개씩 보여줄건지(findAll도 그냥 쓸수 있지만 Page 때문에 있는거임)
    Page<Post> findBySubjectContaining(String kw, Pageable pageable); //키워드를 검색해서 찾으려고

    //create, update, save, delete는 원래 포함이 되어 있기 때문에 repository에 추가해 줄 필요 없음
}
