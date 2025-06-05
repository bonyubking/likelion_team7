package com.test01.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public @Data class StudentCourseDto {
	
	private String studentName;
	private String courseTitle;
}
