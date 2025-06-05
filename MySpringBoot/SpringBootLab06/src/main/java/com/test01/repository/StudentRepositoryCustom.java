package com.test01.repository;

import java.util.List;

import com.test01.dto.StudentCourseDto;
import com.test01.entity.Student;
// 사용ㅈ ㅏ추가 쿼리메서드 선언
// 과목을 입력하면 수가하는 학생 목록 리턴
// select * from student join course on(student.id = course.id)
// where title  ="특정과목"

public interface StudentRepositoryCustom { 
    List<Student> findByCourseTitle(String courseTitle);
    List<StudentCourseDto> findStudentCourseDtoList();
}
