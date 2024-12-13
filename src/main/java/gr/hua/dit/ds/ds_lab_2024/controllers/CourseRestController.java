package gr.hua.dit.ds.ds_lab_2024.controllers;

import gr.hua.dit.ds.ds_lab_2024.entities.Course;
import gr.hua.dit.ds.ds_lab_2024.service.CourseService;
import org.springframework.boot.autoconfigure.batch.BatchTransactionManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Object>  deleteCourse(@PathVariable Integer courseId) {
        boolean result = courseService.deleteCourse(courseId);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).body("Course deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
        }
    }
}
