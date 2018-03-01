package com.exercise.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.exercise.pojo.po.Student;
import com.exercise.service.IStudentService;

@Controller
public class StudentAction {

	@Autowired
	IStudentService studentService;
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String toListStudents(){
		return "redirect:listStudents";
	}
	
	@RequestMapping("/listStudents")
	public String listStudents(Model model){
		Map<String, Object> map=new HashMap<>();
		int pageSize=5;
		map.put("offset", 0);
		map.put("pageSize", 5);
		List<Student> students = studentService.listStudentsByPage(map);
		String condition=null;
		map.put("condition", condition);
		int totalPage=(int)Math.ceil(studentService.listStudentsNum(map)*1.0/pageSize);
		model.addAttribute("totalPage",totalPage);
		model.addAttribute("students", students);
		return "students";
	}
	
	/*//分页
	@RequestMapping("/listByPage")
	public String listByPage(HttpServletRequest request,Model model,HttpSession session){
		String pageStr = request.getParameter("page");
		int page=1;
		if(pageStr!=null){
			page=Integer.parseInt(pageStr);
		}else{
			page=(int) session.getAttribute("page");
		}
		Map<String, Integer> map=new HashMap<>();
		int pageSize=5;
		int offset=(page-1)*pageSize;
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		List<Student> students = studentService.listStudentsByPage(map);
		int totalPage=(int)Math.ceil(studentService.listStudentsNum()*1.0/pageSize);
		model.addAttribute("totalPage",totalPage);
		model.addAttribute("students", students);
		model.addAttribute("page", page);
		session.setAttribute("page", page);
		return "students";
	}*/
	
	//分页+模糊查询
	@RequestMapping("/listByPage")
	public String listByPage(String condition,HttpServletRequest request,Model model,HttpSession session){
		String pageStr = request.getParameter("page");
		int page=1;
		if(pageStr!=null){
			page=Integer.parseInt(pageStr);
		}
		Map<String, Object> map=new HashMap<>();
		int pageSize=5;
		int offset=(page-1)*pageSize;
		if (condition==null) {
			condition=(String) session.getAttribute("condition");
			
		}
		map.put("condition", condition);
		session.setAttribute("condition", condition);
		int totalPage=(int)Math.ceil(studentService.listStudentsNum(map)*1.0/pageSize);
		/*if(!("").equals(condition) && condition!=null){
			map.put("condition", condition);
		}*/
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		List<Student> students = studentService.listStudentsByPage(map);
		model.addAttribute("totalPage",totalPage);
		model.addAttribute("students", students);
		model.addAttribute("page", page);
		return "students";
	}
	@RequestMapping("/resetSearch")
	public String resetSearch(HttpSession session){
		session.removeAttribute("condition");
		session.removeAttribute("page");
		return "redirect:listByPage";
	}
	
	@RequestMapping("/deleteStudent")
	public String deleteStudent(HttpServletRequest request){
		String idStr = request.getParameter("id");
		int id=Integer.parseInt(idStr);
		studentService.deleteStudent(id);
		return "redirect:listByPage";
	}
	
	@RequestMapping("/toUpdateStudent")
	public String toUpdateStudent(HttpServletRequest request,Model model){
		String idStr = request.getParameter("id");
		int id=Integer.parseInt(idStr);
		Student student=studentService.selectById(id);
		model.addAttribute("student", student);
		return "updateStudent";
	}
	@RequestMapping("/updateStudent")
	public String updateStudent(Student student,HttpServletRequest request){
		studentService.updateStudent(student);
		return "redirect:listByPage";
	}
}
