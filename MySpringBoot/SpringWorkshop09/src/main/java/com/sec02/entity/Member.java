package com.sec02.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
public @Data class Member {
	

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String username;

    private String email;

    @Column(updatable = false)
    private LocalDateTime createdDate;
    
    @Override
    public String toString() {
        return "Member{" +
               "id=" + id +
               ", username='" + username + '\'' +
               ", email='" + email + '\'' +
               ", createdDate=" + createdDate +
               '}';
    }
}
