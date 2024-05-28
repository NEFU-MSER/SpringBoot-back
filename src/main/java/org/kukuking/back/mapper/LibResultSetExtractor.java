package org.kukuking.back.mapper;

import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.Lib;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class LibResultSetExtractor implements ResultSetExtractor<List<Lib>> {
    @Override
    public List<Lib> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Lib> users = new ArrayList<>();
        while (rs.next()) {
            users.add(Lib.builder()
                    .id(rs.getString("id"))
                    .name(rs.getString("name"))
                    .type(rs.getString("type"))
                    .createTime(rs.getObject("create_time", LocalDateTime.class))
                    .updateTime(rs.getObject("update_time", LocalDateTime.class))
                    .build());
        }
        return users;
    }
}
