package com.test01.respository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.test01.dto.EmpDeptDto;
import com.test01.entity.Emp;

@Repository 
public interface EmpDeptRepository extends JpaRepository<Emp, Integer> { 
	
	//case 1 . DTO를 활용한 JPQL
	@Query("SELECT new com.test01.dto.EmpDeptDto(e.ename, e.sal, d.dname) " + 
			"FROM Emp e JOIN e.dept d") 
	List<EmpDeptDto> findEmpDeptInfo();
	
	
	// CASE 2 : DB 쿼리
	@Query(value = "SELECT e.ENAME, e.SAL, d.DNAME " + 
					"FROM EMP e JOIN DEPT d ON e.DEPTNO = d.DEPTNO", nativeQuery = true) 
	List<Object[]> findEmpDeptNative(); 
	
	
	// CASE 3 : DTO를 활용한 JPQL 페이징 처리
	@Query("SELECT new com.test01.dto.EmpDeptDto(e.ename, e.sal, d.dname) " +  
	"FROM #{#entityName} e JOIN e.dept d")
	Page<EmpDeptDto> findEmpDeptPage(Pageable pageable); 
	
	
	// case 4:  nativeQuery를 사용한 카운트 처리
    @Query(value = "SELECT e.ENAME, e.SAL, d.DNAME " +
            "FROM EMP e NATURAL JOIN DEPT d",
            countQuery = "SELECT COUNT(*) FROM EMP e NATURAL JOIN DEPT d",
            nativeQuery = true)
    Page<Object[]> findEmpDeptPageNative(Pageable pageable);
   
	// SELECT e.ename , e.sal, d.dname
	// from emp e
	// join dept d on e.deptno = d.deptno
	// Limit ?, ?
} 
