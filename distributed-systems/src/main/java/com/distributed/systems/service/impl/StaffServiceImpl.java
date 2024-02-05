package com.distributed.systems.service.impl;

import com.distributed.systems.dto.StaffDto;
import com.distributed.systems.dto.UserDto;
import com.distributed.systems.entity.Staff;
import com.distributed.systems.entity.User;
import com.distributed.systems.repository.StaffRepository;
import com.distributed.systems.service.StaffService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StaffServiceImpl implements StaffService {

    private StaffRepository staffRepository;
    private PasswordEncoder passwordEncoder;

    public StaffServiceImpl(StaffRepository staffRepository, PasswordEncoder passwordEncoder) {
        this.staffRepository = staffRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveStaff(StaffDto staffDto) {
        Staff staff = new Staff();
        staff.setStaffId(staffDto.getStaffId());
        staff.setFirstName(staffDto.getFirstName());
        staff.setLastName(staffDto.getLastName());
        staff.setEmail(staffDto.getEmail());
        staff.setPassword((passwordEncoder.encode((staffDto.getPassword()))));

        staffRepository.save(staff);
    }

    @Override
    public Staff findStaffByEmail(String email) {
        return staffRepository.findByEmail(email);
    }

    @Override
    public List<StaffDto> findAllStaff() {
        List<Staff> stafff = staffRepository.findAll();
        return stafff.stream().map((staff) -> mapToStaffDto(staff)).collect(Collectors.toList());
    }

    private StaffDto mapToStaffDto(Staff staff){
        StaffDto staffDto = new StaffDto();
        staffDto.setStaffId(staff.getStaffId());
        staffDto.setFirstName(staff.getFirstName());
        staffDto.setLastName(staff.getLastName());
        staffDto.setEmail(staff.getEmail());
        staffDto.setPassword(staff.getPassword());
        return staffDto;
    }
}

