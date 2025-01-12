package gr.hua.dit.ds.ds_lab_2024.service;

import gr.hua.dit.ds.ds_lab_2024.entities.Student;
import gr.hua.dit.ds.ds_lab_2024.repositories.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Transactional
    public Student saveStudent(Student student) {
        return this.studentRepository.save(student);
    }

    @Transactional
    public Optional<Student> getStudent(Integer studentId) {
        return studentRepository.findById(studentId);
    }

    @Transactional
    public boolean deleteStudentById(final Integer studentId) {
        final Optional<Student> studentOptional = this.studentRepository.findById(studentId);
        if (studentOptional.isEmpty()) {
            return false;
        }
        this.studentRepository.deleteById(studentId);
        return true;
    }
}
