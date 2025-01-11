package gr.hua.dit.ds.ds_lab_2024.controllers;

import gr.hua.dit.ds.ds_lab_2024.entities.Course;
import gr.hua.dit.ds.ds_lab_2024.entities.Student;
import gr.hua.dit.ds.ds_lab_2024.service.CourseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.boot.autoconfigure.batch.BatchTransactionManager;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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
    public Course createCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourse(@PathVariable Integer courseId) {
        Optional<Course> course = courseService.getCourse(courseId);
        return course.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<String>  deleteCourse(@PathVariable Integer courseId) {
        boolean result = courseService.deleteCourse(courseId);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).body("Course deleted successfully");
        }
            else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
        }
    }

    @GetMapping("/{courseId}/students")
    public List<Student> getCourseStudents(@PathVariable Integer courseId) {
        List<Student> students = courseService.getCourseStudents(courseId);
        return students;
    }
}
