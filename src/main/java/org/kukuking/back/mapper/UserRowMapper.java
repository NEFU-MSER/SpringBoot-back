package org.kukuking.back.mapper;

import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return User.builder()
                .id(rs.getString("id"))
                .account(rs.getString("account"))
                .name(rs.getString("name"))
                .idCard(rs.getString("id_card"))
                .email(rs.getString("email"))
                .password(rs.getString("password"))
                .gender(rs.getInt("gender"))
                .build();
    }
}
