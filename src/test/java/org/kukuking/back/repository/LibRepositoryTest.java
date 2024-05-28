package org.kukuking.back.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.kukuking.back.DO.Lib;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class LibRepositoryTest {
    @Autowired
    private LibRepository libRepository;

    @Test
    public void saveLib(){
        for(int i = 901; i < 917; i++) {
            Lib lib = Lib.builder()
                    .name(String.valueOf(i))
                    .type("计算机实验室")
                    .build();
            libRepository.save(lib);
        }
    }

    @Test
    public void findLibById() {
        Lib lib = libRepository.findLibById("1244637752160296960");
        System.out.println(lib.toString());
    }


    @Test
    public void deleteLibById() {
        libRepository.deleteById("1244642273632591872");
    }

    @Test
    public void findLibsByType() {
        List<Lib> libs = libRepository.findLibsByType("计算机实验室");
        List<Lib> empty = libRepository.findLibsByType("");
        System.out.println("计算机实验室个数:" + libs.size());
        System.out.println(empty.size());
    }

    @Test
    public void findLibs() {
        List<Lib> libs = libRepository.findLibs();
        System.out.println(libs.size());
    }
}