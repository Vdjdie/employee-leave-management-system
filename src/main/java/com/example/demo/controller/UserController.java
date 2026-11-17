package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Change password
    @PutMapping("/{id}/password")
    public String changePassword(
            @PathVariable Long id,
            @RequestBody User passwordData) {

        User user =
                userRepository.findById(id).orElse(null);

        if (user == null) {
            return "User not found";
        }

        if (!user.getPassword().equals(
                passwordData.getCurrentPassword())) {

            return "Current password is incorrect";
        }

        user.setPassword(
                passwordData.getPassword()
        );

        userRepository.save(user);

        return "Password changed successfully";
    }
}