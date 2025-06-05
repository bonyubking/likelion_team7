package com.test01.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity 
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class Student { 
 
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
 
    private String name; 
 
    // 나는 student_id로 중간 테이블에 연결 joinColumns
    // 나와 연결된 Course는 course_id로 연결되어 inverseJoinColumns
    @ManyToMany 
    @JoinTable( 
        name = "student_course", 
        joinColumns = @JoinColumn(name = "student_id"), 
        inverseJoinColumns = @JoinColumn(name = "course_id") 
    )
    
    @JsonIgnore
    private List<Course> courses = new ArrayList<>(); 
} 
