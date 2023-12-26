package com.svat.svatprojectfinal.repository;

import com.svat.svatprojectfinal.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepositry extends JpaRepository<User,Long> {
 @Query("SELECT u FROM User u where u.email=?1")
 User findByEmail(String email);
}
