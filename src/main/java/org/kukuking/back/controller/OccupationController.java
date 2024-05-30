package org.kukuking.back.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.Course;
import org.kukuking.back.DO.Lib;
import org.kukuking.back.DO.Occupation;
import org.kukuking.back.DTO.Course2Occupations;
import org.kukuking.back.DTO.FrontOccupation;
import org.kukuking.back.DTO.Lib2Occupations;
import org.kukuking.back.component.ReqData;
import org.kukuking.back.component.ResultVO;
import org.kukuking.back.component.utils.TokenUtils;
import org.kukuking.back.service.CourseService;
import org.kukuking.back.service.LibService;
import org.kukuking.back.service.OccupationService;
import org.kukuking.back.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/occupation/")
@RequiredArgsConstructor
@CrossOrigin
public class OccupationController {
    private final OccupationService occupationService;
    private final UserService userService;
    private final CourseService courseService;
    private final LibService libService;

    @PostMapping("/libGet")
    public ResultVO getAll(@RequestBody ReqData<ArrayList<String>> reqData) {
        if (TokenUtils.verifyToken(reqData.getToken())) {
            List<String> libIds = reqData.getData();
            List<Lib2Occupations> libs2Occupations = new ArrayList<>();
            if (libIds != null && !libIds.isEmpty()) {
                for (String libId : libIds) {
                    if (libId != null) {
                        Lib2Occupations lib2Occupations = occupationService.getLib2Occupations(libId);
                        if (lib2Occupations.getLib() == null) {
                            Lib lib = libService.getLibById(libId);
                            lib2Occupations.setLib(lib);
                        }
                        libs2Occupations.add(lib2Occupations);
                    }
                }
                return ResultVO.success(Map.of("libs2Occupation", libs2Occupations));
            }
            return ResultVO.error(400, "请求数据缺失");
        }
        return ResultVO.error(403, "token失效");
    }

    @PostMapping("/courseGet")
    public ResultVO courseGet(@RequestBody ReqData<ArrayList<String>> reqData) {
        if (TokenUtils.verifyToken(reqData.getToken())) {
            List<String> courseIds = reqData.getData();
            List<Course2Occupations> course2Occupations = new ArrayList<>();
            if (courseIds != null && !courseIds.isEmpty()) {
                for (String courseId : courseIds) {
                    if (courseId != null) {
                        Course2Occupations course2Occupation = occupationService.getCourse2Occupations(courseId);
                        if (course2Occupation.getCourse() == null) {
                            Course course = courseService.getCourseById(courseId);
                            course2Occupation.setCourse(course);
                        }
                        course2Occupations.add(course2Occupation);
                    }
                }
                return ResultVO.success(Map.of("course2Occupations", course2Occupations));
            }
            return ResultVO.error(400, "请求数据缺失");
        }
        return ResultVO.error(403, "token失效");
    }

    @PostMapping("/idDelete")
    public ResultVO idDelete(@RequestBody ReqData<String> reqData) {
        String token = reqData.getToken();
        if (TokenUtils.verifyToken(token)) {
            String id = reqData.getData();
            if (id != null) {
                if (occupationService.deleteById(id)) {
                    return ResultVO.success(Map.of());
                } else {
                    return ResultVO.error(405, "删除出错");
                }
            }
            return ResultVO.error(400, "请求数据缺失");
        }
        return ResultVO.error(403, " token失效");
    }

    @PostMapping("/add")
    public ResultVO add(@RequestBody ReqData<FrontOccupation> reqData) {
        if (TokenUtils.verifyToken(reqData.getToken())) {
            FrontOccupation frontOccupation = reqData.getData();
            if (frontOccupation != null) {
                Occupation occupation = new Occupation(frontOccupation);
                if (occupation.getId() == null) {
                    String userId = TokenUtils.getId(reqData.getToken());
                    occupation.setUserId(userId);
                    occupationService.save(occupation);
                    return ResultVO.success(Map.of());
                }
            }
            return ResultVO.error(400, "请求数据错误");
        }
        return ResultVO.error(403, "token失效");
    }

    @PostMapping("/change")
    public ResultVO change(@RequestBody ReqData<FrontOccupation> reqData) {
        if (TokenUtils.verifyToken(reqData.getToken())) {
            FrontOccupation frontOccupation = reqData.getData();
            if (frontOccupation != null) {
                Occupation occupation = new Occupation(frontOccupation);
                if (occupation.getId() != null) {
                    String userId = TokenUtils.getId(reqData.getToken());
                    occupation.setUserId(userId);
                    occupationService.update(occupation);
                    return ResultVO.success(Map.of());
                }
            }
            return ResultVO.error(400, "请求数据错误");
        }
        return ResultVO.error(403, "token失效");
    }
}

