package org.kukuking.back.extractor;

import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.Doctor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DoctorResultSetExtractor implements ResultSetExtractor<List<Doctor>> {
    @Override
    public List<Doctor> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Doctor> doctors = new ArrayList<>();
        while (rs.next()) {
            doctors.add(Doctor.builder()
                    .id(rs.getString("id"))
                    .roleId(rs.getString("role_id"))
                    .name(rs.getString("name"))
                    .idCard(rs.getString("id_card"))
                    .email(rs.getString("email"))
                    .phone(rs.getString("phone"))
                    .gender(rs.getInt("gender"))
                    .build());
        }
        return doctors;
    }
}
