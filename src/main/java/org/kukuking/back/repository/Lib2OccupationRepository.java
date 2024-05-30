package org.kukuking.back.repository;

import org.kukuking.back.DTO.Lib2Occupations;
import org.kukuking.back.mapper.Lib2OccupationResultSetExtractor;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface Lib2OccupationRepository extends CrudRepository<Lib2Occupations, String> {
    @Query(value = """
            select o.*,c.name as 'course_name',l.name as 'lib_name'
            from occupation as o
            join course as c on o.course_id = c.id
            join lib as l on o.lib_id = l.id
            where o.lib_id = :libId
            """,
            resultSetExtractorClass = Lib2OccupationResultSetExtractor.class)
    Lib2Occupations findLibOccupation(String libId);
}
