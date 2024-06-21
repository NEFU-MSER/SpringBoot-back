package org.kukuking.back.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.Patient;
import org.kukuking.back.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    public List<Patient> findAll() {
        return patientRepository.findAllPatients();
    }

    public Patient findById(String id) {
        return patientRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Patient patient) {
        try {
            patientRepository.save(patient);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void update(Patient patient) {
        try {
            patientRepository.save(patient);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void delete(String id) {
        try {
            patientRepository.deleteById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void delete(List<String> idList) {
        try {
            patientRepository.deleteAllById(idList);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
