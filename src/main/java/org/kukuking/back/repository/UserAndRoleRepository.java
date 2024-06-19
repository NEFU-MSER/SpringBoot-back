package org.kukuking.back.repository;

import org.kukuking.back.DO.UserRole;
import org.kukuking.back.DTO.UserAndRole;
import org.kukuking.back.mapper.UserAndRoleResultSetExtractor;
import org.kukuking.back.mapper.UserRoleResultSetExtractor;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserAndRoleRepository extends CrudRepository<UserAndRole, String> {
    @Query(value = """
            select u.id,u.name,u.email,r.id,r.name,d.id,d.name from user_role as ur
            join user as u on ur.user_id  = u.id
            join role as r on r.id = ur.role_id
            join department as d on d.id = r.department_id
            """, resultSetExtractorClass = UserAndRoleResultSetExtractor.class)
    List<UserAndRole> findAllUserAndRoles();
}
