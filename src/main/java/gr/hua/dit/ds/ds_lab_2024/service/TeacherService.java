package gr.hua.dit.ds.ds_lab_2024.service;

import gr.hua.dit.ds.ds_lab_2024.entities.Teacher;
import gr.hua.dit.ds.ds_lab_2024.repositories.CourseRepository;
import gr.hua.dit.ds.ds_lab_2024.repositories.TeacherRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Transactional
    public List<Teacher> getTeachers(){
        return teacherRepository.findAll();
    }

    @Transactional
    public Teacher getTeacher(Integer teacherId) {
        return teacherRepository.findById(teacherId).get();
    }

    @Transactional
    public void saveTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

}
