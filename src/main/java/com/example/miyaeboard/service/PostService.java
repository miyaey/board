package com.example.miyaeboard.service;

import com.example.miyaeboard.config.DataNotFoundException;
import com.example.miyaeboard.data.entity.Post;
import com.example.miyaeboard.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    //전체 리스트 받아오기
    public List<Post> getList(){
        return this.postRepository.findAll();
    }

    //id 하나로 조회하는 메서드
    public Post getPost(Integer id) {
        Optional<Post> post = this.postRepository.findById(id);
        if (post.isPresent()) {
            Post post1 = post.get();
            post1.setReadCnt(post1.getReadCnt()+1);
            this.postRepository.save(post1);
            return post1;
        } else {
            throw new DataNotFoundException("post not found");
        }
    }

    //10/31 과제
    public Page<Post> getPage(int page){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createdDate")); //createdDate 필드를 내림차순으로 정렬

        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts)); //정렬 정보를 포함한 Pageable 생성
        return this.postRepository.findAll(pageable);
    }

}
