package org.kukuking.back.repository;

import org.kukuking.back.DO.Course;
import org.kukuking.back.DO.Occupation;
import org.kukuking.back.mapper.CourseResultSetExtractor;
import org.kukuking.back.mapper.CourseRowMapper;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OccupationRepository extends CrudRepository<Occupation, String> {
}
