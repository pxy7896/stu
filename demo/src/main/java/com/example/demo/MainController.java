package com.example.demo;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Course;
import com.example.demo.model.CourseForShow;
import com.example.demo.model.Student;
import com.example.demo.service.CourseService;
import com.example.demo.service.EnrollService;
import com.example.demo.service.StudentService;

@Controller
public class MainController {
	@Resource
	private StudentService studentService;
	@Resource
	private CourseService courseService;
	@Resource
	private EnrollService enrollService;
	
	@RequestMapping("/")
	public String toHome() {
		return "index";
	}
	
	@RequestMapping("/result")
	public String toResult() {
		return "result";
	}
	
	@PostMapping("/addStudent")
	public String toAddStudentResult(RedirectAttributes redirectAttributes, @RequestParam("studentName")String studentName) {
		//System.out.println("*" + studentName + "*");
		// add student ok
		int sid = this.studentService.addStudent(studentName);
		redirectAttributes.addFlashAttribute("msg", studentName + " has been added successfully! Student id: " + String.valueOf(sid));
		return "redirect:/result";
	}
	
	@PostMapping("/addCourse")
	public String toAddCourseResult(RedirectAttributes redirectAttributes, @RequestParam("courseName")String courseName, 
			@RequestParam("weekday")String weekday, @RequestParam("start")String start) {
		//System.out.println("*" + studentName + "*");
		// add student ok
		int cid = this.courseService.addCourse(courseName, Integer.parseInt(weekday), Integer.parseInt(start));
		redirectAttributes.addFlashAttribute("msg", courseName + " has been added successfully! Course id: " + String.valueOf(cid));
		return "redirect:/result";
	}
	
	@PostMapping("/enroll")
	public String toEnrollResult(RedirectAttributes redirectAttributes, @RequestParam("studentId")String studentId,
			@RequestParam("courseId")String courseId) {
		boolean flag = this.enrollService.enrollCourse(Integer.parseInt(studentId), Integer.parseInt(courseId));
		if (flag) {
			redirectAttributes.addFlashAttribute("msg", "Enroll successfully!");
		} else {
			redirectAttributes.addFlashAttribute("msg", "Student has no spare time!");
		}
		return "redirect:/result";
	}
	
	@PostMapping("/queryStudentsByCid")
	public String toQuerryStudentsResult(RedirectAttributes redirectAttributes, @RequestParam("queryCid")String queryCid) {
		List<Student> result = this.enrollService.queryStudentByCid(Integer.parseInt(queryCid));
		if (result.size() > 0) {
			redirectAttributes.addFlashAttribute("resultStudents", result);
			redirectAttributes.addFlashAttribute("inputInfo", "Query students by course_id: " + queryCid);
		} else {
			redirectAttributes.addFlashAttribute("msg", "Query students by course_id: " + queryCid);
			redirectAttributes.addFlashAttribute("inputInfo", "Nobody enrolls in this course! ");
		}
		return "redirect:/result";
	}
	
	@PostMapping("/queryCoursesBySid")
	public String toQuerryCoursesResult(RedirectAttributes redirectAttributes, @RequestParam("querySid")String querySid) {
		List<CourseForShow> result = this.enrollService.queryCourseBySid(Integer.parseInt(querySid));
		if (result.size() > 0) {
			redirectAttributes.addFlashAttribute("resultCourses", result);
			redirectAttributes.addFlashAttribute("inputInfo", "Query courses by student_id: " + querySid);
		} else {
			redirectAttributes.addFlashAttribute("msg", "Query courses by student_id: " + querySid);
			redirectAttributes.addFlashAttribute("inputInfo", " No courses enrolled yet! ");
		}
		return "redirect:/result";
	}
	
	@PostMapping("/querySchedule")
	public String toQueryScheduleResult(RedirectAttributes redirectAttributes, @RequestParam("querySSid")String querySSid,
			@RequestParam("qWeekday")String qWeekday) {
		List<CourseForShow> result = this.enrollService.querySchedule(Integer.parseInt(querySSid), Integer.parseInt(qWeekday));
		if (result.size() > 0) {
			redirectAttributes.addFlashAttribute("resultCourses", result);
			redirectAttributes.addFlashAttribute("inputInfo", "Query schedule for student_id: " + querySSid + " on " + result.get(0).getWeekday());
		} else {
			redirectAttributes.addFlashAttribute("msg", "Query schedule for student_id: " + querySSid + " on " + result.get(0).getWeekday());
			redirectAttributes.addFlashAttribute("inputInfo", " No courses enrolled yet! ");
		}
		return "redirect:/result";
	}
	
	
}
