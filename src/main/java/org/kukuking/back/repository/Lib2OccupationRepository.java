package org.kukuking.back.repository;

import org.kukuking.back.DTO.Lib2Occupations;
import org.kukuking.back.mapper.Lib2OccupationResultSetExtractor;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface Lib2OccupationRepository extends CrudRepository<Lib2Occupations, String> {
    @Query(value = """
            select o.*,
            c.id as 'course_id',c.name as 'course_name',
            l.id as 'lib_id',l.name as 'lib_name',l.type as 'lib_type'
            from occupation as o
            join course as c on o.course_id = c.id
            join lib as l on o.lib_id = l.id
            where o.lib_id = :libId
            """,
            resultSetExtractorClass = Lib2OccupationResultSetExtractor.class)
    Lib2Occupations findLibOccupation(String libId);
}
