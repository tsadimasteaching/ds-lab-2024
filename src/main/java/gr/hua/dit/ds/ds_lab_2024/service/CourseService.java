package gr.hua.dit.ds.ds_lab_2024.service;

import gr.hua.dit.ds.ds_lab_2024.entities.Course;
import gr.hua.dit.ds.ds_lab_2024.entities.Student;
import gr.hua.dit.ds.ds_lab_2024.entities.Teacher;
import gr.hua.dit.ds.ds_lab_2024.repositories.CourseRepository;
import gr.hua.dit.ds.ds_lab_2024.repositories.StudentRepository;
import gr.hua.dit.ds.ds_lab_2024.repositories.TeacherRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private CourseRepository courseRepository;

    private TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    public CourseService(CourseRepository courseRepository,
                         TeacherRepository teacherRepository,
                         StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional
    public List<Course> getCourses(){
        return courseRepository.findAll();
    }

    @Transactional
    public Course saveCourse(Course course) {
       return courseRepository.save(course);
    }

    @Transactional
    public Optional<Course> getCourse(Integer courseId) {
        return courseRepository.findById(courseId);
    }

    @Transactional
    public List<Student> getCourseStudents(final Integer courseId) {
        final Course course = this.courseRepository.findById(courseId).orElseThrow();
        return Collections.emptyList(); // TODO Implement.
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

    public boolean deleteCourse(Integer courseId) {
        Optional<Course> course = courseRepository.findById(courseId);

        if (course.isPresent()) {
            courseRepository.deleteById(courseId);
            return true;
        }
        else {
            return false;
        }

    }
}
