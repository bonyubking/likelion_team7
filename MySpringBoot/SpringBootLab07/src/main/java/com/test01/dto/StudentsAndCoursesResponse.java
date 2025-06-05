package com.test01.dto;

import java.util.List;

import com.test01.entity.Course;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentsAndCoursesResponse {
	
	private List<StudentResponseDto> students;
	private List<Course> courses;
}
