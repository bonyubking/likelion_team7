package com.test01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.test01.dto.EmpDeptDto;
import com.test01.entity.Dept;
import com.test01.entity.Emp;
import com.test01.respository.DeptRepository;
import com.test01.respository.EmpDeptRepository;
import com.test01.respository.EmpRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpDeptService {
	
	@Autowired
	private DeptRepository deptRepository;
	
	@Autowired
	private EmpRepository empRepository;
	
	@Autowired
	private EmpDeptRepository empdeptRepository;
	
	public List<Emp> getAllEmps(){
		
		return empRepository.findAll();
	}
	
	public List<Dept> getAllDepts(){
		
		return deptRepository.findAll();
	}
	
	public List<EmpDeptDto> getEmpDeptDtos(){
		
		return empdeptRepository.findEmpDeptInfo();
	}
	
	
	public Page<EmpDeptDto> getEmpDeptPage(Pageable pageable){
		
		return empdeptRepository.findEmpDeptPage(pageable);
	}
}
