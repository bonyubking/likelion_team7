package com.test02.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test02.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> { 
    Optional<Member> findByUsername(String username);
    
    List<Member> findTop5ByOrderByIdDesc();
} 
