package com.sec01.controller;

import com.sec01.entity.Comment;
import com.sec01.entity.Post;
import com.sec01.repository.CommentRepository;
import com.sec01.repository.PostRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts/{postId}/comments")
public class CommentController {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public CommentController(PostRepository postRepository,
                             CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    /** 1) 댓글 등록 (Create) **/
    @PostMapping
    public ResponseEntity<Comment> addComment(@PathVariable Long postId,
                                              @RequestBody Comment commentRequest) {

        Optional<Post> optPost = postRepository.findById(postId);
        if (optPost.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Post post = optPost.get();


        Comment comment = new Comment();
        comment.setContent(commentRequest.getContent());
        comment.setPost(post);
        Comment saved = commentRepository.save(comment);

        return ResponseEntity.ok(saved);
    }

    /** 2) 해당 게시글의 모든 댓글 조회 (Read All) **/
    @GetMapping
    public ResponseEntity<List<Comment>> getComments(@PathVariable Long postId) {

        if (!postRepository.existsById(postId)) {
            return ResponseEntity.notFound().build();
        }
        List<Comment> list = commentRepository.findByPostId(postId);
        return ResponseEntity.ok(list);
    }

    /** 3) 댓글 수정 (Update) **/
    @PutMapping("/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long postId,
                                                 @PathVariable Long commentId,
                                                 @RequestBody Comment commentRequest) {

        Optional<Post> optPost = postRepository.findById(postId);
        if (optPost.isEmpty()) {
            return ResponseEntity.notFound().build();
        }


        Optional<Comment> optComment = commentRepository.findById(commentId);
        if (optComment.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Comment existing = optComment.get();

        // 3-3. 댓글이 실제로 해당 게시글에 속한 것인지 검증(optional)
        if (!existing.getPost().getId().equals(postId)) {
            // URL 경로(postId)와 댓글의 postId가 다르면 Bad Request 처리
            return ResponseEntity.badRequest().build();
        }

        // 3-4. 내용 수정 후 저장
        existing.setContent(commentRequest.getContent());
        Comment saved = commentRepository.save(existing);
        return ResponseEntity.ok(saved);
    }

    /** 4) 댓글 삭제 (Delete) **/
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long postId,
                                              @PathVariable Long commentId) {

        if (!postRepository.existsById(postId)) {
            return ResponseEntity.notFound().build();
        }


        Optional<Comment> optComment = commentRepository.findById(commentId);
        if (optComment.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Comment existing = optComment.get();


        if (!existing.getPost().getId().equals(postId)) {
            return ResponseEntity.badRequest().build();
        }

        // 4-4. 삭제
        commentRepository.deleteById(commentId);
        return ResponseEntity.noContent().build();
    }
}