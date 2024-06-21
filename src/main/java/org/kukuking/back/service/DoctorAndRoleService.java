package org.kukuking.back.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DTO.DoctorAndRole;
import org.kukuking.back.repository.DoctorAndRoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DoctorAndRoleService {
    private final DoctorAndRoleRepository doctorAndRoleRepository;

    public List<DoctorAndRole> getAllByDepartment(String departmentId) {
        try {
            return doctorAndRoleRepository.findAllUserAndRoles(departmentId);
        }catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
