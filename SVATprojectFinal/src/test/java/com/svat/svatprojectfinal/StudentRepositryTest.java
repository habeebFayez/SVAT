package com.svat.svatprojectfinal;

import com.svat.svatprojectfinal.Entity.Student;
import com.svat.svatprojectfinal.Entity.User;
import com.svat.svatprojectfinal.repository.StudentRepositry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class StudentRepositryTest {
    @Autowired
    private StudentRepositry studentRepositry;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void creatNewUser(){
        Student student= new Student();
        student.setEmail("habeeb.fayez@email.com");
        student.setFirstName("Habeeb");
        student.setLastName("Fayez");
        student.setStudentNumber("H210209439");
        student.setDepartment("Software ENG");


        Student savedUser = studentRepositry.save(student);
        Student existUser = testEntityManager.find(Student.class , savedUser.getId());
//        as assertTrue
        assertThat(existUser.getEmail()).isEqualTo(student.getEmail());


    }
    @Test
    public void testFindUserByEmail(){
        String studentNumber = "H210209439";

        Student student= studentRepositry.findByStudentNumber(studentNumber);


        assertThat(student).isNotNull();


    }


}
