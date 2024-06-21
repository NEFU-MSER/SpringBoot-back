package org.kukuking.back.extractor;

import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.Patient;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PatientResultSetExtractor implements ResultSetExtractor<List<Patient>> {
    @Override
    public List<Patient> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Patient> patients = new ArrayList<>();
        while (rs.next()) {
            patients.add(Patient.builder()
                    .id(rs.getString("id"))
                    .name(rs.getString("name"))
                    .idCard(rs.getString("id_card"))
                    .email(rs.getString("email"))
                    .phone(rs.getString("phone"))
                    .gender(rs.getInt("gender"))
                    .build());
        }
        return patients;
    }
}
