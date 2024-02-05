package com.distributed.systems.service;

import com.distributed.systems.dto.StaffDto;
import com.distributed.systems.entity.Staff;
import java.util.List;

public interface StaffService {
    void saveStaff(StaffDto staffDto);

    Staff findStaffByEmail(String email);

    List<StaffDto> findAllStaff();
}
