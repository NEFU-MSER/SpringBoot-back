package org.kukuking.back.repository;

import org.kukuking.back.dox.User;
import org.kukuking.back.mapper.UserResultSetExtractor;
import org.kukuking.back.mapper.UserRowMapper;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends CrudRepository<User, String> {
    @Query(value = "select * from user where account = :account",
            rowMapperClass = UserRowMapper.class)
    User findUserByAccount(String account);

    @Query(value = "select * from user where account = :account and password = :password",
            rowMapperClass = UserRowMapper.class)
    User findUserByAccountAndPassword(String account, String password);
}
