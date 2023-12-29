package com.svat.svatprojectfinal.controller;

import com.svat.svatprojectfinal.Entity.Student;
import com.svat.svatprojectfinal.Entity.User;
import com.svat.svatprojectfinal.repository.StudentRepositry;
import com.svat.svatprojectfinal.repository.UserRepositry;
import com.svat.svatprojectfinal.services.CustomStudentDetailsService;
import com.svat.svatprojectfinal.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    private UserRepositry userRepositry;

    @Autowired
    private StudentRepositry studentRepositry;
    @Autowired
    CustomStudentDetailsService customStudentDetailsService ;
    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @GetMapping("/login")
    public String login(Model model,@RequestParam(name = "error", required = false) String error) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            if (error != null) {
                model.addAttribute("error", "Wrong email or password");
                return "index"; // Return the same view with the error message easy in
            }
            return "redirect:";
        }

        return "home";
    }

    @GetMapping("/index")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String signUp(Model model) {
        model.addAttribute("user", new User());
        return "signUp";
    }

    @PostMapping("/process_register")
    public String processRegistration( Model model, @ModelAttribute("user") User user, BindingResult result) {

        if (userRepositry.findByEmail(user.getEmail()) != null) {
            model.addAttribute("emailError", "this Email already has an account");
            return "signUp";
        }
        if (result.hasErrors()) {
            return "signUp";
        }

        // Register the user
        customUserDetailsService.registerUser(user);

        return "index";
    }

    @GetMapping("/home")
    public String listOfStudents(Model model ,@ModelAttribute("student") Student student, BindingResult result) {
        List<Student> studentList = studentRepositry.findAll();
        model.addAttribute("studentList", studentList);

        return "home";
    }
    @GetMapping("/logout")
    public String logout(Model model) {
        // Your logout logic here

        model.addAttribute("logout", true);

        return "redirect:index";
    }

    @PostMapping("/home/add_student")
    public String processAddStudent(Model model, @ModelAttribute("student") Student student, BindingResult result) {

        if (studentRepositry.findByEmail(student.getEmail()) != null) {
            model.addAttribute("emailError", "This Email already belongs to a student");
            return "redirect:";
        } else if (studentRepositry.findByStudentNumber(student.getStudentNumber()) != null) {
            model.addAttribute("studentNumberError", "This Student number is already existing");
            return "redirect:";
        } else {
            customStudentDetailsService.addStudent(student);
            return "redirect:/home";
        }
    }
}
