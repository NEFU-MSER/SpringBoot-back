package org.kukuking.back.repository;

import org.kukuking.back.DO.UserRole;
import org.kukuking.back.mapper.UserRoleResultSetExtractor;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, String> {
    @Query(value = "select * from user_role as ur",
            resultSetExtractorClass = UserRoleResultSetExtractor.class)
    List<UserRole> findAllUserRoles();
}
