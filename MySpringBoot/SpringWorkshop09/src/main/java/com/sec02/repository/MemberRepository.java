package com.sec02.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sec02.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
	
}
