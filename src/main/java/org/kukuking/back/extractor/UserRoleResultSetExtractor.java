package org.kukuking.back.extractor;

import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.UserRole;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UserRoleResultSetExtractor implements ResultSetExtractor<List<UserRole>> {
    @Override
    public List<UserRole> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<UserRole> userRoles = new ArrayList<>();
        while (rs.next()) {
            userRoles.add(UserRole.builder()
                    .id(rs.getString("id"))
                    .roleId(rs.getString("role_id"))
                    .userId(rs.getString("user_id"))
                    .build());
        }
        return userRoles;
    }
}
