package gr.hua.dit.ds.ds_lab_2024.dao;


import gr.hua.dit.ds.ds_lab_2024.entities.Student;

import java.util.List;

public interface StudentDAO {
    public List<Student> getStudents();
    public Student getStudent(Integer student_id);
    public void saveStudent(Student student);
    public void deleteStudent(Integer student_id);
}
