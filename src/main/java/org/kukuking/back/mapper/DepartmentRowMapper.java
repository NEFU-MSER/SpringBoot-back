package org.kukuking.back.mapper;

import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.Department;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class DepartmentRowMapper implements RowMapper<Department> {
    @Override
    public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Department.builder()
                .id(rs.getString("id"))
                .name(rs.getString("name"))
                .parentId(rs.getString("parent_id"))
                .description(rs.getString("description"))
                .build();
    }
}

