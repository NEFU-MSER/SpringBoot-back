package org.kukuking.back.mapper;

import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UserResultSetExtractor implements ResultSetExtractor<List<User>> {
    @Override
    public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            users.add(User.builder()
                    .id(rs.getString("id"))
                    .account(rs.getString("account"))
                    .name(rs.getString("name"))
                    .idCard(rs.getString("id_card"))
                    .email(rs.getString("email"))
                    .password(rs.getString("password"))
                    .gender(rs.getInt("gender"))
                    .createTime(rs.getObject("u.create_time", LocalDateTime.class))
                    .updateTime(rs.getObject("u.update_time", LocalDateTime.class))
                    .build());
        }
        return users;
    }
}
