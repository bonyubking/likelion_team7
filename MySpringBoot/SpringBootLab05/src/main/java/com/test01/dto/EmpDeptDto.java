package com.test01.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data 
@AllArgsConstructor // 매개인자 있는 생성자 명시
public class EmpDeptDto { 
    private String ename; 
    private int sal; 
    private String dname; 
} 