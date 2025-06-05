package com.test01;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpService {
	
	private final EmpRepository empRepository;
	
	public List<Emp> findAll(){
		
		return empRepository.findAll();
		
	}
	
	public Emp findById(int empno) {
		
		return empRepository.findById(empno).orElse(null);
	}
	
    @Transactional
	public void save(Emp emp) {
        empRepository.save(emp);
    }
    
    @Transactional
    public void delete(int empno) {
        empRepository.deleteById(empno);
    }
}
