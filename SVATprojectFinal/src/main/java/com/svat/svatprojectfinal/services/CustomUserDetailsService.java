package com.svat.svatprojectfinal.services;

import com.svat.svatprojectfinal.securty.CustomUserDetails;
import com.svat.svatprojectfinal.Entity.User;
import com.svat.svatprojectfinal.repository.UserRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepositry userRepositry;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user =userRepositry.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("No such User");
        }
        return new CustomUserDetails(user);
    }
    public void registerUser(User user) {
        // Encode the password before saving it to the database
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // Save the user to the database
        userRepositry.save(user);
    }
}
