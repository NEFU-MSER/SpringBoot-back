package org.kukuking.back.extractor;

import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
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
                    .password("")
                    .gender(rs.getInt("gender"))
                    .build());
        }
        return users;
    }
}
