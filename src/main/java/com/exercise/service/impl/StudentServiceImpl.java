package com.exercise.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exercise.mapper.StudentMapper;
import com.exercise.pojo.po.Student;
import com.exercise.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService{

	@Autowired
	StudentMapper studentMapper;
	
	//按页显示学生信息
	@Override
	public List<Student> listStudentsByPage(Map<String, Object> map) {
		List<Student> listStudents = studentMapper.listStudentsByPage(map);
		return listStudents;
	}

	//学生总记录条数
	@Override
	public int listStudentsNum(Map<String, Object> map) {
		int listStudentsNum = studentMapper.listStudentsNum(map);
		return listStudentsNum;
	}

	@Override
	public void deleteStudent(Integer id) {
		studentMapper.deleteStudent(id);
	}

	@Override
	public Student selectById(Integer id) {
		Student student=studentMapper.selectById(id);
		return student;
	}

	@Override
	public void updateStudent(Student student) {
		studentMapper.updateStudent(student);
	}

}
