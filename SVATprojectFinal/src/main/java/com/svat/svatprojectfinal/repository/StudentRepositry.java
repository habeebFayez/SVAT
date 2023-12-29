package com.svat.svatprojectfinal.repository;

import com.svat.svatprojectfinal.Entity.Student;
import com.svat.svatprojectfinal.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepositry extends JpaRepository<Student,Long> {
 @Query("SELECT s FROM Student s where s.studentNumber=?1")
 Student findByStudentNumber(String studentNumber);

 @Query("SELECT s FROM Student s where s.email=?1")
 Student findByEmail(String email);
}
