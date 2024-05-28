package org.kukuking.back.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.Lib;
import org.kukuking.back.component.ResultVO;
import org.kukuking.back.repository.LibRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class LibService {
    private final LibRepository libRepository;

    public ResultVO getLibs(){
        try {
            return ResultVO.success(Map.of("libs", libRepository.findLibs()));
        }catch (Exception e){
            return ResultVO.error(405, e.getMessage());
        }
    }

    public ResultVO getLibsByType(String type){
        try {
            return ResultVO.success(Map.of("libs", libRepository.findLibsByType(type)));
        }catch (Exception e){
            return ResultVO.error(405, e.getMessage());
        }
    }

    @Transactional
    public ResultVO addLib(Lib lib){
        try {
            libRepository.save(lib);
            return ResultVO.success(Map.of());
        }catch (Exception e){
            return ResultVO.error(405, e.getMessage());
        }
    }

    @Transactional
    public ResultVO deleteLibById(String id){
        try {
            libRepository.deleteById(id);
            return ResultVO.success(Map.of());
        }catch (Exception e){
            return ResultVO.error(405, e.getMessage());
        }
    }
}
