package org.kukuking.back.mapper;

import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.dox.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Slf4j
public class UserResultSetExtractor implements ResultSetExtractor<User> {
    @Override
    public User extractData(ResultSet rs) throws SQLException, DataAccessException {
        User user = null;
        while (rs.next()) {
            if (user == null) {
                user = User.builder()
                        .account(rs.getString("account"))
                        .name(rs.getString("name"))
                        .email(rs.getString("email"))
                        .password(rs.getString("password"))
                        .gender(rs.getInt("gender"))
                        .createTime(rs.getObject("u.create_time", LocalDateTime.class))
                        .updateTime(rs.getObject("u.update_time", LocalDateTime.class))
                        .build();
            }
        }
        return user;
    }
}
