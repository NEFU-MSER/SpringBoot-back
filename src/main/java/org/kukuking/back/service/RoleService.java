package org.kukuking.back.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.Role;
import org.kukuking.back.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAllRoles();
    }

    public List<Role> findByDepartmentsId(List<String> departmentsId){
        return roleRepository.findAllByDepartmentIdIn(departmentsId);
    }

    public Role findById(String id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Role department) {
        try {
            roleRepository.save(department);
        }catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void update(Role department){
        try {
            roleRepository.save(department);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void delete(String id) {
        try {
            roleRepository.deleteById(id);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void delete(List<String> idList){
        try {
            roleRepository.deleteAllById(idList);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
