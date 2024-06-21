package org.kukuking.back.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.Prescription;
import org.kukuking.back.component.ResultVO;
import org.kukuking.back.service.PrescriptionService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/prescription/")
@RequiredArgsConstructor
@CrossOrigin
public class PrescriptionController {
    private final PrescriptionService prescriptionService;

    @PostMapping("/doctor/getAll")
    public ResultVO doctorGet(@RequestBody String doctorId) {
        try {
            List<Prescription> prescriptions = prescriptionService.findAllByToFinishAndDoctorId(false, doctorId);
            return ResultVO.success(Map.of("prescriptions", prescriptions));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultVO.error(405, e.getMessage());
        }
    }

    @PostMapping("/patient/getAll")
    public ResultVO patientGet(@RequestBody String patientId) {
        try {
            List<Prescription> prescriptions = prescriptionService.findAllByPatientId(true, false, patientId);
            return ResultVO.success(Map.of("prescriptions", prescriptions));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultVO.error(405, e.getMessage());
        }
    }

    @PostMapping("/patient/history")
    public ResultVO patientHistory(@RequestBody String patientId) {
        try {
            List<Prescription> prescriptions = prescriptionService.findAllByPatientId(true, true, patientId);
            return ResultVO.success(Map.of("prescriptions", prescriptions));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultVO.error(405, e.getMessage());
        }
    }

    @PostMapping("/doctor/history")
    public ResultVO doctorHistory(@RequestBody String doctorId) {
        try {
            List<Prescription> prescriptions = prescriptionService.findAllByDoctorId(true, true, doctorId);
            return ResultVO.success(Map.of("prescriptions", prescriptions));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultVO.error(405, e.getMessage());
        }
    }

    @PostMapping("/add")
    @Transactional
    public ResultVO save(@RequestBody Prescription prescription) {
        try {
            prescription.setId(null);
            prescription.setToFinish(false);
            prescription.setFinish(false);
            prescriptionService.save(prescription);
            List<Prescription> prescriptions = prescriptionService.findAllByPatientId(true, false, prescription.getPatientId());
            return ResultVO.success(Map.of("prescriptions", prescriptions));
        } catch (Exception e) {
            return ResultVO.error(405, e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResultVO update(@RequestBody Prescription prescription) {
        try {
            prescriptionService.update(prescription.getId(), prescription.getCost(), prescription.getDescription());
            List<Prescription> prescriptions = prescriptionService.findAllByToFinishAndDoctorId(false, prescription.getDoctorId());
            return ResultVO.success(Map.of("prescriptions", prescriptions));
        } catch (Exception e) {
            return ResultVO.error(405, e.getMessage());
        }
    }

    @PostMapping("/toFinish")
    @Transactional
    public ResultVO toFinish(@RequestBody Prescription prescription) {
        try {
            prescriptionService.toFinish(prescription.getId());
            List<Prescription> prescriptions = prescriptionService.findAllByToFinishAndDoctorId(false, prescription.getDoctorId());
            return ResultVO.success(Map.of("prescriptions", prescriptions));
        } catch (Exception e) {
            return ResultVO.error(405, e.getMessage());
        }
    }

    @PostMapping("/finish")
    @Transactional
    public ResultVO finish(@RequestBody Prescription prescription) {
        try {
            prescriptionService.finish(prescription.getId());
            List<Prescription> prescriptions = prescriptionService.findAllByPatientId(true, false, prescription.getPatientId());
            return ResultVO.success(Map.of("prescriptions", prescriptions));
        } catch (Exception e) {
            return ResultVO.error(405, e.getMessage());
        }
    }
}
