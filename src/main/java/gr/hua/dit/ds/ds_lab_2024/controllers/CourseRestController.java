package gr.hua.dit.ds.ds_lab_2024.controllers;

import gr.hua.dit.ds.ds_lab_2024.entities.Course;
import gr.hua.dit.ds.ds_lab_2024.service.CourseService;
import org.springframework.boot.autoconfigure.batch.BatchTransactionManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseRestController {

    CourseService courseService;

    public CourseRestController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("")
    public List<Course> getCourses(){
        return courseService.getCourses();
    }

    @PostMapping("")
    public Course createCourse(@RequestBody  Course course) {
        return courseService.saveCourse(course);
    }
}
