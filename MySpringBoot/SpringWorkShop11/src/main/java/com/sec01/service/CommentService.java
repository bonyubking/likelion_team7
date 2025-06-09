package com.sec01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.sec01.entity.Comment;
import com.sec01.entity.Post;
import com.sec01.event.CommentCreatedEvent;
import com.sec01.event.PostCreatedEvent;
import com.sec01.repository.CommentRepository;
import com.sec01.repository.PostRepository;



@Service
public class CommentService {
    
    @Autowired
    private CommentRepository commentRepository;
    
    @Autowired
    private PostRepository postRepository;
    
    @Autowired  
    private ApplicationEventPublisher publisher;
    
    public Comment addComment(Long postId, String writer, String content) throws RuntimeException {
    	
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글이 없습다"));
    			
    	Comment comment = new Comment();
    	comment.setPost(post);
        comment.setWriter(writer);
        comment.setContent(content);  
        
        Comment savedComment = commentRepository.save(comment);
        
        publisher.publishEvent(new CommentCreatedEvent(postId, writer, 
        		content)); 
        		System.out.println("비동기 이벤트 발행 완료"); 
        
        return savedComment;
    }
}
