package gr.hua.dit.ds.ds_lab_2024.config;

import gr.hua.dit.ds.ds_lab_2024.entities.*;
import gr.hua.dit.ds.ds_lab_2024.repositories.CourseRepository;
import gr.hua.dit.ds.ds_lab_2024.repositories.RoleRepository;
import gr.hua.dit.ds.ds_lab_2024.repositories.TeacherRepository;
import gr.hua.dit.ds.ds_lab_2024.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class InitialData {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitialData.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    public InitialData(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder,
                       TeacherRepository teacherRepository,
                       CourseRepository courseRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional
    @PostConstruct
    public void populateDBWithInitialData() {
//        this.roleRepository.deleteAll();
//        this.userRepository.deleteAll();
//        this.teacherRepository.deleteAll();
//        this.courseRepository.deleteAll();

        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleManager = new Role("ROLE_MANAGER");
        Role roleUser = new Role("ROLE_USER");

        roleAdmin = this.roleRepository.updateOrInsert(roleAdmin);
        roleManager = this.roleRepository.updateOrInsert(roleManager);
        roleUser = this.roleRepository.updateOrInsert(roleUser);

        var existing = this.userRepository.findByUsername("admin").orElse(null);
        if (existing == null) {
            LOGGER.info("Creating User 'admin'");
            User userAdmin = new User();
            userAdmin.setUsername("admin");
            userAdmin.setPassword(this.passwordEncoder.encode("admin"));
            userAdmin.setEmail("admin@hua.gr");
            userAdmin.setRoles(Set.of(roleAdmin, roleManager, roleUser));
            this.userRepository.save(userAdmin);
        }

        existing = this.userRepository.findByUsername("manager").orElse(null);
        if (existing == null) {
            LOGGER.info("Creating User 'manager'");
            User userUser = new User();
            userUser.setUsername("manager");
            userUser.setPassword(this.passwordEncoder.encode("manager"));
            userUser.setEmail("manager@hua.gr");
            userUser.setRoles(Set.of(roleManager, roleUser));
            this.userRepository.save(userUser);
        }

        existing = this.userRepository.findByUsername("user").orElse(null);
        if (existing == null) {
            LOGGER.info("Creating User 'user'");
            User userUser = new User();
            userUser.setUsername("user");
            userUser.setPassword(this.passwordEncoder.encode("user"));
            userUser.setEmail("user@hua.gr");
            userUser.setRoles(Set.of(roleUser));
            this.userRepository.save(userUser);
        }

        var defaultTeacher = this.teacherRepository.findByEmail("tsadimas@hua.gr").orElse(null);
        if (defaultTeacher == null) {
            defaultTeacher = new Teacher();
            defaultTeacher.setTitle("Prof");
            defaultTeacher.setFirstName("Argyris");
            defaultTeacher.setLastName("Tsadimas");
            defaultTeacher.setEmail("tsadimas@hua.gr");
            defaultTeacher = this.teacherRepository.save(defaultTeacher);
        }

        var defaultCourse = this.courseRepository.findByTitle("Distributed Systems").orElse(null);
        if (defaultCourse == null) {
            defaultCourse = new Course();
            defaultCourse.setTitle("Distributed Systems");
            defaultCourse.setSemester(Semester.C);
            // defaultCourse.setTeacher(defaultTeacher); // TODO Throws exception.
            defaultCourse = this.courseRepository.save(defaultCourse);
        }
    }
}
