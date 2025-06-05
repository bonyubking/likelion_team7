package com.sec03.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
public @Data class Article {
	

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private String content;

    @Column(updatable = false)
    private LocalDateTime createdDate;
    
    @Override
    public String toString() {
        return "Member{" +
               "id=" + id +
               ", title= '" + title + '\'' +
               ", content='" + content + '\'' +
               ", createdDate=" + createdDate +
               '}';
    }
}
