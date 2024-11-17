package gr.hua.dit.ds.ds_lab_2024.repositories;

import gr.hua.dit.ds.ds_lab_2024.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
}
