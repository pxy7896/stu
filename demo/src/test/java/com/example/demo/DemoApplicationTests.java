package com.example.demo;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.mapper.CourseDao;
import com.example.demo.mapper.EnrollDao;
import com.example.demo.mapper.StudentDao;
import com.example.demo.model.Student;

@SpringBootTest
class DemoApplicationTests {

	@Resource
	private EnrollDao enrollDao;
	@Resource
	private CourseDao courseDao;
	@Autowired
	private StudentDao studentDao;
	
    @Test
    void contextLoads() {
    	Student s = new Student();
    	//s.setName("test");
    	studentDao.insert(s);
    }

}
