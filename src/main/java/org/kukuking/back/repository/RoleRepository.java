package org.kukuking.back.repository;

import org.kukuking.back.DO.Role;
import org.kukuking.back.extractor.RoleResultSetExtractor;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RoleRepository extends CrudRepository<Role, String> {
    @Query(value = "select * from role as r",
            resultSetExtractorClass = RoleResultSetExtractor.class)
    List<Role> findAllRoles();

    List<Role> findAllByDepartmentIdIn(List<String> departmentIds);
}
