package org.kukuking.back.mapper;

import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.Course;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CourseResultSetExtractor implements ResultSetExtractor<List<Course>> {
    @Override
    public List<Course> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Course> users = new ArrayList<>();
        while (rs.next()) {
            users.add(Course.builder()
                    .id(rs.getString("id"))
                    .name(rs.getString("name"))
                    .credit(rs.getDouble("credit"))
                    .time(rs.getDouble("time"))
                    .userId(rs.getString("user_id"))
                    .createTime(rs.getObject("create_time", LocalDateTime.class))
                    .updateTime(rs.getObject("update_time", LocalDateTime.class))
                    .build());
        }
        return users;
    }
}
