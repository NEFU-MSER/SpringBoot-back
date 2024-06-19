package org.kukuking.back.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.Department;
import org.kukuking.back.DO.Role;
import org.kukuking.back.component.ResultVO;
import org.kukuking.back.service.DepartmentService;
import org.kukuking.back.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/department/")
@RequiredArgsConstructor
@CrossOrigin
public class DepartmentController {
    private final DepartmentService departmentService;
    private final RoleService roleService;

    @PostMapping("/getAll")
    public ResultVO getAll() {
        try {
            List<Department> departments = departmentService.findAll();
            return ResultVO.success(Map.of("departments", departments));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultVO.error(405, e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResultVO save(@RequestBody Department department) {
        try {
            department.setId(null);
            departmentService.save(department);
            List<Department> departments = departmentService.findAll();
            return ResultVO.success(Map.of("departments", departments));
        }catch (Exception e) {
            return ResultVO.error(405, e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResultVO update(@RequestBody Department department) {
        try {
            departmentService.update(department);
            List<Department> departments = departmentService.findAll();
            return ResultVO.success(Map.of("departments", departments));
        }catch (Exception e) {
            return ResultVO.error(405, e.getMessage());
        }
    }

    @PostMapping("/delete")
    public ResultVO delete(@RequestBody String id) {
        try {
            departmentService.delete(id);
            List<Department> departments = departmentService.findAll();
            return ResultVO.success(Map.of("departments", departments));
        }catch (Exception e) {
            return ResultVO.error(405, e.getMessage());
        }
    }

    @PostMapping("/deleteAll")
    public ResultVO deleteAll(@RequestBody List<String> idList) {
        try {
            departmentService.delete(idList);
            List<Role> roles = roleService.findByDepartmentsId(idList);
            if(!roles.isEmpty()){
                return ResultVO.error(405,"科室有下辖职位，不能删除");
            }
            List<Department> departments = departmentService.findAll();
            return ResultVO.success(Map.of("departments", departments));
        }catch (Exception e) {
            return ResultVO.error(405, e.getMessage());
        }
    }
}
