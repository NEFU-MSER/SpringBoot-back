package org.kukuking.back.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.Occupation;
import org.kukuking.back.DTO.Course2Occupations;
import org.kukuking.back.DTO.Lib2Occupations;
import org.kukuking.back.repository.Course2OccupationRepository;
import org.kukuking.back.repository.Lib2OccupationRepository;
import org.kukuking.back.repository.OccupationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OccupationService {
    private final OccupationRepository occupationRepository;
    private final Course2OccupationRepository course2OccupationRepository;
    private final Lib2OccupationRepository lib2OccupationRepository;

    @Transactional
    public boolean save(Occupation occupation) {
        try {
            if (occupation.getId() != null) {
                return false;
            }
            occupationRepository.save(occupation);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public boolean update(Occupation occupation) {
        try {
            if (occupation.getId() == null) {
                return false;
            }
            occupationRepository.save(occupation);
            return true;
        }catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public Course2Occupations getCourse2Occupations(String courseId) {
        return course2OccupationRepository.findCourseOccupation(courseId);
    }

    public List<Course2Occupations> getCourses2Occupations(List<String> courseIds) {
        return course2OccupationRepository.findCoursesOccupation(courseIds);
    }

    public Lib2Occupations getLib2Occupations(String libId) {
        return lib2OccupationRepository.findLibOccupation(libId);
    }

    @Transactional
    public boolean deleteById(String id) {
        try {
            if (id == null) {
                return false;
            }
            occupationRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public boolean deleteByCourseId(String courseId) {
        try {
            if (courseId == null) {
                return false;
            }
            occupationRepository.deleteByCourseId(courseId);
            return true;
        }catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public boolean deleteByLibId(String libId) {
        try {
            if (libId == null) {
                return false;
            }
            occupationRepository.deleteByLibId(libId);
            return true;
        }catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
