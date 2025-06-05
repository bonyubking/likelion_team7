package com.test01;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.test01.entity.Emp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@SpringBootTest 
public class EmpPersistenceContextTest { 
 
@PersistenceContext 
    EntityManager entityManager; 
 
@Transactional 
public void testFirstLevelCache() { 
    Emp emp1 = entityManager.find(Emp.class, 7369);  //-> 캐시보관 
    Emp emp2 = entityManager.find(Emp.class, 7369); // -> emp1 리턴 
    System.out.println(emp1 == emp2); // true 
} 


@Test 
@Transactional 
public void testDirtyChecking() { 
    Emp emp = entityManager.find(Emp.class, 7369); 
    System.out.println("기존 급여: " + emp.getSal()); 

    emp.setSal(emp.getSal() + 100); // 급여 변경 (Dirty Checking 발생) 

    System.out.println("변경된 급여: " + emp.getSal()); 
    // 트랜잭션 커밋 시점에 UPDATE 쿼리가 나감 
}


@Test 
@Transactional 
public void testFetchType() { 
Emp emp = entityManager.find(Emp.class, 7369); 
System.out.println("사원이름: " + emp.getEname()); 
// LAZY 관계라면 아래에서 쿼리 발생 
System.out.println("부서이름: " + emp.getDept().getDname()); 
} 

}