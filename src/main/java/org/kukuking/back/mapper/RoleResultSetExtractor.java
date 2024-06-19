package org.kukuking.back.mapper;

import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.Role;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class RoleResultSetExtractor implements ResultSetExtractor<List<Role>> {
    @Override
    public List<Role> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Role> roles = new ArrayList<>();
        while (rs.next()) {
            roles.add(Role.builder()
                    .id(rs.getString("id"))
                    .name(rs.getString("name"))
                    .departmentId(rs.getString("department_id"))
                    .expenses(rs.getDouble("expenses"))
                    .createTime(rs.getObject("create_time", LocalDateTime.class))
                    .updateTime(rs.getObject("update_time", LocalDateTime.class))
                    .build());
        }
        return roles;
    }
}
