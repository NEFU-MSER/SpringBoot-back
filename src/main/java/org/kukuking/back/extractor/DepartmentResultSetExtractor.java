package org.kukuking.back.extractor;

import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.Department;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
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
                    .build());
        }
        return departments;
    }
}
