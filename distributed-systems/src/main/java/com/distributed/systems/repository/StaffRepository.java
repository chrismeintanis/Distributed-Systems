package com.distributed.systems.repository;

import com.distributed.systems.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
        Staff findByEmail(String email);
}
