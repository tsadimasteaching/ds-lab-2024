package gr.hua.dit.ds.ds_lab_2024.controllers;

import gr.hua.dit.ds.ds_lab_2024.entities.Student;
import gr.hua.dit.ds.ds_lab_2024.repositories.StudentRepository;
import gr.hua.dit.ds.ds_lab_2024.service.StudentService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("student")
public class StudentController {


   StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //    @PostConstruct
//    public void setup() {
//        Student Stud1= new Student("Nick", "Jones", "nick@hua.gr");
//        Student Stud2= new Student("Jack", "James", "jack@hua.gr");
//        Student Stud3= new Student("John", "Stone", "john@hua.gr");
//        studentService.saveStudent(Stud1);
//        studentService.saveStudent(Stud2);
//        studentService.saveStudent(Stud3);
//    }


    @GetMapping("")
    public String showStudents(Model model){
        model.addAttribute("students", studentService.getStudents());
        return "student/students";
    }

    @GetMapping("/{id}")
    public String showStudent(@PathVariable Integer id, Model model){
        model.addAttribute("students", studentService.getStudent(id));
        return "student/students";
    }

    @GetMapping("/profile/{id}")
    public String showProfile(@PathVariable Integer id, Model model){
        model.addAttribute("student", studentService.getStudent(id));
        return "student/student-profile";
    }

    @GetMapping("/new")
    public String addStudent(Model model){
        Student student = new Student();
        model.addAttribute("student", student);
        return "student/student";
    }

    @PostMapping("/new")
    public String saveStudent(@ModelAttribute("student") Student student, Model model) {
        studentService.saveStudent(student);
        model.addAttribute("students", studentService.getStudents());
        return "student/students";
    }
}