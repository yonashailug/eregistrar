package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/eregistrar")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/home")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/students")
    public String listStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "students-list";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "student-form";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") Student student, BindingResult bindingResult, Model model) {
        studentService.saveStudent(student);
        return "redirect:/eregistrar/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/eregistrar/students";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Student> student = studentService.getStudentById(id);

        System.out.println(student);
        if (student.isEmpty()) {
            return "redirect:/eregistrar/students";
        }

        model.addAttribute("student", student.get());
        return "student-form";
    }
}






