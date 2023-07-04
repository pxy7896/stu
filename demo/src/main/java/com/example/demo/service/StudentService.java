package com.example.demo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.demo.mapper.StudentDao;
import com.example.demo.model.Student;

@Service("StudentService")
public class StudentService {
	@Resource
	private StudentDao studentDao;
	
	public int addStudent(String name) {
		Student student = new Student();
		student.setName(name);
		this.studentDao.insert(student);
		return this.studentDao.getCurId();
	}
	
	
}
