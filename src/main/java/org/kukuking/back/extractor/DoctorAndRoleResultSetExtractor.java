package org.kukuking.back.extractor;

import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.Role;
import org.kukuking.back.DO.Doctor;
import org.kukuking.back.DTO.DoctorAndRole;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DoctorAndRoleResultSetExtractor implements ResultSetExtractor<List<DoctorAndRole>> {
    @Override
    public List<DoctorAndRole> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<DoctorAndRole> doctorAndRoles = new ArrayList<>();
        while (rs.next()) {
            Doctor doctor = Doctor.builder()
                    .id(rs.getString("d.id"))
                    .name(rs.getString("d.name"))
                    .email(rs.getString("d.email"))
                    .phone(rs.getString("d.phone"))
                    .build();
            Role role = Role.builder()
                    .id(rs.getString("r.id"))
                    .name(rs.getString("r.name"))
                    .expenses(rs.getDouble("r.expenses"))
                    .departmentId(rs.getString("r.department_id"))
                    .build();
            doctorAndRoles.add(new DoctorAndRole(doctor, role));
        }
        return doctorAndRoles;
    }
}
