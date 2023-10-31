package com.example.miyaeboard;

import com.example.miyaeboard.data.entity.Comment;
import com.example.miyaeboard.data.entity.Post;
import com.example.miyaeboard.repository.CommentRepository;
import com.example.miyaeboard.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class MiyaeboardApplicationTests {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Test
	void testPostCreate(){
		Post[] p1 = new Post[110];
		for (int i = 0; i<110; i++){
			p1[i] = new Post();
			p1[i].setSubject(i + "--게시글 테스트");
			p1[i].setContent("안녕하세요~ 게시글 테스트" + i + "입니다");
			p1[i].setReadCnt(0);
			p1[i].setCreatedDate(LocalDateTime.now());
			this.postRepository.save(p1[i]);
		}
	}

	@Test
	void testComment(){
		Optional<Post> po = this.postRepository.findById(2);
		assertTrue(po.isPresent());
		Post q = po.get(); //아이디 2번인 애를 q에 넣어라

		Comment a = new Comment();
		a.setContent("네 자동으로 생성됩니다");
		a.setPost(q); //어떤 게시글의 댓글인지 알기 위해서 Post 객체가 필요하다
		a.setCreatedDate(LocalDateTime.now());
		this.commentRepository.save(a);

	}

}
