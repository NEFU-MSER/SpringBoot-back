package org.kukuking.back.mapper;

import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.Course;
import org.kukuking.back.DTO.Course2Occupations;
import org.kukuking.back.DTO.FrontOccupation;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Courses2OccupationResultSetExtractor implements ResultSetExtractor<List<Course2Occupations>> {
    @Override
    public List<Course2Occupations> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Course2Occupations course2Occupations = new Course2Occupations();
        List<Course2Occupations> courses2Occupations = new ArrayList<>();
        course2Occupations.setCourse(null);
        ArrayList<FrontOccupation> frontOccupations = new ArrayList<>();
        while (rs.next()) {
            if (course2Occupations.getCourse() == null) {
                Course course = Course.builder()
                        .id(rs.getString("id"))
                        .name(rs.getString("name"))
                        .credit(rs.getDouble("credit"))
                        .time(rs.getDouble("time"))
                        .userId(rs.getString("user_id"))
                        .build();
                course2Occupations.setCourse(course);
            }
            if (course2Occupations.getCourse() != null) {
                //新的course
                if (!course2Occupations.getCourse().getId().equals(rs.getString("course_id"))) {
                    //存储occupation
                    course2Occupations.setOccupations(frontOccupations);
                    courses2Occupations.add(course2Occupations);
                    //开新课
                    course2Occupations = new Course2Occupations();
                    frontOccupations = new ArrayList<>();
                    Course course = Course.builder()
                            .id(rs.getString("id"))
                            .name(rs.getString("name"))
                            .credit(rs.getDouble("credit"))
                            .time(rs.getDouble("time"))
                            .userId(rs.getString("user_id"))
                            .build();
                    course2Occupations.setCourse(course);
                }
            }
            FrontOccupation frontOccupation = FrontOccupation.builder()
                    .id(rs.getString("id"))
                    .courseId(rs.getString("course_id"))
                    .courseName(rs.getString("course_name"))
                    .libId(rs.getString("lib_id"))
                    .libName(rs.getString("lib_name"))
                    .week(new int[]{rs.getInt("start_week"), rs.getInt("end_week")})
                    .day(rs.getInt("day"))
                    .time(new int[]{rs.getInt("start_time"), rs.getInt("end_time")})
                    .build();
            frontOccupations.add(frontOccupation);
        }
        return courses2Occupations;
    }
}
