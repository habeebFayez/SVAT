package com.svat.svatprojectfinal;

import com.svat.svatprojectfinal.Entity.User;
import com.svat.svatprojectfinal.repository.UserRepositry;
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
public class UserRepositryTest {
    @Autowired
    private UserRepositry userRepositry;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void creatNewUser(){
        User user= new User();
        user.setEmail("habeebfayez@email.com");
        user.setFirstName("Habeeb");
        user.setLastName("Fayez");
        user.setPassword("nnbbvvcc");

        User savedUser = userRepositry.save(user);
        User existUser = testEntityManager.find(User.class , savedUser.getId());
//        as assertTrue
        assertThat(existUser.getEmail()).isEqualTo(user.getEmail());


    }
    @Test
    public void testFindUserByEmail(){
        String emial = "habeeb.fayez@email.com";

        User user= userRepositry.findByEmail(emial);


        assertThat(user).isNotNull();


    }


}
