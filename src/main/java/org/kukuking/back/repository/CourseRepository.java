package org.kukuking.back.repository;

import org.kukuking.back.DO.Course;
import org.kukuking.back.mapper.CourseResultSetExtractor;
import org.kukuking.back.mapper.CourseRowMapper;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CourseRepository extends CrudRepository<Course, String> {
    @Query(value = "select * from course as c where c.id = :id",
            rowMapperClass = CourseRowMapper.class)
    Course findCourseById(String id);

    @Query(value = "select * from course as c where c.user_id = :userId",
            resultSetExtractorClass = CourseResultSetExtractor.class)
    List<Course> findCoursesByUserId(String userId);

    @Query(value = "select * from course as c join user as u on c.user_id = u.id where u.account = :account",
            resultSetExtractorClass = CourseResultSetExtractor.class)
    List<Course> findCoursesByUserAccount(String account);

    @Query(value = "select * from course",
    resultSetExtractorClass = CourseResultSetExtractor.class)
    List<Course> findCourses();
}
