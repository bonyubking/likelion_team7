package com.test01.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity 
@NoArgsConstructor 
@AllArgsConstructor
@ToString(exclude = "emps")
// Dept -> Emp -> Dept -> ... 무한루프 되는것 방지
// emps에 해당하는 문자열은 출력하지 말아라 -> private List<Emp> emps; 
public @Data class Dept { 
	
	
	@Id 
	private int deptno; 
	private String dname; 
	private String loc; 
	
	@OneToMany(mappedBy = "dept") 
	private List<Emp> emps; 
}
