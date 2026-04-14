package com.company.loginapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.loginapp.model.User;
import com.company.loginapp.service.UserService;
import com.company.loginapp.util.JwtUtil;



@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/auth")
public class AuthController {
    

    @Autowired
    private UserService service;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return service.register(user);
        
    }



    

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        
        System.out.println("Login API HIT");
        User existing = service.findByUsername(user.getUsername());

        if (existing != null && encoder.matches(user.getPassword(), existing.getPassword())) {
            return jwtUtil.generateToken(user.getUsername());
        } else {
            return "Invalid Credentials";
        }
    }
}