package org.kukuking.back.mapper;

import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.Lib;
import org.kukuking.back.DTO.Lib2Occupations;
import org.kukuking.back.DTO.FrontOccupation;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Slf4j
public class Lib2OccupationResultSetExtractor implements ResultSetExtractor<Lib2Occupations> {
    @Override
    public Lib2Occupations extractData(ResultSet rs) throws SQLException, DataAccessException {
        Lib2Occupations lib2Occupations = new Lib2Occupations();
        lib2Occupations.setLib(null);
        ArrayList<FrontOccupation> frontOccupations = new ArrayList<>();
        while (rs.next()) {
            if (lib2Occupations.getLib() == null) {
                Lib lib = Lib.builder()
                        .id(rs.getString("lib_id"))
                        .name(rs.getString("lib_name"))
                        .type(rs.getString("lib_type"))
                        .createTime(rs.getObject("create_time", LocalDateTime.class))
                        .updateTime(rs.getObject("update_time", LocalDateTime.class))
                        .build();
                lib2Occupations.setLib(lib);
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
        lib2Occupations.setOccupations(frontOccupations);
        return lib2Occupations;
    }
}
