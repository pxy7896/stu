package com.example.demo.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.demo.mapper.CourseDao;
import com.example.demo.model.Course;

@Service("CourseService")
public class CourseService {
	@Resource
	private CourseDao courseDao;
	
	public int addCourse(String name, int weekday, int start) {
		Course course = new Course();
		course.setName(name);
		course.setWeekday(weekday);
		course.setStart(start);
		this.courseDao.insert(course);
		return this.courseDao.getCurId();
	}
}
