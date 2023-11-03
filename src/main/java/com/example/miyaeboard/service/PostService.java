package com.example.miyaeboard.service;

import com.example.miyaeboard.config.DataNotFoundException;
import com.example.miyaeboard.data.dto.PostResponse;
import com.example.miyaeboard.data.entity.Post;
import com.example.miyaeboard.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;

    private PostResponse of(Post post) {
        return modelMapper.map(post, PostResponse.class);
    }

    private Post of(PostResponse postResponse) {
        return modelMapper.map(postResponse, Post.class);
    }

    //전체 리스트 받아오기(Page를 만들어서 지금 쓰진 않음)
    public List<Post> getList() {

        return this.postRepository.findAll();
    }

    //id 하나 받아서 postResponse로 리턴
    public PostResponse getPost(Integer id) {
        Optional<Post> post = this.postRepository.findById(id);
        if (post.isPresent()) {
            Post p1 = post.get();
            p1.setReadCnt(p1.getReadCnt()+1);
            this.postRepository.save(p1);
            return of(post.get());
        } else {
            throw new DataNotFoundException("해당 글이 없습니다");
        }
    }

    //Sort는 스프링에서 제공하는 객체
    public Page<Post> getPage(String kw, int page) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(Sort.Order.desc("createdDate")); //createdDate 필드를 내림차순으로 정렬, desc : 역순(최근 날짜순)

        Pageable pageable = PageRequest.of(page, 10, Sort.by(orders)); //정렬 정보를 포함한 Pageable 생성

        //검색해서 보여 주는 기능
        if (kw != null && !kw.isEmpty()) {  //키워드가 null이 아니거나, 비어있지 않거나 둘 다 넣어서 확실하게 하려고
            return postRepository.findBySubjectContaining(kw, pageable); //키워드가 포함된 걸 보여주고
        } else {
            return this.postRepository.findAll(pageable); //검색어가 비어 있으면 전체를 보여줘라
        }

    }

    public void create(String subject, String content){ //새로 생성이기 때문에 response 해줄게 없어서 void
        Post q = new Post();
        q.setSubject(subject); //입력 들어온 것 (subject, content)
        q.setContent(content);
//        q.setCreatedDate(LocalDateTime.now());
        this.postRepository.save(q); //받은 값을 postRepository에 save하라
    }

    public PostResponse update(PostResponse postResponse, String subject, String content) {
        postResponse.setSubject(subject);
        postResponse.setContent(content);
        postResponse.setModifiedDate(LocalDateTime.now());  //BaseTimeEntity에서 자동으로 넣어주는데 설명을 위해 넣어주신 것
        Post post = of(postResponse); //받은걸 Post 객체로 돌려줌
        this.postRepository.save(post);
        return postResponse;
    }
    //create, update가 각각 q, post인건 상관없음 코드 짜는 사람 마음!!

    public void delete(PostResponse postResponse) {

        this.postRepository.deleteById(postResponse.getId());
    }

}
