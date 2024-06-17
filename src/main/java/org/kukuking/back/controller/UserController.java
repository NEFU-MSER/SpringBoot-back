package org.kukuking.back.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.User;
import org.kukuking.back.component.ReqData;
import org.kukuking.back.component.ResultVO;
import org.kukuking.back.component.entity.LoginForm;
import org.kukuking.back.component.utils.LoginDetail;
import org.kukuking.back.component.utils.TokenUtils;
import org.kukuking.back.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/user/")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public ResultVO test(){
        return ResultVO.success(Map.of());
    }

    @PostMapping("/login")
    public ResultVO login(@RequestBody LoginForm loginForm) {
        String token;
        User user = userService.getUserByAccount(loginForm.account);
        if (user != null) {
            if (user.getPassword().equals(loginForm.password)) {
                token = TokenUtils.generateToken(new LoginDetail(user.getAccount(), user.getId(), 30));
                return ResultVO.success(Map.of("token", token));
            } else {
                return ResultVO.error(400, "密码错误");
            }
        }
        return ResultVO.error(400, "用户不存在");
    }

    @PostMapping("/signIn")
    public ResultVO signIn(@RequestBody User user) {
        String token;
        User checkUser = userService.getUserByAccountOrIdCard(user.getAccount(), user.getIdCard());
        if (checkUser == null) {
            user.setId(null);
            userService.saveUser(user);
            LoginDetail detail = new LoginDetail(user.getAccount(), user.getId(), 30);
            token = TokenUtils.generateToken(detail.convert());
            return ResultVO.success(Map.of("token", token));
        }
        return ResultVO.error(400, "用户已存在");
    }

    @PostMapping("/profile")
    public ResultVO profile(@RequestBody ReqData<String> reqData) {
        if (TokenUtils.verifyToken(reqData.getToken())) {
            String account = TokenUtils.getAccount(reqData.getToken());
            if (account != null) {
                User user = userService.getUserByAccount(account);
                if (user != null) {
                    user.setPassword("");
                    return ResultVO.success(Map.of("user", user));
                }
                return ResultVO.error(400, "找不到该用户");
            }
            return ResultVO.error(403, "token解析异常");
        }
        return ResultVO.error(403, "token失效");
    }

    @PostMapping("/change")
    public ResultVO change(@RequestBody ReqData<User> reqData) {
        if (TokenUtils.verifyToken(reqData.getToken())) {
            String account = TokenUtils.getAccount(reqData.getToken());
            User user = reqData.getData();
            if (account != null && user != null) {
                if (account.equals(user.getAccount())) {
                    userService.updateUser(user);
                    return ResultVO.success(Map.of());
                }
            }
            return ResultVO.error(400, "数据缺失");
        }
        return ResultVO.error(403, "token失效");
    }
}
