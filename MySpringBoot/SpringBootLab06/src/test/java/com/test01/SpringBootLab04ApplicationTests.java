package com.test01;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;

import com.test01.entity.Course;
import com.test01.entity.Student;
import com.test01.repository.CourseRepository;
import com.test01.repository.StudentRepository;

@SpringBootTest
@AutoConfigureMockMvc
class SpringBootLab04ApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Test
	void testShowAllpages() throws Exception {
		
		Student student = new Student();
		student.setName("홍길동");
		studentRepository.save(student);
		
		Course course = new Course();
		course.setTitle("자바 프로그래밍");
		courseRepository.save(course);
		
		mockMvc.perform(get("/students"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("홍길동")));
		
		mockMvc.perform(get("/courses"))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("자바 프로그래밍")));
		
		
	}

}
