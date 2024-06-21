package org.kukuking.back.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.Patient;
import org.kukuking.back.DO.PatientCard;
import org.kukuking.back.component.ResultVO;
import org.kukuking.back.service.PatientCardService;
import org.kukuking.back.service.PatientService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/patient/")
@RequiredArgsConstructor
@CrossOrigin
public class PatientController {
    private final PatientService patientService;
    private final PatientCardService patientCardService;

    @PostMapping("/getAll")
    public ResultVO getAll() {
        try {
            List<Patient> patients = patientService.findAll();
            return ResultVO.success(Map.of("patients", patients));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultVO.error(405, e.getMessage());
        }
    }

    @PostMapping("/add")
    @Transactional
    public ResultVO save(@RequestBody Patient patient) {
        try {
            patient.setId(null);
            patientService.save(patient);
            patientCardService.save(PatientCard.builder()
                    .patientId(patient.getId())
                    .balance(0)
                    .enable(true)
                    .build());
            List<Patient> patients = patientService.findAll();
            return ResultVO.success(Map.of("patients", patients));
        } catch (Exception e) {
            return ResultVO.error(405, e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResultVO update(@RequestBody Patient patient) {
        try {
            patientService.update(patient);
            List<Patient> patients = patientService.findAll();
            return ResultVO.success(Map.of("patients", patients));
        } catch (Exception e) {
            return ResultVO.error(405, e.getMessage());
        }
    }

    @PostMapping("/delete")
    @Transactional
    public ResultVO delete(@RequestBody String id) {
        try {
            List<PatientCard> patientCards = patientCardService.findByPatientId(id);
            if(!patientCards.isEmpty()){
                return ResultVO.error(406, "患者名下有诊疗卡,不可删除");
            }
            patientService.delete(id);
            List<Patient> patients = patientService.findAll();
            return ResultVO.success(Map.of("patients", patients));
        } catch (Exception e) {
            return ResultVO.error(405, e.getMessage());
        }
    }
}
