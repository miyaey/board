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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    //전체 리스트 받아오기(Page를 만들어서 지금 쓰진 않음)
    public List<Post> getList() {
        return this.postRepository.findAll();
    }

    //id 하나로 조회하는 메서드
    public Post getPost(Integer id) {
        Optional<Post> post = this.postRepository.findById(id);
        if (post.isPresent()) {
            Post post1 = post.get();
            post1.setReadCnt(post1.getReadCnt() + 1);
            this.postRepository.save(post1);
            return post1;
        } else {
            throw new DataNotFoundException("post not found");
        }
    }

    //10/31 과제
    public Page<Post> getPage(String kw, int page) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(Sort.Order.desc("createdDate")); //createdDate 필드를 내림차순으로 정렬, desc : 역순(최근 날짜순)

        Pageable pageable = PageRequest.of(page, 10, Sort.by(orders)); //정렬 정보를 포함한 Pageable 생성

        //검색해서 보여 주는 기능
        if (kw != null && !kw.isEmpty()) {  //키워드가 null이 아니거라, 비어있지 않거나 둘 다 넣어서 확실하게 하려고
            return postRepository.findBySubjectContaining(kw, pageable); //키워드가 포함된 걸 보여주고
        } else {
            return this.postRepository.findAll(pageable); //검색어가 비어 있으면 전체를 보여줘라
        }

    }

    //11/01 과제 create 만들어 보기
    public void create(String subject, String content){
        Post q = new Post();
        q.setSubject(subject); //입력 들어온 것 (subject, content)
        q.setContent(content);
        q.setCreatedDate(LocalDateTime.now());
        this.postRepository.save(q); //받은 값을 postRepository에 save하라
    }

    public void update(Post post, String subject, String content){
        post.setSubject(subject);
        post.setContent(content);
        post.setModifiedDate(LocalDateTime.now()); //BaseTimeEntity에서 자동으로 넣어주는데 설명을 위해 넣어주신 것
        this.postRepository.save(post);
    }
    //create, update가 각각 q, post인건 상관없음 코드 짜는 사람 마음!!

    public void delete(Integer id) {

        postRepository.deleteById(id);
    }

}
