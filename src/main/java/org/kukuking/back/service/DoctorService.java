package org.kukuking.back.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.Doctor;
import org.kukuking.back.repository.DoctorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public List<Doctor> findAll() {
        return doctorRepository.findAllDoctors();
    }

    public Doctor findById(String id) {
        return doctorRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Doctor doctor) {
        try {
            doctorRepository.save(doctor);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void update(Doctor doctor) {
        try {
            doctorRepository.save(doctor);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void delete(String id) {
        try {
            doctorRepository.deleteById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void delete(List<String> idList) {
        try {
            doctorRepository.deleteAllById(idList);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
