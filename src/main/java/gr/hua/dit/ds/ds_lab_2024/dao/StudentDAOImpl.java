package gr.hua.dit.ds.ds_lab_2024.dao;


import gr.hua.dit.ds.ds_lab_2024.entities.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional
    public List<Student> getStudents() {
        TypedQuery<Student> query = entityManager.createQuery("from Student", Student.class);
        return query.getResultList();
    }

    @Override
    public Student getStudent(Integer student_id) {
        return entityManager.find(Student.class, student_id);
    }

    @Override
    @Transactional
    public void saveStudent(Student student) {
        System.out.println("student "+ student.getId());
        if (student.getId() == null) {
            entityManager.persist(student);
        } else {
            entityManager.merge(student);
        }
    }

    @Override
    @Transactional
    public void deleteStudent(Integer student_id) {
        System.out.println("Deleting student with id: " + student_id);
        entityManager.remove(entityManager.find(Student.class, student_id));
    }
}
