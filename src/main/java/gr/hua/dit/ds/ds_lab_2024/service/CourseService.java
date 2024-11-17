package gr.hua.dit.ds.ds_lab_2024.service;

import gr.hua.dit.ds.ds_lab_2024.entities.Course;
import gr.hua.dit.ds.ds_lab_2024.entities.Teacher;
import gr.hua.dit.ds.ds_lab_2024.repositories.CourseRepository;
import gr.hua.dit.ds.ds_lab_2024.repositories.TeacherRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Transactional
    public List<Course> getCourses(){
        return courseRepository.findAll();
    }

    @Transactional
    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    @Transactional
    public Course getCourse(Integer courseId) {
        return courseRepository.findById(courseId).get();
    }

    @Transactional
    public void assignTeacherToCourse(int courseId, Teacher teacher) {
        Course course = courseRepository.findById(courseId).get();
        course.setTeacher(teacher);
        courseRepository.save(course);
    }

    @Transactional
    public void unassignTeacherFromCourse(int courseId) {
        Course course = courseRepository.findById(courseId).get();
        course.setTeacher(null);
        courseRepository.save(course);
    }

}
