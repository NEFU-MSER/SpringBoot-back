package org.kukuking.back.extractor;

import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.PatientCard;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PatientCardResultSetExtractor implements ResultSetExtractor<List<PatientCard>> {
    @Override
    public List<PatientCard> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<PatientCard> patientCard = new ArrayList<>();
        while (rs.next()) {
            patientCard.add(PatientCard.builder()
                    .id(rs.getString("id"))
                    .patientId(rs.getString("patient_id"))
                    .balance(rs.getDouble("balance"))
                    .enable(rs.getBoolean("enable"))
                    .build());
        }
        return patientCard;
    }
}
