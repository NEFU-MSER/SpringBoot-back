package org.kukuking.back.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.Lib;
import org.kukuking.back.component.ResultVO;
import org.kukuking.back.repository.LibRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class LibService {
    private final LibRepository libRepository;

    public List<Lib> getAllLibs(){
        return libRepository.findLibs();
    }

    public List<Lib> getAllLibsByType(String type){
        return libRepository.findLibsByType(type);
    }

    public Lib getLibById(String id){
        return libRepository.findLibById(id);
    }

    @Transactional
    public void deleteLibById(String id){
        try {
            libRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void saveLib(Lib lib){
        try {
            libRepository.save(lib);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
