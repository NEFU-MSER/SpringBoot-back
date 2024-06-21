package org.kukuking.back.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.Doctor;
import org.kukuking.back.component.ResultVO;
import org.kukuking.back.service.DoctorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/doctor/")
@RequiredArgsConstructor
@CrossOrigin
public class DoctorController {
    private final DoctorService doctorService;

    @PostMapping("/getAll")
    public ResultVO getAll() {
        try {
            List<Doctor> doctors = doctorService.findAll();
            return ResultVO.success(Map.of("doctors", doctors));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultVO.error(405, e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResultVO save(@RequestBody Doctor doctor) {
        try {
            doctor.setId(null);
            doctorService.save(doctor);
            List<Doctor> doctors = doctorService.findAll();
            return ResultVO.success(Map.of("doctors", doctors));
        }catch (Exception e) {
            return ResultVO.error(405, e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResultVO update(@RequestBody Doctor doctor) {
        try {
            doctorService.update(doctor);
            List<Doctor> doctors = doctorService.findAll();
            return ResultVO.success(Map.of("doctors", doctors));
        }catch (Exception e) {
            return ResultVO.error(405, e.getMessage());
        }
    }

    @PostMapping("/delete")
    public ResultVO delete(@RequestBody String id) {
        try {
            doctorService.delete(id);
            List<Doctor> doctors = doctorService.findAll();
            return ResultVO.success(Map.of("doctors", doctors));
        }catch (Exception e) {
            return ResultVO.error(405, e.getMessage());
        }
    }

    @PostMapping("/deleteAll")
    public ResultVO deleteAll(@RequestBody List<String> idList) {
        try {
            doctorService.delete(idList);
            List<Doctor> doctors = doctorService.findAll();
            return ResultVO.success(Map.of("doctors", doctors));
        }catch (Exception e) {
            return ResultVO.error(405, e.getMessage());
        }
    }
}
