package gr.hua.dit.ds.ds_lab_2024.service;

import gr.hua.dit.ds.ds_lab_2024.entities.Student;
import gr.hua.dit.ds.ds_lab_2024.repositories.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Transactional
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    @Transactional
    public Student getStudent(Integer studentId) {
        return studentRepository.findById(studentId).get();
    }

}

