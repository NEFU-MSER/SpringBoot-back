package org.kukuking.back.DTO;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.Department;
import org.kukuking.back.DO.Role;
import org.kukuking.back.DO.User;

@Slf4j
@AllArgsConstructor
public class UserAndRole {
    private User user;
    private Role role;
    private Department department;
}
