package org.kukuking.back.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.Course;
import org.kukuking.back.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    public List<Course> getAllCourses(){
        return courseRepository.findCourses();
    }

    public Course getCourseById(String id){
        return courseRepository.findCourseById(id);
    }

    public List<Course> getAllCoursesByUser(String userId){
        return courseRepository.findCoursesByUserId(userId);
    }

    @Transactional
    public void deleteCourseById(String id){
        try {
            courseRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void saveCourse(Course course){
        try {
            courseRepository.save(course);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
