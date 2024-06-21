package org.kukuking.back.repository;

import org.kukuking.back.DO.User;
import org.kukuking.back.extractor.UserResultSetExtractor;
import org.kukuking.back.mapper.UserRowMapper;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends CrudRepository<User, String> {
    @Query(value = "select * from user as u where u.account = :account or u.id_card = :idCard",
            rowMapperClass = UserRowMapper.class)
    User findUserByAccountOrIdCard(String account, String idCard);

    @Query(value = "select * from user as u where u.account = :account",
            rowMapperClass = UserRowMapper.class)
    User findUserByAccount(String account);

    @Query(value = "select * from user as u where u.id = :id",
            rowMapperClass = UserRowMapper.class)
    User findUserById(String id);

    @Query(value = "select * from user as u where u.account = :account and u.password = :password",
            rowMapperClass = UserRowMapper.class)
    User findUserByAccountAndPassword(String account, String password);

    @Query(value = "select * from user as u where u.gender = :gender",
    resultSetExtractorClass = UserResultSetExtractor.class)
    List<User> findUsersByGender(int gender);
}
