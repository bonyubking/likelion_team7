package com.test01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test01.entity.Course;

@Repository 
public interface CourseRepository extends JpaRepository<Course, Long> {} 
