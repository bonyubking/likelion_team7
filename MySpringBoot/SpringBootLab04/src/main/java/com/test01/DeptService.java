package com.test01;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeptService {
    
	@Autowired
	private DeptRepository deptRepository;

    public List<Dept> findAll() {
        return deptRepository.findAll();
    }
    
    public Dept findById(int deptno) {
        return deptRepository.findById(deptno).orElse(null);
    }

}
