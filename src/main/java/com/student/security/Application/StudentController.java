package com.student.security.Application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

	private static List<Student> studentList = new ArrayList<>();
	
	@GetMapping
	@RequestMapping(value="/allStudents")
	public ResponseEntity< List<Student>> getAllStudents(){
		return new ResponseEntity<>( studentList,HttpStatus.OK);
		
	}
	
	@GetMapping
	@RequestMapping(value="/students/{sid}")
	public ResponseEntity<Student> getStudent(@PathVariable Integer sid){
		return new ResponseEntity<>( getStudentById(sid),HttpStatus.OK);
		
	}
	
	@PostConstruct
	public void getStudents(){
		Student st = new Student(1,"abc",21,getAllCourses());
		Student st1 = new Student(2,"def",22,getAllCourses());
		Student st2 = new Student(3,"ghi",23,getAllCourses());
		Student st3 = new Student(4,"jkl",24,getAllCourses());
		studentList=Arrays.asList(st,st1,st2,st3); 
	}
	public Student getStudentById(int sid){
		
		return  studentList.stream().filter(st->st.getEid()==sid).collect(Collectors.toList()).get(0);
	}
	
	public List<Course> getAllCourses(){
		
		Course c1 = new Course(1,"JAVA");
		Course c2 = new Course(2,".NET");
		Course c3 = new Course(3,"Oracle");
		Course c4 = new Course(4,"AWS");
		
		return Arrays.asList(c1,c2,c3,c4);
	}
}
