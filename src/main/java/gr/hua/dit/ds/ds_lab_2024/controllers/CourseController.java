package gr.hua.dit.ds.ds_lab_2024.controllers;


import gr.hua.dit.ds.ds_lab_2024.entities.Course;
import gr.hua.dit.ds.ds_lab_2024.entities.Semester;
import gr.hua.dit.ds.ds_lab_2024.entities.Student;
import gr.hua.dit.ds.ds_lab_2024.entities.Teacher;
import gr.hua.dit.ds.ds_lab_2024.repositories.CourseRepository;
import gr.hua.dit.ds.ds_lab_2024.service.CourseService;
import gr.hua.dit.ds.ds_lab_2024.service.StudentService;
import gr.hua.dit.ds.ds_lab_2024.service.TeacherService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("course")
public class CourseController {


    private final StudentService studentService;
    private CourseService courseService;


    private TeacherService teacherService;

    public CourseController(CourseService courseService, TeacherService teacherService, StudentService studentService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.studentService = studentService;
    }


    //    @PostConstruct
//    public void setup() {
//        Course c1= new Course("Operating Systems", Semester.C);
//        Course c2= new Course("Data Structures", Semester.A);
//        Course c3= new Course("Algorithms", Semester.B);
//        courseService.saveCourse(c1);
//        courseService.saveCourse(c2);
//        courseService.saveCourse(c3);
//    }

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

    @Secured("ROLE_ADMIN")
    @GetMapping("/new")
    public String addCourse(Model model){
        Course course = new Course();
        model.addAttribute("course", course);
        return "course/course";

    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/new")
    public String saveStudent(@Valid @ModelAttribute("course") Course course,BindingResult theBindingResult, Model model) {
        if (theBindingResult.hasErrors()) {
            System.out.println("error");
            return "course/course";
        } else {
            courseService.saveCourse(course);
            model.addAttribute("courses", courseService.getCourses());
            model.addAttribute("successMessage", "Course added successfully!");
            return "course/courses";
        }

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

    @GetMapping("/studentassign/{id}")
    public String showAssignStudentToCourse(@PathVariable int id, Model model) {
        Course course = courseService.getCourse(id);
        List<Student> students = studentService.getStudents();
        List<Student> existing_students = course.getStudents();
        students.removeAll(existing_students);
        model.addAttribute("course", course);
        model.addAttribute("students", students);
        return "course/assignstudent";
    }


    @PostMapping("/assign/{id}")
    public String assignTeacherToCourse(@PathVariable int id, @RequestParam(value = "teacher", required = true) int teacherId, Model model) {
        System.out.println(teacherId);
        Teacher teacher = teacherService.getTeacher(teacherId);
        Course course = courseService.getCourse(id);
        System.out.println(course);
        courseService.assignTeacherToCourse(id, teacher);
        model.addAttribute("courses", courseService.getCourses());
        model.addAttribute("successMessage", "Form submitted successfully!");
        return "course/courses";
    }


    @PostMapping("/studentassign/{id}")
    public String assignStudentToCourse(@PathVariable int id, @RequestParam(value = "student", required = true) int studentId, Model model) {
        System.out.println(studentId);
        Student student = studentService.getStudent(studentId);
        Course course = courseService.getCourse(id);
        System.out.println(course);
        courseService.assignStudentToCourse(id, student);
        model.addAttribute("courses", courseService.getCourses());
        return "course/courses";
    }


}
