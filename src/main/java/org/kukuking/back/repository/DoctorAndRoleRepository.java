package org.kukuking.back.repository;

import org.kukuking.back.DTO.DoctorAndRole;
import org.kukuking.back.extractor.DoctorAndRoleResultSetExtractor;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DoctorAndRoleRepository extends CrudRepository<DoctorAndRole, String> {
    @Query(value = """
            select d.name,d.id,d.email,d.phone,r.id,r.name,r.department_id,r.expenses from doctor as d
            join role as r on d.role_id = r.id
            where r.department_id = :departmentId
            """, resultSetExtractorClass = DoctorAndRoleResultSetExtractor.class)
    List<DoctorAndRole> findAllUserAndRoles(String departmentId);
}
