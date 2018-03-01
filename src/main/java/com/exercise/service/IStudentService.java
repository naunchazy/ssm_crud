package com.exercise.service;

import java.util.List;
import java.util.Map;

import com.exercise.pojo.po.Student;

public interface IStudentService {

	List<Student> listStudentsByPage(Map<String, Object> map);
	int listStudentsNum(Map<String, Object> map);
	void deleteStudent(Integer id);
	Student selectById(Integer id);
	void updateStudent(Student student);
}
