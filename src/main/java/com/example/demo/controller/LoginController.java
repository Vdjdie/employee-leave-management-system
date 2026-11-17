package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final UserRepository userRepository;


    // Constructor injection
    public LoginController(
            UserRepository userRepository) {

        this.userRepository = userRepository;
    }


    // =====================================================
    // LOGIN
    // =====================================================

    @PostMapping
    public Map<String, Object> login(
            @RequestBody User loginUser) {

        // Username वापरून database मधून user शोधतो
        User user =
                userRepository.findByUsername(
                        loginUser.getUsername()
                );


        // Username आणि password check
        if (user != null &&
            user.getPassword().equals(
                    loginUser.getPassword())) {


            // Login response तयार करतो
            Map<String, Object> response =
                    new HashMap<>();


            response.put("success", true);

            response.put("id", user.getId());

            response.put(
                    "employeeId",
                    user.getEmployeeId()
            );

            response.put(
                    "username",
                    user.getUsername()
            );

            response.put(
                    "role",
                    user.getRole()
            );

            response.put(
                    "name",
                    user.getName()
            );

            response.put(
                    "email",
                    user.getEmail()
            );

            response.put(
                    "department",
                    user.getDepartment()
            );


            // Password response मध्ये पाठवत नाही
            return response;
        }


        // Invalid login
        Map<String, Object> response =
                new HashMap<>();

        response.put("success", false);

        return response;
    }
}