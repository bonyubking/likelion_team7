package com.test01.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test01.entity.Emp;

@Repository 
public interface EmpRepository extends JpaRepository<Emp, Integer> {  
	
	List<Emp> findByDeptDeptno(int deptno);
}
