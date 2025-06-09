package com.sec01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.sec01.entity.Post;
import com.sec01.event.PostCreatedEvent;
import com.sec01.event.PostDeletedEvent;
import com.sec01.repository.PostRepository;

import jakarta.transaction.Transactional;



@Service
public class PostService {
    
    @Autowired
    private PostRepository postRepository;
    
    @Autowired  
    private ApplicationEventPublisher publisher;
    
    public Post createPost(String title) {

        Post post = new Post();
        post.setTitle(title);
        post.setContent("");  
        
        Post savedPost = postRepository.save(post);
        
        System.out.println("서비스 내부 로직 실행 완료");

        publisher.publishEvent(new PostCreatedEvent(savedPost.getId(), savedPost.getTitle()));
        
        System.out.println("이벤트 발행 후 로직 계속 진행됨");
        
        return savedPost;
    }
    
    @Transactional
    public void deletePost(Long postId) {
    	
    	postRepository.deleteById(postId);
    	publisher.publishEvent(new PostDeletedEvent(postId)); 
    }
}
