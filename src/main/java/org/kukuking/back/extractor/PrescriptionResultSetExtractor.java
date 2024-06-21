package org.kukuking.back.extractor;

import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.Prescription;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PrescriptionResultSetExtractor implements ResultSetExtractor<List<Prescription>> {
    @Override
    public List<Prescription> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Prescription> prescriptions = new ArrayList<>();
        while (rs.next()) {
            prescriptions.add(Prescription.builder()
                    .id(rs.getString("id"))
                    .patientId(rs.getString("patient_id"))
                    .cardId(rs.getString("card_id"))
                    .doctorId(rs.getString("doctor_id"))
                    .cost(rs.getDouble("cost"))
                    .title(rs.getString("title"))
                    .description(rs.getString("description"))
                    .finish(rs.getBoolean("finish"))
                    .toFinish(rs.getBoolean("to_finish"))
                    .build());
        }
        return prescriptions;
    }
}
