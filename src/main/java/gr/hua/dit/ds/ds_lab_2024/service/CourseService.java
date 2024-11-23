package gr.hua.dit.ds.ds_lab_2024.service;

import gr.hua.dit.ds.ds_lab_2024.entities.Course;
import gr.hua.dit.ds.ds_lab_2024.entities.Student;
import gr.hua.dit.ds.ds_lab_2024.entities.Teacher;
import gr.hua.dit.ds.ds_lab_2024.repositories.CourseRepository;
import gr.hua.dit.ds.ds_lab_2024.repositories.TeacherRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private CourseRepository courseRepository;

    private TeacherRepository teacherRepository;

    public CourseService(CourseRepository courseRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
    }

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
        System.out.println(course);
        System.out.println(course.getTeacher());
        course.setTeacher(teacher);
        System.out.println(course.getTeacher());
        courseRepository.save(course);
    }

    @Transactional
    public void unassignTeacherFromCourse(int courseId) {
        Course course = courseRepository.findById(courseId).get();
        course.setTeacher(null);
        courseRepository.save(course);
    }

    @Transactional
    public void assignStudentToCourse(int courseId, Student student) {
        Course course = courseRepository.findById(courseId).get();
        course.addStudent(student);
        System.out.println("Course students: ");
        System.out.println(course.getStudents());
        courseRepository.save(course);
    }
}
