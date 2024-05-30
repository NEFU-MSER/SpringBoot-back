package org.kukuking.back.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.kukuking.back.DO.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;


@Slf4j
@SpringBootTest
public class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void save() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            courseRepository.save(Course.builder()
                    .userId("1244574091173335040")
                    .name("小猪蹦迪" + i)
                    .credit(random.nextDouble())
                    .time(random.nextInt())
                    .build());
        }
    }

    @Test
    public void findCourseById() {
    }

    @Test
    public void findCoursesByUserId() {
    }

    @Test
    public void findCourses() {
    }
}