package com.test01.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test01.dto.EmpDeptDto;
import com.test01.entity.Dept;
import com.test01.entity.Emp;
import com.test01.respository.DeptRepository;
import com.test01.respository.EmpDeptRepository;
import com.test01.respository.EmpRepository;
import com.test01.service.EmpDeptService;

@Controller
public class EmpDeptController {
	
	
	@Autowired
	private EmpDeptService service;
	
	@GetMapping("/emp-info")
	public String showEmpDeptInfo(Model model,
									@RequestParam(defaultValue= "0") int page,
									@RequestParam(defaultValue= "5") int size,
									@RequestParam(defaultValue = "ename") String sortBy, 
									@RequestParam(defaultValue = "asc") String direction){
		
		Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		
		Page<EmpDeptDto> empPage = service.getEmpDeptPage(PageRequest.of(page, size, sort));
		model.addAttribute("empPage", empPage);
		
		return "emp-info";
		
	}
	
	
	@GetMapping("/emps")
	public String showAllEmps(Model model) {
		
		List<Emp> emps = service.getAllEmps();
		model.addAttribute("emps", emps);
		return "emps";
	}

	@GetMapping("/depts")
	public String showAllDepts(Model model) {
		
		List<Dept> depts = service.getAllDepts();
		model.addAttribute("depts", depts);
		return "depts";
	}
	

	

}
	
	

