package org.kukuking.back.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.Role;
import org.kukuking.back.component.ResultVO;
import org.kukuking.back.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/role/")
@RequiredArgsConstructor
@CrossOrigin
public class RoleController {
    private final RoleService roleService;

    @PostMapping("/getAll")
    public ResultVO getAll() {
        try {
            List<Role> roles = roleService.findAll();
            return ResultVO.success(Map.of("roles", roles));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultVO.error(405, e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResultVO save(@RequestBody Role role) {
        try {
            role.setId(null);
            roleService.save(role);
            List<Role> roles = roleService.findAll();
            return ResultVO.success(Map.of("roles", roles));
        }catch (Exception e) {
            return ResultVO.error(405, e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResultVO update(@RequestBody Role role) {
        try {
            roleService.update(role);
            List<Role> roles = roleService.findAll();
            return ResultVO.success(Map.of("roles", roles));
        }catch (Exception e) {
            return ResultVO.error(405, e.getMessage());
        }
    }

    @PostMapping("/delete")
    public ResultVO delete(@RequestBody String id) {
        try {
            roleService.delete(id);
            List<Role> roles = roleService.findAll();
            return ResultVO.success(Map.of("roles", roles));
        }catch (Exception e) {
            return ResultVO.error(405, e.getMessage());
        }
    }

    @PostMapping("/deleteAll")
    public ResultVO deleteAll(@RequestBody List<String> idList) {
        try {
            roleService.delete(idList);
            List<Role> roles = roleService.findAll();
            return ResultVO.success(Map.of("roles", roles));
        }catch (Exception e) {
            return ResultVO.error(405, e.getMessage());
        }
    }
}
