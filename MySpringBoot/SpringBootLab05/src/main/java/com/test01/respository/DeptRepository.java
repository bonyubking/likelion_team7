package com.test01.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test01.entity.Dept;

@Repository 
public interface DeptRepository extends JpaRepository<Dept, Integer> { 
}