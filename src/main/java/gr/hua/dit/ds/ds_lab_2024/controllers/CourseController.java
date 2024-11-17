package gr.hua.dit.ds.ds_lab_2024.controllers;


import gr.hua.dit.ds.ds_lab_2024.entities.Course;
import gr.hua.dit.ds.ds_lab_2024.entities.Student;
import gr.hua.dit.ds.ds_lab_2024.entities.Teacher;
import gr.hua.dit.ds.ds_lab_2024.repositories.CourseRepository;
import gr.hua.dit.ds.ds_lab_2024.service.CourseService;
import gr.hua.dit.ds.ds_lab_2024.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;

    @RequestMapping()
    public String showCourses(Model model) {
        model.addAttribute("courses", courseService.getCourses());
        return "course/courses";
    }

    @GetMapping("/{id}")
    public String showCourse(@PathVariable Integer id, Model model){
        Course course = courseService.getCourse(id);
        model.addAttribute("courses", course);
        return "course/courses";
    }

    @GetMapping("/new")
    public String addCourse(Model model){
        Course course = new Course();
        model.addAttribute("course", course);
        return "course/course";

    }

    @PostMapping("/new")
    public String saveStudent(@ModelAttribute("course") Course course, Model model) {
        courseService.saveCourse(course);
        model.addAttribute("courses", courseService.getCourses());
        return "course/courses";
    }

    @GetMapping("/assign/{id}")
    public String showAssignTeacherToCourse(@PathVariable int id, Model model) {
        Course course = courseService.getCourse(id);
        List<Teacher> teachers = teacherService.getTeachers();
        model.addAttribute("course", course);
        model.addAttribute("teachers", teachers);
        return "course/assignteacher";
    }

    @GetMapping("/unassign/{id}")
    public String unassignTeacherToCourse(@PathVariable int id, Model model) {
        courseService.unassignTeacherFromCourse(id);
        model.addAttribute("courses", courseService.getCourses());
        return "course/courses";
    }

    @PostMapping("/assign/{id}")
    public String assignTeacherToCourse(@PathVariable int id, @ModelAttribute("teacher") Teacher teacher, Model model) {
        System.out.println(teacher);
        Course course = courseService.getCourse(id);
        System.out.println(course);
        courseService.assignTeacherToCourse(id, teacher);
        model.addAttribute("courses", courseService.getCourses());
        return "course/courses";
    }


}
