package org.kukuking.back.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.Course;
import org.kukuking.back.DO.User;
import org.kukuking.back.component.ReqData;
import org.kukuking.back.component.ResultVO;
import org.kukuking.back.component.utils.TokenUtils;
import org.kukuking.back.service.CourseService;
import org.kukuking.back.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/course/")
@RequiredArgsConstructor
@CrossOrigin
public class CourseController {
    private final CourseService courseService;
    private final UserService userService;

    @PostMapping("/getAll")
    public ResultVO getAll(@RequestBody ReqData<String> reqData) {
        if (TokenUtils.verifyToken(reqData.getToken())) {
            List<Course> courses = courseService.getAllCourses();
            User user = userService.getUserByAccount(TokenUtils.getAccount(reqData.getToken()));
            return ResultVO.success(Map.of("courses", courses, "user", user));
        }
        return ResultVO.error(403, "token失效");
    }

    @PostMapping("userGet")
    public ResultVO typeGet(@RequestBody ReqData<String> reqData) {
        if (TokenUtils.verifyToken(reqData.getToken())) {
            User user = userService.getUserByAccount(TokenUtils.getAccount(reqData.getToken()));
            List<Course> courses = courseService.getAllCoursesByUser(user.getId());
            return ResultVO.success(Map.of("courses", courses, "user", user));
        }
        return ResultVO.error(403, "token失效");
    }

    @PostMapping("/idDelete")
    public ResultVO idDelete(@RequestBody ReqData<String> reqData) {
        String token = reqData.getToken();
        if (TokenUtils.verifyToken(token)) {
            courseService.deleteCourseById(reqData.getData());
            return ResultVO.success(Map.of());
        }
        return ResultVO.error(403, " token失效");
    }

    @PostMapping("/add")
    public ResultVO add(@RequestBody ReqData<Course> reqData) {
        if (TokenUtils.verifyToken(reqData.getToken())) {
            Course course = reqData.getData();
            course.setId(null);
            courseService.saveCourse(course);
            return ResultVO.success(Map.of());
        }
        return ResultVO.error(403, "token失效");
    }

    @PostMapping("/change")
    public ResultVO change(@RequestBody ReqData<Course> reqData) {
        if (TokenUtils.verifyToken(reqData.getToken())) {
            Course course = reqData.getData();
            courseService.saveCourse(course);
            return ResultVO.success(Map.of());
        }
        return ResultVO.error(403, "token失效");
    }
}
