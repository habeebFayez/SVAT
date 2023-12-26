package com.svat.svatprojectfinal.repository;

import com.svat.svatprojectfinal.Entity.Student;
import com.svat.svatprojectfinal.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepositry extends JpaRepository<Student,Long> {
 @Query("SELECT u FROM Student u where u.studentNumber=?1")
 Student findByStudentNumber(String studentNumber);

}
