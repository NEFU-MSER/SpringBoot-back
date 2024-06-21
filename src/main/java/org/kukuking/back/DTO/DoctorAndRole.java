package org.kukuking.back.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.Doctor;
import org.kukuking.back.DO.Role;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorAndRole {
    private Doctor doctor;
    private Role role;
}
