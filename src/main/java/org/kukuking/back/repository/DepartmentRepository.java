package org.kukuking.back.repository;

import org.kukuking.back.DO.Department;
import org.kukuking.back.mapper.DepartmentResultSetExtractor;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DepartmentRepository extends CrudRepository<Department, String> {
    @Query(value = "select * from department as d",
            resultSetExtractorClass = DepartmentResultSetExtractor.class)
    List<Department> findAllDepartments();

    @Override
    void deleteAllById(Iterable<? extends String> idList);
}
