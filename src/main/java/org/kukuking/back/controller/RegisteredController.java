package org.kukuking.back.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.Department;
import org.kukuking.back.DTO.DoctorAndRole;
import org.kukuking.back.component.ResultVO;
import org.kukuking.back.service.DoctorAndRoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/registered/")
@RequiredArgsConstructor
@CrossOrigin
public class RegisteredController {
    private final DoctorAndRoleService doctorAndRoleService;

    @PostMapping("/getAll")
    public ResultVO getAll(@RequestBody Department department) {
        try {
            List<DoctorAndRole> doctorAndRoles = doctorAndRoleService.getAllByDepartment(department.getId());
            return ResultVO.success(Map.of("doctorAndRoles", doctorAndRoles));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultVO.error(405, e.getMessage());
        }
    }
}
