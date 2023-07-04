package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.CourseDao;
import com.example.demo.mapper.EnrollDao;
import com.example.demo.mapper.StudentDao;
import com.example.demo.model.Course;
import com.example.demo.model.CourseForShow;
import com.example.demo.model.Enroll;
import com.example.demo.model.Student;

@Service("EnrollService")
public class EnrollService {
	@Resource
	private EnrollDao enrollDao;
	@Resource
	private CourseDao courseDao;
	@Autowired
	private StudentDao studentDao;
	
	public boolean enrollCourse(int sid, int cid) {
		try {
			// check
			List<Enroll> enrolled = this.enrollDao.selectBySid(sid);
			if (enrolled.size() == 0) {
				// student haven't enrolled any course
				Enroll enroll = new Enroll();
				enroll.setCid(cid);
				enroll.setSid(sid);
				this.enrollDao.insert(enroll);
				return true;
			} else {
				List<String> times = new ArrayList<String>();
				for(Enroll e : enrolled) {
					int c = e.getCid();
					Course course = this.courseDao.selectByPrimaryKey(c);
					String k = String.valueOf(course.getWeekday()) + "-" + String.valueOf(course.getStart());
					// no spare time
					if (times.contains(k) || c == cid) {
						return false;
					} else {
						times.add(k);
						continue;
					}
				}
				// can enroll
				Enroll enroll = new Enroll();
				enroll.setCid(cid);
				enroll.setSid(sid);
				this.enrollDao.insert(enroll);
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Student> queryStudentByCid(int cid) {
		List<Enroll> enrolled = this.enrollDao.selectByCid(cid);
		List<Student> result = new ArrayList<Student>();
		if (enrolled.size() != 0) {
			for(Enroll e : enrolled) {
				Student s = this.studentDao.selectByPrimaryKey(e.getSid());
				if (s != null) {
					result.add(s);
				}
			}
		}
		return result;
	}
	
	public List<CourseForShow> queryCourseBySid(int sid) {
		List<Enroll> enrolled = this.enrollDao.selectBySid(sid);
		List<CourseForShow> result = new ArrayList<CourseForShow>();
		if (enrolled.size() != 0) {
			for(Enroll e : enrolled) {
				Course c = this.courseDao.selectByPrimaryKey(e.getCid());
				if (c != null) {
					CourseForShow cfs = new CourseForShow();
					cfs.setId(c.getId());
					cfs.setName(c.getName());
					cfs.setStart(c.getStart());
					cfs.setWeekdayByInt(c.getWeekday());
					result.add(cfs);
				}
			}
		}
		return result;
	}
	
	public List<CourseForShow> querySchedule(int sid, int weekday) {
		List<Enroll> enrolled = this.enrollDao.selectBySid(sid);
		List<CourseForShow> result = new ArrayList<CourseForShow>();
		if (enrolled.size() != 0) {
			for(Enroll e : enrolled) {
				Course c = this.courseDao.selectByPrimaryKey(e.getCid());
				if (c != null && c.getWeekday() == weekday) {
					CourseForShow cfs = new CourseForShow();
					cfs.setId(c.getId());
					cfs.setName(c.getName());
					cfs.setStart(c.getStart());
					cfs.setWeekdayByInt(c.getWeekday());
					result.add(cfs);
				}
			}
		}
		return result;
	}

}
