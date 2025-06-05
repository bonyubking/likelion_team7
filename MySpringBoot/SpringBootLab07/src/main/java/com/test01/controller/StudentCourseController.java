package com.test01.controller;

import com.test01.entity.Student;
import com.test01.dto.CourseDto;
import com.test01.dto.StudentRequestDto;
import com.test01.dto.StudentResponseDto;
import com.test01.dto.StudentsAndCoursesResponse;
import com.test01.entity.Course;
import com.test01.service.StudentCourseService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class StudentCourseController {


    private final StudentCourseService service;

    public StudentCourseController(StudentCourseService service) {
		super();
		this.service = service;
	}

	// [1] 모든 학생 목록과 과목 목록을 함께 조회한다.
    @GetMapping("/students")
    public StudentsAndCoursesResponse showStudents () {
        List<Student> students = service.getAllStudents();
        List<Course> courses = service.getAllCourses();
        
        List<StudentResponseDto> studentDtos = students.stream()
        		.map(student -> new StudentResponseDto(
        				student.getId(),
        				student.getName(),
        				student.getCourses().stream()
        					.map(c -> new CourseDto(c.getId(), c.getTitle()))
        					.collect(Collectors.toList())
        	))
			.collect(Collectors.toList());
			
			return new StudentsAndCoursesResponse(studentDtos, courses);
    }

    // [2] 모든 과목 목록을 조회한다.
    @GetMapping("/courses")
    public List<Course> showCourses() {
    	return service.getAllCourses();
    }

    // [3] 학생과 과목을 동시에 추가한다.
    @PostMapping("/students")
    public void addStudent(@RequestBody StudentRequestDto dto) {
    	service.addStudent(dto.getName(), dto.getCourseIds());
    }

    // [4] 과목을 단독으로 추가한다.
    @PostMapping("/courses")
    public void addCourse(@RequestBody Course course) {
        service.addCourse(course.getTitle());

    }

    // [5] 특정 학생을 삭제한다.
    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable Long id) {
        service.deleteStudent(id);

    }

    // [6] 특정 과목을 삭제한다.
    @DeleteMapping("/courses/{id}")
    public void deleteCourse(@PathVariable Long id) {
        service.deleteCourse(id);
    }
    

}