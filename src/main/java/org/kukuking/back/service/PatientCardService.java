package org.kukuking.back.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.PatientCard;
import org.kukuking.back.repository.PatientCardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientCardService {
    private final PatientCardRepository patientCardRepository;

    public List<PatientCard> findAll() {
        return patientCardRepository.findAllPatientCards();
    }

    public PatientCard findById(String id) {
        return patientCardRepository.findById(id).orElse(null);
    }

    public List<PatientCard> findByPatientId(String patientId) {
        return patientCardRepository.findAllByPatientId(patientId);
    }

    @Transactional
    public void save(PatientCard patientCard) {
        try {
            patientCardRepository.save(patientCard);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void update(PatientCard patientCard) {
        try {
            patientCardRepository.save(patientCard);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void delete(String id) {
        try {
            patientCardRepository.deleteById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void delete(List<String> idList) {
        try {
            patientCardRepository.deleteAllById(idList);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void enable(String id) {
        try {
            patientCardRepository.enablePatientCard(id);
        }catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void disable(String id) {
        try {
            patientCardRepository.disablePatientCard(id);
        }catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void execute(String id, double execute){
        try {
            patientCardRepository.executeBalance(id, execute);
        }catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
