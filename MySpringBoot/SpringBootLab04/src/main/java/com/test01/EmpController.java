package com.test01;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/emp")
public class EmpController {
	
	@Autowired
	private EmpRepository empRepository;
	
	@Autowired
	private DeptRepository deptRepository;
	
	@GetMapping
	public String redirectToList() {
		return "redirect:/emp/list";
	}
	@GetMapping("/list")
	public String getAll(Model model) {
		
		List<Emp> emps = empRepository.findAll();
		List<Dept> depts = deptRepository.findAll();
		
		model.addAttribute("emps", emps);
		model.addAttribute("depts", depts);
		
		return "emp/list";
	}
	
	@GetMapping("/new")
	public String createForm(Model model) {

		model.addAttribute("emp", new Emp());
		model.addAttribute("depts", deptRepository.findAll());
		return "emp/new";
	}
	
	/**
	 * 4. 사원 등록처리
	 * 	@GetMapping("/new") -> 요ㅕ청하면 빈 EMP 객체를 가지고 emp/new.html로 이동후 데이터를 채운 EMP 개체를 가지고
	 * 
	 * 현재 메소드 호출
	 * 
	 */
	
	@PostMapping
	public String create(@ModelAttribute Emp emp) {
		empRepository.save(emp);
		return "redirect:/emp/list";
	}
    
	/** 
	 * 
	 * 5. 사원 상세보기 /emp/{empno} <a th:href="@("/emp/" + $(emp.empno}}" class ="btn btn-info btn-sm">상세</a>
	 */
	@GetMapping("/{empno}")
    public String getById(@PathVariable int empno, Model model) {
		
		Emp emp = empRepository.findById(empno).orElse(null);
		model.addAttribute("emp", emp);
		
	
        return "emp/detail"; // detail.html
    }
	
    @GetMapping("/{empno}/edit")
    public String editForm(@PathVariable int empno, Model model) {
    	
    	Emp emp = empRepository.findById(empno).orElse(null);
    	model.addAttribute("emp",emp);
    	model.addAttribute("depts", deptRepository.findAll());
    	
        return "emp/edit";
    }
    
    @PostMapping("/{empno}/edit")
    public String update(@PathVariable int empno, @ModelAttribute Emp empDetails) {
        Emp emp = empRepository.findById(empno).orElse(null);
        if (emp != null) {
        	emp.setEname(empDetails.getEname());
        	emp.setJob(empDetails.getJob());
        	emp.setSal(empDetails.getSal());
        	emp.setDept(empDetails.getDept());
        	empRepository.save(emp);
        }
    	
    	return "redirect:/emp/list";
    }

    @PostMapping("/{empno}/delete")
    public String delete(@PathVariable int empno) {
        
    	empRepository.deleteById(empno);
    	return "redirect:/emp/list";
    }
}
	
	

