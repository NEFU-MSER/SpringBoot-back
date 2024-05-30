package org.kukuking.back.repository;

import org.kukuking.back.DTO.Course2Occupations;
import org.kukuking.back.mapper.Course2OccupationResultSetExtractor;
import org.kukuking.back.mapper.Courses2OccupationResultSetExtractor;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface Course2OccupationRepository extends CrudRepository<Course2Occupations, String> {
    @Query(value = """
            select o.*,c.name as 'course_name',l.name as 'lib_name'
            from occupation as o
            join course as c on o.course_id = c.id
            join lib as l on o.lib_id = l.id
            where o.course_id = :courseId
            """,
            resultSetExtractorClass = Course2OccupationResultSetExtractor.class)
    Course2Occupations findCourseOccupation(String courseId);

    @Query(value = """
            select o.*,c.name as 'course_name',l.name as 'lib_name'
            from occupation as o
            join course as c on o.course_id = c.id
            join lib as l on o.lib_id = l.id
            where o.course_id in :courseId
            order by o.course_id
            """,
            resultSetExtractorClass = Courses2OccupationResultSetExtractor.class)
    List<Course2Occupations> findCoursesOccupation(List<String> courseId);
}
