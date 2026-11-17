package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Leave;
//inter//
public interface LeaveRepository
        extends JpaRepository<Leave, Long> {

    // Specific employee चे leaves मिळवण्यासाठी
    List<Leave> findByEmployeeId(Long employeeId);
}