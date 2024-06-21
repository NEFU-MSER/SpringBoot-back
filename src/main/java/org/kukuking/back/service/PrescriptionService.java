package org.kukuking.back.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.Prescription;
import org.kukuking.back.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;

    public List<Prescription> findAll() {
        return prescriptionRepository.findAllPrescriptions();
    }

    public List<Prescription> findAllByToFinishAndDoctorId(boolean toFinish, String doctorId) {
        return prescriptionRepository.findAllByToFinish(toFinish, doctorId);
    }

    public List<Prescription> findAllByDoctorId(boolean toFinish, boolean finish, String doctorId) {
        return prescriptionRepository.findAllByToFinishAndFinishAndDoctorId(toFinish, finish, doctorId);
    }

    public List<Prescription> findAllByPatientId(boolean toFinish, boolean finish, String patientId) {
        return prescriptionRepository.findAllByToFinishAndFinishAndPatientId(toFinish, finish, patientId);
    }

    public List<Prescription> findAllByPatientId(String patientId) {
        return prescriptionRepository.findAllByPatientId(patientId);
    }

    public List<Prescription> findAllByDoctorId(String doctorId) {
        return prescriptionRepository.findAllByPatientId(doctorId);
    }

    public Prescription findById(String id) {
        return prescriptionRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Prescription prescription) {
        try {
            prescriptionRepository.save(prescription);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void update(Prescription prescription) {
        try {
            prescriptionRepository.save(prescription);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void update(String id, double cost, String description) {
        try {
            prescriptionRepository.updateById(id, cost, description);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void delete(String id) {
        try {
            prescriptionRepository.deleteById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void delete(List<String> idList) {
        try {
            prescriptionRepository.deleteAllById(idList);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void finish(String id) {
        try {
            prescriptionRepository.finishById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void toFinish(String id) {
        try {
            prescriptionRepository.toFinishById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
