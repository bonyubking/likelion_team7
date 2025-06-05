package com.test01.repository;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.test01.dto.StudentCourseDto;
import com.test01.entity.QCourse;
import com.test01.entity.QStudent;
import com.test01.entity.Student;

@Repository
public class StudentRepositoryImpl extends QuerydslRepositorySupport implements StudentRepositoryCustom{
	
	private final JPAQueryFactory queryFactory;
	
	//생성자 명시로 수동 값 전달
	public StudentRepositoryImpl(JPAQueryFactory queryFactory) {
		super(Student.class);
		this.queryFactory = queryFactory;
	}
	
	@Override
	public List<Student> findByCourseTitle(String courseTitle) {
		QStudent student = QStudent.student;
		QCourse course = QCourse.course;
		
		return queryFactory
				.selectFrom(student)
				.join(student.courses, course)
				.where(course.title.eq(courseTitle))
				.fetch();
	}

	@Override
	public List<StudentCourseDto> findStudentCourseDtoList() {
		QStudent student = QStudent.student;
		QCourse course = QCourse.course;
	
		return queryFactory.select(Projections.constructor(StudentCourseDto.class, student.name, course.title))
				.from(student).join(student.courses, course).fetch();
	}
	
}
