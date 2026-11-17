
package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Employee;
import com.example.demo.entity.User;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    public EmployeeController(
            EmployeeRepository employeeRepository,
            UserRepository userRepository) {

        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
    }


    // =====================================================
    // GET ALL EMPLOYEES
    // =====================================================
 ///poly//
    @GetMapping
    public List<Employee> getAllEmployees() {

        return employeeRepository.findAll();
    }


    // =====================================================
    // ADD EMPLOYEE
    // =====================================================

    @PostMapping
    public Employee addEmployee(
            @RequestBody Employee employee) {

        return employeeRepository.save(employee);
    }


    // =====================================================
    // GET EMPLOYEE BY ID
    // =====================================================

    @GetMapping("/{id}")
    public Employee getEmployeeById(
            @PathVariable Long id) {

        return employeeRepository
                .findById(id)
                .orElse(null);
    }


    // =====================================================
    // UPDATE EMPLOYEE
    // =====================================================

    @PutMapping("/{id}")
    public Employee updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee updatedEmployee) {

        Employee employee =
                employeeRepository
                .findById(id)
                .orElse(null);

        if (employee != null) {

            employee.setEmployeeCode(
                    updatedEmployee.getEmployeeCode());

            employee.setName(
                    updatedEmployee.getName());

            employee.setEmail(
                    updatedEmployee.getEmail());

            employee.setPhone(
                    updatedEmployee.getPhone());

            employee.setDepartment(
                    updatedEmployee.getDepartment());

            employee.setDesignation(
                    updatedEmployee.getDesignation());

            employee.setJoiningDate(
                    updatedEmployee.getJoiningDate());

            return employeeRepository.save(employee);
        }

        return null;
    }


    // =====================================================
    // DELETE EMPLOYEE
    // =====================================================

    @DeleteMapping("/{id}")
    public String deleteEmployee(
            @PathVariable Long id) {

        employeeRepository.deleteById(id);

        return "Employee deleted successfully";
    }


    // =====================================================
    // CREATE EMPLOYEE LOGIN
    // =====================================================

    @PostMapping("/{employeeId}/create-login")
    public String createEmployeeLogin(
            @PathVariable Long employeeId,
            @RequestBody User loginData) {

        // Employee शोधतो
        Employee employee =
                employeeRepository
                .findById(employeeId)
                .orElse(null);

        if (employee == null) {

            return "Employee not found";
        }


        // Username आधीपासून आहे का ते check करतो
        User existingUser =
                userRepository
                .findByUsername(
                        loginData.getUsername()
                );

        if (existingUser != null) {

            return "Username already exists";
        }


        // नवीन User तयार करतो
        User user = new User();


        // Manager ने दिलेला username
        user.setUsername(
                loginData.getUsername()
        );


        // Manager ने दिलेला password
        user.setPassword(
                loginData.getPassword()
        );


        // Employee role automatically
        user.setRole("EMPLOYEE");


        // Employee information
        user.setName(
                employee.getName()
        );

        user.setEmail(
                employee.getEmail()
        );

        user.setDepartment(
                employee.getDepartment()
        );


        // Employee table मधला ID
        user.setEmployeeId(
                employee.getId()
        );


        // User table मध्ये save
        userRepository.save(user);


        return "Employee login created successfully";
    }
}

