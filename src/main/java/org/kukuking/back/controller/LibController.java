package org.kukuking.back.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.Lib;
import org.kukuking.back.DO.Occupation;
import org.kukuking.back.component.ReqData;
import org.kukuking.back.component.ResultVO;
import org.kukuking.back.component.utils.TokenUtils;
import org.kukuking.back.service.LibService;
import org.kukuking.back.service.OccupationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/lib/")
@RequiredArgsConstructor
@CrossOrigin
public class LibController {
    private final LibService libService;
    private final OccupationService occupationService;

    @PostMapping("/getAll")
    public ResultVO getAll() {
        List<Lib> libs = libService.getAllLibs();
        return ResultVO.success(Map.of("libs", libs));
    }

    @PostMapping("typeGet")
    public ResultVO typeGet(@RequestBody String type) {
        List<Lib> libs = libService.getAllLibsByType(type);
        log.info("type:{},size{}", type, libs.size());
        return ResultVO.success(Map.of("libs", libs));
    }

    @PostMapping("/idDelete")
    public ResultVO idDelete(@RequestBody String id) {
        libService.deleteLibById(id);
        occupationService.deleteByLibId(id);
        return ResultVO.success(Map.of());
    }

    @PostMapping("/add")
    public ResultVO add(@RequestBody ReqData<Lib> reqData) {
        if (TokenUtils.verifyToken(reqData.getToken())) {
            Lib lib = reqData.getData();
            lib.setId(null);
            libService.saveLib(lib);
            return ResultVO.success(Map.of());
        }
        return ResultVO.error(403, "token失效");
    }

    @PostMapping("/change")
    public ResultVO change(@RequestBody ReqData<Lib> reqData) {
        if (TokenUtils.verifyToken(reqData.getToken())) {
            Lib lib = reqData.getData();
            libService.saveLib(lib);
            return ResultVO.success(Map.of());
        }
        return ResultVO.error(403, "token失效");
    }
}
