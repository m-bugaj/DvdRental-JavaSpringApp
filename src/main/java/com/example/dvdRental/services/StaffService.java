package com.example.dvdRental.services;

import com.example.dvdRental.api.model.StaffDTO;

import java.util.List;
import java.util.Optional;

public interface StaffService {
    List<StaffDTO> findAllStaffs();

    Optional<StaffDTO> findStaffById(Integer staffId);

    StaffDTO createNewStaff(StaffDTO staffDTO);

    StaffDTO updateStaff(Integer staffId, StaffDTO staffDTO);

    void deleteStaff(Integer staffId);
}
