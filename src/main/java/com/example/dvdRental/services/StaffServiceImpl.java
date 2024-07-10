package com.example.dvdRental.services;

import com.example.dvdRental.api.model.StaffDTO;
import com.example.dvdRental.converters.AddressConverter;
import com.example.dvdRental.converters.StaffConverter;
import com.example.dvdRental.model.Address;
import com.example.dvdRental.model.Staff;
import com.example.dvdRental.repositories.StaffRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StaffServiceImpl implements StaffService {
    private final StaffRepository staffRepository;

    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }


    @Override
    public List<StaffDTO> findAllStaffs() {
        return staffRepository
                .findAll()
                .stream()
                .map(StaffConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<StaffDTO> findStaffById(Integer staffId) {
        Optional<Staff> optionalStaff = staffRepository.findById(staffId);

        if (optionalStaff.isPresent()) return optionalStaff.map(StaffConverter::toDTO);

        return Optional.empty();
    }

    @Override
    public StaffDTO createNewStaff(StaffDTO staffDTO) {
        Staff staff = StaffConverter.toEntity(staffDTO);
        Staff savedStaff = staffRepository.save(staff);

        return StaffConverter.toDTO(savedStaff);
    }

    @Override
    public StaffDTO updateStaff(Integer staffId, StaffDTO staffDTO) {
        return null;
    }

    @Override
    public void deleteStaff(Integer staffId) {

    }
}
