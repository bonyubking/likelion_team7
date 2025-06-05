package com.test01;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface EmpRepository extends JpaRepository<Emp, Integer> {  
	
	List<Emp> findByDeptDeptno(int deptno);
}
