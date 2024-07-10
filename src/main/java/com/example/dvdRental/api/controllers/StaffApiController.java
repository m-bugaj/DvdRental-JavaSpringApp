package com.example.dvdRental.api.controllers;

import com.example.dvdRental.api.model.StaffDTO;
import com.example.dvdRental.services.StaffService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/staff")
public class StaffApiController {
    private final StaffService staffService;

    public StaffApiController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping
    public List<StaffDTO> findAllStaffes() {
        return staffService.findAllStaffs();
    }

    @GetMapping("/{id}")
    public Optional<StaffDTO> findStaffById(@PathVariable("id") Integer staffId) {
        return staffService.findStaffById(staffId);
    }

    @PostMapping
    public ResponseEntity<StaffDTO> createNewStaff(@RequestBody StaffDTO staffDTO) {
        return new ResponseEntity<StaffDTO>(staffService.createNewStaff(staffDTO), HttpStatus.CREATED);
    }
}
