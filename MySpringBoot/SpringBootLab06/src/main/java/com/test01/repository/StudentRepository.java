package com.test01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test01.entity.Student;

@Repository 
public interface StudentRepository extends JpaRepository<Student, Long>, StudentRepositoryCustom {} 