package org.kukuking.back.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.TradeLog;
import org.kukuking.back.component.ResultVO;
import org.kukuking.back.service.TradeLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/tradeLog/")
@RequiredArgsConstructor
@CrossOrigin
public class TradeLogController {
    private final TradeLogService tradeLogService;

    @PostMapping("/getAll")
    public ResultVO getAll(@RequestBody String cardId) {
        try {
            log.info("getAll start, cardId: {}", cardId);
            List<TradeLog> tradeLogs = tradeLogService.findAll(cardId);
            return ResultVO.success(Map.of("tradeLogs", tradeLogs));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultVO.error(405, e.getMessage());
        }
    }
}
