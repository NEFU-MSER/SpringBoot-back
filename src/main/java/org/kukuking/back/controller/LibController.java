package org.kukuking.back.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.Lib;
import org.kukuking.back.component.ReqData;
import org.kukuking.back.component.ResultVO;
import org.kukuking.back.component.entity.LoginForm;
import org.kukuking.back.component.utils.TokenUtils;
import org.kukuking.back.service.LibService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/lib/")
@RequiredArgsConstructor
@CrossOrigin
public class LibController {
    private final LibService libService;

    @GetMapping("/get")
    public ResultVO test() {
        return ResultVO.success(Map.of("succeed", "成功"));
    }

    @PostMapping("/getAll")
    public ResultVO getAll() {
        return libService.getLibs();
    }

    @PostMapping("typeGet")
    public ResultVO typeGet(@RequestBody String type) {
        return libService.getLibsByType(type);
    }

    @PostMapping("/idDelete")
    public ResultVO idDelete(@RequestBody String id) {
        return libService.deleteLibById(id);
    }

    @PostMapping("/add")
    public ResultVO add(@RequestBody Lib lib) {
        return libService.addLib(lib);
    }
}
