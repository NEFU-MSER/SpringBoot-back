package org.kukuking.back.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.PatientCard;
import org.kukuking.back.DO.TradeLog;
import org.kukuking.back.component.ResultVO;
import org.kukuking.back.service.PatientCardService;
import org.kukuking.back.service.TradeLogService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/patientCard/")
@RequiredArgsConstructor
@CrossOrigin
public class PatientCardController {
    private final PatientCardService patientCardService;
    private final TradeLogService tradeLogService;

    public static class ExecuteForm {
        private String cardId;
        private double execute;
    }

    @PostMapping("/getAll")
    public ResultVO getAll() {
        try {
            List<PatientCard> patientCards = patientCardService.findAll();
            return ResultVO.success(Map.of("patientCards", patientCards));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultVO.error(405, e.getMessage());
        }
    }

    @PostMapping("/add")
    @Transactional
    public ResultVO save(@RequestBody String patientID) {
        try {
            patientCardService.save(PatientCard.builder()
                    .patientId(patientID)
                    .enable(true)
                    .balance(0)
                    .build());
            List<PatientCard> patientCards = patientCardService.findAll();
            return ResultVO.success(Map.of("patientCards", patientCards));
        } catch (Exception e) {
            return ResultVO.error(405, e.getMessage());
        }
    }

    @PostMapping("/delete")
    @Transactional
    public ResultVO delete(@RequestBody String id) {
        try {
            patientCardService.delete(id);
            List<PatientCard> patientCards = patientCardService.findAll();
            return ResultVO.success(Map.of("patientCards", patientCards));
        } catch (Exception e) {
            return ResultVO.error(405, e.getMessage());
        }
    }

    @PostMapping("/enable")
    @Transactional
    public ResultVO enable(@RequestBody String id) {
        try {
            patientCardService.enable(id);
            List<PatientCard> patientCards = patientCardService.findAll();
            return ResultVO.success(Map.of("patientCards", patientCards));
        } catch (Exception e) {
            return ResultVO.error(405, e.getMessage());
        }
    }

    @PostMapping("/disable")
    @Transactional
    public ResultVO disable(@RequestBody String id) {
        try {
            patientCardService.disable(id);
            List<PatientCard> patientCards = patientCardService.findAll();
            return ResultVO.success(Map.of("patientCards", patientCards));
        } catch (Exception e) {
            return ResultVO.error(405, e.getMessage());
        }
    }

    @PostMapping("/deleteAll")
    @Transactional
    public ResultVO deleteAll(@RequestBody List<String> idList) {
        try {
            patientCardService.delete(idList);
            List<PatientCard> patientCards = patientCardService.findAll();
            return ResultVO.success(Map.of("patientCards", patientCards));
        } catch (Exception e) {
            return ResultVO.error(405, e.getMessage());
        }
    }

    @PostMapping("/recharge")
    @Transactional
    public ResultVO recharge(@RequestBody TradeLog tradeLog) {
        try {
            PatientCard card = patientCardService.findById(tradeLog.getCardId());
            if(card.getBalance() > -tradeLog.getExecute()) {
                patientCardService.execute(tradeLog.getCardId(), tradeLog.getExecute());
                tradeLogService.save(TradeLog.builder()
                        .cardId(tradeLog.getCardId())
                        .execute(tradeLog.getExecute())
                        .reason(tradeLog.getReason())
                        .build());
                List<PatientCard> patientCards = patientCardService.findAll();
                return ResultVO.success(Map.of("patientCards", patientCards));
            }else return ResultVO.error(406, "余额不足,无法完成,请充值!");
        } catch (Exception e) {
            return ResultVO.error(405, e.getMessage());
        }
    }
}
