package com.sec01.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class Comment { 
 
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    
    @ManyToOne
    private Post post;
    
    private String content;
    
    @Override
    public String toString() {
        return "Comment{" +
               "id=" + id +
               ", post='" + post + '\'' +
               ", content='" + content + '\'' +
               '}';
    }

} 
