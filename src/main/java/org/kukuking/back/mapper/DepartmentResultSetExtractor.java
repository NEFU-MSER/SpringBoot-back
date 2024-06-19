package org.kukuking.back.mapper;

import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.Department;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DepartmentResultSetExtractor implements ResultSetExtractor<List<Department>> {
    @Override
    public List<Department> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Department> departments = new ArrayList<>();
        while (rs.next()) {
            departments.add(Department.builder()
                    .id(rs.getString("id"))
                    .name(rs.getString("name"))
                    .parentId(rs.getString("parent_id"))
                    .description(rs.getString("description"))
                    .createTime(rs.getObject("create_time", LocalDateTime.class))
                    .updateTime(rs.getObject("update_time", LocalDateTime.class))
                    .build());
        }
        return departments;
    }
}
