package org.kukuking.back.mapper;

import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.Lib;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Slf4j
public class LibRowMapper implements RowMapper<Lib> {
    @Override
    public Lib mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Lib.builder()
                .id(rs.getString("id"))
                .name(rs.getString("name"))
                .type(rs.getString("type"))
                .createTime(rs.getObject("create_time", LocalDateTime.class))
                .updateTime(rs.getObject("update_time", LocalDateTime.class))
                .build();
    }
}
