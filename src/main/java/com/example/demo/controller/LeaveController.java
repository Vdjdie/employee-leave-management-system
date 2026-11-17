package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Leave;
import com.example.demo.repository.LeaveRepository;

@RestController
@RequestMapping("/leaves")
public class LeaveController {

    private final LeaveRepository leaveRepository;


    // Constructor injection
    public LeaveController(
            LeaveRepository leaveRepository) {

        this.leaveRepository = leaveRepository;
    }


    // =====================================================
    // GET ALL LEAVES
    // =====================================================

    @GetMapping
    public List<Leave> getAllLeaves() {

        // Manager साठी सर्व leave requests return करतो
        return leaveRepository.findAll();
    }


    // =====================================================
    // APPLY LEAVE
    // =====================================================

    @PostMapping
    public Leave applyLeave(
            @RequestBody Leave leave) {

        // Employee ची नवीन leave save करतो
        return leaveRepository.save(leave);
    }


    // =====================================================
    // GET LEAVE BY ID
    // =====================================================

    @GetMapping("/{id}")
    public Leave getLeaveById(
            @PathVariable Long id) {

        return leaveRepository
                .findById(id)
                .orElse(null);
    }


    // =====================================================
    // GET EMPLOYEE'S OWN LEAVES
    // =====================================================

    @GetMapping("/employee/{employeeId}")
    public List<Leave> getEmployeeLeaves(
            @PathVariable Long employeeId) {

        // फक्त specific employee चे leaves database मधून घेतो
        return leaveRepository
                .findByEmployeeId(employeeId);
    }


    // =====================================================
    // APPROVE LEAVE
    // =====================================================

    @PutMapping("/{id}/approve")
    public Leave approveLeave(
            @PathVariable Long id) {

        Leave leave =
                leaveRepository
                .findById(id)
                .orElse(null);


        if (leave != null) {

            // Leave status Approved करतो
            leave.setStatus("Approved");

            return leaveRepository.save(leave);
        }


        return null;
    }


    // =====================================================
    // REJECT LEAVE
    // =====================================================

    @PutMapping("/{id}/reject")
    public Leave rejectLeave(
            @PathVariable Long id) {

        Leave leave =
                leaveRepository
                .findById(id)
                .orElse(null);


        if (leave != null) {

            // Leave status Rejected करतो
            leave.setStatus("Rejected");

            return leaveRepository.save(leave);
        }


        return null;
    }


    // =====================================================
    // DELETE LEAVE
    // =====================================================

    @DeleteMapping("/{id}")
    public String deleteLeave(
            @PathVariable Long id) {

        leaveRepository.deleteById(id);

        return "Leave deleted successfully";
    }
}