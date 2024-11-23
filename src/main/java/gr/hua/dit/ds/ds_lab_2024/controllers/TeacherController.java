package gr.hua.dit.ds.ds_lab_2024.controllers;

import gr.hua.dit.ds.ds_lab_2024.entities.Course;
import gr.hua.dit.ds.ds_lab_2024.entities.Semester;
import gr.hua.dit.ds.ds_lab_2024.entities.Teacher;
import gr.hua.dit.ds.ds_lab_2024.service.TeacherService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("teacher")
public class TeacherController {


    private TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    //    @PostConstruct
//    public void setup() {
//       Teacher t1 = new Teacher("Prof","Mark","Tailor","mark@company.com");
//       Teacher t2 = new Teacher("Lec","John","Marmor","john@example.com");
//
//       teacherService.saveTeacher(t1);
//       teacherService.saveTeacher(t2);
//    }

    @RequestMapping()
    public String showTeachers(Model model) {
        model.addAttribute("teachers", teacherService.getTeachers());
        return "teacher/teachers";
    }

    @GetMapping("/{id}")
    public String showTeacher(@PathVariable Integer id, Model model){
        Teacher teacher = teacherService.getTeacher(id);
        model.addAttribute("teachers", teacher);
        return "teacher/teachers";
    }

    @GetMapping("/new")
    public String addTeacher(Model model){
        Teacher teacher = new Teacher();
        model.addAttribute("teacher", teacher);
        return "teacher/teacher";

    }

    @PostMapping("/new")
    public String saveTeacher(@ModelAttribute("course") Teacher teacher, Model model) {
        teacherService.saveTeacher(teacher);
        model.addAttribute("teachers", teacherService.getTeachers());
        return "teacher/teachers";
    }

    @GetMapping("/{id}/courses")
    public String showCourses(@PathVariable("id") Integer id, Model model){
        Teacher teacher = teacherService.getTeacher(id);
        model.addAttribute("courses", teacher.getCourses());
        return "course/courses";
    }

}
