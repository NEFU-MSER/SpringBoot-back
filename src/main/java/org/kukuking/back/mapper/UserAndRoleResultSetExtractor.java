package org.kukuking.back.mapper;

import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.Department;
import org.kukuking.back.DO.Role;
import org.kukuking.back.DO.User;
import org.kukuking.back.DTO.UserAndRole;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UserAndRoleResultSetExtractor implements ResultSetExtractor<List<UserAndRole>> {
    @Override
    public List<UserAndRole> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<UserAndRole> userAndRoles = new ArrayList<>();
        while (rs.next()) {
            User user = User.builder()
                    .id(rs.getString("u.id"))
                    .name(rs.getString("u.name"))
                    .email(rs.getString("u.email"))
                    .build();
            Role role = Role.builder()
                    .id(rs.getString("r.role"))
                    .name(rs.getString("r.name"))
                    .build();
            Department department = Department.builder()
                    .id(rs.getString("d.id"))
                    .name(rs.getString("d.name"))
                    .build();
            userAndRoles.add(new UserAndRole(user, role, department));
        }
        return userAndRoles;
    }
}
