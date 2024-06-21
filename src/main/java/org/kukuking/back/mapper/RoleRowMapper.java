package org.kukuking.back.mapper;

import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class RoleRowMapper implements RowMapper<Role> {
    @Override
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Role.builder()
                .id(rs.getString("id"))
                .name(rs.getString("name"))
                .departmentId(rs.getString("department_id"))
                .expenses(rs.getDouble("expenses"))
                .build();
    }
}
