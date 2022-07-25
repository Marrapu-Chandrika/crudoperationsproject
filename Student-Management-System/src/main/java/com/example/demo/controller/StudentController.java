package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;

@Controller
public class StudentController {

		private StudentService studentService;

		
		public StudentController(StudentService studentService) {
			super();
			this.studentService = studentService;
		}


		@GetMapping("/students")
		public String listStudents(Model model)
		{
			model.addAttribute("students",studentService.getAllStudents()); 
			return "students";
			
		}
		@GetMapping("/students/new")
		public String createStudentForm(Model model)
		{
			Student student = new Student();
			model.addAttribute("student", student);
			return "create_student";
		}
		
		@PostMapping("/students")
		public String saveStudent(@ModelAttribute("student") Student student)
		{
			studentService.saveStudent(student);
			
			return "redirect:/students"; 
		}
		@GetMapping("/students/edit/{id}")
		public String editStudentForm(@PathVariable Long id,Model model)
		{
			model.addAttribute("student",studentService.getStudentById(id));
			return "update_student";
		}
		@PostMapping("/students/{id}")
		public String updateStudent(@PathVariable Long id, @ModelAttribute("student") Student student,Model model)
		{
			Student temp = studentService.getStudentById(id);
			
			temp.setId(id);
			temp.setEmail(student.getEmail());
			temp.setFirstName(student.getFirstName());
			temp.setLastName(student.getLastName());
			studentService.updateStudent(temp);
			return "redirect:/students";
		}
		@GetMapping("/students/{id}")
		public String deleteStudent(@PathVariable Long id)
		{
			studentService.deleteStudentById(id);
			return "redirect:/students";
		}
	}
		

